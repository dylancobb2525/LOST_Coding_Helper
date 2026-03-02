package com.model;

import com.model.Question;
import com.lost_coding_helper.User;
import com.lost_coding_helper.enums.Topic;

public class Contributor extends User {

    public Contributor() {
        super(null, null, null, null, null, null);
    }

    @Override
    public boolean hasAccess(String feature) {
        return false;
    }

    @Override
    public boolean canSubmitSolutions() {
        return true;
    }

    @Override
    public boolean canTrackProgress() {
        return true;
    }

    @Override
    public boolean canCreateProblems() {
        return true;
    }

    @Override
    public boolean canViewMultipleHints() {
        return true;
    }

    @Override
    public boolean canFavoriteProblems() {
        return true;
    }

    public void addQuestion(Question question) {
        
    }

    public void editQuestion(Question question) {
        
    }

    public void deleteQuestion(Question question) {
        
    }

    public void addTestCases(Question question, TestCase[] testCases) {
        
    }

    public void assignTopics(Question question, Topic[] topics) {
        
    }

    public void addHints(Question question, String[] hints) {
        
    }

    public void setComplexity(Question question, String timeComplexity, String spaceComplexity) {
         
    }
}
