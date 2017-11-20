package com.gameloft.englishvocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.gameloft.englishvocabulary.controllers.TopicManager;
import com.gameloft.englishvocabulary.models.Topic;

import java.util.ArrayList;

public class TopicActivity extends AppCompatActivity {

    public static final String KEY_PASS_DATA = "key_pass_data";

    private GridView gvTopic;
    private ArrayAdapter<String> mAdapter;
    private String []mTopicsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        gvTopic = (GridView)findViewById(R.id.gvTopic);

        mTopicsName = TopicManager.getInstance().getTopicsName();
        mAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, mTopicsName);
        gvTopic.setAdapter(mAdapter);

        gvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<Topic>  topicArrayList = TopicManager.getInstance().getTopicList();
                Topic topic = topicArrayList.get(i);

                Intent intent = new Intent(TopicActivity.this, LearnActivity.class);
                intent.putExtra(KEY_PASS_DATA, topic);
                startActivity(intent);
            }
        });
    }
}
