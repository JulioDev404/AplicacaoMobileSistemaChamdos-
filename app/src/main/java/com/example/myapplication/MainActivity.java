package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.Ticket;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTickets;
    private FloatingActionButton fabChatbot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // 2. Pegue a Intent e verifique o login AQUI DENTRO
        String email = getIntent().getStringExtra("email");

        // 3. Se não houver email redireciona para o login e pare a execução
        if (email == null) {
            Intent loginIntent = new Intent(MainActivity.this, MainLogin.class);
            startActivity(loginIntent);
            finish();
            return;
        }

        //seta o layout
        setContentView(R.layout.activity_main);
        recyclerTickets = findViewById(R.id.recyclerTickets);
        fabChatbot = findViewById(R.id.fabChatbot);



        // 1. LayoutManager
        recyclerTickets.setLayoutManager(new LinearLayoutManager(this));

        // 2. Lista de chamados de exemplo
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("Erro no sistema", "Usuário não consegue logar", "Aberto", "12/09", "João"));
        tickets.add(new Ticket("Falha na impressora", "Impressora não imprime", "Em andamento", "13/09", "Maria"));
        tickets.add(new Ticket("Atualização", "Solicitar atualização do app", "Fechado", "15/09", "Carlos"));

        // 3. Adapter
        TicketAdapter adapter = new TicketAdapter(tickets);
        recyclerTickets.setAdapter(adapter);

        // 4. Ação do FAB (chatbot)
        fabChatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Abrir chat...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
