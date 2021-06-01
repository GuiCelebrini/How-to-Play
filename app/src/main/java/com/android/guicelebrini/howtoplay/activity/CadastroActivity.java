package com.android.guicelebrini.howtoplay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private Button buttonCadastrar;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        findViewsbyId();

        usuarioDAO = new UsuarioDAO(getApplicationContext());

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                if (verificarCampos(email, senha)) {
                    usuarioDAO.cadastrar(email, senha);
                }
            }
        });

    }

    public void findViewsbyId(){
        editEmail = findViewById(R.id.editEmailCadastro);
        editSenha = findViewById(R.id.editSenhaCadastro);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
    }

    public Boolean verificarCampos(String emailInserido, String senhaInserida) {

        if (emailInserido.equals("") || senhaInserida.equals("")) {
            Toast.makeText(getApplicationContext(), "Os campos não podem estar vazios", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}