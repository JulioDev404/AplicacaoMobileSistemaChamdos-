package com.example.myapplication;

import android.content.Context; // <-- MUDANÇA 1: Importar Context
import android.content.Intent;  // <-- MUDANÇA 2: Importar Intent
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> tickets;
    private Context context; // <-- MUDANÇA 3: Adicionar variável de Contexto

    // <-- MUDANÇA 4: Atualizar o construtor
    public TicketAdapter(Context context, List<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }



    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);

        // 1. Inicialização de Variáveis e Atribuições de Texto
        String status = ticket.getStatus(); // <-- Variável 'status' definida aqui

        // Variáveis que estavam faltando:
        int colorDrawableRes;
        int textColorRes = android.R.color.white; // Começamos com texto branco como padrão

        holder.tvTitle.setText(ticket.getTitle());
        holder.tvDescription.setText(ticket.getDescription());
        holder.tvStatus.setText(status);

        // Assumindo que seu objeto Ticket tem métodos getCreatedAt() e getOwner()
        holder.tvMeta.setText(ticket.getCreatedAt() + " • " + ticket.getOwner());

        // 2. Lógica de Mapeamento de Cores (Switch/Case)
        switch (status.toUpperCase()) {
            case "ABERTO":
                colorDrawableRes = R.drawable.bg_status_aberto; // Verde
                textColorRes = android.R.color.white;
                break;
            case "FECHADO":
                colorDrawableRes = R.drawable.bg_status_fechado; // Cinza
                textColorRes = android.R.color.white;
                break;
            case "EM_ANDAMENTO":
                colorDrawableRes = R.drawable.bg_status_andamento; // Ex: Amarelo
                // Se o fundo for amarelo (claro), o texto deve ser preto
                textColorRes = android.R.color.black;
                break;
            default:
                colorDrawableRes = R.drawable.bg_status_default; // Cor padrão (Neutro)
                textColorRes = android.R.color.white;
                break;
        }

        // 3. Aplicação Final da Cor e Clique
        // Aplicar a cor de fundo (Drawable)
        holder.tvStatus.setBackgroundResource(colorDrawableRes);

        // Aplicar a cor do texto, usando ContextCompat e o Contexto salvo
        holder.tvStatus.setTextColor(ContextCompat.getColor(context, textColorRes));

        // <-- MUDANÇA 5: Adicionar o "ouvinte" de clique aqui
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalheChamadoActivity.class);
            // Assumindo que seu objeto Ticket tem um método getId()
            intent.putExtra("ticketId", ticket.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvStatus, tvMeta;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvMeta = itemView.findViewById(R.id.tvMeta);
        }
    }
}