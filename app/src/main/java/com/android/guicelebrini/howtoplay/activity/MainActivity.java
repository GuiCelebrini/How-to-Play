package com.android.guicelebrini.howtoplay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.adapter.AdapterTutoriais;
import com.android.guicelebrini.howtoplay.helper.RecyclerItemClickListener;
import com.android.guicelebrini.howtoplay.helper.TutorialDAO;
import com.android.guicelebrini.howtoplay.helper.UsuarioDAO;
import com.android.guicelebrini.howtoplay.model.Tutorial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTutoriais;
    private List<Tutorial> listaTutoriais;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private AdapterTutoriais adaptador;
    private TutorialDAO tutorialDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        tutorialDAO = new TutorialDAO();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerTutoriais.setLayoutManager(layoutManager);
        recyclerTutoriais.setHasFixedSize(true);
        recyclerTutoriais.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        adicionarOnClick();

    }

    public void findViewsById(){
        recyclerTutoriais = findViewById(R.id.recyclerTutoriais);
    }

    public void montarListaFirebase(){
        listaTutoriais = new ArrayList<>();

        tutorialDAO.montarLista(listaTutoriais, recyclerTutoriais);
    }

    public void adicionarOnClick(){
        recyclerTutoriais.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerTutoriais, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tutorial tutorialSelecionado = listaTutoriais.get(position);
                Intent destino = new Intent(getApplicationContext() ,TutorialActivity.class);
                destino.putExtra("key", tutorialSelecionado.getKey());
                startActivity(destino);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        montarListaFirebase(); //monta a lista e seta o adaptador ao recyclerview
    }
}