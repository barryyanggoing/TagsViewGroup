package com.lrts.me.barryyangdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * @desc:
 * @author: BarryYang
 * @date:create on 2018/8/28 09:33
 */
public class TagsViewGroup extends ViewGroup {

    private static final String TAG = "TagsViewGroup";

    private int mViewWidth;

    private int newHeight = 0;

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
            if (child.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();
                topMargin = layoutParams.topMargin;
                bottomMargin = layoutParams.bottomMargin;
                leftMargin = layoutParams.leftMargin;
                rightMargin = layoutParams.rightMargin;
            }

            int width = child.getMeasuredWidth();
            height = child.getMeasuredHeight();
            if (viewWidth + width + leftMargin + rightMargin > mViewWidth) {
                row++;
                viewWidth = 0;
                newHeight += height + topMargin;
            }

            child.layout(viewWidth + leftMargin, (height * row) + (topMargin * (row + 1)), viewWidth + width
                    + rightMargin, (height * (row + 1)) + (bottomMargin * (row + 1)));
            viewWidth += width + leftMargin + rightMargin;
        }
        setMeasuredDimension(mViewWidth, newHeight + height + topMargin + bottomMargin);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int width = getWidth();
//        int childCount = getChildCount();
//
//        int allChiledWidth = 0;
//        int allChiledLeft = 0;
//        int allChiledRight = 0;
//        int firstTagWidth = getChildAt(0).getMeasuredWidth();
//        int firstTagHeight = getChildAt(0).getMeasuredHeight();
//
//        int allRowWidth = firstTagWidth;
//        int row = 1;
//        int clomn = 1;
//
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//            int childWidth = child.getMeasuredWidth();
//            allChiledWidth += childWidth;
//            allChiledLeft += lp.leftMargin;
//            allChiledRight += lp.rightMargin;
//            //换行后的布局
//            if (allChiledWidth + allChiledLeft + allChiledRight > width) {
//                firstTagWidth += lp.rightMargin + lp.leftMargin;
//                int measuredWidth1 = getChildAt(i).getMeasuredWidth();
//                allRowWidth += measuredWidth1 + lp.rightMargin + lp.leftMargin;
//                if (allRowWidth > width) {
//                    clomn++;
//                    row = 1;
//                    allRowWidth = firstTagWidth + lp.leftMargin + lp.rightMargin;
//                } else {
//                    allRowWidth = allRowWidth - (measuredWidth1 + lp.rightMargin + lp.leftMargin);
//                }
//                child.layout((row + 1) * lp.leftMargin + row * lp.rightMargin + allRowWidth,
//                        clomn * firstTagHeight + (clomn + 1) * lp.topMargin + clomn * lp.bottomMargin,
//                        (row + 1) * lp.leftMargin + row * lp.rightMargin + allRowWidth + child.getMeasuredWidth(),
//                        clomn * firstTagHeight + (clomn + 1) * lp.topMargin + clomn * lp.bottomMargin + child.getMeasuredHeight());
//                row++;
//                allRowWidth += measuredWidth1 + lp.rightMargin + lp.leftMargin;
//            }
//        }
    }
}
