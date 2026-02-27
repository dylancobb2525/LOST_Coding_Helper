package com.lost_coding_helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Question {
    private UUID id;
    private String title;
    private String prompt;
    private Difficulty difficulty;
    private ArrayList<Topic> topics;
    private ArrayList<CompanyTag> companyTags;
    private ArrayList<String> hints;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private PublishStatus status;
    private int voteCount;
    private ArrayList<Solution> solutions;
    private ArrayList<Comment> comments;
    private ArrayList<Attachment> attachments;

    public Question(UUID id, String title, String prompt, Difficulty difficulty, ArrayList<Topic> topics, ArrayList<CompanyTag> companyTags,
                    ArrayList<String> hints, UUID createdBy, LocalDateTime createdAt, PublishStatus status) {

    }

    public void addSolution(Solution solution) {

    }

    public ArrayList<Solution> getSolutions() {
        return solutions;
    }

    public void upvote(UUID userId) {

    }

    public void downvote(UUID userId) {

    }



}
