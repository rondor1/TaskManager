package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ASUS on 6/4/2017.
 */

public class PieChart extends View {

    protected Paint mPaint;
    protected Paint mBackgroundPaint;
    protected Paint mTextPaint;
    protected RectF mRect;
    protected float mPercentageTarget = 0;
    protected float mPercentage = 0;
    protected boolean mPercentageSet = false;

    public PieChart(Context context)
    {
        super(context);
        initialize();
    }

    public PieChart(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        initialize();
    }

    public PieChart(Context context, AttributeSet attributeSet, int defStyle)
    {
        super(context,attributeSet,defStyle);
        initialize();
    }

    public Paint getPaint()
    {
        return mPaint;
    }

    public void setPercentageTarget(float percentage)
    {
        this.mPercentageTarget = percentage;
    }

    public void setPercentage()
    {
        if(mPercentage == mPercentageTarget || mPercentageTarget == 0)
        {
            mPercentageSet = true;
            this.mPercentage--;
        }
        this.mPercentage++;
        invalidate();
    }

    public void initialize()
    {
        mPaint = new Paint();
        mPaint.setColor(getContext().getResources().getColor(R.color.priorityHigh));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(getContext().getResources().getColor(R.color.blackBackground));
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(getContext().getResources().getColor(R.color.textColor));
        mTextPaint.setTextSize(30);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);

        mRect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        /*Draw background*/
        mRect.set(0, 0, getWidth(),getHeight());
        canvas.drawArc(mRect, -90, 360, true, mBackgroundPaint);
        canvas.drawArc(mRect, -90, 3.6f*mPercentage, true, mPaint);
        canvas.drawText(String.valueOf(mPercentage)+"%",getWidth()/2-30,getHeight()/2+10,mTextPaint);
        if(!mPercentageSet)
        {
            setPercentage();
        }
    }

}
