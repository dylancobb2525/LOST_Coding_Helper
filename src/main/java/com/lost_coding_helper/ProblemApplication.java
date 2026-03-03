package com.lost_coding_helper;

import java.util.ArrayList;
import java.util.UUID;
import com.model.Question;
import com.model.QuestionList;
import com.model.DataLoader;
import com.model.DataWriter;
import com.model.UserList;
import com.lost_coding_helper.User;
import com.lost_coding_helper.Solution;

public class ProblemApplication {
    private UserList userList; 
    private QuestionList questionList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;
    private Leaderboard leaderboard;

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
        return userList.authenticate(username, password);
    }

    public void logOut() {
        
    }

    public ArrayList<Question> getAllQuestions() {
        return questionList.getAll();
    }
    
    public Question getQuestionById(UUID questionId) {
        return questionList.getById(questionId);
    }

    public void createQuestion(Question question) {
        if (question != null) {
            questionList.getAll().add(question);
        }
    }

    public boolean updateQuestion(Question question) {
        return questionList.update(question);
    }

    public boolean deleteQuestion(UUID questionId) {
        Question question = questionList.getById(questionId);
        if (question != null) {
            return questionList.getAll().remove(question);
        }
        return false;
    }

    public void addSolution(UUID questionId, Solution solution) {
        Question question = questionList.getById(questionId);
        if (question != null) {
            question.addSolution(solution);
        }
    }

    public void recordAttempt(UUID questionId, int timeSpentSec) {
        
        // Placeholder for user progress tracking
    }

    public void markCompleted(UUID questionId, int timeSpentSec) {
        // 
        // Placeholder for user progress tracking
    }

    public ArrayList<Question> getCompletedQuestion() {
        // This would need to get from User's ProgressTracker
        return new ArrayList<>();
    }

    public void init() {
        // Load users and questions from JSON files
        ArrayList<User> users = dataLoader.getUsers();
        ArrayList<Question> questions = dataLoader.getProblems();
        
        // Add loaded users to userList
        userList.getAll().addAll(users);
        
        // Add loaded questions to questionList
        questionList.getAll().addAll(questions);
    }

    public boolean saveAll() {
        boolean usersSaved = dataWriter.saveUsers(userList.getAll());
        boolean questionsSaved = dataWriter.saveProblem(questionList.getAll());
        return usersSaved && questionsSaved;
    }
}
