package com.model;

public class DataConstants {
    // File paths
    public static final String USER_FILE_NAME = "json/users.json";
    public static final String QUESTION_FILE_NAME = "json/questions.json";
    
    // JSON top-level keys
    public static final String USERS = "users";
    public static final String QUESTIONS = "questions";
    
    // User field names
    public static final String USER_ID = "userId";
    public static final String USER_USERNAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_DISPLAY_NAME = "displayName";
    public static final String USER_HASHED_PASSWORD = "hashedPassword";
    public static final String USER_JOIN_DATE = "joinDate";
    public static final String USER_ACCOUNT_ID = "accountId";
    public static final String USER_IS_LOCKED = "isLocked";
    public static final String USER_FAILED_LOGIN_COUNT = "failedLoginCount";
    public static final String USER_LAST_FAILED_LOGIN_AT = "lastFailedLoginAt";
    public static final String USER_ACHIEVEMENT_IDS = "achievementIds";
    public static final String USER_STREAK = "streak";
    public static final String USER_LAST_ACTIVE_DATE = "lastActiveDate";
    public static final String USER_FAVORITE_PROBLEMS = "favoriteProblems";
    public static final String USER_PROGRESS_TRACKER_ID = "progressTrackerId";
    
    // Question field names
    public static final String QUESTION_ID = "id";
    public static final String QUESTION_TITLE = "title";
    public static final String QUESTION_PROMPT = "prompt";
    public static final String QUESTION_DIFFICULTY = "difficulty";
    public static final String QUESTION_TOPICS = "topics";
    public static final String QUESTION_COMPANY_TAGS = "companyTags";
    public static final String QUESTION_HINTS = "hints";
    public static final String QUESTION_CREATED_BY = "createdBy";
    public static final String QUESTION_CREATED_AT = "createdAt";
    public static final String QUESTION_STATUS = "status";
    public static final String QUESTION_VOTE_COUNT = "voteCount";
    public static final String QUESTION_SOLUTIONS = "solutions";
    public static final String QUESTION_COMMENTS = "comments";
    public static final String QUESTION_ATTACHMENTS = "attachments";
}
