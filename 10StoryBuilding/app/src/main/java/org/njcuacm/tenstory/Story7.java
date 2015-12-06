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
import org.njcuacm.exceptions.NotAChoiceException;

public class Story7 extends ActionBarActivity {

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
        setContentView(R.layout.story7);
        //Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Six - Warning to Kristen");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView4);
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new SelectiveTextAdapter(getApplicationContext(), R.layout.story_list);
        //Now set our ListView's adapter to the TextAdapter.
        lv.setAdapter(adapter);
        button = (Button) findViewById(R.id.questionButton);
        button.setEnabled(false);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.showResultingQuestion(Story7.this, "Do you think Kristen will stop seeing Ray now?");
            }
        });
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversation(conversationSwitch);
                try {
                    showMainQuestions(conversationSwitch);
                } catch (NotAChoiceException e) {
                    e.printStackTrace();
                }
                conversationSwitch++;
                if (conversationSwitch > 4)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    button.setEnabled(true);
                }
            }
        });


        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "STORY SIX\nWarning to Kristen", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }

    private void showConversation(int position)
    {
        switch (position)
        {
            case 0:
                adapter.add(new SelectiveDisplayTextAdapter(
                        false,
         /*COMMENT*/    "Dear Kristen,\n" +
                        "You do not know me, but I am worried about you.  " +
                        "I saw you at that Ethiopian Restaurant with Ray Coning.  " +
                        "I asked the waiter to find out your name, and I looked it __________ on the Internet.   \n",
         /*SPEAKER*/    "======EMAIL======",
         /*COLOR*/      0));
                break;
            case 1:
                adapter.add(new SelectiveDisplayTextAdapter(
                        false,
         /*COMMENT*/    "Based __________ the fact that I saw you and ray kissing and that I know Ray Coning’s wife, I have to tell you to stop dating a married man!   " +
                        "I’m not only worried about Lizzy (yes, she has a name), but you too.  " +
                        "I know Ray is going to fill up your head with dreams of the two of you living together—they will not come true.  " +
                        "This is similar __________ what he did last year with his daughter’s 5th grade teacher!  " +
                        "That situation is relevant to yours.  " +
                        "The teacher got fired when the principal caught her and Ray together in a classroom at night.  " +
                        "The entire school was talking __________ it. ",
         /*SPEAKER*/    "",
         /*COLOR*/      0));
                break;
            case 2:
                adapter.add(new SelectiveDisplayTextAdapter(
                        false,
         /*COMMENT*/    "The man cannot stop.  " +
                        "He is full __________ charm and lies. " +
                        " He should be ashamed of himself, but he is too much of an egomaniac.  " +
                        "He’s successful in his career, but he’s a disaster as a husband.  ",
         /*SPEAKER*/    "",
         /*COLOR*/      0));
                break;
            case 3:
                adapter.add(new SelectiveDisplayTextAdapter(
                        false,
         /*COMMENT*/    "If his wife learns about this, it will crush her ... again.  " +
                        "I wish she would just divorce him and be finished __________ him.   " +
                        "For the sake of his children—stay away from Ray.",
         /*SPEAKER*/    "",
         /*COLOR*/      0));
                break;
            case 4:
                adapter.add(new SelectiveDisplayTextAdapter(
                        false,
         /*COMMENT*/    "I am not happy about finding this out or warning you.  It’s one of the most unpleasant things that I’ve had to do.\n" +
                        "\n" +
                        "Sincerely,\n" +
                        "Janet Healey \n",
         /*SPEAKER*/    "",
         /*COLOR*/      0));
                break;
        }
    }

    private void showMainQuestions(int position) throws NotAChoiceException
    {
        //Cases 0, 1, 2, 3
        switch (position)
        {
            case 0:
                questions.showThreeChoices(
                        Story7.this,
                        "up",
                        "in",
                        "of",
                        "You do not know me, but I am worried about you.  " +
                                "I saw you at that Ethiopian Restaurant with Ray Coning.  " +
                                "I asked the waiter to find out your name, and I looked it __________ on the Internet.   ",
                        0
                );
                break;
            case 1:
                questions.showThreeChoices(
                        Story7.this,
                        "about",
                        "into",
                        "on",
                        "That situation is relevant to yours.  " +
                                "The teacher got fired when the principal caught her and Ray together in a classroom at night. " +
                                " The entire school was talking __________ it. ",
                        0
                );
                questions.showThreeChoices(
                        Story7.this,
                        "on",
                        "to",
                        "of",
                        "I’m not only worried about Lizzy (yes, she has a name), but you too.  " +
                                "I know Ray is going to fill up your head with dreams of the two of you living together—" +
                                "they will not come true.  " +
                                "This is similar__________ what he did last year with his daughter’s 5th grade teacher!  " +
                                "That situation is relevant to yours.  ",
                        1
                );
                questions.showThreeChoices(
                        Story7.this,
                        "over",
                        "on",
                        "in",
                        "Based __________ the fact that I saw you and ray kissing and that I know Ray Coning’s wife, " +
                                "I have to tell you to stop dating a married man!   " +
                                "I’m not only worried about Lizzy (yes, she has a name), but you too.  " +
                                "I know Ray is going to fill up your head with dreams of the two of you living together—" +
                                "they will not come true.  ",
                        1
                );
                break;
            case 2:
                questions.showThreeChoices(
                        Story7.this,
                        "of",
                        "in",
                        "on",
                        "The man cannot stop.  " +
                                "He is full __________ charm and lies. " +
                                " He should be ashamed of himself, but he is too much of an egomaniac. " +
                                " He’s successful in his career, but he’s a disaster as a husband.  ",
                        0
                );
                break;
            case 3:
                questions.showThreeChoices(
                        Story7.this,
                        "in",
                        "with",
                        "about",
                        "If his wife learns about this, it will crush her ... again.  " +
                                "I wish she would just divorce him and be finished __________ him.  " +
                                " For the sake of his children—stay away from Ray.",
                        1
                );
                break;
        }
    }

    private void showResultingQuestion()
    {
        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(Story7.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story7.this);
        final RadioButton rb11 = new RadioButton(Story7.this);
        //Set the radio text of the choices.
        rb10.setText("Yes");
        rb11.setText("No");
        //Now we add the radio buttons to the view (Radio Group)
        group3.addView(rb10);
        group3.addView(rb11);
        //Change the view's gravity.
        group3.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        //Set the gravity of the radio buttons to be in the middle.
        rb10.setGravity(Gravity.FILL_HORIZONTAL);
        rb11.setGravity(Gravity.FILL_HORIZONTAL);
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story7.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Do you think Kristen will stop seeing Ray now?");
        //Make sure the user cannot cancel the Dialog until the user answers it.
        diag3.setCancelable(true);
        diag3.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        final AlertDialog dialog3 = diag3.create();
        dialog3.show();
        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                //The radio button number 3 is our answer, so check if the user chose it.
                if (rb10.isChecked()) {
                    resultingAnswer = rb10.getText().toString();

                    dialog3.dismiss();
                    intent = new Intent(Story7.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story7.this, StoryViewer.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //The onBackPressed() method will go to the previous activity...ONLY if it was not FINISHED.
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
