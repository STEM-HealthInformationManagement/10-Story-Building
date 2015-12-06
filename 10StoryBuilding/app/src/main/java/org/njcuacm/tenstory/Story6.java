package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.njcuacm.adapters.DialogAdapter;
import org.njcuacm.adapters.Questions;
import org.njcuacm.adapters.SelectiveDisplayTextAdapter;
import org.njcuacm.adapters.SelectiveTextAdapter;
import org.njcuacm.exceptions.NotAChoiceException;

public class Story6 extends ActionBarActivity {

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
    DialogAdapter dialogAdapter1;
    Questions questions = new Questions();
    int conversationSwitch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story6);
        //Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Five - Dangerous Work");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView3);
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
                questions.showResultingQuestion(Story6.this, "Should John go back to a life of crime?");
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversation(conversationSwitch);
                try
                {
                    showMainQuestions(conversationSwitch);
                }
                catch (NotAChoiceException e)
                {
                    e.printStackTrace();
                }
                conversationSwitch++;
                if (conversationSwitch > 10)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    button.setEnabled(true);
                }
            }
        });

        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "To answer, please click on the GREY comments and choose an answer.", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);

        dialogAdapter1 = new DialogAdapter();
        dialogAdapter1.dialogOut(this, "STORY FIVE\nDangerous Work", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter1.dialogOutDialog.dismiss();
            }
        }, null);


    }

    private void showConversation(int position)
    {
        switch (position) {
            case 0:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "=========================================.", "EMAIL", 0));
                break;
            case 1:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Dear Mr. Jennings,\n\n" +
                                "We regret to inform you that we cannot continue to employ you at Northside Cleaning and Maintenance  Company.  The new company policy is that we do not employ workers who were previously convicted of felony crimes.   Your employment will be terminated based on your 1998 conviction of armed robbery.\n\n" +
                                "We are attaching the section of our company policy that is relevant to this situation.  If you have any questions, please read the policy.\n\n" +
                                "Your last day of work will be today.\n\n" +
                                "Sincerely,\n" +
                                "Max Maralas\n" +
                                "Director of Operations \n", "", 0));
                break;
            case 2:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "=========================================", "TEXT MESSAGES", 0));
                break;
            case 3:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Marc, I’m finished with the cleaning job.  I need some work... the kind that pays a lot. ", "John", 0));
                break;
            case 4:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "What happened?  You got __________ going straight?  Not enough excitement?  ", "Marc", 0));
                break;
            case 5:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Something like that.  " +
                                "I’m __________ your friend’s offer—to drive that truck on that special job.  " +
                                "Can we talk about it?", "John", 0));
                break;
            case 6:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "You should forget about it.  " +
                                "You have a wife now…and kids.  " +
                                "They need a dad who isn’t in jail.  " +
                                "You said you were finished with the life of crime.", "Marc", 0));
                break;
            case 7:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "They need food on the table.  " +
                                "I’m more __________ losing our apartment than losing my freedom. ", "John", 0));
                break;
            case 8:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "OK.  " +
                                "I’m a little __________ you.  " +
                                "You have a family to think about.  " +
                                "I’m just into this because I like the excitement and I don't like hard work.  " +
                                "Let me look up my friend’s number and get back to you—the job is tonight.", "Marc", 0));
                break;
            case 9:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Honey, Mr. Morales took me to lunch and told me he has a new maintenance job for me—" +
                                "I’ll have to stay overnight to do it, but it should more money in overtime and maybe a promotion.  " +
                                "I’m __________ this opportunity.   " +
                                "I won’t be home tonight.  Wish me luck.", "John (To MARIA)", 0));
                break;
            case 10:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I knew this job at Northside would be good for you.  " +
                                "I’m so proud of you.  " +
                                "You are showing them what a good man you are…and you are even getting __________ the boss!   " +
                                "I love you. ", "John", 0));
                break;
        }
    }

    private void showMainQuestions(int position) throws NotAChoiceException
    {
        //Cases 4, 5, 7, 8, 9, 10
        switch (position)
        {
            case 4:
                questions.showThreeChoices(
                        Story6.this,
                        "with",
                        "in",
                        "on",
                        "What happened?  " +
                                "You got bored __________ going straight? " +
                                " Not enough excitement?  ",
                        0
                );
                break;
            case 5:
                questions.showThreeChoices(
                        Story6.this,
                        "in",
                        "with",
                        "to",
                        "Something like that.  " +
                                "I’m interested __________ your friend’s offer—to drive that truck on that special job.  " +
                                "Can we talk about it?",
                        0
                );
                break;
            case 7:
                questions.showThreeChoices(
                        Story6.this,
                        "of",
                        "in",
                        "on",
                        "They need food on the table.  " +
                                "I’m more frightened __________ losing our apartment than losing my freedom. ",
                        0
                );
                break;
            case 8:
                questions.showThreeChoices(
                        Story6.this,
                        "on",
                        "of",
                        "to",
                        "OK.  I’m a little jealous__________ you.  " +
                                "You have a family to think about.  " +
                                "I’m just into this because I like the excitement and I don't like hard work. " +
                                " Let me look up my friend’s number and get back to you—the job is tonight.",
                        1
                );
                break;
            case 9:
                questions.showThreeChoices(
                        Story6.this,
                        "about",
                        "on",
                        "with",
                        "John (to Maria) Honey, Mr. Morales took me to lunch and told me he has a new maintenance job for me—" +
                                "I’ll have to stay overnight to do it, but it should more money in overtime and maybe a promotion.  " +
                                "I’m excited __________ this opportunity.   " +
                                "I won’t be home tonight.  Wish me luck.",
                        0
                );
                break;
            case 10:
                questions.showThreeChoices(
                        Story6.this,
                        "in",
                        "to",
                        "with",
                        "I knew this job at Northside would be good for you.  " +
                                "I’m so proud of you.  " +
                                "You are showing them what a good man you are…and you are even getting friendly __________ the boss!   " +
                                "I love you. ",
                        2
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
        final RadioGroup group3 = new RadioGroup(Story6.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story6.this);
        final RadioButton rb11 = new RadioButton(Story6.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story6.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Should John go back to a life of crime? ");
        //Make sure the user cannot cancel the Dialog until the user answers it.
        diag3.setCancelable(false);
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
                    intent = new Intent(Story6.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story6.this, StoryViewer.class);
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
