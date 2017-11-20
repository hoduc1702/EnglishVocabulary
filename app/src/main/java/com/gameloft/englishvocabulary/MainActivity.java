package com.gameloft.englishvocabulary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gameloft.englishvocabulary.controllers.TopicManager;
import com.gameloft.englishvocabulary.models.Topic;
import com.gameloft.englishvocabulary.models.Vocabulary;
import com.gameloft.englishvocabulary.utils.TextReader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean[] selectTopics, selectTopicsBackup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopicManager topicManager = TopicManager.getInstance();
        topicManager.load();

        selectTopics = new boolean[topicManager.getTopicsName().length];
        selectTopicsBackup = new boolean[selectTopics.length];

        TextReader.init(this);
        TextReader.getInstance().speak("");
    }

    public void gotoTopics(View view){
        Intent intent = new Intent(MainActivity.this, TopicActivity.class);
        startActivity(intent);
    }

    public void gotoPractice(View view){
        Intent intent = new Intent(MainActivity.this, PracticeActivity.class);
        startActivity(intent);
    }

    public void showRating(View view){
        new AlertDialog.Builder(this)
                .setTitle(R.string.rating)
                //builder.setCancelable(false);
                .setMessage("Do you like app?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton(R.string.not_now, null)
                .create().show();

    }

    public void gotoTest(View view){
        String []testList = getResources().getStringArray(R.array.test_type);
        new AlertDialog.Builder(this)
                .setTitle(R.string.test)
                .setItems(testList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showChooseTopic(i);
                    }
                })
                .create().show();
    }


    private void showChooseTopic(final int testType){
        for(int i = 0; i < selectTopics.length; i++){
            selectTopicsBackup[i] = selectTopics[i];
        }

        TopicManager topicManager = TopicManager.getInstance();
        String [] topicNames = topicManager.getTopicsName();

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_title_checkbox, null);
        TextView tvTitle = (TextView)layout.findViewById(R.id.tvTitle);
        final CheckBox cbAll = (CheckBox)layout.findViewById(R.id.cbSelectAll);

        tvTitle.setText(R.string.topics);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(layout);
        builder.setMultiChoiceItems(topicNames, selectTopics, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doTest(testType);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for(int j = 0; j < selectTopics.length; j++){
                    selectTopics[j] = selectTopicsBackup[j];
                }
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView listView = dialog.getListView();
                boolean isCheck = cbAll.isChecked();
                for(int i = 0; i < listView.getCount(); i++){
                    listView.setItemChecked(i, isCheck);
                    selectTopics[i] = isCheck;
                }
            }
        });
    }

    private void doTest(int testType){
        ArrayList<Vocabulary> list = new ArrayList<>();

        ArrayList<Topic> topicList = TopicManager.getInstance().getTopicList();
        for(int i = 0; i < selectTopics.length; i++){
            if(selectTopics[i]){
                Topic topic = topicList.get(i);
                ArrayList<Vocabulary> vocabularies = TopicManager.getInstance().getVocabularyList(topic);
                list.addAll(vocabularies);
            }
        }

        if(list.size() == 0){
            Toast.makeText(getApplicationContext(),
                    "Please choose one Topic", Toast.LENGTH_SHORT).show();
        }
        else if(list.size() < 4){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please choose more Topic", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.show();
        }
        else{
            ///Do Test
        }
    }
}
