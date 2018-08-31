package com.lrts.me.barryyangdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/***
 *
 * 标签显示
 *
 * @author barry
 *
 */
public class TagsViewGroup extends ViewGroup {

    public TagsViewGroup(Context context) {
        super(context);
    }

    public TagsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagsViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View children = getChildAt(i);
            measureChild(children, widthMeasureSpec, heightMeasureSpec);
        }
        int allChiledWidth = 0;
        int allChildHeight;
        int row = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            allChiledWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            if (allChiledWidth > widthSize) {
                allChiledWidth = getChildAt(0).getMeasuredWidth() + lp.rightMargin + lp.leftMargin +
                        getChildAt(i).getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                row++;
            }
            allChildHeight = (row + 1) * (getChildAt(0).getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            setMeasuredDimension(widthSize, allChildHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int allChiledWidth = 0;
        int allChildHeight;
        int everyRowWidthAll = 0;
        int row = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            allChiledWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            if (allChiledWidth > getWidth()) {
                allChiledWidth = getChildAt(0).getMeasuredWidth() + lp.rightMargin + lp.leftMargin +
                        getChildAt(i).getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                everyRowWidthAll = getChildAt(0).getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                row++;
            }
            allChildHeight = (row + 1) * (getChildAt(0).getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            child.layout(everyRowWidthAll + lp.leftMargin, allChildHeight / (row + 1) * row + lp.topMargin, everyRowWidthAll + lp.leftMargin + child.getMeasuredWidth(), allChildHeight - lp.bottomMargin);
            everyRowWidthAll += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            setMeasuredDimension(getWidth(), allChildHeight);
        }
    }

}
