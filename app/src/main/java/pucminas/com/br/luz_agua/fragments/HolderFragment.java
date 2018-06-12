package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import pucminas.com.br.luz_agua.IndividualFactory;
import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.adapters.HolderAdapter;
import pucminas.com.br.luz_agua.data.HolderData;
import pucminas.com.br.luz_agua.models.Company;
import pucminas.com.br.luz_agua.models.Holder;
import pucminas.com.br.luz_agua.models.Individual;


public class HolderFragment extends Fragment {

    HolderAdapter adapter;
    List<HolderData> dataListFisica;
    List<HolderData> dataListJuridica;
    List<HolderData> dataListFinal;

    // Retrieve data from Firebase

    DatabaseReference databaseReference, databaseReferenceJuridica;
    private View mView;

    public HolderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HolderFragment.
     */
    public static HolderFragment newInstance() {
        return new HolderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_holder, container, false);
        dataListFisica   = new ArrayList<>();
        dataListJuridica = new ArrayList<>();
        dataListFinal    = new ArrayList<>();
        addData();
        return mView;
    }


   /**
    * Aqui será feito a inserção dos dados buscados do Firebase
    */
    private void addData() {
        databaseReference.child("pessoa_fisica").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataListFisica.clear();
                dataListFinal.clear();
                for(DataSnapshot holder : dataSnapshot.getChildren())  {
                    Holder h = holder.getValue(Individual.class);
                    assert h != null;

                    dataListFisica.add(new HolderData(((Individual) h).fullName(), ((Individual) h).getCPF()));
                }
                RecyclerView recyclerView = mView.findViewById(R.id.recicler_holder);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dataListFinal.addAll(dataListFisica);
                dataListFinal.addAll(dataListJuridica);
                adapter = new HolderAdapter(getContext(), dataListFinal);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("pessoa_juridica").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataListJuridica.clear();
                dataListFinal.clear();
                for(DataSnapshot holder : dataSnapshot.getChildren())  {
                    Holder h = holder.getValue(Company.class);
                    assert h != null;

                    dataListJuridica.add(new HolderData(((Company) h).getCompanyName(), ((Company) h).getCNPJ()));
                }
                RecyclerView recyclerView = mView.findViewById(R.id.recicler_holder);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                dataListFinal.addAll(dataListFisica);
                dataListFinal.addAll(dataListJuridica);

                adapter = new HolderAdapter(getContext(), dataListFinal);
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


}