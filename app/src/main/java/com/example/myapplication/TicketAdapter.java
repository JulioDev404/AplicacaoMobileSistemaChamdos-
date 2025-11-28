package com.example.myapplication;

import android.content.Context; // <-- MUDANÇA 1: Importar Context
import android.content.Intent;  // <-- MUDANÇA 2: Importar Intent
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.tvTitle.setText(ticket.getTitle());
        holder.tvDescription.setText(ticket.getDescription());
        holder.tvStatus.setText(ticket.getStatus());
        holder.tvMeta.setText(ticket.getCreatedAt() + " • " + ticket.getOwner());



        // <-- MUDANÇA 5: Adicionar o "ouvinte" de clique aqui
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalheChamadoActivity.class);
            intent.putExtra("ticketId", ticket.getId()); // enviando ID do ticket
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