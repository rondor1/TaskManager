package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 5/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "Tasks";
    public static final String COLUMN_NAME = "TaskName";
    public static final String COLUMN_DATE = "TaskDate";
    public static final String COLUMN_TIME = "TaskTime";
    public static final String COLUMN_DESCRIPTION = "TaskDescription";
    public static final String COLUMN_REMINDER = "TaskReminder";
    public static final String COLUMN_PRIORITY = "TaskPriority";
    public static final String COLUMN_DONE = "TaskDone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_NAME + "("+COLUMN_NAME+" TEXT, "+ COLUMN_DATE+" TEXT, "
            +COLUMN_TIME+" TEXT, "+COLUMN_DESCRIPTION+" TEXT, "+ COLUMN_REMINDER+" INTEGER DEFAULT 0, "
            +COLUMN_PRIORITY+" TEXT, "+COLUMN_DONE+" INTEGER DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Task task)
    {
        SQLiteDatabase mSQLiteDatabase = getWritableDatabase();
        ContentValues mContentValues = new ContentValues();

        mContentValues.put(COLUMN_NAME, task.getTaskName());
        mContentValues.put(COLUMN_DATE, task.getTaskDate());
        mContentValues.put(COLUMN_TIME, task.getTaskTime());
        mContentValues.put(COLUMN_DESCRIPTION, task.getTaskDescription());
        mContentValues.put(COLUMN_REMINDER, task.isTaskReminder());
        mContentValues.put(COLUMN_PRIORITY, task.getTaskPriorityText());
        mContentValues.put(COLUMN_DONE, task.isTaskDone());

        mSQLiteDatabase.insert(TABLE_NAME, null, mContentValues);
        mSQLiteDatabase.close();
    }

}
