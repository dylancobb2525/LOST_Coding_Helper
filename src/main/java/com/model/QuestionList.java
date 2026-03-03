package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Holds the list of questions. Can load from json and save to json.
 */
public class QuestionList {
    private ArrayList<Question> questions;

    public QuestionList() {
        this.questions = new ArrayList<>();
    }

    /**
     * Gets all questions in the list.
     */
    public ArrayList<Question> getAll() {
        return questions;
    }

    /**
     * Loads questions from the questions json file using DataLoader. Replaces the current list.
     * @return true if load worked
     */
    public boolean load() {
        DataLoader loader = new DataLoader();
        ArrayList<Question> loaded = loader.getProblems();
        if (loaded == null) return false;
        this.questions = loaded;
        return true;
    }

    /**
     * Saves the current list of questions to the questions json file using DataWriter.
     * @return true if save worked
     */
    public boolean save() {
        DataWriter writer = new DataWriter();
        return writer.saveProblem(questions);
    }

    /**
     * Finds one question by its id.
     * @param questionId the id to look for
     * @return the question or null if not found
     */
    public Question getById(UUID questionId) {
        if (questionId == null || questions == null) return null;
        for (Question q : questions) {
            if (questionId.equals(q.getId())) return q;
        }
        return null;
    }

    /**
     * Searches questions by a query string. Right now just returns empty list.
     */
    public ArrayList<Question> search(String query) {
        return new ArrayList<>();
    }

    /**
     * Updates one question in the list and in the json file. Finds it by id and replaces it, then saves.
     * @param question the question with new data (same id)
     * @return true if found and update worked
     */
    public boolean update(Question question) {
        if (question == null || question.getId() == null || questions == null) return false;
        for (int i = 0; i < questions.size(); i++) {
            if (question.getId().equals(questions.get(i).getId())) {
                questions.set(i, question);
                DataWriter writer = new DataWriter();
                return writer.updateProblem(question);
            }
        }
        return false;
    }

    /**
     * Deletes one question from the list and from the json file. Finds it by id.
     * @param question the question to delete (only the id is used)
     * @return true if found and delete worked
     */
    public boolean delete(Question question) {
        if (question == null || question.getId() == null || questions == null) return false;
        for (int i = 0; i < questions.size(); i++) {
            if (question.getId().equals(questions.get(i).getId())) {
                questions.remove(i);
                DataWriter writer = new DataWriter();
                return writer.deleteProblem(question);
            }
        }
        return false;
    }

    /**
     * Test main. Prints how many questions loaded and if save worked.
     */
    public static void main(String[] args) {
        QuestionList list = new QuestionList();
        boolean loaded = list.load();
        System.out.println("QuestionList test:");
        System.out.println("  load() = " + loaded);
        System.out.println("  number of questions = " + list.getAll().size());
        if (!list.getAll().isEmpty()) {
            Question first = list.getAll().get(0);
            String title = first.getTitle();
            System.out.println("  first question title = " + (title != null ? title : "(no title)"));
        }
        boolean saved = list.save();
        System.out.println("  save() = " + saved);
    }
}
