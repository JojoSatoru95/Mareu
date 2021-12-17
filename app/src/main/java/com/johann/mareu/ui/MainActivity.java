package com.johann.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.johann.mareu.DI.DI;
import com.johann.mareu.Model.Meeting;
import com.johann.mareu.R;
import com.johann.mareu.Service.MeetingApiService;
import com.johann.mareu.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.SSLSessionBindingEvent;

public class MainActivity extends AppCompatActivity implements DeleteMeeting, View.OnClickListener {

    private ActivityMainBinding binding;
    private ArrayList<Meeting> mMeetingArrayList = new ArrayList<>();
    private MeetingApiService mMeetingApiService = DI.getMeetingApiService();
    MeetingAdapter mAdapter;

    private void initUI() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);


        mAdapter = new MeetingAdapter(this, mMeetingArrayList);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(), layoutManager.getOrientation());
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void initData() {
        mMeetingArrayList = new ArrayList<>(mMeetingApiService.getMeetings());
    }

    private void setButton() {
        binding.fabAddActivity.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterDate:
                dateDialog();
                return true;
            case R.id.filter_reset:
                resetFilter();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    private void resetFilter() {
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mMeetingApiService.getMeetings());
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }

    private void dateDialog() {
        int selectedYear = 2021;
        int selectedMonth = 11;
        int selectedDayofMonth = 30;

// Date Select Listener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = Calendar.getInstance();
                cal.set(i, i1, i2);
                mMeetingArrayList.clear();
                mMeetingArrayList.addAll(mMeetingApiService.getMeetingsFilteredByDate(cal.getTime()));
                binding.recyclerview.getAdapter().notifyDataSetChanged();
            }
        };

// Create Datepicker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, selectedYear, selectedMonth, selectedDayofMonth);

// Show
            datePickerDialog.show();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        resetFilter();
    }


    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetingApiService.deleteMeeting(meeting);
        mMeetingArrayList.clear();
        mMeetingArrayList.addAll(mMeetingApiService.getMeetings());
        binding.recyclerview.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        if (view == binding.fabAddActivity) {
            startActivity(new Intent(this, AddMeetingActivity.class));
        }
    }
}