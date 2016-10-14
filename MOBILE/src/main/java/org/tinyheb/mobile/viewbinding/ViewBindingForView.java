package org.tinyheb.mobile.viewbinding;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;

import android.view.View;

@ViewBinding(simpleOneWayProperties = {"enabled"})
public class ViewBindingForView extends CustomViewBinding<View> {
}
