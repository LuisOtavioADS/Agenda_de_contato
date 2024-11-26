package br.fepi.agendadecontatos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {
    private List<Contato> contatoList;
    private Context context;

    public ContatoAdapter(List<Contato> contatoList) {
        this.contatoList = contatoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contato, parent, false);
        return new ContatoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        Contato contato = contatoList.get(position);
        holder.tvNome.setText(contato.getNome());
        holder.tvTelefone.setText(contato.getTelefone());
        holder.tvEndereco.setText(contato.getEndereco());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalheContatoActivity.class);
                intent.putExtra("contato", contato); // Passar o objeto Contato
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public static class ContatoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNome;
        public TextView tvTelefone;
        public TextView tvEndereco;

        public ContatoViewHolder(View view) {
            super(view);
            tvNome = view.findViewById(R.id.tvNome);
            tvTelefone = view.findViewById(R.id.tvTelefone);
            tvEndereco = view.findViewById(R.id.tvEndereco);
        }
    }
}
