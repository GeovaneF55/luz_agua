package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.adapters.ReportAdapter;
import pucminas.com.br.luz_agua.data.HolderData;
import pucminas.com.br.luz_agua.data.ReportData;
import pucminas.com.br.luz_agua.models.Bill;
import pucminas.com.br.luz_agua.models.Holder;
import pucminas.com.br.luz_agua.models.Individual;
import pucminas.com.br.luz_agua.models.Report;
import pucminas.com.br.luz_agua.models.WaterBill;

public class ReportFragment extends Fragment {

    // Instance Firebase
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;

    private Switch mSwitchData;
    private EditText mEditDate;

    ReportAdapter adapter;
    List<ReportData> dataListFinal;
    List<ReportData> dataListAgua;
    List<ReportData> dataListLuz;
    Context mContext;

    private View mView;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReportFragment.
     */
    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this.getActivity();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabase = mFirebaseDatabase.getReference().child("contas");
    }

    @Override
    public void onStart() {
        super.onStart();

         new MyEditTextDatePicker(this.getActivity() , R.id.input_relatorio);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_report, container, false);

        mEditDate = (EditText) mView.findViewById(R.id.input_relatorio);

        dataListLuz = new ArrayList<>();
        dataListAgua = new ArrayList<>();
        dataListFinal = new ArrayList<>();
        addData();

        //createComponents(view);

        return mView;
    }

    /**
     * Aqui será feito a inserção dos dados buscados do Firebase
     */
    private void addData() {

        // Switch
        Switch mSwitchData = (Switch) mView.findViewById(R.id.switch_data_relatorio);

        mSwitchData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mEditDate.setText("");
                    mEditDate.setEnabled(false);
                } else {
                    mEditDate.setEnabled(true);
                }
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

                RecyclerView recyclerView = mView.findViewById(R.id.recicler_report);
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

                RecyclerView recyclerView = mView.findViewById(R.id.recicler_report);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
