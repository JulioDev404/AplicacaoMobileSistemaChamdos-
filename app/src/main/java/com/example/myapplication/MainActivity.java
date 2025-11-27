package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.Ticket;
import com.example.myapplication.models.TicketResponse;
import com.example.myapplication.services.ApiService;
import com.example.myapplication.services.AuthInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTickets;
    private FloatingActionButton fabChatbot;
    private TicketAdapter adapter;
    private List<Ticket> listaTickets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // --- VERIFICA O TOKEN ---
        String token = getSharedPreferences("app", MODE_PRIVATE)
                .getString("jwt", null);

        if (token == null || token.isEmpty()) {
            // Sem token → volta para login
            startActivity(new Intent(MainActivity.this, MainLogin.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        recyclerTickets = findViewById(R.id.recyclerTickets);
        fabChatbot = findViewById(R.id.fabChatbot);
        recyclerTickets.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TicketAdapter(this, listaTickets);
        recyclerTickets.setAdapter(adapter);

        carregarTickets();

        fabChatbot.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Abrir chat...", Toast.LENGTH_SHORT).show()
        );
    }

    private void carregarTickets() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(MainActivity.this))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eleanore-sporophytic-tautly.ngrok-free.dev/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService ticketService = retrofit.create(ApiService.class);

        ticketService.getTickets().enqueue(new Callback<List<TicketResponse>>() {
            @Override
            public void onResponse(Call<List<TicketResponse>> call, Response<List<TicketResponse>> response) {

                // --- TOKEN INVÁLIDO (401) ---
                if (response.code() == 401) {
                    getSharedPreferences("app", MODE_PRIVATE)
                            .edit()
                            .remove("jwt")
                            .apply();

                    startActivity(new Intent(MainActivity.this, MainLogin.class));
                    finish();
                    return;
                }

                if (response.isSuccessful() && response.body() != null) {
                    listaTickets.clear();

                    for (TicketResponse t : response.body()) {
                        listaTickets.add(new Ticket(
                                t.getTitulo(),
                                t.getDescricao(),
                                t.getStatus(),
                                t.getCriadoEm(),
                                "Sistema"
                        ));
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<TicketResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
