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

public class Story8 extends ActionBarActivity {

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
        setContentView(R.layout.story8);
        //Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Seven - Stolen");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView5);
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new SelectiveTextAdapter(getApplicationContext(), R.layout.story_list);
        //Now set our ListView's adapter to the TextAdapter.
        lv.setAdapter(adapter);
        button = (Button) findViewById(R.id.questionButton);
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        button.setEnabled(false);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.showResultingQuestion(Story8.this, "Do you think Beth will return the money?");
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversation(conversationSwitch);
                showMainQuestions(conversationSwitch);
                conversationSwitch++;
                if (conversationSwitch > 11)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    button.setEnabled(true);
                }
            }
        });


        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "STORY SEVEN\nStolen\n\n" +
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
                        "Can I count on you to keep a secret?", "Beth", 0));
                break;
            case 1:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I’ve been doing a lot that lately.  Yes.  Whatever it is,  No one will learn ________ it from me.", "Kristen", 0));
                break;
            case 2:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I stole $5000 from the restaurant, and I’m scared of getting caught.", "Beth", 0));
                break;
            case 3:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "What?!? You’re Miss Goody Good.  How did it happen? ", "Kristen", 0));
                break;
            case 4:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Basically, I took out an ice bucket. ", "Beth", 0));
                break;
            case 5:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Huh.  I don’t understand.  Go ___________ it slowly. ", "Kristen", 0));
                break;
            case 6:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I can’t tell you.  I’m too ashamed _________ what I’ve done.", "Beth", 0));
                break;
            case 7:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Don’t worry ___________ what I think.  I may not agree____________ what you did, but I’m always here for you.  " +
                                "I’ve got your back.", "Kristen", 0));
                break;
            case 8:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "The manager is always careful ____________ the cash, so he hides it in a bowl under the safe.  " +
                                "I saw it in an ice bucket. " +
                                " I think the other waiter was planning to steal it.  " +
                                "Everyone thinks he did it now. " +
                                " But really I just took it and walked out with it.  " +
                                "I don’t know what came over me.  ", "Beth", 0));
                break;
            case 9:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "OK…I get it.  " +
                                "You lost control.  " +
                                "Maybe you were fed up ________ your job.  " +
                                "You told me before you don’t like working there.", "Kristen", 0));
                break;
            case 10:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I don’t know…..  I know it’s late.  " +
                                "I can’t sleep.  " +
                                "I have nightmares.  " +
                                "I dream about being in a police station. " +
                                " I guess I’m really sorry ______ the whole situation," +
                                " but I’m not ready to admit that I walked out with the money.", "Beth", 0));
                break;
            case 11:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Come over my place and we can talk.  " +
                                "I’ve been keeping a secret too.  ", "Kristen", 0));
                break;
        }
    }

    private void showMainQuestions(int position)
    {
        //Cases 1, 5, 6, 7(x2), 8, 9, 10
        switch (position)
        {
            case 1:
                questions.showEditableChoice(
                        Story8.this,
                        "Kristen: I’ve been doing a lot that lately.  Yes.  Whatever it is,  No one will learn ________ it from me.",
                        "about"
                );
                break;
            case 5:
                questions.showEditableChoice(
                        Story8.this,
                        "Kristen: Huh.  I don’t understand.  Go ___________ it slowly. ",
                        "over"
                );
                break;
            case 6:
                questions.showEditableChoice(
                        Story8.this,
                        "Beth:  I can’t tell you.  I’m too ashamed _________ what I’ve done.",
                        "of"
                );
                break;
            case 7:
                questions.showEditableChoice(
                        Story8.this,
                        "I may not agree____________ what you did, but I’m always here for you.  I’ve got your back.",
                        "with"
                );
                questions.showEditableChoice(
                        Story8.this,
                        "Kristen:  Don’t worry ___________ what I think.",
                        "about"
                );
                break;
            case 8:
                questions.showEditableChoice(
                        Story8.this,
                        "Beth:  The manager is always careful ____________ the cash, so he hides it in a bowl under the safe.  " +
                                "I saw it in an ice bucket.  " +
                                "I think the other waiter was planning to steal it.  " +
                                "Everyone thinks he did it now.  " +
                                "But really I just took it and walked out with it.  " +
                                "I don’t know what came over me.  ",
                        "of"
                );
                break;
            case 9:
                questions.showEditableChoice(
                        Story8.this,
                        "Kristen: OK…I get it.  " +
                                "You lost control.  " +
                                "Maybe you were fed up ________ your job.  " +
                                "You told me before you don’t like working there.",
                        "with"
                );
                break;
            case 10:
                questions.showEditableChoice(
                        Story8.this,
                        "Beth:  I don’t know…..  " +
                                "I know it’s late.  " +
                                "I can’t sleep.  " +
                                "I have nightmares.  " +
                                "I dream about being in a police station.  " +
                                "I guess I’m really sorry ______ the whole situation, but I’m not ready to admit that I walked out with the money.",
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
