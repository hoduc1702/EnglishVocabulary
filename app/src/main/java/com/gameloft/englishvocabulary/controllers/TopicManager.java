package com.gameloft.englishvocabulary.controllers;

import com.gameloft.englishvocabulary.models.Topic;
import com.gameloft.englishvocabulary.models.Vocabulary;

import java.util.ArrayList;

/**
 * Created by tai.nguyenduc on 8/23/2017.
 */

public class TopicManager {
    private ArrayList<Topic> mTopicList;
    private ArrayList<Vocabulary> mVocabularyList;

    private static TopicManager sInstance = null;

    private TopicManager(){
        mTopicList = new ArrayList<>();
        mVocabularyList = new ArrayList<>();
    }

    public static TopicManager getInstance(){
        if(sInstance == null){
            sInstance = new TopicManager();
        }
        return sInstance;
    }

    public void load(){
        //fake data
        fakeDate();
    }

    public ArrayList<Topic> getTopicList(){
        return mTopicList;
    }

    public String [] getTopicsName(){
        String [] names = new String[mTopicList.size()];
        for(int i = 0; i < mTopicList.size(); i++){
            Topic topic = mTopicList.get(i);
            names[i] = topic.getName();
        }
        return names;
    }

    public ArrayList<Vocabulary> getVocabularyList(Topic topic){
        ArrayList<Vocabulary> list = new ArrayList<>();
        for(Vocabulary vocabulary : mVocabularyList){
            if(vocabulary.getTopicId() == topic.getId()){
                list.add(vocabulary);
            }
        }
        return list;
    }

    private void fakeDate(){
        mTopicList.add(new Topic(1, "Topic1"));
        mTopicList.add(new Topic(2, "Topic2"));
        mTopicList.add(new Topic(3, "Topic3"));

        mVocabularyList.add(new Vocabulary("Voc11", "Pro11", "M11", 1));
        mVocabularyList.add(new Vocabulary("Voc12", "Pro12", "M12", 1));
        mVocabularyList.add(new Vocabulary("Voc21", "Pro21", "M21", 2));
        mVocabularyList.add(new Vocabulary("Voc22", "Pro22", "M22", 2));
        mVocabularyList.add(new Vocabulary("Voc31", "Pro31", "M31", 3));
        mVocabularyList.add(new Vocabulary("Voc32", "Pro32", "M32", 3));
    }
}
