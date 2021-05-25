package com.android.guicelebrini.howtoplay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.model.Tutorial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TutorialActivity extends AppCompatActivity {

    private TextView titulo, autor, jogo, descricao;
    private ImageView imagem;

    private Tutorial tutorialEscolhido;

    private DatabaseReference tutoriais = FirebaseDatabase.getInstance().getReference().child("tutoriais");
    private DatabaseReference tutorialFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        tutorialFirebase = tutoriais.child(recuperarKey());

        findViewsById();

        recuperarTutorial(); //recupera o tutorial do firebase e adiciona à tela

    }

    public String recuperarKey(){
        String keyRecuperada = (String) getIntent().getSerializableExtra("key");
        return keyRecuperada;
    }

    public void findViewsById(){
        titulo = findViewById(R.id.textActivityTitulo);
        autor = findViewById(R.id.textActivityAutor);
        jogo = findViewById(R.id.textActivityJogo);
        descricao = findViewById(R.id.textActivityDescricao);

        imagem = findViewById(R.id.imageActivityTutorial);
    }

    public void recuperarTutorial(){
        tutorialFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tutorialEscolhido = snapshot.getValue(Tutorial.class);
                set();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void set(){
        titulo.setText(tutorialEscolhido.getTitulo());
        autor.setText(tutorialEscolhido.getAutor());
        jogo.setText(tutorialEscolhido.getJogo());
        descricao.setText(tutorialEscolhido.getDescricaoTutorial());
        Picasso.get().load(tutorialEscolhido.getImagem()).into(imagem);
    }
}