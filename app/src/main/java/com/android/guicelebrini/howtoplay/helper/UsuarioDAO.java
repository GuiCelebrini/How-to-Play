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

    public UsuarioDAO(){}

    public interface FuncionouCallback {
        void funcionou(boolean funcionou);
    }

    public void logar(String email, String senha, FuncionouCallback funcionouCallback) {

        usuario.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            funcionouCallback.funcionou(true);
                        } else {
                            funcionouCallback.funcionou(false);
                        }
                    }
                });

    }

    public void cadastrar(String email, String senha, FuncionouCallback funcionouCallback) {
        usuario.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            funcionouCallback.funcionou(true);
                        } else {
                            funcionouCallback.funcionou(false);
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

}
