package org.tinyheb.mobile.activity;

import java.util.ArrayList;
import java.util.Calendar;

import org.tinyheb.core.DateFormatter;
import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.mobile.R;
import org.tinyheb.mobile.dialogs.MonthYearPickerDialog;
import org.tinyheb.mobile.presentation.PatronEditPresentation;

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

import com.j256.ormlite.dao.RuntimeExceptionDao;


public class PatronInsertActivity extends AbstractActivity {

	private AutoCompleteTextView InsuranceAgencyTextView;
	private TextView BirthdayTextview;
	private TextView ChildBirthdayTextview;
	private TextView ChildTimeOfBirthTextview;
	private TextView ValidUntilTextview;
	private ArrayList<HealthInsurance> insurances = null;
	private InsuranceAdapter adapter;
	private PatronEditPresentation insertPatron = new PatronEditPresentation();
	private PatronInsertActivity currentActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patron_insert);

		long patid = getIntent().getIntExtra("org.tinyheb.core.Patron", 0);
		RuntimeExceptionDao<PatronEditPresentation, Integer>  patronDao = getHelper().getRuntimeExceptionDao(PatronEditPresentation.class);
		if (patid == 0)
			insertPatron = new PatronEditPresentation();
		else
			insertPatron = patronDao.queryForEq("_id", patid).get(0);
		initializeContentView(R.layout.activity_patron_insert, insertPatron);

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
				Calendar cal = Calendar.getInstance(currentTimezone);
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
				Calendar cal = Calendar.getInstance(currentTimezone);
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
			Calendar pickedDate = Calendar.getInstance(currentTimezone);
			pickedDate.set(year, monthOfYear, dayOfMonth);
			BirthdayTextview.setText(DateFormatter.toDateString(pickedDate.getTime(), currentLocale) );
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
			ValidUntilTextview.setText(String.format("%02d%02d", monthOfYear, year % 100));
		}

	};
	
	private void bindInsuranceAgencyAutocompleteData() {
		InsuranceAgencyTextView = (AutoCompleteTextView) findViewById(R.id.IkInsuranceAgencyAutoCompleteTextView);
		getInsurances();
		adapter = new InsuranceAdapter(this, R.layout.row, this.insurances);
		InsuranceAgencyTextView.setAdapter(adapter);		
		InsuranceAgencyTextView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				HealthInsurance healthInsurance = (HealthInsurance) parent.getItemAtPosition(position);
				insertPatron.setInsurance(healthInsurance);
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

	public void savePatron(View view) {
		RuntimeExceptionDao<Patron, Integer>  patronDao = getHelper().getRuntimeExceptionDao(Patron.class);
		patronDao.deleteById(0);

		if (insertPatron.getId() == 0)
			insertPatron.setId((int) -(patronDao.countOf()+1));
		patronDao.createOrUpdate(insertPatron);
		finish();
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
							if(insurance.getName().toLowerCase(currentLocale).matches(".*" + constraint.toString().toLowerCase(currentLocale) + ".*")
									|| insurance.getIknumber().toLowerCase(currentLocale).matches(".*" + constraint.toString().toLowerCase(currentLocale) + ".*")){
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
