package com.android.guicelebrini.howtoplay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.helper.UsuarioDAO;
import com.android.guicelebrini.howtoplay.helper.Validador;
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

        usuarioDAO = new UsuarioDAO();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                if (Validador.verificarCampos(email, senha)) {
                    usuarioDAO.cadastrar(email, senha, new UsuarioDAO.FuncionouCallback() {
                        @Override
                        public void funcionou(boolean funcionou) {
                            if (funcionou){
                                Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Oops... Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Os campos não podem estar vazios", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void findViewsbyId(){
        editEmail = findViewById(R.id.editEmailCadastro);
        editSenha = findViewById(R.id.editSenhaCadastro);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
    }


}