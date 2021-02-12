package com.vortimo.sbu.commitCounter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitInfo {
    private String username;
    private Integer commits;

    public CommitInfo() {
    }

    public CommitInfo(String username, Integer commits) {
        this.username = username;
        this.commits = commits;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }

    @Override
    public String toString() {
        return "CommitInfo{" +
                "username='" + username + '\'' +
                ", commits=" + commits +
                '}';
    }
}
