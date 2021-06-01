package com.android.guicelebrini.howtoplay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.helper.UsuarioDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private Button buttonLogin;
    private Button buttonCadastro;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        findViewsById();
        usuarioDAO = new UsuarioDAO(getApplicationContext());


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                if (verificarCampos(email, senha)) {
                    usuarioDAO.logar(email, senha);
                }
            }
        });

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent destino = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(destino);
            }
        });

    }

    public void findViewsById() {
        editEmail = findViewById(R.id.editEmailCadastro);
        editSenha = findViewById(R.id.editSenhaCadastro);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCadastro = findViewById(R.id.buttonCadastro);
    }


    public Boolean verificarCampos(String emailInserido, String senhaInserida) {

        if (emailInserido.equals("") || senhaInserida.equals("")) {
            Toast.makeText(getApplicationContext(), "Os campos não podem estar vazios", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void zerarCampos() {
        editEmail.setText("");
        editSenha.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        zerarCampos();
    }
}