package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;


public class CreateTask extends AppCompatActivity implements View.OnClickListener{

    /*Calendar*/
    protected Calendar mCalendar;
    protected Calendar mCurrentDate;

    /*Task priority color*/
    private boolean mSetRed = false;
    private boolean mSetYellow = false;
    private boolean mSetGreen = false;

    /*Strings*/
    protected CharSequence mSuccessfulText;
    protected CharSequence mCancelText;;
    private String mLeftButtonName;
    private String mRightButtonName;
    private String mTaskNameString;
    private String mDateString;
    private String mTimeString;
    private CharSequence mDateDisplay;

    /*Task created flags*/
    protected boolean mPriorityButton;
    protected boolean mEditTextFilled;
    protected boolean mTimeSet;
    protected boolean mDateSet;

    /*Date picker*/
    protected DatePickerDialog.OnDateSetListener mDate;
    protected DatePickerDialog mDatePicker;

    /*Time picker*/
    protected TimePickerDialog.OnTimeSetListener mTime;
    protected TimePickerDialog mTimePicker;

    /*EditText boxes*/
    protected EditText mTaskName;
    protected EditText mTaskDescription;

    /*Buttons*/
    Button mLeftButton;
    Button mRightButton;
    Button mPriorityRed;
    Button mPriorityYellow;
    Button mPriorityGreen;
    Button mSetDate;
    Button mSetTime;

    protected TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            /*DO NOTHING*/
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            /*DO NOTHING*/
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            mTaskNameString = mTaskName.getText().toString();
            checkFieldsForEmpty();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        /*Instances of buttons*/
        mLeftButton = (Button) findViewById(R.id.leftButton);
        mRightButton = (Button) findViewById(R.id.rightButton);
        mPriorityRed = (Button) findViewById(R.id.Red);
        mPriorityYellow = (Button) findViewById(R.id.Yellow);
        mPriorityGreen = (Button) findViewById(R.id.Green);
        mSetDate = (Button) findViewById(R.id.setDate);
        mSetTime = (Button) findViewById(R.id.setTime);

        /*Connect them with context*/
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
        mPriorityRed.setOnClickListener(this);
        mPriorityYellow.setOnClickListener(this);
        mPriorityGreen.setOnClickListener(this);
        mSetDate.setOnClickListener(this);
        mSetTime.setOnClickListener(this);

        /*Instances of EditText*/
        mTaskName = (EditText) findViewById(R.id.taskName);
        mTaskDescription = (EditText) findViewById(R.id.taskDescription);

        /*Set initial state of flags*/
        mEditTextFilled = false;
        mPriorityButton = false;

        /*Connect with context*/
        mTaskName.addTextChangedListener(mTextWatcher);
        mTaskDescription.addTextChangedListener(mTextWatcher);
        checkFieldsForEmpty();


         /*Calendar*/
        mCalendar = Calendar.getInstance();
        mCurrentDate = Calendar.getInstance();
        mCurrentDate.setTimeInMillis(System.currentTimeMillis());

        mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if(mCalendar.get(Calendar.YEAR) == year)
                {
                    if(mCalendar.get(Calendar.DAY_OF_YEAR) - mCurrentDate.get(Calendar.DAY_OF_YEAR) == 0)
                    {
                        mDateDisplay = getText(R.string.today);
                    }
                    else if(mCalendar.get(Calendar.DAY_OF_YEAR) - mCurrentDate.get(Calendar.DAY_OF_YEAR) == 1)
                    {
                        mDateDisplay = getText(R.string.tomorrow);
                    }
                    else if (mCalendar.get(Calendar.DAY_OF_YEAR) - mCurrentDate.get(Calendar.DAY_OF_YEAR) == 2)
                    {
                        mDateDisplay = getText(R.string.dayAfterTomorrow);
                    }
                    else if(mCalendar.get(Calendar.DAY_OF_YEAR) - mCurrentDate.get(Calendar.DAY_OF_YEAR) >=3 && mCalendar.get(Calendar.DAY_OF_YEAR) - mCurrentDate.get(Calendar.DAY_OF_YEAR) <7)
                    {
                        switch (mCalendar.get(Calendar.DAY_OF_WEEK))
                        {
                            case(Calendar.MONDAY) :
                            {
                                mDateDisplay = getText(R.string.monday);
                                break;
                            }
                            case(Calendar.TUESDAY) :
                            {
                                mDateDisplay = getText(R.string.tuesday);
                                break;
                            }
                            case(Calendar.WEDNESDAY) :
                            {
                                mDateDisplay = getText(R.string.wednesday);
                                break;
                            }
                            case(Calendar.THURSDAY) :
                            {
                                mDateDisplay = getText(R.string.thursday);
                                break;
                            }
                            case(Calendar.FRIDAY) :
                            {
                                mDateDisplay = getText(R.string.friday);
                                break;
                            }
                            case(Calendar.SATURDAY) :
                            {
                                mDateDisplay = getText(R.string.saturday);
                                break;
                            }
                            case(Calendar.SUNDAY) :
                            {
                                mDateDisplay = getText(R.string.sunday);
                                break;
                            }
                        }
                    }
                    else
                    {
                        mDateDisplay = Integer.toString(dayOfMonth)+"-"+Integer.toString(monthOfYear+1)+"-"+Integer.toString(year);
                    }
                }
            }
        };


        mTime = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                mCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                mCalendar.set(Calendar.MINUTE,minute);
            }
        };

         /*Date Picker*/
        mDatePicker = new DatePickerDialog(CreateTask.this, mDate,
                mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));

        mDatePicker.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                int mDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
                int mMonth = mCalendar.get(Calendar.MONTH)+1;
                int mYear = mCalendar.get(Calendar.YEAR);
                mDateString =  Integer.toString(mDayOfMonth) + "-" +Integer.toString(mMonth) +
                        "-"+Integer.toString(mYear);
                mSetDate.setText(mDateString);
                mDateSet = true;
            }
        });

        /*Time picker*/
        mTimePicker = new  TimePickerDialog(CreateTask.this, mTime, mCalendar
                .get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),true);


        mTimePicker.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = mCalendar.get(Calendar.MINUTE);
                if(mHour < 10 && mMinute<10)
                {
                    mTimeString = "0"+Integer.toString(mHour) + ":"+"0"+Integer.toString(mMinute);
                }
                else if(mHour<10)
                {
                    mTimeString = "0"+Integer.toString(mHour) + ":"+Integer.toString(mMinute);
                }
                else if(mMinute<10)
                {
                    mTimeString = Integer.toString(mHour) + ":"+"0"+Integer.toString(mMinute);
                }
                else
                {
                    mTimeString = Integer.toString(mHour) + ":"+Integer.toString(mMinute);
                }
                mSetTime.setText(mTimeString);
                mTimeSet = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.leftButton :
                this.finish();
                break;

            case R.id.rightButton :
                this.finish();
                break;

            case R.id.Red :
                mSetRed = true;
                mPriorityRed.setEnabled(false);
                mPriorityButton = true;
                if(mEditTextFilled && mTimeSet && mDateSet)
                {
                    mLeftButton.setEnabled(true);
                }
                mPriorityYellow.setEnabled(true);
                mPriorityGreen.setEnabled(true);
                break;

            case R.id.Yellow :
                mSetYellow = true;
                mPriorityYellow.setEnabled(false);
                mPriorityButton = true;
                if(mEditTextFilled && mDateSet && mTimeSet)
                {
                    mLeftButton.setEnabled(true);
                }
                mPriorityRed.setEnabled(true);
                mPriorityGreen.setEnabled(true);
                break;

            case R.id.Green :
                mSetGreen = true;
                mPriorityGreen.setEnabled(false);
                mPriorityButton = true;
                if(mEditTextFilled && mDateSet && mTimeSet)
                {
                    mLeftButton.setEnabled(true);
                }
                mPriorityRed.setEnabled(true);
                mPriorityYellow.setEnabled(true);
                break;
            case R.id.setDate :
                mDatePicker.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                mDatePicker.show();
                break;

            case R.id.setTime :
                mTimePicker.show();
                break;
        }
    }

    private void checkFieldsForEmpty()
    {
        String s1 = mTaskName.getText().toString();
        String s2 = mTaskDescription.getText().toString();

        if(!(s1.isEmpty()) && !(s2.isEmpty()))
        {
            mEditTextFilled = true;
            if(mPriorityButton)
            {
                mLeftButton.setEnabled(true);
            }
        }
        else
        {
            mLeftButton.setEnabled(false);
        }
    }
}
