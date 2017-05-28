package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import java.io.Serializable;

/**
 * Created by ASUS on 5/28/2017.
 */

public class Task  implements Serializable{
    private String mTaskName;
    private String mTaskDescription;
    private String mTaskDate;
    private String mTaskTime;
    private int mTaskReminder;
    private String mTaskPriorityText;
    private int mTaskPriorityColor;
    private int mTaskDone;

    public Task(String name, String description, String date, String time, int reminder,
                String priorityText, int priorityColor, int taskDone)
    {
        mTaskName = name;
        mTaskDescription = description;
        mTaskDate = date;
        mTaskTime = time;
        mTaskReminder = reminder;
        mTaskPriorityText = priorityText;
        mTaskPriorityColor = priorityColor;
        mTaskDone = taskDone;
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


    public String getTaskPriorityText()
    {
        return mTaskPriorityText;
    }

}
