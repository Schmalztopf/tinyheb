package org.tinyheb.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Editor;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.tinyheb.mobile.R;
import org.tinyheb.mobile.TinyhebApp;
import org.tinyheb.mobile.data.sqlite.SQLiteDBHelper;
import org.tinyheb.mobile.service.APIGetAllDataService;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public abstract class AbstractActivity extends Activity {
	
	protected TimeZone currentTimezone = TimeZone.getTimeZone("Europe/Berlin");
	protected Locale currentLocale = new Locale("de-DE");
	protected SQLiteDBHelper dbHelper = null;
	
	protected ArrayList<Intent> startedActivities = new ArrayList<Intent>();
	
	public void initializeContentView(int layoutId, Object presentationModel) {
		ViewBinder viewBinder = createViewBinder();
		View rootView = viewBinder.inflateAndBind(layoutId, presentationModel);
		setContentView(rootView);
	}

	private ViewBinder createViewBinder() {
		BinderFactory binderFactory = getReusableBinderFactory();
		return binderFactory.createViewBinder(this);
	}

	private BinderFactory getReusableBinderFactory() {
		BinderFactory binderFactory = getTinyhebApp().getReusableBinderFactory();
		return binderFactory;
	}

	public TinyhebApp getTinyhebApp() {
		return (TinyhebApp) getApplicationContext();
	}
	

	protected SQLiteDBHelper getHelper() {
		if (dbHelper == null) {
			dbHelper = (SQLiteDBHelper)OpenHelperManager.getHelper(this, SQLiteDBHelper.class);
		}
		return dbHelper;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.insertPatron:
	            insertPatrons();
	            return true;
	        case R.id.showPatrons:
	        	showPatrons();
	            return true;
	        case R.id.Settings:
	        	showSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	

	public void showPatrons() {
		Intent MasterdataIntent = new Intent(this, MasterDataPagerActivity.class);
		startActivity(MasterdataIntent);
	}

	public void insertPatrons() {
		startActivity(new Intent(this, PatronInsertActivity.class));
	}
	
	public void showSettings() {
		startActivity(new Intent(this, SettingsActivity.class));
	}

	public void startSync() {
		Intent intent = new Intent(this, APIGetAllDataService.class);
		startService(intent);
	}
}
