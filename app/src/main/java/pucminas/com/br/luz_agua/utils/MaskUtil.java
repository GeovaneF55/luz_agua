package pucminas.com.br.luz_agua.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class MaskUtil {

    private static final String CPFMask = "###.###.###-##";
    private static final String CNPJMask = "##.###.###/####-##";
    public enum MaskType {
        CPF,
        CNPJ,
        CONTA_AGUA,
        CONTA_LUZ
    }

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insert(final EditText editText, final MaskType maskType) {
        return new TextWatcher() {

            boolean isUpdating;
            String oldValue = "";

            private String insertDocMask(String mask, String value) {
                String maskAux = "";
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && value.length() > oldValue.length()) || (m != '#' && value.length() < oldValue.length() && value.length() != i)) {
                        maskAux += m;
                        continue;
                    }

                    try {
                        maskAux += value.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }

                return maskAux;
            }

            private String insertDoubleMask(String value, int decimalDigits) {
                StringBuilder strBuilder=  new StringBuilder();
                int i;
                for (i = 0; i < value.length() - decimalDigits; i++) {
                    strBuilder.append(value.charAt(i));
                }

                strBuilder.append(".");
                for (; i < value.length(); i++) {
                    strBuilder.append(value.charAt(i));
                }

                Double v = Double.parseDouble(strBuilder.toString());
                return NumberFormat.getInstance(Locale.getDefault()).format(v);
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = MaskUtil.unmask(s.toString());
                String mask = "";
                int posSelection = 0;

                if (isUpdating) {
                    oldValue = value;
                    isUpdating = false;
                    return;
                }

                switch (maskType) {
                    case CPF:
                        mask = insertDocMask(CPFMask, value);
                        posSelection = mask.length();
                        break;
                    case CNPJ:
                        mask = insertDocMask(CNPJMask, value);
                        posSelection = mask.length();
                        break;
                    case CONTA_AGUA:
                        if (value.length() > 0) {
                            mask = insertDoubleMask(value, 3);
                            mask += " m³";
                            posSelection = mask.length() - 3;
                        }
                        break;
                    case CONTA_LUZ:
                        if (value.length() > 0) {
                            mask = insertDoubleMask(value, 3);
                            mask += " kW/h";
                            posSelection = mask.length() - 5;
                        }
                        break;
                }

                isUpdating = true;
                editText.setText(mask);
                editText.setSelection(posSelection);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) {}
        };
    }
}
