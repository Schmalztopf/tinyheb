package org.tinyheb.mobile;

import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;
import org.tinyheb.mobile.data.sqlite.SQLiteDBHelper;
import org.tinyheb.mobile.viewbinding.ExpandableListViewBinding;
import org.tinyheb.mobile.viewbinding.TinyExpandableListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.app.Application;

public class TinyhebApp extends Application {
	private BinderFactory reusableBinderFactory;
    private SQLiteDBHelper dbHelper = null;
    
	@Override
	public void onCreate() {
		super.onCreate();

		reusableBinderFactory = new BinderFactoryBuilder()
				.add(new ExpandableListViewBinding().forView(TinyExpandableListView.class))
				.build();
		reusableBinderFactory.createViewBinder(this);
	}
	
    @Override
    public void onTerminate() {
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

	public BinderFactory getReusableBinderFactory() {
		return reusableBinderFactory;
	}
	

    public SQLiteDBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = (SQLiteDBHelper) OpenHelperManager.getHelper(this, SQLiteDBHelper.class);
        }
        return dbHelper;
    }
}
