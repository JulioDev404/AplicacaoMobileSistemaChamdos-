package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.services.ApiService;
import com.example.myapplication.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogin extends AppCompatActivity {

    EditText editEmail, editSenha;
    Button button;
    CheckBox cbLembrar;

    private static final String PREFS_FILE = "app";
    private static final String PREF_EMAIL = "saved_email";
    private static final String PREF_LEMBRAR = "remember_me";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_login);


        editEmail = findViewById(R.id.edtEmail);
        editSenha = findViewById(R.id.edtSenha);
        button = findViewById(R.id.btnLogin);

        cbLembrar = findViewById(R.id.cbLembrar);


        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        boolean lembrarMe = prefs.getBoolean(PREF_LEMBRAR, false);
        cbLembrar.setChecked(lembrarMe);

        if (lembrarMe) {
            String savedEmail = prefs.getString(PREF_EMAIL, "");
            editEmail.setText(savedEmail);
            editSenha.requestFocus();
        }

        button.setOnClickListener(v -> {
            String email = editEmail.getText().toString().toLowerCase();
            String senha = editSenha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            fazerLogin(email, senha);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fazerLogin(String email, String senha) {

        button.setEnabled(false);

        ApiService apiService = RetrofitClient.getClient(MainLogin.this).create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest(email, senha);

        Call<LoginResponse> call = apiService.postlogin(loginRequest);

        call.enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        button.setEnabled(true);

                        if (!response.isSuccessful()) {
                            Toast.makeText(MainLogin.this, "Erro no servidor ou credenciais inválidas.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        LoginResponse loginResponse = response.body();

                        if (loginResponse == null || !loginResponse.isSucesso()) {
                            Toast.makeText(MainLogin.this, "E-mail ou senha inválidos.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String token = loginResponse.getToken();
                        SharedPreferences.Editor editor = getSharedPreferences(PREFS_FILE, MODE_PRIVATE).edit();

                        boolean lembrarMe = cbLembrar.isChecked();
                        editor.putBoolean(PREF_LEMBRAR, lembrarMe);

                        if (lembrarMe) {
                            editor.putString(PREF_EMAIL, email);
                        } else {
                            editor.remove(PREF_EMAIL);
                        }

                        editor.putString("jwt", token);
                        editor.apply();

                        Toast.makeText(MainLogin.this, "Login bem sucedido!", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainLogin.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        button.setEnabled(true);
                        Toast.makeText(MainLogin.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}

    