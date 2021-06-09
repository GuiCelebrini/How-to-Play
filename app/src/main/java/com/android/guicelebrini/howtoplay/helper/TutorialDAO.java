package com.android.guicelebrini.howtoplay.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.howtoplay.adapter.AdapterTutoriais;
import com.android.guicelebrini.howtoplay.model.Tutorial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TutorialDAO {

    private DatabaseReference tutoriais = FirebaseDatabase.getInstance().getReference().child("tutoriais");
    private DatabaseReference tutorialFirebase;


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

    public interface FuncionouCallback {
        void funcionou(boolean funcionou, Tutorial tutorial);
    }

    public void recuperarTutorial(String keyRecuperada, FuncionouCallback funcionouCallback){
        tutorialFirebase = tutoriais.child(keyRecuperada);

        tutorialFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Tutorial tutorial = snapshot.getValue(Tutorial.class);
                funcionouCallback.funcionou(true, tutorial);
                //set(tutorial, titulo, autor, jogo, descricao, imagem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                funcionouCallback.funcionou(false, null);
            }
        });
    }



}
