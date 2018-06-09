package pucminas.com.br.luz_agua;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyEditTextDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText _editText;
    private int _day;
    private int _month;
    private int _year;
    private Context _context;

    public MyEditTextDatePicker(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _year = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        try {
            updateDisplay();
        } catch (ParseException e) {
            this._editText.setError(null);
        }
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    // updates the date in the birth date EditText
    private void updateDisplay() throws ParseException {

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder stringBuilder = new StringBuilder().append(_day).append("/").append(_month + 1).append("/").append(_year).append(" ");
        Date data = formatador.parse(stringBuilder.toString());

        this._editText.setText(formatador.format(data));
        this._editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("/").append(_month + 1).append("/").append(_year).append(" "));
        this._editText.setError(null);
    }
}
