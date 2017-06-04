package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASUS on 5/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "Tasks";
    public static final String COLUMN_ID= "TaskID";
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
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("+COLUMN_ID+" INT, "+COLUMN_NAME+" TEXT, "
                + COLUMN_DATE+" TEXT, "+COLUMN_TIME+" TEXT, "+COLUMN_DESCRIPTION+" TEXT, "
                + COLUMN_REMINDER+" INT, "+COLUMN_PRIORITY+" INT, "
                +COLUMN_DONE+" INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Task task)
    {
        SQLiteDatabase mSQLiteDatabase = getWritableDatabase();
        ContentValues mContentValues = new ContentValues();

        mContentValues.put(COLUMN_ID, task.getTaskID());
        mContentValues.put(COLUMN_NAME, task.getTaskName());
        mContentValues.put(COLUMN_DATE, task.getTaskDate());
        mContentValues.put(COLUMN_TIME, task.getTaskTime());
        mContentValues.put(COLUMN_DESCRIPTION, task.getTaskDescription());
        mContentValues.put(COLUMN_REMINDER, task.isTaskReminder());
        mContentValues.put(COLUMN_PRIORITY, task.getTaskPriorityColor());
        mContentValues.put(COLUMN_DONE, task.isTaskDone());

        mSQLiteDatabase.insert(TABLE_NAME, null, mContentValues);
        mSQLiteDatabase.close();
    }

    public Task readTask(String id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=?",
                new String[] {id}, null, null, null);
        cursor.moveToFirst();
        Task mTask = createTask(cursor);

        close();
        return mTask;
    }



    public Task[] readTasks()
    {
        SQLiteDatabase mSQLiteDatabase = getReadableDatabase();
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);

        Task[] tasks = new Task[mCursor.getCount()];
        int i = 0;
        for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            tasks[i++] = createTask(mCursor);
        }

        close();
        return tasks;
    }



    public void deleteTask(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[] {id});
        close();
    }


    public void updateTask(Task task, String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues mContentValues = new ContentValues();

        mContentValues.put(COLUMN_ID, task.getTaskID());
        mContentValues.put(COLUMN_NAME, task.getTaskName());
        mContentValues.put(COLUMN_DATE, task.getTaskDate());
        mContentValues.put(COLUMN_TIME, task.getTaskTime());
        mContentValues.put(COLUMN_DESCRIPTION, task.getTaskDescription());
        mContentValues.put(COLUMN_REMINDER, task.isTaskReminder());
        mContentValues.put(COLUMN_PRIORITY, task.getTaskPriorityColor());
        mContentValues.put(COLUMN_DONE, task.isTaskDone());

        db.update(TABLE_NAME,mContentValues, "TaskID=?",new String[] {id});
        db.close();
    }


    public Task createTask(Cursor cursor)
    {
        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
        String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
        int reminder = cursor.getInt(cursor.getColumnIndex(COLUMN_REMINDER));
        int color = cursor.getInt(cursor.getColumnIndex(COLUMN_PRIORITY));
        int done = cursor.getInt(cursor.getColumnIndex(COLUMN_DONE));
        Task mTask = new Task(name, description, date,time, reminder, color);
        mTask.setTaskDone(done);
        mTask.setTaskID(id);
        return mTask;
    }

}
