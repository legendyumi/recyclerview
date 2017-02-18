package com.example.com.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ENZ on 2016/10/15.
 */

public class DividerItemDecoration02 extends RecyclerView.ItemDecoration {
    //获取布局的方向
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL = LinearLayoutManager.VERTICAL;


    //可以延长的
    private Drawable mDivider;

    private int mOrientation;


    //方向的判断
    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("你传递的方向参数好像有问题");
        }
        //方向赋值给对象mOrientation;
        mOrientation = orientation;
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    public DividerItemDecoration02(Context context, int orientation, int drawableId) {
        mDivider = ContextCompat.getDrawable(context, drawableId);
        setOrientation(orientation);
    }

    //重写onDraw（）方法。并且根据传递过来的方向来进行绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }

    /**
     * 绘制横向 item 分割线
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //左右的间距 ，left就是距离父类边界的距离，right同理
        final int left = parent.getPaddingLeft()+10;
        final int right = parent.getWidth() - parent.getPaddingRight()-10;
        //获取item数据的长度
        final int childCount = parent.getChildCount();
        //循环绘制分割线
        for (int i = 0; i < childCount; i++) {
            //
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
    /**
     * 绘制纵向 item 分割线
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        //左右的间距 ，top就是距离父类顶边界的距离，bottom是距离父类底部的边界距离
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        //循环绘制分割线
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /*
    * 获取分割线尺寸
    * getItemOffsets 中为 outRect 设置的4个方向的值，将被计算进所有 decoration 的尺寸中，而这个尺寸，被计入了 RecyclerView 每个 item view 的 padding 中
    * */
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL) {
            //在这个地方，我们才获取
            outRect.set(0, 0, 0,  mDivider.getIntrinsicWidth());
        }else{
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
