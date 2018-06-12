package pucminas.com.br.luz_agua.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportView report, int position) {
        ReportData data = dataList.get(position);
        report.contaTextView.setText(data.getConta());
        report.dataTextView.setText(data.getData());
        String moeda = Currency.getInstance(Locale.getDefault()).getSymbol();
        String medidaConsumo = data.getConta().equals("Água")
                ? "m³"
                : "kW/h";
        report.consumoAnteriorTextView.setText(Double.toString(data.getConsumo_anterior()) + " " + medidaConsumo);
        report.consumoTextView.setText(Double.toString(data.getConsumo()) + " " + medidaConsumo);
        report.valorTextView.setText(moeda + " " +
                String.format(Locale.getDefault(),  "%.2f", data.getValor()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ReportView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView contaTextView;
        private TextView dataTextView;
        private TextView consumoAnteriorTextView;
        private TextView consumoTextView;
        private TextView valorTextView;

        ReportView(View itemView) {
            super(itemView);
            contaTextView   = itemView.findViewById(R.id.conta_report_EditText);
            dataTextView    = itemView.findViewById(R.id.data_report_EditText);
            consumoAnteriorTextView = itemView.findViewById(R.id.consumo_anterior_EditText);
            consumoTextView = itemView.findViewById(R.id.consumo_total_EditText);
            valorTextView = itemView.findViewById(R.id.valor_total_EditText);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
        }

    }
}
