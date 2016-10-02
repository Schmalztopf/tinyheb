package org.tinyheb.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.j256.ormlite.table.DatabaseTable;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MySqlTable {
	DatabaseTable ormtable();
}
