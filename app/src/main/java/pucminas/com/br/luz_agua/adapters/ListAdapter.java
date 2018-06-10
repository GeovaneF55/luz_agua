package pucminas.com.br.luz_agua.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.model.OurData;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context mCtx;
    private List<OurData> dataList;

    public ListAdapter(Context mCtx, List<OurData> dataList) {
        this.mCtx = mCtx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item, null );
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        OurData data = dataList.get(position);
        holder.titularTextView.setText(data.getNome());
        holder.cpfTextView.setText(data.getCpf());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titularTextView;
        private TextView cpfTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            titularTextView = (TextView) itemView.findViewById(R.id.nome_titular_EditText);
            cpfTextView     = (TextView) itemView.findViewById(R.id.cpf_titular_EditText);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
        }

    }
}