package com.gameloft.englishvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gameloft.englishvocabulary.adapters.LearnArrayAdapter;
import com.gameloft.englishvocabulary.adapters.PracticePageAdapter;
import com.gameloft.englishvocabulary.controllers.TopicManager;
import com.gameloft.englishvocabulary.models.Topic;
import com.gameloft.englishvocabulary.models.Vocabulary;

import java.util.ArrayList;

public class PracticeActivity extends AppCompatActivity {

    private ViewPager vpVocabyulary;
    private PracticePageAdapter mAdapter;
    private ArrayList<Vocabulary> mList;

    private TextView tvCurrentOverTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init(){
        ArrayList<Topic> topics = TopicManager.getInstance().getTopicList();
        mList = TopicManager.getInstance().getVocabularyList(topics.get(0));
        mAdapter = new PracticePageAdapter(this, R.layout.item_vocabulary_practice, mList);
    }

    private void getWidgets(){
        vpVocabyulary = (ViewPager)findViewById(R.id.vpVocabulary);
        tvCurrentOverTotal = (TextView)findViewById(R.id.tvCurrentOverTotal);
    }

    private void setWidgets(){
        vpVocabyulary.setAdapter(mAdapter);
        setCurrentOverTotal(0);
    }

    private void addWidgetsListener(){
        vpVocabyulary.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                setCurrentOverTotal(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCurrentOverTotal(int position){
        tvCurrentOverTotal.setText((position + 1) + "/" + mList.size());
    }

}
