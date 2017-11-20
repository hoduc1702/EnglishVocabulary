package com.gameloft.englishvocabulary.models;

/**
 * Created by tai.nguyenduc on 8/23/2017.
 */

public class Vocabulary {
    private String name;
    private String pronunciation;
    private String mean;
    private int topicId;

    public Vocabulary(String name, String pronunciation, String mean, int topicId) {
        this.name = name;
        this.pronunciation = pronunciation;
        this.mean = mean;
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "name='" + name + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", mean='" + mean + '\'' +
                ", topicId=" + topicId +
                '}';
    }
}
