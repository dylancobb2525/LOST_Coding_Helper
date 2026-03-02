package com.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lost_coding_helper.Question;

public class DataWriter {

    private static final String USERS_FILE = "json/users.json";
    private static final String QUESTIONS_FILE = "json/questions.json";

    public boolean saveUsers(ArrayList<User> users) {
        try {
            JSONObject root = readJson(USERS_FILE);
            if (root == null) {
                root = new JSONObject();
                root.put("achievements", new JSONArray());
                root.put("users", new JSONArray());
                root.put("progressTrackers", new JSONArray());
                root.put("leaderboard", new JSONArray());
            }
            JSONArray usersArray = new JSONArray();
            for (User u : users) {
                usersArray.add(userToJson(u));
            }
            root.put("users", usersArray);
            return writeJson(USERS_FILE, root);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveProblem(ArrayList<Question> problems) {
        try {
            JSONObject root = readJson(QUESTIONS_FILE);
            if (root == null) {
                root = new JSONObject();
                root.put("languages", new JSONArray());
                root.put("questions", new JSONArray());
            }
            JSONArray questionsArray = new JSONArray();
            for (Question q : problems) {
                questionsArray.add(questionToJson(q));
            }
            root.put("questions", questionsArray);
            return writeJson(QUESTIONS_FILE, root);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProblem(Question problem) {
        try {
            JSONObject root = readJson(QUESTIONS_FILE);
            if (root == null) return false;
            JSONArray questions = (JSONArray) root.get("questions");
            if (questions == null) return false;
            String targetId = problem.getId().toString();
            for (int i = 0; i < questions.size(); i++) {
                JSONObject q = (JSONObject) questions.get(i);
                if (targetId.equals(q.get("id"))) {
                    questions.set(i, questionToJson(problem));
                    return writeJson(QUESTIONS_FILE, root);
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveFavorites(UUID userId, ArrayList<Question> favorites) {
        try {
            JSONObject root = readJson(USERS_FILE);
            if (root == null) return false;
            JSONArray users = (JSONArray) root.get("users");
            if (users == null) return false;
            String targetId = userId.toString();
            for (Object o : users) {
                JSONObject u = (JSONObject) o;
                if (targetId.equals(u.get("userId"))) {
                    JSONArray favIds = new JSONArray();
                    for (Question q : favorites) {
                        favIds.add(q.getId().toString());
                    }
                    u.put("favoriteProblems", favIds);
                    return writeJson(USERS_FILE, root);
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private JSONObject readJson(String path) {
        try (FileReader fr = new FileReader(path)) {
            Object parsed = new JSONParser().parse(fr);
            return (JSONObject) parsed;
        } catch (IOException | ParseException e) {
            return null;
        }
    }

    private boolean writeJson(String path, JSONObject root) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(root.toJSONString());
            fw.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject userToJson(User u) {
        JSONObject obj = new JSONObject();
        obj.put("userId", u.getUserId().toString());
        obj.put("joinDate", u.getJoinDate() != null ? u.getJoinDate().toString() : null);
        obj.put("displayName", u.getDisplayName());
        obj.put("accountId", u.getAccountId());
        obj.put("email", u.getEmail());
        obj.put("username", u.getUsername());
        obj.put("hashedPassword", u.getHashedPassword());
        obj.put("isLocked", u.isLocked());
        obj.put("failedLoginCount", u.getFailedLoginCount());
        obj.put("lastFailedLoginAt", u.getLastFailedLoginAt() != null ? u.getLastFailedLoginAt().toString() : null);
        JSONArray achievementIds = new JSONArray();
        if (u.getAchievementIds() != null) {
            for (UUID id : u.getAchievementIds()) {
                achievementIds.add(id.toString());
            }
        }
        obj.put("achievementIds", achievementIds);
        obj.put("streak", u.getStreak());
        obj.put("lastActiveDate", u.getLastActiveDate() != null ? u.getLastActiveDate().toString() : null);
        JSONArray favoriteIds = new JSONArray();
        if (u.getFavoriteProblems() != null) {
            for (Question q : u.getFavoriteProblems()) {
                favoriteIds.add(q.getId().toString());
            }
        }
        obj.put("favoriteProblems", favoriteIds);
        obj.put("progressTrackerId", u.getProgressTrackerId() != null ? u.getProgressTrackerId().toString() : null);
        return obj;
    }

    @SuppressWarnings("unchecked")
    private JSONObject questionToJson(Question q) {
        JSONObject obj = new JSONObject();
        obj.put("id", q.getId().toString());
        obj.put("title", q.getTitle());
        obj.put("prompt", q.getPrompt());
        obj.put("difficulty", q.getDifficulty() != null ? q.getDifficulty().toString() : null);
        JSONArray topics = new JSONArray();
        if (q.getTopics() != null) {
            for (Object t : q.getTopics()) {
                topics.add(t != null ? t.toString() : null);
            }
        }
        obj.put("topics", topics);
        JSONArray companyTags = new JSONArray();
        if (q.getCompanyTags() != null) {
            for (Object ct : q.getCompanyTags()) {
                companyTags.add(ct != null ? ct.toString() : null);
            }
        }
        obj.put("companyTags", companyTags);
        obj.put("hints", q.getHints() != null ? q.getHints() : new JSONArray());
        obj.put("createdBy", q.getCreatedBy() != null ? q.getCreatedBy().toString() : null);
        obj.put("createdAt", q.getCreatedAt() != null ? q.getCreatedAt().toString() : null);
        obj.put("status", q.getStatus() != null ? q.getStatus().toString() : null);
        obj.put("voteCount", q.getVoteCount());
        obj.put("solutions", new JSONArray());
        obj.put("comments", new JSONArray());
        obj.put("attachments", new JSONArray());
        return obj;
    }
}
