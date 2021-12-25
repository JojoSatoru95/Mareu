package com.johann.mareu;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.johann.mareu.DI.DI;
import com.johann.mareu.Model.Meeting;
import com.johann.mareu.Service.DummyMeetingGenerator;
import com.johann.mareu.Service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.List;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class UnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingsWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingsWithSuccess() {
        Meeting meetingToAdd = new Meeting("Toad", "Conseil", "jojo@gmail.com", new Date(1640077200000L), new Date(1640077200000L));
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }


    @Test
    public void getFilteredDateWithSucces() {
        Meeting meetingFiltered = new Meeting("Yoshi", "Contrat", "gege@gmail.com", new Date(1640077200000L), new Date(1640077200000L));
        Meeting meetingNotFiltered = new Meeting("Maskass", "Entretien", "gigi@gmail.com", new Date(1640169000000L), new Date(1640169000000L));
        service.createMeeting(meetingFiltered);
        service.getMeetingsFilteredByDate(new Date(1640077200000L));
        assertTrue(service.getMeetingsFilteredByDate(new Date(1640077200000L)).contains(meetingFiltered));
        assertFalse(service.getMeetingsFilteredByDate(new Date(1640077200000L)).contains(meetingNotFiltered));
    }

}