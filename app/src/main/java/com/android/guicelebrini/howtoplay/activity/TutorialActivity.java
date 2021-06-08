package com.android.guicelebrini.howtoplay.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.helper.TutorialDAO;
import com.android.guicelebrini.howtoplay.model.Tutorial;

import com.squareup.picasso.Picasso;

public class TutorialActivity extends AppCompatActivity {

    private TextView titulo, autor, jogo, descricao;
    private ImageView imagem;

    private Tutorial tutorialEscolhido;
    private TutorialDAO tutorialDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        tutorialDAO = new TutorialDAO();

        findViewsById();

        tutorialDAO.recuperarTutorial(recuperarKey(), new TutorialDAO.FuncionouCallback() {
            @Override
            public void funcionou(boolean funcionou, Tutorial tutorial) {
                if (true){
                    tutorialEscolhido = tutorial;
                    set();
                } else {
                    Toast.makeText(getApplicationContext(), "Algo deu errado...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String recuperarKey(){
        String keyRecuperada = (String) getIntent().getSerializableExtra("key");
        return keyRecuperada;
    }

    public void set(){
        titulo.setText(tutorialEscolhido.getTitulo());
        autor.setText("Por: " + tutorialEscolhido.getAutor());
        jogo.setText(tutorialEscolhido.getJogo());
        descricao.setText(tutorialEscolhido.getDescricaoTutorial());
        Picasso.get().load(tutorialEscolhido.getImagem()).into(imagem);
    }

    public void findViewsById(){
        titulo = findViewById(R.id.textActivityTitulo);
        autor = findViewById(R.id.textActivityAutor);
        jogo = findViewById(R.id.textActivityJogo);
        descricao = findViewById(R.id.textActivityDescricao);

        imagem = findViewById(R.id.imageActivityTutorial);
    }
}