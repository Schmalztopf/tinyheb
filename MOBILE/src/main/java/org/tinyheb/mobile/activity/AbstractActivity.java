package org.tinyheb.mobile.activity;

import android.app.Activity;
import android.view.View;

import java.util.Locale;
import java.util.TimeZone;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.tinyheb.mobile.TinyhebApp;
import org.tinyheb.mobile.data.sqlite.SQLiteDBHelper;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public abstract class AbstractActivity extends Activity {
	
	protected TimeZone currentTimezone = TimeZone.getTimeZone("Europe/Berlin");
	protected Locale currentLocale = new Locale("de-DE");

	protected SQLiteDBHelper dbHelper = null;
	
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
}
