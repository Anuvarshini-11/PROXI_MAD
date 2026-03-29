package com.muruga.proxistudy;

public class Session {
    public String name;
    public String subject;
    public String location;
    public int maxParticipants;
    public int participants;

    public Session(String name, String subject, String location, int maxParticipants) {
        this.name = name;
        this.subject = subject;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.participants = 1; // Creator is first participant
    }
}
