package com.lrts.me.barryyangdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @desc:
 * @author: BarryYang
 * @date:create on 2018/8/28 09:33
 */
public class TagsViewGroup extends ViewGroup {

    private int mViewWidth;

    private int newHeight = 0;

    private int maxLine = 0;

    private boolean showExtraDots;//单行时，超出行数，是否显示省略号（...）

    private int otherViewWidth;

    private int screenWidth;

    private int bgColor = 0x00000000;//背景色

    public TagsViewGroup(Context context) {
        super(context);
    }

    public TagsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagsViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMaxLine(int pMaxLine) {
        this.maxLine = pMaxLine;
    }

    public void setShowExtraDots(boolean showExtraDots) {
        this.showExtraDots = showExtraDots;
    }

    public void setExtraData(int otherViewWidth, int screenWidth) {
        this.otherViewWidth = otherViewWidth;
        this.screenWidth = screenWidth;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        newHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        newHeight = 0;
        int count = getChildCount();
        int row = 0;
        int viewWidth = 0;
        int height = 0;

        int topMargin = 0;
        int bottomMargin = 0;
        int leftMargin = 0;
        int rightMargin = 0;

        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            if (child.getLayoutParams() instanceof android.widget.LinearLayout.LayoutParams) {
                android.widget.LinearLayout.LayoutParams layoutParams = (android.widget.LinearLayout.LayoutParams) child.getLayoutParams();
                topMargin = layoutParams.topMargin;
                bottomMargin = layoutParams.bottomMargin;
                leftMargin = layoutParams.leftMargin;
                rightMargin = layoutParams.rightMargin;
            }
            int width = child.getMeasuredWidth();
            height = child.getMeasuredHeight();

            mViewWidth = showExtraDots ? otherViewWidth == 0 ? mViewWidth : screenWidth - otherViewWidth : mViewWidth;

            if (viewWidth + width + leftMargin + rightMargin > mViewWidth) {
                if (showExtraDots && mViewWidth > 0) {
                    removeViews(i, count - i);
                    break;
                } else {
                    row++;
                    if (maxLine != 0 && row == maxLine) {
                        break;
                    }
                    viewWidth = 0;
                    newHeight += height + topMargin;//// += height + topMargin + bottomMargin
                }
            }
            child.layout(viewWidth + leftMargin, (height * row) + (topMargin * (row + 1)), viewWidth + width
                    + rightMargin, (height * (row + 1)) + (bottomMargin * (row + 1)));
            viewWidth += width + leftMargin + rightMargin;
        }
        setMeasuredDimension(mViewWidth, newHeight + height + topMargin + bottomMargin);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

}
