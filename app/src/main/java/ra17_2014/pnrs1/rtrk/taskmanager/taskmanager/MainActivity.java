package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static int ADD_TASK = 0;
    public static int EDIT_TASK = 1;

    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mAddNewTask = (Button) findViewById(R.id.newTask);
        Button mStatistics = (Button) findViewById(R.id.viewStatistics);

        mAddNewTask.setOnClickListener(this);
        mStatistics.setOnClickListener(this);

        mDatabaseHelper = new DatabaseHelper(this);
        Log.i("Database", "Database created!");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.newTask :
                Intent mTaskIntent = new Intent(this, CreateTask.class);
                startActivity(mTaskIntent);
                break;
            case R.id.viewStatistics :
                Intent mViewStatics = new Intent(this, ViewStatistics.class);
                startActivity(mViewStatics);
                break;
        }
    }

}
