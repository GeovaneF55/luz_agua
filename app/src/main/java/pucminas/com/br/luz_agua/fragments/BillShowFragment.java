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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.adapters.BillAdapter;
import pucminas.com.br.luz_agua.model.BillData;

public class BillShowFragment extends Fragment {

    BillAdapter bill_adapter;
    List<BillData> dataList;
    Context mContext;

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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_show, container, false);

        createComponents(view);

        return view;
    }

    /**
     * Aqui será feito a inserção dos dados buscados do Firebase
     */
    private void addData() {

        dataList.add(new BillData("Água","22/06/2018", "6,880 m3"));
        dataList.add(new BillData("Luz", "11/07/2018", "7,083 kW/h"));
        dataList.add(new BillData("Água", "06/08/2018", "6,865 m3"));
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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recicler_contas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dataList = new ArrayList<>();
        addData();

        bill_adapter = new BillAdapter(mContext ,dataList);
        recyclerView.setAdapter(bill_adapter);
    }
}
