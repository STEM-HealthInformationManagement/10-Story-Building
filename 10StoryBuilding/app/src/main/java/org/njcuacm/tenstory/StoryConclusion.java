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

public class StoryConclusion extends ActionBarActivity {

    private SelectiveTextAdapter adapter;
    public ListView lv;
    Button button;
    DialogAdapter dialogAdapter;
    Questions questions = new Questions();
    int conversationSwitch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_conclusion);
        setTitle("Conclusion: Murder");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView9);
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
                scrollListView();
                showMainQuestions(conversationSwitch);
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
        switch (position)
        {
            case 0:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "The next morning Beth Matos was found murdered in her apartment.  " +
                                "She was stabbed to death in her living room.  " +
                                "That is the one fact that no one disagrees ________.  ", "", 0));
                break;
            case 1:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "In a city know for crime and famous ______________ murder, this mystery is especially hard to solve.  ", "", 0));
                break;
            case 2:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "There was no murder weapon found, and the apartment was wiped clean of fingerprints.  " +
                                "It is obvious that someone wanted to get rid ________ all of the evidence.    ", "", 0));
                break;
            case 3:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Everyone in the building is upset.  " +
                                "But the police are taking statements and looking at phone and text messages to put the facts together.  " +
                                "Everyone seems sorry _________ her death, " +
                                "but the police suspect that the murder was planned—nothing was stolen and Beth’s apartment " +
                                "is far from the entrance of the building.", "", 0));
                break;
            case 4:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Also, with the terrible snowstorm, there were not many strangers walking into the building.  " +
                                "The building’s camera only recording 3 people entering—Dr. Coning (10:30pm), " +
                                "Liz Coning (11:45pm), and Father Ward (1pm) .", "", 0));
                break;
            case 5:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Tommy was hiding in the basement, high on drugs, for days.  " +
                                "He said he spoke to Beth because he was looking for food, she gave him some chili and left her alive.\n" +
                                "The money Beth stole was from was stolen from wedding reception of Danny Patron and Gregory Adams," +
                                " who live in the building.\n", "Detective Notes", 0));
                break;
            case 6:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "The three people who were recording entering the building were Father Ward, Doctor Coning, and his wife Lizzy Coning. ", "", 0));
                break;
            case 7:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "John Jennings found out that his wife was hiding the money and became furious at Beth for putting them in trouble. ", "", 0));
                break;
            case 8:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Kristen says she was asleep the entire night and didn’t hear her friend get murdered.", "", 0));
                break;
            case 9:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "Dr. Coning and his wife Liz refuse to cooperate.", "", 0));
                break;
            case 10:
                adapter.add(new SelectiveDisplayTextAdapter(false,
                        "No one has been able to contact Fr. Ward.", "", 0));
                break;
        }
    }

    private void showMainQuestions(int position)
    {
        //Cases 0, 1, 2, 3
        switch (position)
        {
            case 0:
                questions.showEditableChoice(
                        StoryConclusion.this,
                        "The next morning Beth Matos was found murdered in her apartment.  " +
                                "She was stabbed to death in her living room.  " +
                                "That is the one fact that no one disagrees __________. ",
                        "with"
                );
                break;
            case 1:
                questions.showEditableChoice(
                        StoryConclusion.this,
                        "In a city know for crime and famous __________ murder, this mystery is especially hard to solve.  ",
                        "for"
                );
                break;
            case 2:
                questions.showEditableChoice(
                        StoryConclusion.this,
                        "There was no murder weapon found, and the apartment was wiped clean of fingerprints.  " +
                                "It is obvious that someone wanted to get rid __________ all of the evidence.    ",
                        "of"
                );
                break;
            case 3:
                questions.showEditableChoice(
                        StoryConclusion.this,
                        "Everyone in the building is upset.  " +
                                "But the police are taking statements and " +
                                "looking at phone and text messages to put the facts together.  " +
                                "Everyone seems sorry __________ her death, but the police suspect " +
                                "that the murder was planned—nothing was stolen and Beth's " +
                                "apartment is far from the entrance of the building.  ",
                        "about"
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
        final RadioGroup group3 = new RadioGroup(StoryConclusion.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(StoryConclusion.this);
        final RadioButton rb11 = new RadioButton(StoryConclusion.this);
        final RadioButton rb12 = new RadioButton(StoryConclusion.this);
        final RadioButton rb13 = new RadioButton(StoryConclusion.this);
        final RadioButton rb14 = new RadioButton(StoryConclusion.this);
        final RadioButton rb15 = new RadioButton(StoryConclusion.this);
        final RadioButton rb16 = new RadioButton(StoryConclusion.this);
        final RadioButton rb17 = new RadioButton(StoryConclusion.this);
        final RadioButton rb18 = new RadioButton(StoryConclusion.this);
        //Set the radio text of the choices.
        rb10.setText("Danny Patron and Gregory Adams");
        rb11.setText("Kristen");
        rb12.setText("Dr. Coning");
        rb13.setText("Lizzy Coning");
        rb14.setText("John Jennings");
        rb15.setText("Ravi Gupta");
        rb16.setText("Fr. Ward");
        rb17.setText("John Jennings");
        rb18.setText("Maria Jennings");
        //Now we add the radio buttons to the view (Radio Group)
        group3.addView(rb10);
        group3.addView(rb11);
        group3.addView(rb12);
        group3.addView(rb13);
        group3.addView(rb14);
        group3.addView(rb15);
        group3.addView(rb16);
        group3.addView(rb17);
        group3.addView(rb18);
        //Change the view's gravity.
        group3.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        //Set the gravity of the radio buttons to be in the middle.
        rb10.setGravity(Gravity.FILL_HORIZONTAL);
        rb11.setGravity(Gravity.FILL_HORIZONTAL);
        rb12.setGravity(Gravity.FILL_HORIZONTAL);
        rb13.setGravity(Gravity.FILL_HORIZONTAL);
        rb14.setGravity(Gravity.FILL_HORIZONTAL);
        rb15.setGravity(Gravity.FILL_HORIZONTAL);
        rb16.setGravity(Gravity.FILL_HORIZONTAL);
        rb17.setGravity(Gravity.FILL_HORIZONTAL);
        rb18.setGravity(Gravity.FILL_HORIZONTAL);
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(StoryConclusion.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Who did it?");
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
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        });
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(StoryConclusion.this, StoryViewer.class);
                startActivity(intent);
                //The radio button number 3 is our answer, so check if the user chose it.
                /*if (rb10.isChecked()) {
                    resultingAnswer = rb10.getText().toString();

                    dialog3.dismiss();
                    intent = new Intent(StoryConclusion.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(StoryConclusion.this, StoryViewer.class);
                    startActivity(intent);
                }
                else if (rb12.isChecked()) {
                    resultingAnswer = rb12.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(StoryConclusion.this, StoryViewer.class);
                    startActivity(intent);
                }*/
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //The onBackPressed() method will go to the previous activity...ONLY if it was not FINISHED.
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void scrollListView() {
        lv.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                lv.setSelection(adapter.getCount() - 1);
            }
        });
    }

}
