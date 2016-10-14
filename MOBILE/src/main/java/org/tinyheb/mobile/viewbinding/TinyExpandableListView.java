package org.tinyheb.mobile.viewbinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.tinyheb.core.Patron;
import org.tinyheb.mobile.R;
import org.tinyheb.mobile.activity.PatronExpandableListAdapter;
import org.tinyheb.mobile.activity.PatronInsertActivity;
import org.tinyheb.mobile.service.WritePatronService;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

public class TinyExpandableListView extends ExpandableListView {
	List<Patron> source;
	final Context baseContext;

	public TinyExpandableListView(Context context, AttributeSet attrs) {
		this(context, attrs, R.layout.listitem_id_name, R.layout.listitem_singletext); 
	}

	public TinyExpandableListView(Context context, AttributeSet attrs, int itemlayoutid, int grouplayoutid) {
		super(context, attrs); 
		this.baseContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(grouplayoutid, this, false);

		setSource(new ArrayList<Patron>());
		
		this.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView a, View v, int groupPosition, int childPosition, long id) {
				Patron item = (Patron) a.getItemAtPosition(groupPosition);
				Intent intent;
				switch (childPosition) {
				case 0:
					intent = new Intent(baseContext, PatronInsertActivity.class);
					intent.putExtra("org.tinyheb.core.Patron", item.getId());
					baseContext.startActivity(intent);
					break;
				case 1:
					intent = new Intent(baseContext, WritePatronService.class);
					intent.putExtra("patron", item.getId());
					baseContext.startService(intent);
					break;
				}

				return false;
			}
		}); 
	}


	public void setSource(List<Patron> sourceitems) {
		this.source = sourceitems;
		HashMap<Patron, List<String>> listPatronDataChild = new HashMap<Patron, List<String>>();

		for (Patron patron : sourceitems) {
			List<String> PatronOptions = new ArrayList<String>();
			PatronOptions.add("Edit");
			PatronOptions.add("Write To Tinyheb");
			listPatronDataChild.put(patron, PatronOptions);
		}

		this.setAdapter(new PatronExpandableListAdapter(this.baseContext, source, listPatronDataChild));
	}

}
