package com.johann.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johann.mareu.Model.Meeting;
import com.johann.mareu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolder> {
    private DeleteMeeting myInterface;
    private ArrayList<Meeting> meetings;

    public MeetingAdapter(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }

    public MeetingAdapter(DeleteMeeting mainActivity, ArrayList<Meeting> meetingArrayList) {
        this.meetings = meetingArrayList;
        this.myInterface = mainActivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView circle;
        private TextView meetingSubject;
        private TextView participantsName;
        private TextView meetingDate;
        private ImageButton deleteButton;
        private TextView meetingRoom;
        private TextView meetingTime;

        public MyViewHolder(final View view){
            super(view);
            circle = view.findViewById(R.id.circle);
            meetingSubject = view.findViewById(R.id.meetingSubject);
            participantsName = view.findViewById(R.id.mailUsers);
            meetingRoom = view.findViewById(R.id.meetingRoom);
            meetingDate = view.findViewById(R.id.meetingDate);
            meetingTime = view.findViewById(R.id.meetingTime);
            deleteButton = view.findViewById(R.id.deleteButton);
        }


        void displayMeeting(Meeting meeting){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            meetingRoom.setText("Salle " + meeting.getRoom());
            meetingSubject.setText(meeting.getSubject());
            participantsName.setText(meeting.getParticipants());
            meetingDate.setText(simpleDateFormat.format(meeting.getDate()));
            meetingTime.setText(sdf.format(meeting.getTime()));


        }
    }


    @NonNull
    @Override
    public MeetingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.MyViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        holder.displayMeeting(meeting);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myInterface.deleteMeeting(meeting);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }
}
