package com.lost_coding_helper;

import java.util.ArrayList;
import java.util.UUID;

import com.model.DataLoader;
import com.model.DataWriter;
import com.model.Question;
import com.model.QuestionList;
import com.model.UserList;

public class ProblemApplication {
    private UserList userList; 
    private QuestionList questionList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;
    //private Leaderboard leaderboard;
    private User currentUser; // Track currently logged-in user (need to update uml )

    // Constructor to initialize all components
    public ProblemApplication() {
        this.userList = new UserList();
        this.questionList = new QuestionList();
        this.dataLoader = new DataLoader();
        this.dataWriter = new DataWriter();
        // Set DataWriter for QuestionList so it can save
        this.questionList.setDataWriter(this.dataWriter);
    }

    public User createAccount(String displayName, String username, String email, String password) {
        return userList.createAccount(displayName, username, email, password);
    }

    public User login(String username, String password) {
        User user = userList.authenticate(username, password);
        if (user != null) {
            this.currentUser = user;
        }
        return user;
    }

    public void logOut() {
        this.currentUser = null;
    }

    /**
     * Gets all questions.
     */
    public ArrayList<Question> getQuestions() {
        return questionList.getAll();
    }

    /** Same as getQuestions (for UI). */
    public ArrayList<Question> getAllQuestions() {
        return getQuestions();
    }

    /**
     * Gets one question by id.
     */
    public Question getQuestion(UUID questionId) {
        return questionList.getById(questionId);
    }

    /** Same as getQuestion (for UI). */
    public Question getQuestionById(UUID questionId) {
        return getQuestion(questionId);
    }

    /**
     * Searches questions by query string (title).
     */
    public ArrayList<Question> searchQuestions(String query) {
        return questionList.search(query);
    }

    /**
     * Adds a new question to the list. Call saveAll() to write to file.
     */
    public void createQuestion(Question question) {
        questionList.addQuestion(question);
    }

    /**
     * Edits a question. Finds it by id and updates with the new data, then saves to file.
     */
    public void editQuestion(UUID questionId, Question question) {
        Question existing = questionList.getById(questionId);
        if (existing == null || question == null) {
            return;
        }
        existing.updateQuestion(question);
        questionList.updateQuestion(existing);
    }

    /**
     * Updates a question in the list and in the json file.
     */
    public boolean updateQuestion(Question question) {
        return questionList.updateQuestion(question);
    }

    /**
     * Deletes a question from the list and from the json file.
     */
    public boolean deleteQuestion(UUID questionId) {
        Question question = questionList.getById(questionId);
        if (question == null) {
            return false;
        }
        return questionList.deleteQuestion(question);
    }

    public void addSolution(UUID questionId, Solution solution) {
        Question question = questionList.getById(questionId);
        if (question != null) {
            question.addSolution(solution);
        }
    }

    public void recordAttempt(UUID questionId, int timeSpentSec) {
        if (currentUser == null) {
            return; // No user logged in
        }
        Question question = questionList.getById(questionId);
        if (question != null) {
            currentUser.getProgressTracker().recordAttempt(question);
        }
    }

    public void markCompleted(UUID questionId, int timeSpentSec) {
        if (currentUser == null) {
            return; // No user logged in
        }
        Question question = questionList.getById(questionId);
        if (question != null) {
            currentUser.getProgressTracker().markCompleted(question, timeSpentSec);
        }
    }

    public ArrayList<Question> getCompletedQuestion() {
        if (currentUser == null) {
            return new ArrayList<>(); // No user logged in
        }
        return currentUser.getProgressTracker().getCompletedQuestionsByDifficulty();
    }

    public void init() {
        // Load users and questions from JSON files
        ArrayList<User> users = dataLoader.getUsers();
        ArrayList<Question> questions = dataLoader.getProblems();
        
        // Add loaded users to userList
        if (users != null) {
            userList.addAll(users);
        }
        
        // Add loaded questions to questionList
        if (questions != null) {
            questionList.getAll().addAll(questions);
        }
    }

    public boolean saveAll() {
        boolean usersSaved = dataWriter.saveUsers(userList.getAll());
        boolean questionsSaved = dataWriter.saveProblem(questionList.getAll());
        return usersSaved && questionsSaved;
    }
}
