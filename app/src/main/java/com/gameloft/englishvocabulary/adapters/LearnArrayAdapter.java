package com.gameloft.englishvocabulary.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gameloft.englishvocabulary.R;
import com.gameloft.englishvocabulary.models.Vocabulary;
import com.gameloft.englishvocabulary.utils.TextReader;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by tai.nguyenduc on 9/27/2017.
 */

public class LearnArrayAdapter extends ArrayAdapter<Vocabulary> {
    private Context mContext;
    private int mLayout;
    private ArrayList<Vocabulary> mList;

    public LearnArrayAdapter(Context context, int resource, ArrayList<Vocabulary> list) {
        super(context, resource, list);
        mContext = context;
        mLayout = resource;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            System.out.println("Create New View");
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvWord = (TextView)convertView.findViewById(R.id.tvWord);
            viewHolder.tvPronunciation = (TextView)convertView.findViewById(R.id.tvPronunciation);
            viewHolder.tvMean = (TextView)convertView.findViewById(R.id.tvMean);
            viewHolder.btnSound = (Button)convertView.findViewById(R.id.btnSound);

            convertView.setTag(viewHolder);
        }
        else{
            System.out.println("Re-use View");
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Vocabulary vocabulary = mList.get(position);
        viewHolder.tvWord.setText(vocabulary.getName());
        viewHolder.tvPronunciation.setText(vocabulary.getPronunciation());
        viewHolder.tvMean.setText(vocabulary.getMean());

        viewHolder.btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play audio later
                TextReader.getInstance().speak(vocabulary.getName());
            }
        });

        return convertView;
    }

    private class ViewHolder{
        public TextView tvWord;
        public TextView tvPronunciation;
        public TextView tvMean;
        public Button btnSound;
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mLayout, parent, false);

        TextView tvWord = (TextView)convertView.findViewById(R.id.tvWord);
        TextView tvPronunciation = (TextView)convertView.findViewById(R.id.tvPronunciation);
        TextView tvMean = (TextView)convertView.findViewById(R.id.tvMean);
        Button btnSound = (Button)convertView.findViewById(R.id.btnSound);

        Vocabulary vocabulary = mList.get(position);
        tvWord.setText(vocabulary.getName());
        tvPronunciation.setText(vocabulary.getPronunciation());
        tvMean.setText(vocabulary.getMean());

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play audio later
            }
        });
        return convertView;
    }
    */
}
