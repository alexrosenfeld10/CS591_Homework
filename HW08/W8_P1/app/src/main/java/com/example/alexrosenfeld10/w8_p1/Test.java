package com.example.alexrosenfeld10.w8_p1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {

    private String GUID;
    private String dateTaken;
    private int score;
    private String user;
    private ArrayList<String> questions;
    private String fullName;

    public Test() {
        // Required for deserialization
    }

    public Test(String GUID, String dateTaken, int score, String user, ArrayList<String> questions, String fullName) {
        this.GUID = GUID;
        this.dateTaken = dateTaken;
        this.score = score;
        this.user = user;
        this.questions = questions;
        this.fullName = fullName;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("GUID", GUID);
        result.put("dateTaken", dateTaken);
        result.put("score", score);
        result.put("user", user);
        result.put("questions", questions);
        result.put("fullName", fullName);
        return result;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGUID() {

        return GUID;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public int getScore() {
        return score;
    }

    public String getUser() {
        return user;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public String getFullName() {
        return fullName;
    }
}
