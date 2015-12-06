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

public class Story5 extends ActionBarActivity {

    private SelectiveTextAdapter adapter;
    public ListView lv;
    public String resultingAnswer;
    Button button;
    Button nextButton;
    DialogAdapter dialogAdapter;
    int conversationSwitch = 0;
    Questions questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story5);
        //Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Four - Medical Affair");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView2);
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new SelectiveTextAdapter(getApplicationContext(), R.layout.story_list);
        //Now set our ListView's adapter to the TextAdapter.
        lv.setAdapter(adapter);
        //----showConversation();
        nextButton = (Button) findViewById(R.id.nextButton);
        button = (Button) findViewById(R.id.showQuestionButton);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultingQuestion();
            }
        });
        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "STORY FOUR\nMedical Affair", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
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
                if(conversationSwitch > 8)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void showConversation(int position)
    {
        //Every time the method is called, it will add the specific line based on the CASE.
        switch (position) {
            case 0:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "To: Dr. Coning\n" +
                            "From: Kristen Santos\n" +
                            "RE: Thank you\n" +
                            "\n" +
                            "Dear Dr. Coning\n" +
                            "\n" +
                            "I just want to send you a ‘thank-you’ for the way you treated my daughter in the emergency room.  She is usually so frightened of doctors and hospitals.  You made the experience fun, for her and me.  \n" +
                            "Sincerely,\n" +
                            "Kristen\n", "Email Message:", 0));
                break;
            case 1:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "Kristen,\n" +
                            "It was my pleasure.  She’s a wonderful girl and you’re a wonderful mom, and woman.  Please bring her by for a follow up visit.   Text me when you can come.  If I’m not busy with an emergency, maybe we can have lunch.\n" +
                            "Ray.\n", "Reply:", 0));
                break;
            case 2:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "=====================================", "Text", 0));
                break;
            case 3:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "Ray, My daughter is doing great, so no need for a visit.  I’m still interested in lunch.  Are you free today?", "Kristen", 0));
                break;
            case 4:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "Sure, come by the hospital at 1pm.   " +
                            "There’s a new Brazilian Restaurant that I’ve been excited about trying.  " +
                            "It’s a little far, but I’m bored with all of the restaurants close to the hospital. " +
                            "Do you like Brazilian food?", "Ray", 0));
                break;
            case 5:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "Love it.", "Kristin", 0));
                break;
            case 6:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "Thank you so much for lunch today.   " +
                            "I was not disappointed with the food....or the company.  " +
                            "Do you get this friendly with all of your patients or am I just lucky?", "Kristin", 0));
                break;
            case 7:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "To Kristin,\n" +
                            "I had a very good time at lunch...too good.   You should know that I am married to a wonderful woman, and I have four beautiful daughters.   I had no right to ask you to lunch, but you fascinated me so much.   Now that you know I have a wife, would you still agree with another lunch date with me?\n" +
                            "Sincerely,\n" +
                            "Ray\n.", "Email", 0));
                break;
            case 8:
            adapter.add(new SelectiveDisplayTextAdapter(false,
                    "Ray,\n" +
                            "I suspected that.  You should know that I’m a single mom, and I am responsible for working to support my two kids.  I’m also the cook, cleaner, therapist, taxi driver, coach, mother and father to them.  \n" +
                            "I would love to see you again.   I haven’t had such a great time with a man in years...we really connect.  Funny, I am actually jealous of your wife—she has you.  You’re handsome, kind, charming…but you are hers!\n" +
                            "I don’t know if I want to connected to you socially or emotionally or.... \n" +
                            "My head is spinning!\n" +
                            "Kristin\n", "Email", 0));
                break;
        }
    }

    private void showMainQuestions(int position) throws NotAChoiceException {
        questions = new Questions();
        switch (position) {
            case 0:

                break;
            case 1:
                questions.showThreeChoices(
                        Story5.this,
                        "with",
                        "on",
                        "in",
                        "It was my pleasure.  " +
                                "She’s a wonderful girl and you’re a wonderful mom, and woman.  " +
                                "Please bring her by for a follow up visit.   " +
                                "Text me when you can come.  " +
                                "If I’m not busy ______ an emergency, maybe we can have lunch.", 0);
                break;
            case 2:

                break;
            case 3:
                questions.showThreeChoices(
                    Story5.this,
                    "interested in",
                    "interested with",
                    "interesting into",
                    "Ray, My daughter is doing great, so no need for a visit.  " +
                            "I’m still ________________ lunch.  Are you free today?",
                    0);
                break;
            case 4:
                /*questions.showThreeChoices(
                        Story5.this,
                        "",
                        "",
                        "",
                        "",
                        0);*/
                break;
            case 5:
                /*questions.showThreeChoices(
                        Story5.this,
                        "",
                        "",
                        "",
                        "",
                        0);*/
                break;
            case 6:
                questions.showThreeChoices(
                        Story5.this,
                        "with",
                        "in",
                        "on",
                        "Thank you so much for lunch today.   " +
                                "I was not disappointed __________ the food….or the company.  " +
                                "Do you get this friendly with all of your patients or am I just lucky?",
                        0);
                break;
            case 7:
                questions.showThreeChoices(
                        Story5.this,
                        "with",
                        "to",
                        "on",
                        "I had a very good time at lunch…too good.   " +
                                "You should know that I am married __________ a wonderful woman, and I have four beautiful daughters.   ",
                        1);
                questions.showThreeChoices(
                        Story5.this,
                        "to",
                        "with",
                        "in",
                        "I had no right to ask you to lunch, but you fascinated me so much.   " +
                                "Now that you know I have a wife, would you still agree __________ another lunch date with me?",
                        0);
                break;
            case 8:
                questions.showThreeChoices(
                        Story5.this,
                        "in",
                        "with",
                        "for",
                        "I suspected that.  " +
                                "You should know that I’m a single mom, and I am responsible __________ working to support my two kids.  " +
                                "I’m also the cook, cleaner, therapist, taxi driver, coach, mother and father to them.  \n" +
                                "I would love to see you again.",
                        2);
                questions.showThreeChoices(
                        Story5.this,
                        "of",
                        "on",
                        "through",
                        "I haven’t had such a great time with a man in years…we really connect.  " +
                                "Funny, I am actually jealous __________ your wife—she has you.  " +
                                "You’re handsome, kind, charming…but you are hers!\n" +
                                "I don’t know if I want to connected to you socially or emotionally or…. \n" +
                                "My head is spinning!\n",
                        0);
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
        final RadioGroup group3 = new RadioGroup(Story5.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story5.this);
        final RadioButton rb11 = new RadioButton(Story5.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story5.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Do you think Kristin will agree to see Ray again?");
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
                    intent = new Intent(Story5.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story5.this, StoryViewer.class);
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
