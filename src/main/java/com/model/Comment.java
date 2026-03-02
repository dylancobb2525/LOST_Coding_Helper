package com.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private UUID id;
    private UUID authorId;
    private String body;
    private LocalDateTime createdOn;
    private LocalDateTime updatedAt;
    private UUID questionId;
    private UUID solutionId;

    public Comment(UUID authorId, String body, UUID questionId, UUID solutionId) {
        this.id = UUID.randomUUID();
        this.authorId = authorId;
        this.body = body;
        this.createdOn = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.questionId = questionId;
        this.solutionId = solutionId;
    }

    public Comment(UUID id, UUID authorId, String body, LocalDateTime createdOn, LocalDateTime updatedAt,
                   UUID questionId, UUID solutionId) {
        this.id = id;
        this.authorId = authorId;
        this.body = body;
        this.createdOn = createdOn != null ? createdOn : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
        this.questionId = questionId;
        this.solutionId = solutionId;
    }

    public void editComment(String newBody) {
        if (newBody != null) {
            this.body = newBody;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void deleteComment() {
        this.body = null;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public UUID getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(UUID solutionId) {
        this.solutionId = solutionId;
    }
}
