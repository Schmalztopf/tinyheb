package org.tinyheb.mobile.fragments;

import java.util.Calendar;

import org.tinyheb.mobile.R;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public class MonthYearPickerDialog  extends DialogFragment {

	  private static final int MAX_YEAR = 2099;
	  private View dialog;
	  private NumberPicker monthPicker;
	  private NumberPicker yearPicker;
	  private DatePickerDialog.OnDateSetListener listener;

	  public void setListener(DatePickerDialog.OnDateSetListener listener) {
	    this.listener = listener;
	  }

	  @Override
	  public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    Calendar cal = Calendar.getInstance();
	    dialog = inflater.inflate(R.layout.date_picker_dialog, null);
	    yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);
	    
	    setMonthRange(1, 12, cal.get(Calendar.MONTH) + 1);
	    setYearRange(cal.get(Calendar.YEAR), MAX_YEAR, cal.get(Calendar.YEAR));

	    builder.setView(dialog)
	        // Add action buttons
	        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	          @Override
	          public void onClick(DialogInterface dialog, int id) {
	            listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), 0);
	          }
	        })
	        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int id) {
	            MonthYearPickerDialog.this.getDialog().cancel();
	          }
	        });
	    return builder.create();
	  }

	public void setMonthRange(int min, int max, int selected) {

	    monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
	    monthPicker.setMinValue(min);
	    monthPicker.setMaxValue(max);
	    monthPicker.setValue(selected);
	}
	
	public void setYearRange(int min, int max, int selected) {
		yearPicker.setMinValue(min);
		yearPicker.setMaxValue(max);
		yearPicker.setValue(selected);
	}

	public NumberPicker getMonthPicker() {
		return monthPicker;
	}

	public NumberPicker getYearPicker() {
		return yearPicker;
	}
}
