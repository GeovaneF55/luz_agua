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
import pucminas.com.br.luz_agua.data.ReportData;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportView> {

    private Context mCtx;
    private List<ReportData> dataList;

    public ReportAdapter(Context mCtx, List<ReportData> dataList) {
        this.mCtx = mCtx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ReportAdapter.ReportView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.report_item, null );
        return new ReportAdapter.ReportView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportView report, int position) {
        ReportData data = dataList.get(position);
        report.contaTextView.setText(data.getConta());
        report.dataTextView.setText(data.getData());
        report.consumoTextView.setText(data.getConsumo());
        report.valorTextView.setText(data.getValor());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ReportView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView contaTextView;
        private TextView dataTextView;
        private TextView consumoTextView;
        private TextView valorTextView;

        ReportView(View itemView) {
            super(itemView);
            contaTextView   = (TextView) itemView.findViewById(R.id.conta_report_EditText);
            dataTextView    = (TextView) itemView.findViewById(R.id.data_report_EditText);
            consumoTextView = (TextView) itemView.findViewById(R.id.consumo_total_EditText);
            valorTextView = (TextView) itemView.findViewById(R.id.valor_total_EditText);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
        }

    }
}
