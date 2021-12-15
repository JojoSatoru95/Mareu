package com.johann.mareu.Model;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Meeting {
    String room;
    String subject;
    String participants;
    Date date;
    Date time;

    public Meeting(String room, String subject, String participants, Date date, Date time) {
        this.room = room;
        this.subject = subject;
        this.participants = participants;
        this.date = date;
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(room, meeting.room) && Objects.equals(subject, meeting.subject) && Objects.equals(participants, meeting.participants) && Objects.equals(date, meeting.date) && Objects.equals(time, meeting.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, subject, participants, date, time);
    }
}
