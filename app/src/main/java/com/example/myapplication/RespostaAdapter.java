package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.myapplication.models.Resposta;

public class RespostaAdapter extends RecyclerView.Adapter<RespostaAdapter.RespostaViewHolder> {

    private Context context;
    private List<Resposta> listaRespostas;

    // Construtor
    public RespostaAdapter(Context context, List<Resposta> listaRespostas) {
        this.context = context;
        this.listaRespostas = listaRespostas;
    }

    // 1. Cria o layout (ViewHolder)
    @NonNull
    @Override
    public RespostaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_resposta, parent, false);
        return new RespostaViewHolder(view);
    }

    // 2. Preenche o layout com os dados de uma posição específica
    @Override
    public void onBindViewHolder(@NonNull RespostaViewHolder holder, int position) {
        Resposta resposta = listaRespostas.get(position);

        holder.textNomeUsuario.setText(resposta.getNomeUsuario());
        holder.textDataResposta.setText(resposta.getDataResposta());
        holder.textComentario.setText(resposta.getComentario());

        // Aqui você pode usar uma biblioteca como Glide ou Picasso para carregar
        // a imagem do avatar a partir de uma URL, por exemplo.
        // holder.imageAvatar.setImageResource(...);
    }

    // 3. Retorna a contagem total de itens
    @Override
    public int getItemCount() {
        return listaRespostas.size();
    }

    // Classe interna que "segura" as Views do layout do item
    public class RespostaViewHolder extends RecyclerView.ViewHolder {

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