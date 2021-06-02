package com.android.guicelebrini.howtoplay.helper;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.howtoplay.adapter.AdapterTutoriais;
import com.android.guicelebrini.howtoplay.model.Tutorial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TutorialDAO {

    private DatabaseReference tutoriais = FirebaseDatabase.getInstance().getReference().child("tutoriais");
    private DatabaseReference tutorialFirebase;
    private Object Tutorial;


    public void montarLista(List<Tutorial> listaTutoriais, RecyclerView recyclerTutoriais){


        tutoriais.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sn : snapshot.getChildren()){
                    Tutorial tutorial = sn.getValue(Tutorial.class);
                    tutorial.setKey(sn.getKey());
                    listaTutoriais.add(tutorial);
                }
                AdapterTutoriais adaptador = new AdapterTutoriais(listaTutoriais);
                recyclerTutoriais.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void recuperarESetarTutorial(String keyRecuperada, TextView titulo, TextView autor, TextView jogo, TextView descricao, ImageView imagem){
        tutorialFirebase = tutoriais.child(keyRecuperada);

        tutorialFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Tutorial tutorial = snapshot.getValue(Tutorial.class);
                set(tutorial, titulo, autor, jogo, descricao, imagem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void set(Tutorial tutorial, TextView titulo, TextView autor, TextView jogo, TextView descricao, ImageView imagem){
        titulo.setText(tutorial.getTitulo());
        autor.setText("Por: " + tutorial.getAutor());
        jogo.setText(tutorial.getJogo());
        descricao.setText(tutorial.getDescricaoTutorial());
        Picasso.get().load(tutorial.getImagem()).into(imagem);
    }



}
