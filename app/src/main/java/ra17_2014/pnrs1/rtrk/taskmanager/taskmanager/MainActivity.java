package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    public static int ADD_TASK = 0;
    public static int EDIT_TASK = 1;
    public static int ADD = 2;
    public static int EDIT = 3;
    public static int mPosition;

    public static DatabaseHelper mDatabaseHelper;
    private ListAdapter mListAdapter;

    public static ArrayList<Task> mTaskList;

    /*Service stuff*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mAddNewTask = (Button) findViewById(R.id.newTask);
        Button mStatistics = (Button) findViewById(R.id.viewStatistics);

        ListView mListView = (ListView) findViewById(R.id.listView);
        mListAdapter = new ListAdapter(MainActivity.this);

        mTaskList = mListAdapter.getTaskList();


        mAddNewTask.setOnClickListener(this);
        mStatistics.setOnClickListener(this);

        mDatabaseHelper = new DatabaseHelper(this);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mTaskIntent = new Intent(MainActivity.this, CreateTask.class);

                mPosition = position;

                mTaskIntent.putExtra(getString(R.string.leftButtonText), getString(R.string.save));
                mTaskIntent.putExtra(getString(R.string.rightButtonText), getString(R.string.delete));
                mTaskIntent.putExtra(getString(R.string.taskPosition), position+1);
                mTaskIntent.putExtra(getString(R.string.typeOfTask), EDIT);
                startActivityForResult(mTaskIntent, EDIT_TASK);
                return true;
            }
        });

    }

    @Override
    public void onResume()
    {
        super.onResume();

        Task[] mTasks = mDatabaseHelper.readTasks();
        mListAdapter.update(mTasks);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.newTask :
                Intent mTaskIntent = new Intent(this, CreateTask.class);
                mTaskIntent.putExtra(getString(R.string.leftButtonText), getString(R.string.add));
                mTaskIntent.putExtra(getString(R.string.rightButtonText), getText(R.string.cancel));
                mTaskIntent.putExtra(getString(R.string.typeOfTask), ADD);
                startActivityForResult(mTaskIntent, ADD_TASK);
                break;

            case R.id.viewStatistics :
                Intent mViewStaticsIntent = new Intent(this, ViewStatistics.class);
                startActivity(mViewStaticsIntent);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ADD_TASK && resultCode == RESULT_OK)
        {
            Bundle mBundle = data.getBundleExtra(getString(R.string.passedBundle));
            Task mTask = (Task) mBundle.get(getString(R.string.passedTask));
            mListAdapter.addTask(mTask);
            mTask.setTaskID(mListAdapter.getCount());
            mDatabaseHelper.insert(mTask);
            Task[] mTasks = mDatabaseHelper.readTasks();
            mListAdapter.update(mTasks);
            Log.i("Robert", "task back");



        }
        else if(requestCode == EDIT_TASK && RESULT_FIRST_USER == resultCode)
        {
            Bundle mBundle = data.getBundleExtra(getString(R.string.passedBundle));
            Task mTask = (Task) mBundle.get(getString(R.string.passedTask));
            mDatabaseHelper.updateTask(mTask, String.valueOf(mTask.getTaskID()));
            Task[] mTasks = mDatabaseHelper.readTasks();
            mListAdapter.update(mTasks);

        }
        else if(requestCode == EDIT_TASK && resultCode == RESULT_CANCELED)
        {
            mDatabaseHelper.deleteTask(String.valueOf(data.getExtras().
                    getInt(getString(R.string.taskPosition))));
            if(mListAdapter.getCount() > 1)
            {
                Task[] mTasks = mDatabaseHelper.readTasks();
                for(Task mTask : mTasks)
                {
                    if(mTask.getTaskID() > mPosition)
                    {
                        mTask.setTaskID(mTask.getTaskID() -1);
                        mDatabaseHelper.updateTask(mTask,String.valueOf(mTask.getTaskID()+1));
                    }
                }
            }
            Task[] mTasks = mDatabaseHelper.readTasks();
            mListAdapter.update(mTasks);
        }
    }


}
