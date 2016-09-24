package org.tinyheb.mobile;

import java.util.ArrayList;
import java.util.Vector;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.data.sqlite.SQLiteDBHelper;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MasterDataPagerActivity extends Activity {

	private Context mContext;
	private SQLiteDBHelper dbHelper = null;
	private ArrayList<Patron> patrons = null;
	private ArrayList<HealthInsurance> insurances = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master_data_pager);

		mContext = this;
		ListView listview1 = new ListView(mContext);
		ListView listview2 = new ListView(mContext);

		Vector<View> pages = new Vector<View>();

		pages.add(listview1);
		pages.add(listview2);

		ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
		PagerAdapter adapter = new CustomPagerAdapter(mContext,pages);
		vp.setAdapter(adapter);

		getPatrons();
		getInsurances();

		listview1.setAdapter(new PatronAdapter(mContext, R.layout.row, this.patrons));
		listview2.setAdapter(new InsuranceAdapter(mContext, R.layout.row, this.insurances));
	}

	private void getPatrons() {
		ArrayList<Patron> returnList = new ArrayList<Patron>();
		RuntimeExceptionDao<Patron, Integer> patronDao = null;

		patronDao = getHelper().getRuntimeExceptionDao(Patron.class);

		for (Patron patron : patronDao.queryForAll()) {
			returnList.add(patron);
		}
		this.patrons = returnList;
		//runOnUiThread(returnRes);
	}

	private void getInsurances() {
		ArrayList<HealthInsurance> returnList = new ArrayList<HealthInsurance>();
		RuntimeExceptionDao<HealthInsurance, String> patronDao = null;

		patronDao = getHelper().getRuntimeExceptionDao(HealthInsurance.class);

		for (HealthInsurance patron : patronDao.queryForAll()) {
			returnList.add(patron);
		}
		this.insurances = returnList;
		//runOnUiThread(returnRes);
	}

	@Override
	protected void onDestroy() {
		if (dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
		super.onDestroy();
	}

	private SQLiteDBHelper getHelper() {
		if (dbHelper == null) {
			dbHelper = (SQLiteDBHelper)OpenHelperManager.getHelper(this, SQLiteDBHelper.class);
		}
		return dbHelper;
	}

	public class CustomPagerAdapter extends PagerAdapter {

		private Vector<View> pages;

		public CustomPagerAdapter(Context context, Vector<View> pages) {
			super();
			this.pages=pages;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View page = pages.get(position);
			container.addView(page);
			return page;
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	private class PatronAdapter extends ArrayAdapter<Patron> {

		private ArrayList<Patron> items;

		public PatronAdapter(Context context, int textViewResourceId, ArrayList<Patron> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, null);
			}
			Patron o = items.get(position);
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.toptext);
				TextView bt = (TextView) v.findViewById(R.id.bottomtext);
				if (tt != null) {
					tt.setText(o.getLastname() + ", " + o.getFirstname());                            }
				if(bt != null){
					bt.setText("");
				}
			}
			return v;
		}
	}

	private class InsuranceAdapter extends ArrayAdapter<HealthInsurance> {

		private ArrayList<HealthInsurance> items;

		public InsuranceAdapter(Context context, int textViewResourceId, ArrayList<HealthInsurance> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, null);
			}
			HealthInsurance o = items.get(position);
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.toptext);
				TextView bt = (TextView) v.findViewById(R.id.bottomtext);
				if (tt != null) {
					tt.setText(o.getIknumber()); 
				}
				if(bt != null){
					bt.setText(o.getName());
				}
			}
			return v;
		}
	}

}
