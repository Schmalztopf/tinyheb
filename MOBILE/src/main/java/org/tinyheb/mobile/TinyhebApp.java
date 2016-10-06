package org.tinyheb.mobile;

import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

import android.app.Application;

public class TinyhebApp extends Application {
	private BinderFactory reusableBinderFactory;

	@Override
	public void onCreate() {
		super.onCreate();

		reusableBinderFactory = new BinderFactoryBuilder()
				.build();
	}

	public BinderFactory getReusableBinderFactory() {
		return reusableBinderFactory;
	}
}
