package org.tinyheb.mobile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.data.sqlite.SQLiteDBHelper;
import org.tinyheb.dialogs.MonthYearPickerDialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class PatronInsertActivity extends Activity {

	private AutoCompleteTextView InsuranceAgencyTextView;
	private TextView InsuranceAgencyDataTextView;
	private TextView BirthdayTextview;
	private TextView ChildBirthdayTextview;
	private TextView ChildTimeOfBirthTextview;
	private TextView ValidUntilTextview;
	private SQLiteDBHelper dbHelper = null;
	private ArrayList<HealthInsurance> insurances = null;
	private InsuranceAdapter adapter;
	private Patron insertPatron;
	private PatronInsertActivity currentActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patron_insert);

		Button btnAbort = (Button) findViewById(R.id.btnAbort);
		btnAbort.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				currentActivity.finish();
			}
		});
		
		bindInsuranceAgencyAutocompleteData();

		bindLayoutShowHIdeButton(R.id.AdressInputButton, R.id.AddressLayout);
		bindLayoutShowHIdeButton(R.id.InsuranceInputButton, R.id.InsuranceLayout);
		bindLayoutShowHIdeButton(R.id.ChildBirthInputButton, R.id.BirthInputLayout);

		BirthdayTextview = (TextView) findViewById(R.id.BirthdayLabelTextView);
		BirthdayTextview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
				DateDialog(BirthdayDateListener, 
						cal.get(Calendar.YEAR) - 30, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
						);
			}
		});
		ValidUntilTextview = (TextView) findViewById(R.id.IkInsuranceValidUntilEditText);
		ValidUntilTextview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MonthYearPickerDialog pd = new MonthYearPickerDialog();
				pd.setListener(InsuranceValidityDateListener);
				pd.show(getFragmentManager(), "MonthYearPickerDialog");
			}
		});


		ChildBirthdayTextview = (TextView) findViewById(R.id.ChildBirthLabelTextView);
		ChildBirthdayTextview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
				DateDialog(ChildBirthdayDateListener, 
						cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
						);
			}
		});

		ChildTimeOfBirthTextview = (TextView) findViewById(R.id.TimeOfBirthTextView);
		ChildTimeOfBirthTextview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TimeDialog(ChildTimeOfBirthTimeListener, 0, 0);
			}
		});
	}

	private void bindLayoutShowHIdeButton(int adressinputbuttonid, final int addresslayoutid) {
		Button ShowHideInputBtn = (Button) findViewById(adressinputbuttonid);
		ShowHideInputBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RelativeLayout Layout = (RelativeLayout) findViewById(addresslayoutid);
				if (Layout.getVisibility() == View.VISIBLE) {
					Layout.setVisibility(View.GONE);
				} else {
					Layout.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	public void DateDialog(DatePickerDialog.OnDateSetListener DateListener, int year, int month, int day){
		DatePickerDialog dpDialog=new DatePickerDialog(this, DateListener, year, month, day);
		dpDialog.show();
	}

	public void TimeDialog(TimePickerDialog.OnTimeSetListener TimeListener, int hour, int minute){
		TimePickerDialog tpDialog = new TimePickerDialog(this, TimeListener, hour, minute, true);
		tpDialog.show();
	}

	private DatePickerDialog.OnDateSetListener BirthdayDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			BirthdayTextview.setText(String.format("%02d. %02d. %04d" , dayOfMonth, (monthOfYear + 1), year));
		}
	};

	private DatePickerDialog.OnDateSetListener ChildBirthdayDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			ChildBirthdayTextview.setText(String.format("%02d. %02d. %04d" , dayOfMonth, (monthOfYear + 1), year));
		}
	};
	
	private TimePickerDialog.OnTimeSetListener ChildTimeOfBirthTimeListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hour, int minute) {
			ChildTimeOfBirthTextview.setText(String.format("%02d:%02d",hour, minute));
		}
	};

	private DatePickerDialog.OnDateSetListener InsuranceValidityDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			ValidUntilTextview.setText(String.format("%02d%02d", monthOfYear, String.valueOf(year).substring(2)));
		}

	};

	private void bindInsuranceAgencyAutocompleteData() {
		InsuranceAgencyTextView = (AutoCompleteTextView) findViewById(R.id.IkInsuranceAgencyAutoCompleteTextView);
		InsuranceAgencyDataTextView = (TextView) findViewById(R.id.InsuranceDataTextView);
		getInsurances();
		adapter = new InsuranceAdapter(this, R.layout.row, this.insurances);
		InsuranceAgencyTextView.setAdapter(adapter);		
		InsuranceAgencyTextView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				HealthInsurance healthInsurance = (HealthInsurance) parent.getItemAtPosition(position);
				InsuranceAgencyDataTextView.setText(healthInsurance.getIknumber() + " - " + healthInsurance.getName());
			}
		});
	}

	private void getInsurances() {
		ArrayList<HealthInsurance> returnList = new ArrayList<HealthInsurance>();
		RuntimeExceptionDao<HealthInsurance, String> insuranceDao = getHelper().getRuntimeExceptionDao(HealthInsurance.class);

		for (HealthInsurance insurance : insuranceDao.queryForAll()) {
			returnList.add(insurance);
		}
		this.insurances = returnList;
	}

	private SQLiteDBHelper getHelper() {
		if (dbHelper == null) {
			dbHelper = (SQLiteDBHelper)OpenHelperManager.getHelper(this, SQLiteDBHelper.class);
		}
		return dbHelper;
	}


	public void savePatron(View view) {
		getPatronFromUI();
	}

	private void getPatronFromUI () {
		insertPatron = new Patron();
		insertPatron.setFirstname(findViewById(R.id.FirstnameTextView).toString());
	}

	private class InsuranceAdapter extends ArrayAdapter<HealthInsurance> {

		private ArrayList<HealthInsurance> items;
		private ArrayList<HealthInsurance> itemsAll;
		private ArrayList<HealthInsurance> suggestions;
		private int viewResourceId;

		@SuppressWarnings("unchecked")
		public InsuranceAdapter(Context context, int textViewResourceId, ArrayList<HealthInsurance> items) {
			super(context, textViewResourceId, items);
			this.items = items;
			if (items instanceof ArrayList) {
				this.itemsAll =  (ArrayList<HealthInsurance>) items.clone();
			}
			this.suggestions = new ArrayList<HealthInsurance>();
			this.viewResourceId = textViewResourceId;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(this.viewResourceId, null);
			}
			HealthInsurance o = items.get(position);
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.toptext);
				TextView bt = (TextView) v.findViewById(R.id.bottomtext);
				if (tt != null) {
					tt.setText(o.getIknumber());                            }
				if(bt != null){
					bt.setText(o.getShortname());
				}
			}
			return v;
		}

		@Override 
		public Filter getFilter() {
			Filter filter = new Filter() {
				@Override
				protected FilterResults performFiltering(CharSequence constraint) {
					FilterResults filterResults = new Filter.FilterResults();
					if (constraint != null) {

						suggestions.clear();
						for (HealthInsurance insurance : itemsAll) {
							if(insurance.getName().toLowerCase(new Locale("de_DE")).matches(".*" + constraint.toString().toLowerCase(new Locale("de_DE")) + ".*")
									|| insurance.getIknumber().toLowerCase(new Locale("de_DE")).matches(".*" + constraint.toString().toLowerCase(new Locale("de_DE")) + ".*")){
								suggestions.add(insurance);
							}
						}

						filterResults.values = suggestions;
						filterResults.count = suggestions.size();
					}

					return filterResults ;
				}

				@Override
				protected void publishResults(CharSequence constraint, FilterResults results) {
					if(results != null && results.count > 0) {
						clear();
						for (Object insurance : (ArrayList<?>)results.values) {
							add((HealthInsurance) insurance);
						}
						notifyDataSetChanged();
					}
				}
			};

			return filter;
		}

	}





}
