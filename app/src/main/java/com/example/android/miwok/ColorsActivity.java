package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
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

        //ArrayList for colors to be displayed
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red","wetetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("Green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("Brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("Gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("Black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("White","kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("Dusty Yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("Mustard Yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        //using a custom adapter
        WordAdapter wordAdapter=new WordAdapter(this,words,R.color.category_colors);

        //setting up the list view
        ((ListView)findViewById(R.id.list)).setAdapter(wordAdapter);
    }
}