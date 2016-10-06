package org.tinyheb.data.sqlite;

import java.sql.SQLException;
import java.util.HashMap;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.mobile.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class  SQLiteDBHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "tinyheb.db";
	HashMap<Class<?>, RuntimeExceptionDao<?, ?>> daomap = new HashMap<Class<?>, RuntimeExceptionDao<?, ?>>(2);



	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 14;	

	public SQLiteDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}


	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(SQLiteDBHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Patron.class);
			TableUtils.createTable(connectionSource, HealthInsurance.class);
		} catch (SQLException e) {
			Log.e(SQLiteDBHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(SQLiteDBHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Patron.class, true);
			TableUtils.dropTable(connectionSource, HealthInsurance.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(SQLiteDBHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
	 * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
	 * @param <T>
	 * @throws SQLException 
	 */
	public <T> RuntimeExceptionDao<?, ?> getDao(T classtype) {
		if (!daomap.containsKey(classtype.getClass())) {
			daomap.put(classtype.getClass(), getRuntimeExceptionDao(classtype.getClass()));
		}
		return daomap.get(classtype.getClass());
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		daomap = null;
	}

	public void deleteDB(Context context) {
		context.deleteDatabase(DATABASE_NAME);
	}

}
