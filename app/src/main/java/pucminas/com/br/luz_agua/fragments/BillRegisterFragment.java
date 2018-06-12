package pucminas.com.br.luz_agua.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import pucminas.com.br.luz_agua.CompanyFactory;
import pucminas.com.br.luz_agua.ElectricityBillFactory;
import pucminas.com.br.luz_agua.IndividualFactory;
import pucminas.com.br.luz_agua.R;
import pucminas.com.br.luz_agua.MyEditTextDatePicker;
import pucminas.com.br.luz_agua.WaterBillFactory;
import pucminas.com.br.luz_agua.data.HolderData;
import pucminas.com.br.luz_agua.models.Bill;
import pucminas.com.br.luz_agua.models.Company;
import pucminas.com.br.luz_agua.models.Holder;
import pucminas.com.br.luz_agua.models.Individual;
import pucminas.com.br.luz_agua.utils.MaskUtil;

public class BillRegisterFragment extends Fragment{
    private enum BillSelectIds {
        WATER,
        ELECTRICITY
    }

    private enum HolderSelectIds {
        INDIVIDUAL,
        COMPANY
    }

    // View elements
    private EditText mEditDate;
    private EditText mEditHolder;
    private EditText mEditDOC;
    private EditText mEditLastConsumpt;
    private EditText mEditConsumpt;
    private TextInputLayout mLayoutTitular;
    private TextInputLayout mLayoutDOC;
    private TextWatcher mTextWatcherDOC;
    private TextWatcher mTextWatcherLastConsumpt;
    private TextWatcher mTextWatcherConsumpt;
    private Spinner mHolderSpinner;
    private Spinner mBillSpinner;

    //Instance Firebase
    private DatabaseReference mDatabase;

    public BillRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BillRegisterFragment.
     */
    public static BillRegisterFragment newInstance() {
        return new BillRegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onStart() {
        super.onStart();
        new MyEditTextDatePicker(this.getActivity() , R.id.input_data_conta);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_register, container, false);
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
        mEditHolder = view.findViewById(R.id.input_titular);
        mEditDate = view.findViewById(R.id.input_data_conta);

        mLayoutDOC = view.findViewById(R.id.input_layout_cpf_cnpj);
        mEditDOC = view.findViewById(R.id.input_cpf_cnpj);
        mLayoutTitular = view.findViewById(R.id.input_layout_titular);
        mTextWatcherDOC = MaskUtil.insert(mEditDOC, MaskUtil.MaskType.CPF);
        mEditDOC.addTextChangedListener(mTextWatcherDOC);

        mEditLastConsumpt = view.findViewById(R.id.input_consumo_anterior);
        mEditConsumpt = view.findViewById(R.id.input_consumo);
        mTextWatcherConsumpt = MaskUtil.insert(mEditConsumpt, MaskUtil.MaskType.CONTA_AGUA);
        mEditConsumpt.addTextChangedListener(mTextWatcherConsumpt);
        mTextWatcherLastConsumpt = MaskUtil.insert(mEditLastConsumpt, MaskUtil.MaskType.CONTA_AGUA);
        mEditLastConsumpt.addTextChangedListener(mTextWatcherLastConsumpt);

        // Spinner Tipo Pessoa
        mBillSpinner = view.findViewById(R.id.input_tipo_conta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.spinner_tipo_conta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mBillSpinner.setAdapter(adapter);
        mBillSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                final BillSelectIds ids = BillSelectIds.values()[(int) id];
                switch (ids) {
                    // Agua
                    case WATER:
                        mEditConsumpt.removeTextChangedListener(mTextWatcherConsumpt);
                        mTextWatcherConsumpt = MaskUtil.insert(mEditConsumpt, MaskUtil.MaskType.CONTA_AGUA);
                        mEditConsumpt.addTextChangedListener(mTextWatcherConsumpt);

                        mEditLastConsumpt.removeTextChangedListener(mTextWatcherLastConsumpt);
                        mTextWatcherLastConsumpt = MaskUtil.insert(mEditLastConsumpt, MaskUtil.MaskType.CONTA_AGUA);
                        mEditLastConsumpt.addTextChangedListener(mTextWatcherLastConsumpt);
                        break;

                    // Luz
                    case ELECTRICITY:
                        mEditConsumpt.removeTextChangedListener(mTextWatcherConsumpt);
                        mTextWatcherConsumpt = MaskUtil.insert(mEditConsumpt, MaskUtil.MaskType.CONTA_LUZ);
                        mEditConsumpt.addTextChangedListener(mTextWatcherConsumpt);

                        mEditLastConsumpt.removeTextChangedListener(mTextWatcherLastConsumpt);
                        mTextWatcherLastConsumpt = MaskUtil.insert(mEditLastConsumpt, MaskUtil.MaskType.CONTA_LUZ);
                        mEditLastConsumpt.addTextChangedListener(mTextWatcherLastConsumpt);
                        break;
                }

                mEditConsumpt.setText(mEditConsumpt.getText());
                mEditLastConsumpt.setText(mEditLastConsumpt.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // Spinner Tipo Pessoa
        mHolderSpinner = view.findViewById(R.id.input_tipo_pessoa);
        adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.spinner_tipo_pessoa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mHolderSpinner.setAdapter(adapter);
        mHolderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                final HolderSelectIds ids = HolderSelectIds.values()[(int) id];
                switch (ids) {
                    // CPF
                    case INDIVIDUAL:
                        mLayoutTitular.setHint(getText(R.string.nome_titular));
                        mLayoutDOC.setHint(getText(R.string.cpf));
                        mEditDOC.removeTextChangedListener(mTextWatcherDOC);
                        mTextWatcherDOC = MaskUtil.insert(mEditDOC, MaskUtil.MaskType.CPF);
                        mEditDOC.addTextChangedListener(mTextWatcherDOC);
                        break;

                    // CNPJ
                    case COMPANY:
                        mLayoutTitular.setHint(getText(R.string.razao_social));
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
            private String writeHolder(String name, String doc) {
                HolderSelectIds id = HolderSelectIds.values()[(int) mHolderSpinner.getSelectedItemId()];
                Holder holder = null;
                String child = "";

                switch (id) {
                    case INDIVIDUAL:
                        child = "pessoa_fisica";
                        holder = new IndividualFactory().createHolder();
                        if (name.contains(" ")) {
                            ((Individual) holder).setFirstName(name.substring(0, name.indexOf(' ')));
                            ((Individual) holder).setLastName(name.substring(name.indexOf(' ') + 1));
                        } else {
                            ((Individual) holder).setFirstName(name);
                            ((Individual) holder).setLastName("");
                        }

                        ((Individual) holder).setCPF(doc);
                        break;
                    case COMPANY:
                        child = "pessoa_juridica";
                        holder = new CompanyFactory().createHolder();
                        ((Company) holder).setCompanyName(name);
                        ((Company) holder).setCNPJ(doc);
                        break;
                }

                mDatabase.child(child).child(doc).setValue(holder);
                return child;
            }

            private void writeBill(String holderChild, String holderDoc,
                                   String date, double lastConsumpt, double consumpt) {
                String month = "";
                String year = "";
                if (date.length() > 0) {
                    month = date.substring(date.indexOf('/') + 1, date.lastIndexOf('/'));
                    year = date.substring(date.lastIndexOf('/') + 1);
                }

                String typeBill = "";
                Bill bill = null;
                BillSelectIds billId = BillSelectIds.values()[(int) mBillSpinner.getSelectedItemId()];
                switch (billId) {
                    case WATER:
                        bill = new WaterBillFactory().createAccount(month, year, lastConsumpt, consumpt);
                        typeBill = "agua";
                        break;
                    case ELECTRICITY:
                        bill = new ElectricityBillFactory().createAccount(month, year, lastConsumpt, consumpt);
                        typeBill = "luz";
                        break;
                }

                mDatabase.child("contas").child(typeBill).child(holderChild + "_" + holderDoc)
                        .push().setValue(bill);
            }

            @Override
            public void onClick(View view) {
                String holderName = mEditHolder.getText().toString().trim();
                String doc = MaskUtil.unmask(mEditDOC.getText().toString());
                String child = writeHolder(holderName, doc);

                String date = mEditDate.getText().toString();
                char decimalSeparator = DecimalFormatSymbols.getInstance(Locale.getDefault()).getDecimalSeparator();
                String aux = mEditLastConsumpt.getText().toString()
                        .replaceAll("[^0-9" + decimalSeparator + "]", "");
                aux = aux.replace(decimalSeparator, '.');

                double lastConsumpt = Double.parseDouble(aux);

                aux = mEditConsumpt.getText().toString()
                        .replaceAll("[^0-9" + decimalSeparator + "]", "");
                aux = aux.replace(decimalSeparator, '.');

                double consumpt = Double.parseDouble(aux);

                writeBill(child, doc, date, lastConsumpt, consumpt);
            }
        });
    }
}
