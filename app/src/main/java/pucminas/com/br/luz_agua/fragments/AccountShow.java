package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pucminas.com.br.luz_agua.R;

public class AccountShow extends Fragment {

    public AccountShow() {
        // Required empty public constructor
    }

    public static AccountShow newInstance(String param1, String param2) {
        return new AccountShow();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_show, container, false);

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
        //TODO
    }
}
