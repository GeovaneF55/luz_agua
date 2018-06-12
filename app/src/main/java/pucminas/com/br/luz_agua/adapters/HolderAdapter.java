package pucminas.com.br.luz_agua.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.data.HolderData;

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ListViewHolder> {

    private Context mCtx;
    private List<HolderData> dataList;

    public HolderAdapter(Context mCtx, List<HolderData> dataList) {
        this.mCtx = mCtx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.holder_item, null );
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final HolderData data = dataList.get(position);
        holder.titularTextView.setText(data.getNome());
        holder.cpfTextView.setText(data.getDoc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"", Toast.LENGTH_LONG).show();
            }
        });
        holder.itemView.setOnLongClickListener( new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                removeItem(data);
                Toast.makeText(v.getContext(), "Titular Removido Com  Sucesso!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void removeItem(HolderData data) {
        int position = dataList.indexOf(data);
        dataList.remove(data);
        notifyItemRemoved(position);
        DatabaseReference databaseReference;
        if(data.getDoc().length() == 11 ) {
            String individual = "pessoa_fisica_" + data.getDoc();
            databaseReference = FirebaseDatabase.getInstance().getReference("pessoa_fisica").child(data.getDoc());
            databaseReference.removeValue();
            databaseReference = FirebaseDatabase.getInstance().getReference("contas").child("agua")
                    .child(individual);
            databaseReference.removeValue();
            databaseReference = FirebaseDatabase.getInstance().getReference("contas").child("luz")
                    .child(individual);
            databaseReference.removeValue();

        } else {
            String company = "pessoa_juridica_" + data.getDoc();
            databaseReference = FirebaseDatabase.getInstance().getReference("pessoa_juridica").child(data.getDoc());
            databaseReference.removeValue();
            databaseReference = FirebaseDatabase.getInstance().getReference("contas").child("agua")
                    .child(company);
            databaseReference.removeValue();
            databaseReference = FirebaseDatabase.getInstance().getReference("contas").child("luz")
                    .child(company);
            databaseReference.removeValue();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView titularTextView;
        private TextView cpfTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            titularTextView = (TextView) itemView.findViewById(R.id.nome_titular_EditText);
            cpfTextView     = (TextView) itemView.findViewById(R.id.cpf_titular_EditText);
        }
    }
}