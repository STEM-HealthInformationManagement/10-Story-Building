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
        dialogAdapter.dialogOut(this, "STORY SEVEN\nStolen", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }

    private void showConversation()
    {
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Can I count on you to keep a secret?", "Beth", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I’ve been doing a lot that lately.  Yes.  Whatever it is,  No one will learn ________ it from me.", "Kristen", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I stole $5000 from the restaurant, and I’m scared of getting caught.", "Beth", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "What?!? You’re Miss Goody Good.  How did it happen? ", "Kristen", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Basically, I took out an ice bucket. ", "Beth", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Huh.  I don’t understand.  Go ___________ it slowly. ", "Kristen", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I can’t tell you.  I’m too ashamed _________ what I’ve done.", "Beth", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Don’t worry ___________ what I think.  I may not agree____________ what you did, but I’m always here for you.  " +
                        "I’ve got your back.", "Kristen", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "The manager is always careful ____________ the cash, so he hides it in a bowl under the safe.  " +
                        "I saw it in an ice bucket. " +
                        " I think the other waiter was planning to steal it.  " +
                        "Everyone thinks he did it now. " +
                        " But really I just took it and walked out with it.  " +
                        "I don’t know what came over me.  ", "Beth", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "OK…I get it.  " +
                        "You lost control.  " +
                        "Maybe you were fed up ________ your job.  " +
                        "You told me before you don’t like working there.", "Kristen", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I don’t know…..  I know it’s late.  " +
                        "I can’t sleep.  " +
                        "I have nightmares.  " +
                        "I dream about being in a police station. " +
                        " I guess I’m really sorry ______ the whole situation," +
                        " but I’m not ready to admit that I walked out with the money.", "Beth", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Come over my place and we can talk.  " +
                        "I’ve been keeping a secret too.  ", "Kristen", 0));
    }

    private void showResultingQuestion()
    {
        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(Story8.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story8.this);
        final RadioButton rb11 = new RadioButton(Story8.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story8.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Do you think Beth will return the money?");
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
                    intent = new Intent(Story8.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story8.this, StoryViewer.class);
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
