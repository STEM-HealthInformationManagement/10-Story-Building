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

public class Story10 extends ActionBarActivity {

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
        setContentView(R.layout.story10);//Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Nine - Confused about gay marriage");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView7);
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
        dialogAdapter.dialogOut(this, "STORY NINE\nConfused about gay marriage", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }

    private void showConversation()
    {
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "====================", "EMAIL", R.color.bg_drawer_blue_active));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I have been going to church all of my life and I believe ________ God and the Church’s teachings.   But now, I need your advice.  I’m sick of worrying about this problem.   I just got ______ from a long talk with a good friend.   She’s a person I’m really fond ______, and I trust her opinion a lot.   She wants me to go to a gay wedding reception.   At first I told her to forget _________ it. I didn’t even wait for her to give me the details.  I told her ‘no’ immediately.  I’m not exactly known _____being open minded to that stuff.   I was surprised at her reaction—she doesn’t want to see me if I have that attitude.  \n\n" +
                        "But now I’m wondering if I’m wrong.  I have been exposed to gay people—my cousin is gay and that dude from that TV show I like.  I don’t have a problem with them.  I’m just accustomed to marriage happening between a man and a woman—not two dudes.   If I think about it, I guess it depends on the people who are getting married.  Do they really love each other?  I know what the church says about this—it’s wrong.  But, I’m curious about your personal opinion—what should I do?    If I go to this wedding party, can I still be a member of your church or do I have to hand in my Rosary Beads, lol.  Just kidding.  It’s just all very confusing to me.  \n\n" +
                        "Please, whatever you do—don’t tell my dad that I’m asking about this.\n\n\n" +
                        "Sincerely,\n" +
                        "Trevor\n", "To: Father Ward\n" +
                "From Trevor Adams\n" +
                "RE:  My Faith\n" +
                "Dear Father Ward,\n", 0));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "\n\n\n\n", "", R.color.bg_drawer_blue_active));
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Trevor,\n\n" +
                        "I read your message below.  You don’t have to hand in your Rosary Beads.  I actually know the couple and will be attending that same reception as a guest.   I will see you there…if you decide to come. \n\n\n" +
                        "Fr. Ward\n", "Re: Re: My Faith", 0));
    }

    private void showResultingQuestion()
    {
        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(Story10.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story10.this);
        final RadioButton rb11 = new RadioButton(Story10.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story10.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Will Trevor go to the Wedding reception?");
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
                    intent = new Intent(Story10.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story10.this, StoryViewer.class);
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
