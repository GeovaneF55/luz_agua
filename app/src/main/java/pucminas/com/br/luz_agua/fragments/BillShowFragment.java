package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Objects;

import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.R;

public class BillShowFragment extends Fragment {

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
    }
}
