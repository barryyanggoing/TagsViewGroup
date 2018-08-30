package com.lrts.me.barryyangdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/***
 *
 *
 * 标签显示
 *
 * @author barry
 *
 */
public class TagsViewGroup extends ViewGroup {

    private static final String TAG = "TagsViewGroup";

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
        int clomn = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            allChiledWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            // TODO: 2018/8/30  为什么没有加上第一个tag
            if (allChiledWidth > widthSize) {
                allChiledWidth = getChildAt(0).getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                clomn++;
            }
            allChildHeight = (clomn + 1) * (getChildAt(0).getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            setMeasuredDimension(widthSize, allChildHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int allChiledWidth = 0;
        int allChildHeight;
        int firstRowWidthAll = 0;
        int clomn = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            allChiledWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            // TODO: 2018/8/30  为什么没有加上第一个tag   这里第10个tab的时候才进入到这个判断中，也就是说少加了第10个这个tab的宽度
            if (allChiledWidth > getWidth()) {
                allChiledWidth = getChildAt(0).getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                firstRowWidthAll = getChildAt(0).getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                clomn++;
            }
            allChildHeight = (clomn + 1) * (getChildAt(0).getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
            child.layout(firstRowWidthAll + lp.leftMargin, allChildHeight / (clomn + 1) * clomn + lp.topMargin, firstRowWidthAll + lp.leftMargin + child.getMeasuredWidth(), allChildHeight - lp.bottomMargin);
            firstRowWidthAll += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            setMeasuredDimension(getWidth(), allChildHeight);
        }
    }

}
