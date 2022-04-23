package com.visionvera.library.widget.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;

/**
 * author lilongfeng
 * date 2019/6/19
 * recyclerview的分割线
 * https://www.jianshu.com/p/b46a4ff7c10a
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;
    private int color;
    private Paint dividePaint;
    private SpaceType mSpaceType=SpaceType.Only_top;
    public SpaceItemDecoration(int mSpace) {
        this.mSpace = ConvertUtils.dp2px(mSpace);
    }

    public SpaceItemDecoration(int space,SpaceType spaceType){
        mSpace=ConvertUtils.dp2px(space);
        mSpaceType=spaceType;
    }



    public SpaceItemDecoration(int mSpace, int divideColor) {
        this.mSpace = mSpace;
        this.color = divideColor;
        dividePaint=new Paint();
        dividePaint.setColor(color);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mSpaceType==SpaceType.Only_top){
            if (parent.getChildAdapterPosition(view) >= 0) {
                outRect.top = mSpace;
            }

        }else if (mSpaceType==SpaceType.Only_bottom){
            if (parent.getChildAdapterPosition(view) >= 0) {
                outRect.bottom = mSpace;
            }
        }else if (mSpaceType==SpaceType.Top_bottom){
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mSpace;
                outRect.bottom=mSpace;
            }else{
                outRect.bottom=mSpace;
            }
        }


    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (color==0){
            //不做设置
        }else{
            int childCount=parent.getChildCount();
            int left=parent.getPaddingLeft();
            int right=parent.getWidth()-parent.getPaddingRight();
            for (int i = 0; i < childCount; i++) {
                View view=parent.getChildAt(i);
                float top=view.getTop();
                float bottom=view.getTop()+mSpace;
                c.drawRect(left,top,right,bottom,dividePaint);
            }
        }

    }

    public enum SpaceType{
        Top_bottom,
        Only_top,
        Only_bottom
    }

}
