package com.android.guicelebrini.howtoplay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.adapter.AdapterTutoriais;
import com.android.guicelebrini.howtoplay.model.Tutorial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTutoriais;
    private List<Tutorial> listaTutoriais = new ArrayList<>();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        montarLista();

        AdapterTutoriais adaptador = new AdapterTutoriais(listaTutoriais);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerTutoriais.setLayoutManager(layoutManager);
        recyclerTutoriais.setHasFixedSize(true);
        recyclerTutoriais.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerTutoriais.setAdapter(adaptador);


    }

    public void findViewsById(){
        recyclerTutoriais = findViewById(R.id.recyclerTutoriais);
    }

    public void montarLista(){
        DatabaseReference tutoriais = referencia.child("tutoriais");

        Tutorial tutorial1 = new Tutorial(
                "Como sobreviver ao labirinto de Ivaldi",
                "Guilherme",
                "God of War",
                "",
                "https://static.wixstatic.com/media/7e1518_470d563171e8456d9b228e4b89bf7fac~mv2.jpg/v1/fill/w_1000,h_563,al_c,q_90,usm_0.66_1.00_0.01/7e1518_470d563171e8456d9b228e4b89bf7fac~mv2.jpg"
        );
        listaTutoriais.add(tutorial1);

        Tutorial tutorial2 = new Tutorial(
                "Como jogar Valorant para iniciantes",
                "JettMorgan",
                "Valorant",
                "",
                "https://s2.glbimg.com/-MFrOgfjaPYQaVlB7SsyfdKCK5U=/0x0:1400x788/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2020/p/B/vlmXnfQqee9zfEWCI6Ig/valorant.jpg"
        );
        listaTutoriais.add(tutorial2);
    }
}