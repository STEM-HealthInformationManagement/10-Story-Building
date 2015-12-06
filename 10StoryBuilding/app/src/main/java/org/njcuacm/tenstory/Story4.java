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
import org.njcuacm.adapters.DisplayTextAdapter;
import org.njcuacm.adapters.SelectiveDisplayTextAdapter;
import org.njcuacm.adapters.SelectiveTextAdapter;
import org.njcuacm.adapters.TextAdapter;

public class Story4 extends ActionBarActivity {

    private SelectiveTextAdapter adapter;
    public ListView lv;
    boolean questionOneAnswered = false;
    boolean questionTwoAnswered = false;
    boolean questionThreeAnswered = false;
    boolean questionFourAnswered = false;
    public String resultingAnswer;
    Button button;
    DialogAdapter dialogAdapter;
    DialogAdapter dialogAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story4);
        //Rename our title on the ActionBar and have the score show up as well.
        setTitle("Story Two - Tommy is Missing");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listView);
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new SelectiveTextAdapter(getApplicationContext(), R.layout.story_list);
        //Now set our ListView's adapter to the TextAdapter.
        lv.setAdapter(adapter);
        showConversation();
        button = (Button) findViewById(R.id.showQuestionButton);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultingQuestion();
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
        dialogAdapter1.dialogOut(this, "STORY TWO\nTommy is Missing", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter1.dialogOutDialog.dismiss();
            }
        }, null);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 2:
                        if(!questionOneAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group = new RadioGroup(Story4.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb1 = new RadioButton(Story4.this);
                            final RadioButton rb2 = new RadioButton(Story4.this);
                            //Set the radio text of the choices.
                            rb1.setText("succeed at");
                            rb2.setText("succeed on");
                            //Now we add the radio buttons to the view (Radio Group)
                            group.addView(rb1);
                            group.addView(rb2);
                            //Change the view's gravity.
                            group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb1.setGravity(Gravity.FILL_HORIZONTAL);
                            rb2.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag = new AlertDialog.Builder(Story4.this);
                            //Set the RadioGroup to the dialog's view.
                            diag.setView(group);
                            //We now show the message and the speaker in the dialog.
                            diag.setMessage("Johnny" + ": " + "...We’ll never succeed __________ being a band if he goes missing ..." + "\n\nCHOICES: ");
                            //Make sure the user cannot cancel the Dialog until the user answers it.
                            diag.setCancelable(false);
                            diag.setPositiveButton("CHECK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            final AlertDialog dialog = diag.create();
                            dialog.show();
                            //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //The radio button number 3 is our answer, so check if the user chose it.
                                    if (rb1.isChecked()) {
                                        questionOneAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    /*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*/
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered && questionFourAnswered)
                                        {
                                            button.setEnabled(true);
                                        }
                                        dialog.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    /*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*/
                                    }
                                }
                            });
                        }
                        break;
                    case 3:
                        if(!questionTwoAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group = new RadioGroup(Story4.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb1 = new RadioButton(Story4.this);
                            final RadioButton rb2 = new RadioButton(Story4.this);
                            //Set the radio text of the choices.
                            rb1.setText("related with");
                            rb2.setText("related to");
                            //Now we add the radio buttons to the view (Radio Group)
                            group.addView(rb1);
                            group.addView(rb2);
                            //Change the view's gravity.
                            group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb1.setGravity(Gravity.FILL_HORIZONTAL);
                            rb2.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag = new AlertDialog.Builder(Story4.this);
                            //Set the RadioGroup to the dialog's view.
                            diag.setView(group);
                            //We now show the message and the speaker in the dialog.
                            diag.setMessage("Duane" + ": " + "...His disappearance had better not be related __________ drugs...." + "\n\nCHOICES: ");
                            //Make sure the user cannot cancel the Dialog until the user answers it.
                            diag.setCancelable(false);
                            diag.setPositiveButton("CHECK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            final AlertDialog dialog = diag.create();
                            dialog.show();
                            //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //The radio button number 3 is our answer, so check if the user chose it.
                                    if (rb2.isChecked()) {
                                        questionTwoAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    /*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*/
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered && questionFourAnswered)
                                        {
                                            button.setEnabled(true);
                                        }
                                        dialog.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    /*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*/
                                    }
                                }
                            });
                        }
                        break;
                    case 4:

                        if(!questionThreeAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group = new RadioGroup(Story4.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb1 = new RadioButton(Story4.this);
                            final RadioButton rb2 = new RadioButton(Story4.this);
                            //Set the radio text of the choices.
                            rb1.setText("look into");
                            rb2.setText("look at");
                            //Now we add the radio buttons to the view (Radio Group)
                            group.addView(rb1);
                            group.addView(rb2);
                            //Change the view's gravity.
                            group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb1.setGravity(Gravity.FILL_HORIZONTAL);
                            rb2.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag = new AlertDialog.Builder(Story4.this);
                            //Set the RadioGroup to the dialog's view.
                            diag.setView(group);
                            //We now show the message and the speaker in the dialog.
                            diag.setMessage("Johnny" + ": " + "Can you visit his house and look _______ it?...." + "\n\nCHOICES: ");
                            //Make sure the user cannot cancel the Dialog until the user answers it.
                            diag.setCancelable(false);
                            diag.setPositiveButton("CHECK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            final AlertDialog dialog = diag.create();
                            dialog.show();
                            //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //The radio button number 3 is our answer, so check if the user chose it.
                                    if (rb1.isChecked()) {
                                        questionThreeAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    /*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*/
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered && questionFourAnswered)
                                        {
                                            button.setEnabled(true);
                                        }
                                        dialog.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    /*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*/
                                    }
                                }
                            });
                        }

                        break;
                    case 5:

                        if(!questionFourAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group = new RadioGroup(Story4.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb1 = new RadioButton(Story4.this);
                            final RadioButton rb2 = new RadioButton(Story4.this);
                            //Set the radio text of the choices.
                            rb1.setText("guilty with");
                            rb2.setText("guilty of");
                            //Now we add the radio buttons to the view (Radio Group)
                            group.addView(rb1);
                            group.addView(rb2);
                            //Change the view's gravity.
                            group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb1.setGravity(Gravity.FILL_HORIZONTAL);
                            rb2.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag = new AlertDialog.Builder(Story4.this);
                            //Set the RadioGroup to the dialog's view.
                            diag.setView(group);
                            //We now show the message and the speaker in the dialog.
                            diag.setMessage("Duane" + ": " + "...I don’t want his parents to think I’m guilty __________ getting him the drugs...." + "\n\nCHOICES: ");
                            //Make sure the user cannot cancel the Dialog until the user answers it.
                            diag.setCancelable(false);
                            diag.setPositiveButton("CHECK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            final AlertDialog dialog = diag.create();
                            dialog.show();
                            //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //The radio button number 3 is our answer, so check if the user chose it.
                                    if (rb2.isChecked()) {
                                        questionFourAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    /*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*/
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered && questionFourAnswered)
                                        {
                                            button.setEnabled(true);
                                        }
                                        dialog.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    /*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*/
                                    }
                                }
                            });
                        }

                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void showConversation()
    {
        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Tommy, did you turn off your cell phone?  " +
                        "You are not answering my texts?  " +
                        "Everyone is looking for you.  " +
                        "We want to know what’s happening to you.", "Duane", 0));

        adapter.add(new SelectiveDisplayTextAdapter(false,
                "...", "(NO REPLY)", 0));

        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Hey Duane I’m fed up with Tommy.   " +
                        "We’ll never succeed __________ being a band if he " +
                        "goes missing for days and makes us cancel shows.", "Johnny", R.color.grey2));

        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I feel the same way.  His disappearance had better not be related __________  drugs.  " +
                        "He can’t handle that stuff.    " +
                        "He needs someone to look over him 24/7.  " +
                        "One mistake and he’s lost.", "Duane", R.color.grey2));

        adapter.add(new SelectiveDisplayTextAdapter(false,
                "Can you visit his house and look __________ it?  " +
                        "I don’t know what’s happened to him.  " +
                        "If it is drugs, he’s capable of anything—running away, stealing…anything.  " +
                        "This could be similar to the time he stole his brother’s car last year.", "Johnny", R.color.grey2));

        adapter.add(new SelectiveDisplayTextAdapter(false,
                "I’m a little frightened to go.  " +
                        "I don’t want his parents to think I’m guilty __________ getting him the drugs.  " +
                        "They hate me already because they don’t approve of the band. ", "Duane", R.color.grey2));
    }

    private void showResultingQuestion()
    {
        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(Story4.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story4.this);
        final RadioButton rb11 = new RadioButton(Story4.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story4.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Did anything bad happen to Tommy?");
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
                    intent = new Intent(Story4.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story4.this, StoryViewer.class);
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
