package com.android.guicelebrini.howtoplay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.howtoplay.R;
import com.android.guicelebrini.howtoplay.model.Tutorial;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTutoriais extends RecyclerView.Adapter<AdapterTutoriais.MyViewHolder> {

    private List<Tutorial> listaTutoriais;

    public AdapterTutoriais (List<Tutorial> listaTutoriais){
        this.listaTutoriais = listaTutoriais;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_tutoriais, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTutoriais.MyViewHolder holder, int position) {
        Tutorial tutorial = listaTutoriais.get(position);
        holder.set(tutorial);

    }

    @Override
    public int getItemCount() {
        return listaTutoriais.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView autor;
        ImageView imagem;

        public void set(Tutorial tutorial){
            this.titulo.setText(tutorial.getTitulo());
            this.autor.setText("Por: " + tutorial.getAutor());
            Picasso.get().load(tutorial.getImagem()).into(this.imagem);
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            autor = itemView.findViewById(R.id.textAutor);
            imagem = itemView.findViewById(R.id.imageTutorial);


        }
    }

}
