package pucminas.com.br.luz_agua.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.model.OurData;

public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return OurData.titulares.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titularTextView;
        private TextView cpfTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            titularTextView = (TextView) itemView.findViewById(R.id.nome_titular_EditText);
            cpfTextView     = (TextView) itemView.findViewById(R.id.cpf_titular_EditText);
            itemView.setOnClickListener(this);
        }

        /**
         * Adiciona itens do banco à RecyclerView
         */
        public void bindView(int posicao) {
            titularTextView.setText(OurData.titulares[posicao]);
            cpfTextView.setText(OurData.cpfs[posicao]);
        }

        public void onClick(View view){

        }

    }
}
