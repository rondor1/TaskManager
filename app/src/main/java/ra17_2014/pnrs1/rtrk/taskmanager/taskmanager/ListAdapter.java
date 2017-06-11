package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.content.Context;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 5/28/2017.
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Task> mTaskList;
    private DatabaseHelper mDatabaseHelper;

    public ListAdapter(Context context)
    {
        mContext = context;
        mTaskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList()
    {
        return mTaskList;
    }

    public void addTask(Task mTask)
    {
        mTaskList.add(mTask);
        notifyDataSetChanged();
    }

    public void removeTask(int position)
    {
        mTaskList.remove(position);
        notifyDataSetChanged();
    }


    public void update(Task[] mTasks) {
        mTaskList.clear();
        if(mTasks != null) {
            for(Task mTask: mTasks) {
                mTaskList.add(mTask);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getCount()
    {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position)
    {
        Object mReturnValue = null;
        try
        {
            mReturnValue = mTaskList.get(position);
        }
        catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }

        return mReturnValue;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View mView = convertView;
        if(mView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            mView = mInflater.inflate(R.layout.task_element, null);
            ViewHolder mViewHolder = new ViewHolder();
            mViewHolder.mName = (TextView) mView.findViewById(R.id.task_name);
            mViewHolder.mDate = (TextView) mView.findViewById(R.id.task_date);
            mViewHolder.mTime = (TextView) mView.findViewById(R.id.task_time);
            mViewHolder.mImage = (ImageView) mView.findViewById(R.id.priority_image);
            mViewHolder.mAlarm = (ImageView) mView.findViewById(R.id.task_alarm);
            mViewHolder.mCheckbox = (CheckBox) mView.findViewById(R.id.task_done);
            mView.setTag(mViewHolder);
        }

        final Task mTask = (Task) getItem(position);
        final ViewHolder mViewHolder = (ViewHolder) mView.getTag();
        mViewHolder.mName.setText(mTask.mTaskName);
        mViewHolder.mDate.setText(mTask.mTaskDate);
        mViewHolder.mTime.setText(mTask.mTaskTime);
        mViewHolder.mImage.setImageResource(mTask.mTaskPriorityColor);
        mViewHolder.mAlarm.setImageResource(mTask.mTaskReminder);
        mViewHolder.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mViewHolder.mName.setPaintFlags(mViewHolder.mName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    Task mNewTask = MainActivity.mDatabaseHelper.readTask(String.valueOf(position+1));
                    Log.i("Robert", "read");
                    mNewTask.setTaskDone(1);
                    MainActivity.mDatabaseHelper.updateTask(mNewTask, String.valueOf(position+1));
                }
                else
                {
                    mViewHolder.mName.setPaintFlags(mViewHolder.mName.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    Task mNewTask = MainActivity.mDatabaseHelper.readTask(String.valueOf(position+1));
                    mNewTask.setTaskDone(0);
                    MainActivity.mDatabaseHelper.updateTask(mNewTask, String.valueOf(position+1));
                }
            }
        });
        return mView;
    }


    private class ViewHolder
    {
        public TextView mName = null;
        public TextView mDate = null;
        public TextView mTime = null;
        public ImageView mImage = null;
        public ImageView mAlarm = null;
        public CheckBox mCheckbox = null;
    }

}
