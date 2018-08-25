package read.eyydf.terr.jokecollection.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liang on 2018/8/2.
 */

public class MyRecyclerView extends RecyclerView {
    private boolean isIntercept;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsIntercept(boolean isIntercept) {
        this.isIntercept = isIntercept;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        //if (isIntercept)
            return false;
        //else
           // return super.onInterceptTouchEvent(e);
    }
}
