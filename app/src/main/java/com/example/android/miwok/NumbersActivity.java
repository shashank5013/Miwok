package com.example.android.miwok;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    @Override
    protected void onStop() {
        super.onStop();
        ArrayList<Word> words=new ArrayList<Word>();
        WordAdapter temp = new WordAdapter(this,words,0);
        temp.releaseMediaPlayer();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //ArrayList for numbers to be displayed
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));



        //Linking Adapter and ListView for recycling views
        WordAdapter wordAdapter=new WordAdapter(this,words,R.color.category_numbers);
        ((ListView)findViewById(R.id.list)).setAdapter(wordAdapter);

    }

}