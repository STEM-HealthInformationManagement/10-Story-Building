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

public class Story11 extends ActionBarActivity {

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
        setContentView(R.layout.story11);
        setTitle("Story Ten - Fear Loves Company");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView8);
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
                showResultingQuestion();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversation(conversationSwitch);
                showMainQuestions(conversationSwitch);
                conversationSwitch++;
                if (conversationSwitch > 16)
                {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    button.setEnabled(true);
                }
            }
        });

        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "STORY TEN\nFear Loves Company\n\n" +
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
                        "Can I spend a few days at your place?  " +
                                "My kids are with their father, and I’m afraid to stay by myself. ", "Kristen", 0));
                break;
            case 1:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Sure.  It will give me a reason to make a big pot of my spicy chili.  " +
                                "You know I'm famous for it in this building :)", "Beth", 0));
                break;
            case 2:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Yes, you're famous for smelling up the building with the odor.  ;)", "Kristen", 0));
                break;
            case 3:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I've been here 3 years—everyone here should be accustomed ____ the \"aroma\"", "Beth", 0));
                break;
            case 4:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "lol.  I need a good meal and a good laugh.  " +
                                "I'm getting scared ___my doctor friend.  " +
                                "His messages are frightening.  ", "Kristen", 0));
                break;
            case 5:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I can’t disagree with that--I always thought he was creepy.", "Beth", 0));
                break;
            case 6:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I can’t get rid of him.  " +
                                "He’s so mad at me for breaking up with him, but HE'S MARRIED!", "Kristen", 0));
                break;
            case 7:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I’m sorry about your problems.  " +
                                "Look at the bright side-" +
                                "-At least you don’t have the police looking for you, like I do.", "Beth", 0));
                break;
            case 8:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I might call the police myself..." +
                                "if he doesn't agree ______ stop contacting me " +
                                "and just forget about the whole thing.", "Kristen", 0));
                break;
            case 9:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I’m really surprised ________ you.  " +
                                "Did you really think he would leave his wife and kids?  " +
                                "Sorry.  I shouldn’t blame you....too much.  " +
                                "You should call the police on him.", "Beth", 0));
                break;
            case 10:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Not yet.", "Kristen", 0));
                break;
            case 11:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Just think about it.", "Beth", 0));
                break;
            case 12:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I will think about it.  " +
                                "Hmmm...speaking of things to think about--" +
                                "-do I have to sleep with your stolen money?  " +
                                "We can worry about that instead, lol.", "Kristen", 0));
                break;
            case 13:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "No, I asked my neighbor to hold it.  " +
                                "I can count ____ her not to say anything.  " +
                                "Her husband was a criminal, and she kept his secrets. lol.", "Beth", 0));
                break;
            case 14:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "lol.  You can depend _____ her, but what about him?  " +
                                "You need to spend that money fast.", "Kristen", 0));
                break;
            case 15:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "I’m not going to spend it.  " +
                                "I actually talked to my priest and he convinced me to return it and admit that I took it.  " +
                                "I’m committed _______ doing the right thing now.", "Beth", 0));
                break;
            case 16:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Great—you’ll definitely go to jail now.  " +
                                "Let’s talk about.  " +
                                "There’s a big snowstorm coming tonight, so we’ll have plenty of time to eat, " +
                                "talk, and worry.  Love you.", "Kristen", 0));
                break;
        }
    }

    private void showMainQuestions(int position)
    {
        //Cases 3, 4, 8, 9, 13, 14, 15
        switch (position)
        {
            case 3:
                questions.showEditableChoice(
                        Story11.this,
                        "Beth:  I’ve been here 3 years—everyone here should be accustomed ____ the \"aroma\".  ",
                        "to"
                );
                break;
            case 4:
                questions.showEditableChoice(
                        Story11.this,
                        "Kristen: lol.  I need a good meal and a good laugh.  " +
                                "I’m getting scared ___my doctor friend.  His messages are frightening.  ",
                        "of"
                );
                break;
            case 8:
                questions.showEditableChoice(
                        Story11.this,
                        "Kristen: I might call the police myself…if he doesn’t agree ______ stop contacting me and just forget about the whole thing.",
                        "to"
                );
                break;
            case 9:
                questions.showEditableChoice(
                        Story11.this,
                        "Beth: I’m really surprised ________ you.  Did you really think he would leave his wife and kids?  Sorry.  I shouldn’t blame you….too much.  You should call the police on him.",
                        "at"
                );
                break;
            case 13:
                questions.showEditableChoice(
                        Story11.this,
                        "Beth: No, I asked my neighbor to hold it.  I can count ____ her not to say anything.  Her husband was a criminal, and she kept his secrets. lol.",
                        "on"
                );
                break;
            case 14:
                questions.showEditableChoice(
                        Story11.this,
                        "Kristen:  lol.  You can depend _____ her, but what about him?  You need to spend that money fast.",
                        "on"
                );
                break;
            case 15:
                questions.showEditableChoice(
                        Story11.this,
                        "Beth:  I’m not going to spend it.  " +
                                "I actually talked to my priest and he convinced me to return it and admit that I took it.  " +
                                "I’m committed _______ doing the right thing now.",
                        "to"
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
        final RadioGroup group3 = new RadioGroup(Story11.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story11.this);
        final RadioButton rb11 = new RadioButton(Story11.this);
        final RadioButton rb12 = new RadioButton(Story11.this);
        //Set the radio text of the choices.
        rb10.setText("keep the stolen money");
        rb11.setText("return the money but don’t confess");
        rb12.setText("return the money and confess");
        //Now we add the radio buttons to the view (Radio Group)
        group3.addView(rb10);
        group3.addView(rb11);
        group3.addView(rb12);
        //Change the view's gravity.
        group3.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        //Set the gravity of the radio buttons to be in the middle.
        rb10.setGravity(Gravity.FILL_HORIZONTAL);
        rb11.setGravity(Gravity.FILL_HORIZONTAL);
        rb12.setGravity(Gravity.FILL_HORIZONTAL);
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story11.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Would you advise Beth to...");
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
                    intent = new Intent(Story11.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story11.this, StoryViewer.class);
                    startActivity(intent);
                }
                else if (rb12.isChecked()) {
                    resultingAnswer = rb12.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story11.this, StoryViewer.class);
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
