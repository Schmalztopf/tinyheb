package org.tinyheb.mobile.activity;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.core.TinyhebDataContainer;
import org.tinyheb.mobile.R;
import org.tinyheb.mobile.data.sqlite.SQLiteDBHelper;
import org.tinyheb.mobile.viewbinding.TinyExpandableListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MasterDataPagerActivity extends AbstractActivity {

	private SQLiteDBHelper dbHelper = null;

	TinyhebDataContainer masterdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master_data_pager);
		LocalBroadcastManager.getInstance(this).registerReceiver(
				mMessageReceiver, new IntentFilter("TinyhebPatronUpdate"));

		masterdata = new TinyhebDataContainer();
		try {
			getPatrons();
			getInsurances();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		initializeContentView(R.layout.activity_master_data_pager, masterdata);


		TinyExpandableListView listview1 = (TinyExpandableListView) findViewById(R.id.PatronsListView);
		ListView listview2 = new ListView(this);

		Vector<View> pages = new Vector<View>();

		pages.add(listview1);
		pages.add(listview2);

		//		ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
		//		PagerAdapter adapter = new CustomPagerAdapter(this, pages);
		//		vp.setAdapter(adapter);

		listview2.setAdapter(new InsuranceAdapter(this, R.layout.row, masterdata.insurances));

	}

	private void getPatrons() throws SQLException {
		RuntimeExceptionDao<Patron, Integer> patronDao = 
				getHelper().getRuntimeExceptionDao(Patron.class);
		QueryBuilder<Patron, Integer> qb = patronDao.queryBuilder();
		qb.orderBy("lastname", true);
		qb.orderBy("firstname", true);
		masterdata.patrons = qb.query();
	}

	private void getInsurances() throws SQLException {
		RuntimeExceptionDao<HealthInsurance, String> insuranceDao = 
				getHelper().getRuntimeExceptionDao(HealthInsurance.class);
		
		QueryBuilder<HealthInsurance, String> qb = insuranceDao.queryBuilder();
		qb.orderBy("name", true);

		masterdata.insurances = qb.query();
	}

	@Override
	protected void onDestroy() {
		if (dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
		super.onDestroy();
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

	private class InsuranceAdapter extends ArrayAdapter<HealthInsurance> {

		private List<HealthInsurance> items;

		public InsuranceAdapter(Context context, int textViewResourceId, List<HealthInsurance> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, parent, false);
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

	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				getPatrons();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
}
