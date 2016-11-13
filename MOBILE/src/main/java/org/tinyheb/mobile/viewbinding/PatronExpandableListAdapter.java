package org.tinyheb.mobile.viewbinding;

import java.util.HashMap;
import java.util.List;

import org.tinyheb.core.Patron;
import org.tinyheb.mobile.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class PatronExpandableListAdapter extends BaseExpandableListAdapter {
	private Context _context;
	private List<Patron> _listDataHeader; 
	private HashMap<Patron, List<String>> _listDataChild;

	public PatronExpandableListAdapter(Context context, List<Patron> listDataHeader,
			HashMap<Patron, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String child = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.listitem_singletext, parent, false);
		}

		TextView txtId = (TextView) convertView.findViewById(R.id.lblListItem);

		txtId.setText(child);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return _listDataHeader.get(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		Patron header = (Patron) getGroup(groupPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.listitem_id_name, parent, false);
		}

		if  ( header.getId() < 0)
			convertView.findViewById(R.id.vwCircle).setBackgroundResource(R.drawable.circle_red);
		else
			convertView.findViewById(R.id.vwCircle).setBackgroundResource(R.drawable.circle_green);

		((TextView) convertView.findViewById(R.id.lblId)).setText(String.format("%d", header.getId()));
		((TextView) convertView.findViewById(R.id.lblLastname)).setText(header.getLastname());
		((TextView) convertView.findViewById(R.id.lblFirstname)).setText(header.getFirstname());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
