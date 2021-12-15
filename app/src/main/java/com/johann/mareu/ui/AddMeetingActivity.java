package com.johann.mareu.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.johann.mareu.DI.DI;
import com.johann.mareu.Model.Meeting;
import com.johann.mareu.Service.MeetingApiService;
import com.johann.mareu.databinding.ActivityAddmeetingBinding;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityAddmeetingBinding binding;
    Calendar calendar;
    private MeetingApiService mMeetingApiService = DI.getMeetingApiService();


    private void initUI() {
        binding = ActivityAddmeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
    }

    private void setButton() {
        binding.btnCreateMeeting.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        binding.dateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(binding.dateTxt);
            }
        });

        binding.timeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(binding.timeTxt);
            }
        });

    }

    private void showTimeDialog(EditText timeTxt) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                binding.timeTxt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(AddMeetingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(EditText dateTxt) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yy");

                binding.dateTxt.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(AddMeetingActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }



    @Override
    public void onClick(View view) {
        if (view == binding.btnCreateMeeting) {
            addMeeting();
        }
    }

    private void addMeeting() {

        // date

        String dte = binding.dateTxt.getText().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

        Date date = null;
        try {
            date = simpleDateFormat.parse(dte);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // time

        String tme = binding.timeTxt.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Date time = null;
        try {
            time = sdf.parse(tme);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String participants = binding.textParticipants.getEditText().getText().toString();
        String subject = binding.textSubject.getEditText().getText().toString();
        String room = binding.textRoom.getEditText().getText().toString();



        if (participants.isEmpty()) {
            binding.textParticipants.setError("Please type any participants");
            return;
        }
        if (subject.isEmpty()) {
            binding.textSubject.setError("Please type a subject");
            return;
        }
        if (room.isEmpty()) {
            binding.textRoom.setError("Please type a room's name");
            return;
        }
        if (dte.isEmpty()) {
            binding.dateTxt.setError("Please select date");
            return;
        }

        if (tme.isEmpty()) {
            binding.timeTxt.setError("please select time");
            return;
        }

        mMeetingApiService.createMeeting(new Meeting(room, subject, participants, date, time));
        Toast.makeText(this, "Meeting added !", Toast.LENGTH_SHORT).show();
        finish();

    }
}
