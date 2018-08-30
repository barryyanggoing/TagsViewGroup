package com.lrts.me.barryyangdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @desc:内容筛选
 * @author: BarryYang
 * @date:create on 2018/8/28 14:11
 */
public class ClassifyFilterView extends FrameLayout implements View.OnClickListener {

    private TagsViewGroup tagsViewGroup;
    private TextView animText;

    private List<Category> list;

    private int startMarginLeft, startMarginTop, selectedIndex;
    private long clickTime = 0;
    private int clickDely = 300;

    private int tabTopMargin = 20, tabBottomMargin = 0, tabLeftMargin = 0, tabRightMargin = 0;

    public ClassifyFilterView(@NonNull Context context) {
        this(context, null);
    }

    public ClassifyFilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassifyFilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClassifyFilterView);
        tabTopMargin = (int) typedArray.getDimension(R.styleable.ClassifyFilterView_tabTopMargin, tabTopMargin);
        tabBottomMargin = (int) typedArray.getDimension(R.styleable.ClassifyFilterView_tabBottomMargin, tabBottomMargin);
        tabLeftMargin = (int) typedArray.getDimension(R.styleable.ClassifyFilterView_tabLeftMargin, tabLeftMargin);
        tabRightMargin = (int) typedArray.getDimension(R.styleable.ClassifyFilterView_tabRightMargin, tabRightMargin);
        typedArray.recycle();
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_classify_filter, this, true);
        tagsViewGroup = findViewById(R.id.tagsViewGroupss);
    }

    public void setData(List<Category> list) {
        if (list == null || list.size() == 0) return;
        this.list = list;
        tagsViewGroup.removeAllViews();
        LinearLayout.LayoutParams layoutParams = getTabLayoutParams();
        for (int i = 0; i < list.size(); i++) {
            TextView tagTextView = new TextView(getContext());
            tagTextView.setText(list.get(i).getName());
            tagTextView.setTextAppearance(getContext(), R.style.style_classify_filter_tabs);
            tagTextView.setBackgroundResource(R.drawable.tab_selected);
            tagTextView.setTag(list.get(i).getId());
            tagTextView.setLayoutParams(layoutParams);
            tagTextView.setOnClickListener(this);
            tagsViewGroup.addView(tagTextView);
        }
        addAnimView(list);
        startMarginLeft = tagsViewGroup.getChildAt(0).getLeft();
        startMarginTop = tagsViewGroup.getChildAt(0).getTop();
        tagsViewGroup.getChildAt(0).setSelected(true);
    }

    private LinearLayout.LayoutParams getTabLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = tabTopMargin;
        layoutParams.bottomMargin = tabBottomMargin;
        layoutParams.rightMargin = tabRightMargin;
        layoutParams.leftMargin = tabLeftMargin;
        return layoutParams;
    }

    /**
     * 添加切换tab的view,用于切换tab动画
     *
     * @param list
     */
    private void addAnimView(List<Category> list) {
        LayoutParams layoutParams1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        animText = new TextView(getContext());
        animText.setText(list.get(0).getName());
        animText.setTextAppearance(getContext(), R.style.style_classify_filter_tabs);
        animText.setBackgroundResource(R.drawable.shape_classify_filter_tag_bg_normal);
        animText.setTextColor(getResources().getColor(R.color.transaction));
        animText.setLayoutParams(layoutParams1);
        addView(animText);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        if (System.currentTimeMillis() - clickTime < clickDely) return;
        clickTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            Category category = list.get(i);
            if (tag == category.getId() && selectedIndex != i) {
                selectedIndex = i;
                animText.setText(category.getName());
                animText.setBackgroundResource(R.drawable.shape_classify_filter_tag_bg_pressed);
                startAnim((TextView) tagsViewGroup.getChildAt(i), tagsViewGroup.getChildAt(i).getLeft(), tagsViewGroup.getChildAt(i).getTop());
            } else if (tag == category.getId() && selectedIndex == i) {
                tagsViewGroup.getChildAt(i).setSelected(true);
            } else {
                tagsViewGroup.getChildAt(i).setSelected(false);
            }
        }
    }

    private void startAnim(final TextView textViewSelected, final int endMarginLeft, final int endMarginTop) {
        try {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(animText, "translationX", startMarginLeft, endMarginLeft);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(animText, "translationY", startMarginTop, endMarginTop);
            animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
            animatorSet.setDuration(200);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animText.setBackgroundResource(R.drawable.shape_classify_filter_tag_bg_normal);
                    textViewSelected.setSelected(true);
                    startMarginLeft = endMarginLeft;
                    startMarginTop = endMarginTop;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animText != null) {
            animText.clearAnimation();
        }
    }
}
