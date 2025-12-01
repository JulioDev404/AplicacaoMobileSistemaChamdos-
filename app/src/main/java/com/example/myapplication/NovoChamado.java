package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

// IMPORTAÇÕES NECESSÁRIAS
import com.example.myapplication.models.NovoChamadoRequest;
import com.example.myapplication.models.TicketResponse;
import com.example.myapplication.services.RetrofitClient;
import com.example.myapplication.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovoChamado extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextDescricao;
    private Spinner spinnerPrioridade;
    private MaterialButton buttonEnviarChamado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);

        // 1. Inicializar Views
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        spinnerPrioridade = findViewById(R.id.spinnerPrioridade);
        buttonEnviarChamado = findViewById(R.id.buttonEnviarChamado);

        // 2. Definir o Listener para o botão de envio
        buttonEnviarChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNovoChamado();
            }
        });
    }

    private void enviarNovoChamado() {
        String titulo = editTextTitulo.getText().toString().trim();
        String descricao = editTextDescricao.getText().toString().trim();
        // Assume que o valor do Spinner é exatamente "Baixa", "Média", etc., para a API
        String prioridade = spinnerPrioridade.getSelectedItem().toString();

        // 1. Validação simples
        if (titulo.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(this, "Título e descrição são obrigatórios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Criar o DTO (corpo da requisição JSON)
        NovoChamadoRequest requestBody = new NovoChamadoRequest(titulo, descricao, prioridade);

        // 3. Inicializar o serviço Retrofit
        // CORREÇÃO: Deve ser a interface TicketService.class
        ApiService service = RetrofitClient.getClient(this).create(ApiService.class);

        // 4. Criar a chamada
        // CORREÇÃO: Deve chamar o método createTicket() na interface Service
        Call<TicketResponse> call = service.createTicket(requestBody);

        // Opcional: Desabilitar o botão para prevenir cliques múltiplos
        buttonEnviarChamado.setEnabled(false);

        // 5. Executar a chamada de forma assíncrona
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                // Habilitar o botão novamente ao receber resposta
                buttonEnviarChamado.setEnabled(true);

                if (response.isSuccessful() && response.code() == 201) {
                    // SUCESSO: Chamado criado com sucesso
                    Toast.makeText(NovoChamado.this, "Chamado #" + response.body().getId() + " criado!", Toast.LENGTH_LONG).show();
                    finish(); // Fechar a tela
                } else {
                    // ERRO DA API (400, 401, etc.)
                    String errorMsg = "Erro (" + response.code() + "): Verifique os dados.";
                    if (response.errorBody() != null) {
                        try {
                            // Tentativa de mostrar erro de validação da API C#
                            errorMsg = response.errorBody().string();
                        } catch (Exception e) {
                            // Ignora
                        }
                    }
                    Toast.makeText(NovoChamado.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                // Habilitar o botão novamente
                buttonEnviarChamado.setEnabled(true);
                // ERRO DE CONEXÃO (sem internet, servidor offline, etc.)
                Toast.makeText(NovoChamado.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}