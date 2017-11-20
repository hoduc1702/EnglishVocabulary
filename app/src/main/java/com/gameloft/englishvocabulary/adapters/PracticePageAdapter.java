package com.gameloft.englishvocabulary.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gameloft.englishvocabulary.R;
import com.gameloft.englishvocabulary.models.Vocabulary;

import java.util.ArrayList;

/**
 * Created by tai.nguyenduc on 10/11/2017.
 */

public class PracticePageAdapter extends PagerAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<Vocabulary> mList;

    public PracticePageAdapter(Context context, int layout, ArrayList<Vocabulary> list){
        mContext = context;
        mLayout = layout;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(mLayout, container, false);

        TextView tvWord = (TextView)layout.findViewById(R.id.tvWord);
        TextView tvPronunciation = (TextView)layout.findViewById(R.id.tvPronunciation);
        TextView tvMean = (TextView)layout.findViewById(R.id.tvMean);

        Vocabulary vocabulary = mList.get(position);
        tvWord.setText(vocabulary.getName());
        tvPronunciation.setText(vocabulary.getPronunciation());
        tvMean.setText(vocabulary.getMean());

        container.addView(layout);
        return layout;
    }
}
