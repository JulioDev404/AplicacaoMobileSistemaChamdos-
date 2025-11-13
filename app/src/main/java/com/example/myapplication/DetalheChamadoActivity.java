package com.example.myapplication;

import com.example.myapplication.models.Resposta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Supondo que o layout se chama "activity_detalhe_chamado.xml"
public class DetalheChamadoActivity extends AppCompatActivity {

    private RecyclerView recyclerRespostas;
    private RespostaAdapter respostaAdapter;
    private List<Resposta> listaDeRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_chamado);

        // 1. Encontre o RecyclerView no seu layout
        recyclerRespostas = findViewById(R.id.recyclerRespostas);

        // 2. Configure o RecyclerView
        configurarRecyclerView();

        // 3. Carregue os dados
        // (Mais tarde, você vai substituir isso pela chamada de API)
        carregarDadosDeExemplo();
    }

    private void configurarRecyclerView() {
        // Inicialize a lista (mesmo que vazia por enquanto)
        listaDeRespostas = new ArrayList<>();

        // Crie o Adapter
        respostaAdapter = new RespostaAdapter(this, listaDeRespostas);

        // Defina o Layout Manager (vertical)
        recyclerRespostas.setLayoutManager(new LinearLayoutManager(this));

        // Conecte o Adapter ao RecyclerView
        recyclerRespostas.setAdapter(respostaAdapter);
    }

    private void carregarDadosDeExemplo() {
        // Isso é o que viria da sua API
        Resposta r1 = new Resposta("Júlio César", "08/10/2023 11:18", "Verificar planilha por favor");
        Resposta r2 = new Resposta("Guilherme Henrique", "08/10/2023 11:20", "Verificando");
        Resposta r3 = new Resposta("Guilherme Henrique", "08/10/2023 11:25", "Status alterado de Aberto para Andamento");
        Resposta r4 = new Resposta("Guilherme Henrique", "09/10/2023 12:00", "MAIS UMMMMM");

        // Adicione os dados à lista
        listaDeRespostas.add(r1);
        listaDeRespostas.add(r2);
        listaDeRespostas.add(r3);
        listaDeRespostas.add(r4);

        // Notifique o adapter que os dados mudaram!
        // Isso fará a lista aparecer na tela.
        respostaAdapter.notifyDataSetChanged();
    }
}
