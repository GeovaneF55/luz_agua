package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.utils.MaskUtil;

public class AccountRegister extends Fragment{
    private EditText mEditDOC;
    private TextInputLayout mLayoutDOC;
    private TextWatcher mTextWatcherDOC;

    public AccountRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AccountRegister.
     */
    public static AccountRegister newInstance() {
        return new AccountRegister();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        new MyEditTextDatePicker(this.getActivity() , R.id.input_data_conta);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_register, container, false);

        createComponents(view);

        return view;
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

    public void createComponents(View view){
        mLayoutDOC = view.findViewById(R.id.input_layout_cpf_cnpj);
        mEditDOC = view.findViewById(R.id.input_cpf_cnpj);
        mTextWatcherDOC = MaskUtil.insert(mEditDOC, MaskUtil.MaskType.CPF);
        mEditDOC.addTextChangedListener(mTextWatcherDOC);

        // Spinner Tipo Pessoa
        Spinner spinner = view.findViewById(R.id.input_tipo_conta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.spinner_tipo_conta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // Spinner Tipo Pessoa
        spinner = view.findViewById(R.id.input_tipo_pessoa);
        adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.spinner_tipo_pessoa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                switch ((int) id) {
                    // CPF
                    case 0:
                        mEditDOC.setHint(getText(R.string.cpf));
                        mLayoutDOC.setHint(getText(R.string.cpf));
                        mEditDOC.removeTextChangedListener(mTextWatcherDOC);
                        mTextWatcherDOC = MaskUtil.insert(mEditDOC, MaskUtil.MaskType.CPF);
                        mEditDOC.addTextChangedListener(mTextWatcherDOC);
                        break;

                    // CNPJ
                    case 1:
                        mEditDOC.setHint(getText(R.string.cnpj));
                        mLayoutDOC.setHint(getText(R.string.cnpj));
                        mEditDOC.removeTextChangedListener(mTextWatcherDOC);
                        mTextWatcherDOC = MaskUtil.insert(mEditDOC, MaskUtil.MaskType.CNPJ);
                        mEditDOC.addTextChangedListener(mTextWatcherDOC);
                        break;
                }

                mEditDOC.setText(mEditDOC.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {  }
        });

        FloatingActionButton fab = view.findViewById(R.id.new_conta);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Geovane lindo!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }
}
