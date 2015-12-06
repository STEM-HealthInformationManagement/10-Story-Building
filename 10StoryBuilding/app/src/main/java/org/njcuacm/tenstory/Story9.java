package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.njcuacm.adapters.DialogAdapter;
import org.njcuacm.adapters.Questions;
import org.njcuacm.adapters.SelectiveDisplayTextAdapter;
import org.njcuacm.adapters.SelectiveTextAdapter;

public class Story9 extends ActionBarActivity {

    private SelectiveTextAdapter adapter;
    public ListView lv;
    boolean questionOneAnswered = false;
    boolean questionTwoAnswered = false;
    boolean questionThreeAnswered = false;
    boolean questionFourAnswered = false;
    boolean questionFiveAnswered = false;
    boolean questionSixAnswered = false;
    public String resultingAnswer;
    Button button;
    DialogAdapter dialogAdapter;
    Questions questions = new Questions();
    int conversationSwitch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story9);
        //Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Eight - Parents' Worries");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView6);
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new SelectiveTextAdapter(getApplicationContext(), R.layout.story_list);
        //Now set our ListView's adapter to the TextAdapter.
        lv.setAdapter(adapter);
        button = (Button) findViewById(R.id.questionButton);
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.showResultingQuestion(Story9.this, "Do you trust Detective Dietrich?");
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversation(conversationSwitch);
                showMainQuestions(conversationSwitch);
                conversationSwitch++;
                if (conversationSwitch > 14)
                {
                    nextButton.setVisibility(View.INVISIBLE);
                    nextButton.setEnabled(false);
                    button.setVisibility(View.VISIBLE);
                    button.setEnabled(true);
                }
            }
        });

        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "STORY EIGHT\nParents' Worries\n\n" +
                "FOR TESTING PURPOSES, THE RIGHT ANSWER IS SHOWN IN THE TEXT FIELD, WHEN THE POP-UP SHOWS", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }

    private void showConversation(int position)
    {
        switch (position) {
            case 0:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I had a dream __________ Tommy last night.", "Elisa", 0));
                break;
            case 1:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "As soon as I get back from work, I’ll call his friends.   ", "Morgan", 0));
                break;
            case 2:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I’m sick of worrying so much.  I hope he’s alright…somewhere.", "Elisa", 0));
                break;
            case 3:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I trust that detective.  He said he’s committed to finding him.  He promised an answer ", "Morgan", 0));
                break;
            case 4:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Yes, that could mean finding Tommy dead.", "Elisa", 0));
                break;
            case 5:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "====================", "===============", R.color.bg_drawer_blue_active));
                break;
            case 6:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Duane, this is Tommy’s father.   " +
                                "I’m sorry _______ texting you this late at night, but I need some answers.  " +
                                "Tommy is still missing.  Has he checked ______ with you or anybody else?", "Morgan", 0));
                break;
            case 7:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "No.  Sorry.  We’re all looking for him.  We’re very concerned _____________ him.  ", "Duane", 0));
                break;
            case 8:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "We’ve hired a good detective.  " +
                                "He’s known for finding missing kids.  " +
                                "He’s been successful ______ solving similar cases. ", "Morgan", 0));
                break;
            case 9:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "That’s great.  I’ll help however I can.", "Duane", 0));
                break;
            case 10:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "====================", "===============", R.color.bg_drawer_blue_active));
                break;
            case 11:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I need you to come to Philadelphia. " +
                                " We have an apartment that you son might have been in.  " +
                                "We need you to look _____________ some clothes and other items to see if they belong to him.", "Det. Dietrich", 0));
                break;
            case 12:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Philadephia!?!?  How did he get that far?  I’ll drive now.  " +
                                "Give me some time to fill _______the gas tank and I’ll be there by the afternoon. " +
                                " What’s the address?", "Morgan", 0));
                break;
            case 13:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "It’s a dangerous neighborhood.  " +
                                "You need to be careful ________ who you talk to when you get here.  " +
                                "Let meet in front of the Liberty Bell and then we can over together. ", "Det. Dietrich", 0));
                break;
            case 14:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I’m not happy ____________ the delay that this will cause or not knowing the exact address, " +
                                "but I’ll trust you.  I’ll see you in a few hours. ", "Morgan", 0));
                break;

        }
    }

    private void showMainQuestions(int position)
    {
        //Cases 0, 6(x2), 7, 8, 11, 12, 13, 14
        switch (position)
        {
            case 0:
                questions.showEditableChoice(
                        Story9.this,
                        "Elisa:  I had a dream __________ Tommy last night.",
                        "about"
                );
                break;
            case 6:
                questions.showEditableChoice(
                        Story9.this,
                        "Tommy is still missing.  Has he checked ______ with you or anybody else?",
                        "in"
                );
                questions.showEditableChoice(
                        Story9.this,
                        "Morgan: Duane, this is Tommy’s father.   I’m sorry _______ texting you this late at night, but I need some answers.  Tommy is still missing.",
                        "about"
                );
                break;
            case 7:
                questions.showEditableChoice(
                        Story9.this,
                        "Duane:  No.  Sorry.  We’re all looking for him.  We’re very concerned _____________ him.  ",
                        "about"
                );
                break;
            case 8:
                questions.showEditableChoice(
                        Story9.this,
                        "Morgan: We’ve hired a good detective.  " +
                                "He’s known for finding missing kids.  He’s been successful ______ solving similar cases. ",
                        "in"
                );
                break;
            case 11:
                questions.showEditableChoice(
                        Story9.this,
                        "Det. Dietrich:  I need you to come to Philadelphia. " +
                                " We have an apartment that you son might have been in.  " +
                                "We need you to look _____________ some clothes and other items to see if they belong to him.   ",
                        "into"
                );
                break;
            case 12:
                questions.showEditableChoice(
                        Story9.this,
                        "Morgan: Philadelphia!?!?  " +
                                "How did he get that far?  " +
                                "I’ll drive now.  " +
                                "Give me some time to fill _______the gas tank and I’ll be there by the afternoon.  " +
                                "What’s the address?",
                        "up"
                );
                break;
            case 13:
                questions.showEditableChoice(
                        Story9.this,
                        "Det. Dietrich: It’s a dangerous neighborhood. " +
                                " You need to be careful ________ who you talk to when you get here.  " +
                                "Let meet in front of the Liberty Bell and then we can over together. ",
                        "with"
                );
                break;
            case 14:
                questions.showEditableChoice(
                        Story9.this,
                        "Morgan: I’m not happy ____________ the delay that this will cause or not knowing the exact address, but I’ll trust you.  " +
                                "I’ll see you in a few hours. ",
                        "about"
                );
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //The onBackPressed() method will go to the previous activity...ONLY if it was not FINISHED.
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
