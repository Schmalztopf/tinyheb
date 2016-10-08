package org.tinyheb.mobile.data;

import java.io.IOException;
import java.sql.SQLException;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

	  private static final Class<?>[] classes = new Class[] {
		    Patron.class,
		    HealthInsurance.class,
		  };
	public static void main(String[] args) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt", classes);
	}
}
