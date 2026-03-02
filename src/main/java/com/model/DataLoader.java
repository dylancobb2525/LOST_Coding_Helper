package com.model;

import java.util.ArrayList;
import java.util.UUID;

import com.lost_coding_helper.User;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray usersJSON = (JSONArray) jsonObject.get(USERS);
            
            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
                UUID userId = UUID.fromString((String) userJSON.get(USER_ID));
                String username = (String) userJSON.get(USER_USERNAME);
                String email = (String) userJSON.get(USER_EMAIL);
                String displayName = (String) userJSON.get(USER_DISPLAY_NAME);
                String hashedPassword = (String) userJSON.get(USER_HASHED_PASSWORD);
                String joinDate = (String) userJSON.get(USER_JOIN_DATE);
                String accountId = (String) userJSON.get(USER_ACCOUNT_ID);
                Boolean isLocked = (Boolean) userJSON.get(USER_IS_LOCKED);
                Long failedLoginCount = (Long) userJSON.get(USER_FAILED_LOGIN_COUNT);
                String lastFailedLoginAt = (String) userJSON.get(USER_LAST_FAILED_LOGIN_AT);
                JSONArray achievementIdsArray = (JSONArray) userJSON.get(USER_ACHIEVEMENT_IDS);
                Long streak = (Long) userJSON.get(USER_STREAK);
                String lastActiveDate = (String) userJSON.get(USER_LAST_ACTIVE_DATE);
                JSONArray favoriteProblemsArray = (JSONArray) userJSON.get(USER_FAVORITE_PROBLEMS);
                String progressTrackerId = (String) userJSON.get(USER_PROGRESS_TRACKER_ID);
                
                // TODO: Create User object with constructor once User class is implemented
                // User user = new User(userId, username, email, displayName, hashedPassword, 
                //     joinDate, accountId, isLocked, failedLoginCount != null ? failedLoginCount.intValue() : 0,
                //     lastFailedLoginAt, achievementIdsArray, streak != null ? streak.intValue() : 0,
                //     lastActiveDate, favoriteProblemsArray, progressTrackerId);
                User user = new User();
                users.add(user);
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return users;
    }
    
    public ArrayList<Question> getProblems() {
        ArrayList<Question> questions = new ArrayList<>();
        
        try {
            FileReader reader = new FileReader(QUESTION_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray questionsJSON = (JSONArray) jsonObject.get(QUESTIONS);
            
            for (int i = 0; i < questionsJSON.size(); i++) {
                JSONObject questionJSON = (JSONObject) questionsJSON.get(i);
                String id = (String) questionJSON.get(QUESTION_ID);
                String title = (String) questionJSON.get(QUESTION_TITLE);
                String prompt = (String) questionJSON.get(QUESTION_PROMPT);
                String difficulty = (String) questionJSON.get(QUESTION_DIFFICULTY);
                JSONArray topics = (JSONArray) questionJSON.get(QUESTION_TOPICS);
                JSONArray companyTags = (JSONArray) questionJSON.get(QUESTION_COMPANY_TAGS);
                JSONArray hints = (JSONArray) questionJSON.get(QUESTION_HINTS);
                String createdBy = (String) questionJSON.get(QUESTION_CREATED_BY);
                String createdAt = (String) questionJSON.get(QUESTION_CREATED_AT);
                String status = (String) questionJSON.get(QUESTION_STATUS);
                Long voteCount = (Long) questionJSON.get(QUESTION_VOTE_COUNT);
                JSONArray solutions = (JSONArray) questionJSON.get(QUESTION_SOLUTIONS);
                JSONArray comments = (JSONArray) questionJSON.get(QUESTION_COMMENTS);
                JSONArray attachments = (JSONArray) questionJSON.get(QUESTION_ATTACHMENTS);
                
                // TODO: Create Question object with constructor once Question class is implemented
                // Question question = new Question(id, title, prompt, difficulty, topics, 
                //     companyTags, hints, createdBy, createdAt, status, 
                //     voteCount != null ? voteCount.intValue() : 0, solutions, comments, attachments);
                Question question = new Question();
                questions.add(question);
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return questions;
    }
}
