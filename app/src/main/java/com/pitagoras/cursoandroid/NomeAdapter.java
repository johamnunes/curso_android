package com.pitagoras.cursoandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joham Nunes on 25/06/2017.
 */

public class NomeAdapter extends RecyclerView.Adapter<NomeAdapter.ViewHolder>{

    private ArrayList<Nome> nomes;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView nomeCompleto;
        ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            nomeCompleto = itemView.findViewById(R.id.nome_completo);
        }
    }

    public NomeAdapter(ArrayList<Nome> nomes){
        this.nomes = nomes;
    }

    @Override
    public NomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nome_list, parent, false));
    }

    @Override
    public void onBindViewHolder(NomeAdapter.ViewHolder holder, int position) {
        holder.nomeCompleto.setText(nomes.get(position).getNome() + " " + nomes.get(position).getSobrenome());
    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }
}
