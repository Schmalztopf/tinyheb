package org.tinyheb.api;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.Patron;
import org.tinyheb.core.TinyhebDataContainer;
import org.tinyheb.core.annotations.MySqlField;
import org.tinyheb.core.annotations.MySqlTable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.DatabaseTableConfig;



@Path("/tinyheb")
public class PatronService {

	String databaseUrl = Constants.dbUrl  + Constants.dbName + "?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull";
	ConnectionSource connectionSource;
	Dao<Patron, String> patronDao;
	Dao<HealthInsurance, String> insuranceDao;

	public PatronService() {
		try {
			connectionSource = new JdbcConnectionSource(databaseUrl, "putu", "");
			insuranceDao = DaoManager.createDao(connectionSource, getDatabaseTableConfig(HealthInsurance.class));
			patronDao = DaoManager.createDao(connectionSource, getDatabaseTableConfig(Patron.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public TinyhebDataContainer getAll() throws Exception {
		TinyhebDataContainer alldata = new TinyhebDataContainer();

		alldata.patrons = patronDao.queryForAll();
		alldata.insurances = insuranceDao.queryForAll();

		return alldata;
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/writepatron")
	public Patron writePatron(Patron insertPatron) throws JsonProcessingException {
		int newid = getMaxPatronID() + 1;

		try {
			if (insertPatron != null && insertPatron.getId()<=0) {
				insertPatron.setId(newid);
				patronDao.create(insertPatron);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return HTTP response 200 in case of success
		return insertPatron;
	}

	private int getMaxPatronID() {
		GenericRawResults<String[]> results;
		String[] result = null;
		try {
			QueryBuilder<Patron, String> qb = patronDao.queryBuilder();
			// select 2 aggregate functions as the return
			qb.selectRaw("MAX(`id`)");
			// the results will contain 2 string values for the min and max
			results = patronDao.queryRaw(qb.prepareStatementString());
			result = results.getFirstResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseUnsignedInt(result[0]);
	}


	private static <T> DatabaseTableConfig<T> getDatabaseTableConfig(Class<T> objectclass) {
		DatabaseTable ormtableannotation = objectclass.getAnnotation(MySqlTable.class).ormtable();

		List<DatabaseFieldConfig> fieldConfigs = new ArrayList<DatabaseFieldConfig>();

		for (Field field : objectclass.getDeclaredFields()) {
			MySqlField mysqlannotation = field.getAnnotation(MySqlField.class);
			if (mysqlannotation == null) {
				continue;
			}
			DatabaseFieldConfig fieldconfig = new DatabaseFieldConfig(field.getName());
			fieldconfig.setColumnName(mysqlannotation.ormfield().columnName());
			fieldconfig.setId(mysqlannotation.ormfield().id());
			fieldconfig.setUseGetSet(mysqlannotation.ormfield().useGetSet());
			fieldconfig.setGeneratedId(mysqlannotation.ormfield().generatedId());
			fieldconfig.setForeign(mysqlannotation.ormfield().foreign());
			fieldConfigs.add(fieldconfig);
		}

		DatabaseTableConfig<T> tableconf = new DatabaseTableConfig<T>(objectclass, ormtableannotation.tableName(), fieldConfigs);
		return tableconf;
	}
}
