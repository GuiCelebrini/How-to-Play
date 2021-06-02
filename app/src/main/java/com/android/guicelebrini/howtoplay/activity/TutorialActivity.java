package com.android.guicelebrini.howtoplay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.helper.TutorialDAO;
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

    private TutorialDAO tutorialDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        tutorialDAO = new TutorialDAO();

        findViewsById();

        tutorialDAO.recuperarESetarTutorial(recuperarKey(), titulo, autor, jogo, descricao, imagem);
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
}