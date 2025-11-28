package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.ComentarioResponse;

import java.util.List;

public class RespostaAdapter extends RecyclerView.Adapter<RespostaAdapter.RespostaViewHolder> {

    private Context context;
    private List<ComentarioResponse> listaRespostas;

    // Construtor
    public RespostaAdapter(Context context, List<ComentarioResponse> listaRespostas) {
        this.context = context;
        this.listaRespostas = listaRespostas;
    }

    @NonNull
    @Override
    public RespostaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_resposta, parent, false);
        return new RespostaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RespostaViewHolder holder, int position) {
        ComentarioResponse resposta = listaRespostas.get(position);

        // Preenche os dados
        holder.textNomeUsuario.setText(resposta.autor);
        holder.textDataResposta.setText(resposta.data);
        holder.textComentario.setText(resposta.mensagem);
    }

    @Override
    public int getItemCount() {
        return listaRespostas.size();
    }

    public static class RespostaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageAvatar;
        TextView textNomeUsuario;
        TextView textDataResposta;
        TextView textComentario;

        public RespostaViewHolder(@NonNull View itemView) {
            super(itemView);

            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            textNomeUsuario = itemView.findViewById(R.id.textNomeUsuario);
            textDataResposta = itemView.findViewById(R.id.textDataResposta);
            textComentario = itemView.findViewById(R.id.textComentario);
        }
    }
}