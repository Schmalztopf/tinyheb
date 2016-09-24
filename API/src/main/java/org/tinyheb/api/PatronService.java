package org.tinyheb.api;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tinyheb.core.HealthInsurance;
import org.tinyheb.core.MySqlField;
import org.tinyheb.core.MySqlTable;
import org.tinyheb.core.Patron;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseFieldConfig;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.DatabaseTableConfig;

@Path("/tinyheb")
public class PatronService {

    String databaseUrl = "jdbc:mysql://rpi-fs01:3306/PRD_Hebamme?serverTimezone=UTC";
    ConnectionSource connectionSource;
    Dao<Patron, String> patronDao;
    Dao<HealthInsurance, String> insuranceDao;

    public PatronService() {
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl, "putu", "");
            patronDao = DaoManager.createDao(connectionSource, getDatabaseTableConfig(Patron.class));
            insuranceDao = DaoManager.createDao(connectionSource, getDatabaseTableConfig(HealthInsurance.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() throws Exception {
        TinyhebDataContainer alldata = new TinyhebDataContainer();

        alldata.patrons = patronDao.queryForAll();
        alldata.insurances = insuranceDao.queryForAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(alldata);
        return jsonInString;
    }

    @GET
    @Path("/getpatrons")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patron> getPatrons() throws Exception {
        return patronDao.queryForAll();
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
            fieldConfigs.add(fieldconfig);
        }

        DatabaseTableConfig<T> tableconf = new DatabaseTableConfig<T>(objectclass, ormtableannotation.tableName(), fieldConfigs);
        return tableconf;

    }

    public class TinyhebDataContainer {

        public List<Patron> patrons;
        public List<HealthInsurance> insurances;
    }
}
