package com.johann.mareu.Service;

import com.johann.mareu.Model.Meeting;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

abstract class DummyMeetingGenerator {


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(new Meeting("Peach", "Marketing", "alexandre@gmail.com, jeanluc@gmail.com, pierre@gmail.com", new Date(1640007000000L), new Date(1640007000000L)),
            new Meeting("Mario", "Financement","benjamin@gmail.com, fanny@gmail.com, eric@gmail.com", new Date(1640077200000L), new Date(1640077200000L)),
            new Meeting("Luigi", "Recrutement", "alexandra@gmail.com, ludovic@gmail.com", new Date(1640169000000L), new Date(1640169000000L)));


    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
