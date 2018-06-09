package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pucminas.com.br.luz_agua.R;


public class Holder extends Fragment {


    public Holder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Holder.
     */
    public static Holder newInstance() {
        return new Holder();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_holder, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recicler_holder);

        ListAdapter listAdapter = new ListAdapter();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

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

        // ReciclerView Titular
        /*mRecyclerView = (RecyclerView) view.findViewById(R.id.recicler_holder);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TitularAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    public static class ListAdapter extends RecyclerView.Adapter {


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView nome_titular_tv;
            private TextView cpf_titular_tv;

            public ListViewHolder(View itemView) {
                super(itemView);
                nome_titular_tv = (TextView) itemView.findViewById(R.id.nome_titular_EditText);
                cpf_titular_tv =  (TextView) itemView.findViewById(R.id.cpf_titular_EditText);
                itemView.setOnClickListener(this);
            }

            /*
             * Utilizado para inserir dados do banco no Recycler View

            public void bindView(int position) {
                nome_titular_tv.setText(ALGUMA CLASSE[POSITION]);
                cpf_titular.tv.setText(ALGUMA CLASSE[POSITION]);
            }

             */

            public void onClick(View view){

            }
        }
    }
}
