package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.adapters.ReportAdapter;
import pucminas.com.br.luz_agua.data.ReportData;

public class ReportFragment extends Fragment {

    // Instance Firebase
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private ChildEventListener mChildEventListener;

    ReportAdapter report_adapter;
    List<ReportData> dataList;
    Context mContext;

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
        mDatabase = mFirebaseDatabase.getReference().child("messages");
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
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        createComponents(view);

        return view;
    }

    /**
     * Aqui será feito a inserção dos dados buscados do Firebase
     */
    private void addData() {

        dataList.add(new ReportData("Água","22/06/2018", "6,880 m3", "R$60,00"));
        dataList.add(new ReportData("Luz", "11/07/2018", "7,083 kW/h", "R$140,00"));
        dataList.add(new ReportData("Água", "06/08/2018", "6,865 m3", "R$30,00"));
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

    public void createComponents(View view) {
        // RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recicler_report);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dataList = new ArrayList<>();
        addData();

        report_adapter = new ReportAdapter(mContext ,dataList);
        recyclerView.setAdapter(report_adapter);
    }
}
