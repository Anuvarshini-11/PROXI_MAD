package com.muruga.proxistudy;

import java.util.ArrayList;

public class SessionManager {
    public static ArrayList<Session> sessions = new ArrayList<>();

    public static void createSession(String name, String subject, String location, int maxParticipants) {
        Session session = new Session(name, subject, location, maxParticipants);
        sessions.add(session);
    }

    public static void clearSessions() {
        sessions.clear();
    }
}
