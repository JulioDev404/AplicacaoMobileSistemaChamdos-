package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.AdicionarComentarioRequest;
import com.example.myapplication.models.ComentarioResponse;
import com.example.myapplication.models.TicketDetalhesResponse;
import com.example.myapplication.services.ApiService;
import com.example.myapplication.services.RetrofitClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheChamadoActivity extends AppCompatActivity {

    private RecyclerView recyclerRespostas;
    private RespostaAdapter respostaAdapter;
    private List<ComentarioResponse> listaDeRespostas;

    // Novos views para preencher
    private TextView textTituloChamado;
    private Chip chipStatus;
    private Chip chipPrioridade;
    private TextView textDescricao;
    private TextView textAnexo;
    private MaterialToolbar toolbar;

    private EditText editTextComentario;

    private Button btnEnviarComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RetrofitClient.reset();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhe_chamado);

        // recycler
        recyclerRespostas = findViewById(R.id.recyclerRespostas);
        configurarRecyclerView();

        // novos finds
        textTituloChamado = findViewById(R.id.textTituloChamado);
        chipStatus = findViewById(R.id.chipStatus);
        chipPrioridade = findViewById(R.id.chipPrioridade);
        textDescricao = findViewById(R.id.textDescricao);
        textAnexo = findViewById(R.id.textAnexo);
        toolbar = findViewById(R.id.toolbar);
        btnEnviarComentario = findViewById(R.id.btnEnviarComentario);
        editTextComentario = findViewById(R.id.editTextComentario);

        // configura toolbar (seta botão voltar se quiser)
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Pegando o ID do ticket enviado pela outra tela
        int ticketId = getIntent().getIntExtra("ticketId", -1);

        if (ticketId == -1) {
            Toast.makeText(this, "Erro: ticket inválido.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnEnviarComentario.setOnClickListener(v -> {
            String texto = editTextComentario.getText().toString().trim();

            if (texto.isEmpty()) {
                Toast.makeText(this, "Digite um comentário.", Toast.LENGTH_SHORT).show();
                return;
            }

            enviarComentario(ticketId, texto);
        });

        carregarTicket(ticketId);
    }

    private void enviarComentario(int ticketId, String mensagem) {

        ApiService api = RetrofitClient.getClient(this).create(ApiService.class);

        AdicionarComentarioRequest corpo = new AdicionarComentarioRequest(mensagem);

        Call<ComentarioResponse> call = api.adicionarComentario(ticketId, corpo);

        call.enqueue(new Callback<ComentarioResponse>() {
            @Override
            public void onResponse(Call<ComentarioResponse> call, Response<ComentarioResponse> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(DetalheChamadoActivity.this,
                            "Comentário enviado!", Toast.LENGTH_SHORT).show();

                    // limpa o campo
                    editTextComentario.setText("");

                    // atualiza lista
                    carregarTicket(ticketId);

                } else {
                    Toast.makeText(DetalheChamadoActivity.this,
                            "Erro ao enviar: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ComentarioResponse> call, Throwable t) {
                Toast.makeText(DetalheChamadoActivity.this,
                        "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

                // --- comentários (já funcionava) ---
                listaDeRespostas.clear();
                // Se no seu model o campo tem outro nome, adapte aqui.
                // Exemplo: dados.getComentarios() ou dados.comentarios
                if (dados.comentarios != null) {
                    listaDeRespostas.addAll(dados.comentarios);
                } else {
                    // tenta getters (caso seu model use getters)
                    try {
                        List<ComentarioResponse> tmp = (List<ComentarioResponse>) dados.getClass()
                                .getMethod("getComentarios")
                                .invoke(dados);
                        if (tmp != null) listaDeRespostas.addAll(tmp);
                    } catch (Exception ignored) {
                    }
                }
                respostaAdapter.notifyDataSetChanged();

                // --- preencher outros campos ---
                // Aqui eu uso nomes comuns. Adapte se seu model tiver nomes diferentes.
                // Título
                try {
                    // tenta campo público "titulo"
                    if (dados.titulo != null) {
                        textTituloChamado.setText(dados.titulo);
                    } else {
                        // tenta getter getTitulo() ou getTitle()
                        try {
                            String titulo = null;
                            try { titulo = (String) dados.getClass().getMethod("getTitulo").invoke(dados); } catch (Exception e) { }
                            if (titulo == null) {
                                try { titulo = (String) dados.getClass().getMethod("getTitle").invoke(dados); } catch (Exception e) { }
                            }
                            if (titulo != null) textTituloChamado.setText(titulo);
                        } catch (Exception ignored) {}
                    }
                } catch (Exception e) {
                    // se acessar dados.titulo der erro (campo não existe), tenta getters (já coberto acima)
                }

                // Status
                String statusText = null;
                try {
                    if (dados.status != null) statusText = dados.status;
                } catch (Exception ignored) {}
                if (statusText == null) {
                    try { statusText = (String) dados.getClass().getMethod("getStatus").invoke(dados); } catch (Exception ignored) {}
                }
                if (statusText != null) chipStatus.setText(statusText);

                // Prioridade
                chipPrioridade.setText(String.valueOf(dados.prioridade));

                // Descrição
                String descricao = null;
                try {
                    if (dados.descricao != null) descricao = dados.descricao;
                } catch (Exception ignored) {}
                if (descricao == null) {
                    try { descricao = (String) dados.getClass().getMethod("getDescricao").invoke(dados); } catch (Exception ignored) {}
                    try { if (descricao == null) descricao = (String) dados.getClass().getMethod("getDescription").invoke(dados); } catch (Exception ignored) {}
                }
                if (descricao != null) textDescricao.setText(descricao);


                // Se quiser, atualiza o título da toolbar com o título do ticket
                CharSequence tituloToolbar = textTituloChamado.getText();
                if (tituloToolbar != null && tituloToolbar.length() > 0) {
                    toolbar.setTitle(tituloToolbar);
                }

                // DEBUG: log da resposta para inspecionar nomes de campos
                Log.d("DetalheChamado", "Ticket detalhes: " + dados.toString());
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
