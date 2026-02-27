package com.lost_coding_helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgressTracker {
    private List<Question> completedProblems;
    private ArrayList<String> userActivities;

    public ProgressTracker() {
        completedProblems = new ArrayList<>();
        userActivities = new ArrayList<>();
    }

    public void recordAttempt(Question problem) {

    }

    public void markCompleted(Question problem, int timeSpentSec) {

    }

    public void logActivity(ActivityType activityType, String details) {

    } 

    public void updateStreak(Date activityDate) {

    }

    public ArrayList<Question> getCompletedQuestionsByDifficulty() {
        return new ArrayList<>();
    }

    public ArrayList<Question> getCompletedQuestionsByTopic() {
        return new ArrayList<>();
    }

    public int getCurrentCount() {
        return 0;
    }

    public void addActivity() {

    }






}
