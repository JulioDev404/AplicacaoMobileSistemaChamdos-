package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.ComentarioResponse;
import com.example.myapplication.models.TicketDetalhesResponse;
import com.example.myapplication.services.ApiService;
import com.example.myapplication.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheChamadoActivity extends AppCompatActivity {

    private RecyclerView recyclerRespostas;
    private RespostaAdapter respostaAdapter;
    private List<ComentarioResponse> listaDeRespostas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RetrofitClient.reset();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhe_chamado);

        recyclerRespostas = findViewById(R.id.recyclerRespostas);

        configurarRecyclerView();

        // Pegando o ID do ticket enviado pela outra tela
        int ticketId = getIntent().getIntExtra("ticketId", -1);

        if (ticketId == -1) {
            Toast.makeText(this, "Erro: ticket inválido.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        carregarTicket(ticketId);
    }

    private void configurarRecyclerView() {
        listaDeRespostas = new ArrayList<>();
        respostaAdapter = new RespostaAdapter(this, listaDeRespostas);
        recyclerRespostas.setLayoutManager(new LinearLayoutManager(this));
        recyclerRespostas.setAdapter(respostaAdapter);
    }

    private void carregarTicket(int id) {

        ApiService api = RetrofitClient.getClient(this).create(ApiService.class);

        Call<TicketDetalhesResponse> call = api.getTicketDetalhe(id);
        call.enqueue(new Callback<TicketDetalhesResponse>() {
            @Override
            public void onResponse(Call<TicketDetalhesResponse> call, Response<TicketDetalhesResponse> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(DetalheChamadoActivity.this,
                            "Erro: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                TicketDetalhesResponse dados = response.body();

                if (dados == null) {
                    Toast.makeText(DetalheChamadoActivity.this,
                            "Erro: resposta vazia.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // atualiza lista com os comentários
                listaDeRespostas.clear();
                listaDeRespostas.addAll(dados.comentarios); // assume getComentarios() retorna List<ComentarioResponse>
                respostaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TicketDetalhesResponse> call, Throwable t) {
                Toast.makeText(DetalheChamadoActivity.this,
                        "Falha na conexão", Toast.LENGTH_SHORT).show();
                Log.e("API", t.getMessage() == null ? "null" : t.getMessage());
            }
        });
    }
}
