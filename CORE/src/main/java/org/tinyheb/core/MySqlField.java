package org.tinyheb.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.j256.ormlite.field.DatabaseField;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MySqlField {
	DatabaseField ormfield();
}
