package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class QuestionList {
    private final ArrayList<Question> questions;
    private DataWriter dataWriter;

    public QuestionList() {
        this.questions = new ArrayList<>();
    }

    public void setDataWriter(DataWriter dataWriter) {
        this.dataWriter = dataWriter;
    }

    public ArrayList<Question> getAll() {
        return questions;
    }

    public Question getById(UUID questionId) {
        if (questionId == null) {
            return null;
        }
        for (Question q : questions) {
            if (questionId.equals(q.getId())) {
                return q;
            }
        }
        return null;
    }

    public ArrayList<Question> search(String query) {
        ArrayList<Question> results = new ArrayList<>();
        if (query == null || query.isEmpty()) {
            return results;
        }
        String lowerQuery = query.toLowerCase();
        for (Question q : questions) {
            if (q.getTitle() != null && q.getTitle().toLowerCase().contains(lowerQuery)) {
                results.add(q);
            }
        }
        return results;
    }

    public boolean update(Question question) {
        if (question == null) {
            return false;
        }
        Question existing = getById(question.getId());
        if (existing == null) {
            return false;
        }
        return existing.updateQuestion(question);
    }

    public boolean save() {
        if (dataWriter == null) {
            return false;
        }
        return dataWriter.saveProblem(questions);
    }
}
