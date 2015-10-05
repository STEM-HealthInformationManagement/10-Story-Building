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
        showConversation();
        button = (Button) findViewById(R.id.questionButton);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultingQuestion();
            }
        });


        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "STORY EIGHT\nParents' Worries", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }

    private void showConversation()
    {
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I had a dream __________ Tommy last night.", "Elisa", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "As soon as I get back from work, I’ll call his friends.   ", "Morgan", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I’m sick of worrying so much.  I hope he’s alright…somewhere.", "Elisa", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I trust that detective.  He said he’s committed to finding him.  He promised an answer ", "Morgan", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Yes, that could mean finding Tommy dead.", "Elisa", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "====================", "===============", R.color.bg_drawer_blue_active));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Duane, this is Tommy’s father.   " +
                        "I’m sorry _______ texting you this late at night, but I need some answers.  " +
                        "Tommy is still missing.  Has he checked ______ with you or anybody else?", "Morgan", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "No.  Sorry.  We’re all looking for him.  We’re very concerned _____________ him.  ", "Duane", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "We’ve hired a good detective.  " +
                        "He’s known for finding missing kids.  " +
                        "He’s been successful ______ solving similar cases. ", "Morgan", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "That’s great.  I’ll help however I can.", "Duane", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "====================", "===============", R.color.bg_drawer_blue_active));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I need you to come to Philadelphia. " +
                        " We have an apartment that you son might have been in.  " +
                        "We need you to look _____________ some clothes and other items to see if they belong to him.", "Det. Dietrich", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Philadephia!?!?  How did he get that far?  I’ll drive now.  " +
                        "Give me some time to fill _______the gas tank and I’ll be there by the afternoon. " +
                        " What’s the address?", "Morgan", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "It’s a dangerous neighborhood.  " +
                        "You need to be careful ________ who you talk to when you get here.  " +
                        "Let meet in front of the Liberty Bell and then we can over together. ", "Det. Dietrich", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I’m not happy ____________ the delay that this will cause or not knowing the exact address, " +
                        "but I’ll trust you.  I’ll see you in a few hours. ", "Morgan", 0));

    }

    private void showResultingQuestion()
    {
        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(Story9.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story9.this);
        final RadioButton rb11 = new RadioButton(Story9.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story9.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Do you trust Detective Dietrich?");
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
                    intent = new Intent(Story9.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story9.this, StoryViewer.class);
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
