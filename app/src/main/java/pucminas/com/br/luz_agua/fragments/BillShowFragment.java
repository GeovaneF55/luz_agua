package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.adapters.BillAdapter;
import pucminas.com.br.luz_agua.adapters.ReportAdapter;
import pucminas.com.br.luz_agua.data.BillData;
import pucminas.com.br.luz_agua.data.ReportData;
import pucminas.com.br.luz_agua.models.Bill;
import pucminas.com.br.luz_agua.models.WaterBill;

public class BillShowFragment extends Fragment {

    Context mContext;

    // RecyclerView
    BillAdapter bill_adapter;
    List<BillData> dataList;
    ReportAdapter adapter;
    List<ReportData> dataListFinal;
    List<ReportData> dataListAgua;
    List<ReportData> dataListLuz;

    private View mView;

    //Instance Firebase
    private DatabaseReference mDatabase;

    public BillShowFragment() {
        // Required empty public constructor
    }

    public static BillShowFragment newInstance() {
        return new BillShowFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this.getActivity();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("contas");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bill_show, container, false);
        dataListLuz = new ArrayList<>();
        dataListAgua = new ArrayList<>();
        dataListFinal = new ArrayList<>();
        addData();

        createComponents(mView);

        return mView;
    }

    /**
     * Aqui será feito a inserção dos dados buscados do Firebase
     */
    private void addData() {
        mDatabase.child("luz").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataListLuz.clear();
                dataListFinal.clear();

                for (DataSnapshot holder : dataSnapshot.getChildren()) {
                    for (DataSnapshot bill : holder.getChildren()) {
                        Bill b = bill.getValue(WaterBill.class);
                        assert b != null;

                        String conta = "Luz";
                        String data = b.date();
                        double consumo = b.getLeituraAtual();
                        double consumo_anterior = b.getLeituraAnterior();
                        double valor = b.calcularValor();

                        dataListLuz.add(new ReportData(conta, data, consumo, consumo_anterior, valor));
                    }
                }

                RecyclerView recyclerView = mView.findViewById(R.id.robson);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dataListFinal.addAll(dataListAgua);
                dataListFinal.addAll(dataListLuz);
                adapter = new ReportAdapter(getContext(), dataListFinal);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        mDatabase.child("agua").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataListAgua.clear();
                dataListFinal.clear();

                for (DataSnapshot holder : dataSnapshot.getChildren()) {
                    for (DataSnapshot bill : holder.getChildren()) {
                        Bill b = bill.getValue(WaterBill.class);
                        assert b != null;

                        String conta = "Água";
                        String data = b.date();
                        double consumo = b.getLeituraAtual();
                        double consumo_anterior = b.getLeituraAnterior();
                        double valor = b.calcularValor();

                        dataListAgua.add(new ReportData(conta, data, consumo, consumo_anterior, valor));
                    }
                }

                RecyclerView recyclerView = mView.findViewById(R.id.robson);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dataListFinal.addAll(dataListAgua);
                dataListFinal.addAll(dataListLuz);
                adapter = new ReportAdapter(getContext(), dataListFinal);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void createComponents(View view){

        final TextInputLayout titular = view.findViewById(R.id.input_layout_titular_exibir);
        final TextInputLayout data = view.findViewById(R.id.input_layout_data_exibir);
        titular.setVisibility(View.VISIBLE);
        data.setVisibility(View.GONE);

        // Spinner Tipo Conta
        Spinner spinner = view.findViewById(R.id.input_tipo_conta_exibir);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.spinner_tipo_conta_exibir, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // Spinner Listar Por
        spinner = view.findViewById(R.id.input_tipo_listar_por_exibir);
        adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.spinner_listar_por_exibir, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                switch ((int) id) {
                    // Titular
                    case 0:
                        titular.setVisibility(View.VISIBLE);
                        data.setVisibility(View.GONE);
                        break;

                    // Data
                    case 1:
                        titular.setVisibility(View.GONE);
                        data.setVisibility(View.VISIBLE);
                        new MyEditTextDatePicker(mContext , R.id.input_data_exibir);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {  }
        });

        // RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.robson);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dataList = new ArrayList<>();
        addData();

        bill_adapter = new BillAdapter(mContext ,dataList);
        recyclerView.setAdapter(bill_adapter);
    }
}
