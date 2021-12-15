package com.johann.mareu.Service;

import com.johann.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetingsFilteredByDate(Date date);

}
