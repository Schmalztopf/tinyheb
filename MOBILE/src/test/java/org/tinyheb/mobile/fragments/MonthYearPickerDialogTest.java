package org.tinyheb.mobile.fragments;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.tinyheb.mobile.R;
import org.tinyheb.mobile.activity.MainActivity;
import org.tinyheb.test.TinyhebTestRunner;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.widget.NumberPicker;

@Config(sdk = 23)
@RunWith(TinyhebTestRunner.class)
public class MonthYearPickerDialogTest {
	
	@InjectMocks
	private MonthYearPickerDialog monthYearPickerDialog;
	
	private Activity activity;

	@BeforeMethod
	public void setUp() throws Exception {
		if (activity == null) {
			activity = Robolectric.buildActivity(Activity.class).create().start().visible().get();
		}
		monthYearPickerDialog = new MonthYearPickerDialog();
		startFragment(monthYearPickerDialog);
	}

	@Test
	public void testSetMonthRange() throws Exception {
		monthYearPickerDialog.setMonthRange(1, 12, 3);
		NumberPicker monthPicker = (NumberPicker) monthYearPickerDialog.getMonthPicker();
		
		AssertJUnit.assertEquals(1, monthPicker.getMinValue());
		AssertJUnit.assertEquals(12, monthPicker.getMaxValue());
		AssertJUnit.assertEquals(3, monthPicker.getValue());
	}
	
	@Test
	public void testSetYearRange() throws Exception {
		monthYearPickerDialog.setYearRange(1980, 2025, 2000);
		NumberPicker yearPicker = (NumberPicker) monthYearPickerDialog.getYearPicker();
		
		AssertJUnit.assertEquals(1980, yearPicker.getMinValue());
		AssertJUnit.assertEquals(2025, yearPicker.getMaxValue());
		AssertJUnit.assertEquals(2000, yearPicker.getValue());
	}


	private void startFragment( Fragment fragment ) {
		FragmentManager fragmentManager = activity.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(fragment, null );
		fragmentTransaction.commit();
	}

}
