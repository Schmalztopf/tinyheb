package org.tinyheb.mobile;

import android.view.View;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;

@ViewBinding(simpleOneWayProperties = {"enabled"})
public class ViewBindingForView extends CustomViewBinding<View> {
}