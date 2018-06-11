package pucminas.com.br.luz_agua.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.data.BillData;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillView> {
    private Context mCtx;
    private List<BillData> dataList;

    public BillAdapter(Context mCtx, List<BillData> dataList) {
        this.mCtx = mCtx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BillAdapter.BillView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.bill_item, null );
        return new BillAdapter.BillView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillView bill, int position) {
        BillData data = dataList.get(position);
        bill.contaTextView.setText(data.getConta());
        bill.dataTextView.setText(data.getData());
        bill.consumoTextView.setText(data.getConsumo());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class BillView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView contaTextView;
        private TextView dataTextView;
        private TextView consumoTextView;

        BillView(View itemView) {
            super(itemView);
            contaTextView   = (TextView) itemView.findViewById(R.id.conta_EditText);
            dataTextView    = (TextView) itemView.findViewById(R.id.data_EditText);
            consumoTextView = (TextView) itemView.findViewById(R.id.consumo_EditText);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
        }

    }
}
