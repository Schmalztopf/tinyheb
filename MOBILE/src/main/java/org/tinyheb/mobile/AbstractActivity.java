package org.tinyheb.mobile;

import android.app.Activity;
import android.view.View;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;

public abstract class AbstractActivity extends Activity {
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
		BinderFactory binderFactory = ((AbstractActivity) getApplicationContext()).getReusableBinderFactory();
		return binderFactory;
	}
}
