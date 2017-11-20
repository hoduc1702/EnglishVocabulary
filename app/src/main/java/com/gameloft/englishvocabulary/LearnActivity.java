package com.gameloft.englishvocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.gameloft.englishvocabulary.adapters.LearnArrayAdapter;
import com.gameloft.englishvocabulary.controllers.TopicManager;
import com.gameloft.englishvocabulary.models.Topic;
import com.gameloft.englishvocabulary.models.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    private ListView lvVocabulary;
    private LearnArrayAdapter mAdapter;
    private ArrayList<Vocabulary> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Intent intent = getIntent();
        Topic topic = (Topic)intent.getSerializableExtra(TopicActivity.KEY_PASS_DATA);
        mList = TopicManager.getInstance().getVocabularyList(topic);

        lvVocabulary = (ListView)findViewById(R.id.lvVocabulary);
        mAdapter = new LearnArrayAdapter(this, R.layout.item_vocabulary_learn, mList);
        lvVocabulary.setAdapter(mAdapter);

        registerForContextMenu(lvVocabulary);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        menu.setHeaderTitle(R.string.test);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_memory:
                Toast.makeText(this, "Memory", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_listening:
                Toast.makeText(this, "Listening", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_writing:
                Toast.makeText(this, "Writing", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
