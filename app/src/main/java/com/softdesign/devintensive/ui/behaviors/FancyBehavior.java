package com.softdesign.devintensive.ui.behaviors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class FancyBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

  public FancyBehavior(Context context, AttributeSet attrs) {
           }
  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
  return dependency instanceof NestedScrollView;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
  CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
    if(dependency.getY()>=128 &&dependency.getY()<=224)lp.height=(int)dependency.getY()/2;
       dependency.setPadding(dependency.getPaddingLeft(), lp.height, dependency.getPaddingRight(), dependency.getPaddingBottom());
       child.setY(dependency.getY());
       System.out.println(lp.height);
       System.out.println(lp.getBehavior());
       child.setLayoutParams(lp);
       return true;
       }
 }