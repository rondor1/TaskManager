package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import java.io.Serializable;

/**
 * Created by ASUS on 5/28/2017.
 */

public class Task  implements Serializable{
    public String mTaskName;
    public String mTaskDescription;
    public String mTaskDate;
    public String mTaskTime;
    public int mTaskID;
    public int mTaskReminder;
    public int mTaskPriorityColor;
    public int mTaskDone;

    public Task(String name, String description, String date, String time, int reminder,
                int priorityColor)
    {
        mTaskID = 0;
        mTaskName = name;
        mTaskDescription = description;
        mTaskDate = date;
        mTaskTime = time;
        mTaskReminder = reminder;
        mTaskPriorityColor = priorityColor;
        mTaskDone = 0;
    }

    public void setTaskName(String mTaskName) {
        this.mTaskName = mTaskName;
    }

    public void setTaskReminder(int mTaskReminder) {
        this.mTaskReminder = mTaskReminder;
    }

    public void setTaskPriorityColor(int mTaskPriorityColor) {
        this.mTaskPriorityColor = mTaskPriorityColor;
    }

    public void setTaskDescription(String mTaskDescription) {
        this.mTaskDescription = mTaskDescription;
    }

    public void setTaskDate(String mTaskDate) {
        this.mTaskDate = mTaskDate;
    }

    public void setTaskTime(String mTaskTime) {
        this.mTaskTime = mTaskTime;
    }

    public int getTaskID()
    {
        return mTaskID;
    }

    public void setTaskID(int id)
    {
        mTaskID = id;
    }

    public String getTaskName()
    {
        return mTaskName;
    }

    public String getTaskDescription()
    {
        return mTaskDescription;
    }

    public String getTaskDate()
    {
        return mTaskDate;
    }

    public String getTaskTime()
    {
        return mTaskTime;
    }

    public int isTaskReminder()
    {
        return mTaskReminder;
    }

    public int isTaskDone()
    {
        return mTaskDone;
    }

    public void setTaskDone(int done)
    {
        mTaskDone = done;
    }

    public int getTaskPriorityColor()
    {
        return mTaskPriorityColor;
    }
}
