package com.android.guicelebrini.howtoplay.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.guicelebrini.howtoplay.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UsuarioDAO {
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private Context contexto;

    public UsuarioDAO(Context contexto) {
        this.contexto = contexto;
    }

    public void logar(String email, String senha) {

        usuario.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("Resultado", "Usuário logado com sucesso");
                            mudarActivity();
                        } else {
                            Toast.makeText(contexto, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void cadastrar(String email, String senha) {
        usuario.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(contexto, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(contexto, "Ops... Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void deslogar() {
        usuario.signOut();
    }

    public Boolean estaLogado() {
        if (usuario.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    private void mudarActivity(){
        Intent destino = new Intent(contexto, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(destino);
    }

}
