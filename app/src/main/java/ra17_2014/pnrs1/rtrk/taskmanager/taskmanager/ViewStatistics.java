package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ViewStatistics extends AppCompatActivity implements View.OnClickListener {


    private int mHighPriorityNum = 0;
    private int mMediumPriorityNum = 0;
    private int mLowPriorityNum = 0;
    private int mHighPriorityDone = 0;
    private int mMediumPriorityDone = 0;
    private int mLowPriorityDone = 0;
    private float mHighPriorityPercentage = 0;
    private float mMediumPriorityPercentage = 0;
    private float mLowPriorityPercentage = 0;


    protected Button mBackButton;
    protected ViewStatistics mViewStatistics;
    protected PieChart mHighPriorityPie;
    protected PieChart mMediumPriorityPie;
    protected PieChart mLowPriorityPie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistics);


        mViewStatistics = this;

        calculateStatistics();

        mBackButton = (Button) findViewById(R.id.backToMain);
        mHighPriorityPie = (PieChart) findViewById(R.id.highPie);
        mMediumPriorityPie = (PieChart) findViewById(R.id.mediumPie);
        mLowPriorityPie = (PieChart) findViewById(R.id.lowPie);

        mHighPriorityPie.setPercentageTarget(mHighPriorityPercentage*100);
        mMediumPriorityPie.setPercentageTarget(mMediumPriorityPercentage*100);
        mLowPriorityPie.setPercentageTarget(mLowPriorityPercentage*100);

        mMediumPriorityPie.getPaint().setColor(getResources().getColor(R.color.priorityMedium));
        mLowPriorityPie.getPaint().setColor(getResources().getColor(R.color.priorityLow));

        mBackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToMain:
                this.finish();
        }
    }

    private void calculateStatistics() {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

        Task[] mTasks = mDatabaseHelper.readTasks();
        for (Task mTask : mTasks) {
            switch (mTask.getTaskPriorityColor()) {
                case R.drawable.red:
                    mHighPriorityNum++;
                    if (mTask.isTaskDone() == 1)
                        mHighPriorityDone++;
                    break;

                case R.drawable.yellow:
                    mMediumPriorityNum++;
                    if (mTask.isTaskDone() == 1)
                        mMediumPriorityDone++;
                    break;

                case R.drawable.green:
                    mLowPriorityNum++;
                    if (mTask.isTaskDone() == 1)
                        mLowPriorityDone++;
                    break;
            }

            if(mHighPriorityDone == 0)
            {
                mHighPriorityPercentage = 0;
            }
            else
            {
                mHighPriorityPercentage = (float) mHighPriorityDone/mHighPriorityNum;
            }
            if(mMediumPriorityDone == 0)
            {
                mMediumPriorityPercentage = 0;
            }
            else
            {
                mMediumPriorityPercentage = (float) mMediumPriorityDone/mMediumPriorityNum ;
            }
            if(mLowPriorityDone== 0)
            {
                mLowPriorityPercentage = 0;
            }
            else
            {
                mLowPriorityPercentage = (float) mLowPriorityDone/mLowPriorityNum;
            }
        }
    }
}

