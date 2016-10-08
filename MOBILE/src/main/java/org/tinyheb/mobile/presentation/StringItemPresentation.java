package org.tinyheb.mobile.presentation;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

public class StringItemPresentation  implements ItemPresentationModel<String>
{
	protected String value;

	public String getValue()
	{
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void updateData(String bean, ItemContext itemContext) {
		value = bean;
	}
}