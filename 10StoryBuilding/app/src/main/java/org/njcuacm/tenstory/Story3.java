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
import org.njcuacm.adapters.DialogDisplayListAdapter;
import org.njcuacm.adapters.DialogViewTextAdapter;
import org.njcuacm.adapters.DisplayTextAdapter;
import org.njcuacm.adapters.FakebookDisplayTextAdapter;
import org.njcuacm.adapters.FakebookTextAdapter;
import org.njcuacm.adapters.Questions;
import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.exceptions.NotAChoiceException;
import org.njcuacm.filemanager.InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;


public class Story3 extends ActionBarActivity {

    DialogAdapter dialogAdapter = new DialogAdapter();
    DialogAdapter dialogAdapter1;
    ListView listView;
    private DialogViewTextAdapter dialogViewTextAdapter;
    ArrayList<String> phraseArray;
    String phrases;
    ListView lv;
    FakebookTextAdapter adapter;
    boolean questionOneAnswered = false;
    boolean questionTwoAnswered = false;
    boolean questionThreeAnswered = false;
    public String resultingAnswer;
    public Button showResultingQuestionButton;
    public Button nextButton;
    Questions questions = new Questions();
    int conversationSwitch = 0;
    //DialogAdapter dialogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story3);
        setTitle("Fakebook Announcement");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.postReplyListView);
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new FakebookTextAdapter(getApplicationContext(), R.layout.fakebook_comment_list);
        //Now set our ListView's dialogViewTextAdapter to the TextAdapter.
        lv.setAdapter(adapter);
        showResultingQuestionButton = (Button) findViewById(R.id.showResultingQuestionButton);
        nextButton = (Button) findViewById(R.id.nextItemButton);
        showResultingQuestionButton.setVisibility(View.INVISIBLE);
        showResultingQuestionButton.setEnabled(false);
        showResultingQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.showResultingQuestion(Story3.this, "Do you think Ravi is still interested in a relationship with Trevor?");
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFakebookComments(conversationSwitch);
                try {
                    showMainQuestions(conversationSwitch);
                } catch (NotAChoiceException e) {
                    e.printStackTrace();
                }
                conversationSwitch++;
                if (conversationSwitch > 5) {
                    nextButton.setEnabled(false);
                    nextButton.setVisibility(View.INVISIBLE);
                    showResultingQuestionButton.setVisibility(View.VISIBLE);
                    showResultingQuestionButton.setEnabled(true);
                }
            }
        });

        /*boolean b = false;
        for(int i = 0; i <= 10; i++) {
            b = !b;
            adapter.add(new FakebookDisplayTextAdapter(b,
                    "The text they say number " + i, "Test Speaker " + i, R.drawable.tsb_action_null_image));
            System.out.println(b);
        }*/

        dialogViewTextAdapter = new DialogViewTextAdapter(getApplicationContext(), R.layout.dialog_view_list);
        /*for(int i = 0; i <= 15; i++)
        {
            dialogViewTextAdapter.add(new DialogDisplayListAdapter("TEST " + i));
        }*/
        try {
            Scanner scanner = new Scanner(new File(InputOutput.getFileDirectory() + "/stn/" + Story1.st2_phrasal_verbs));
            if (scanner.hasNextLine()) {
                phrases = scanner.hasNextLine() ? scanner.nextLine() : "";
                phraseArray = new ArrayList<String>(Arrays.asList(phrases.split(",")));
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            dialogAdapter.dialogOut(Story3.this, "One or more files were not found.\n" +
                    "Please re-start this application if error persists.",
                    true, false, true, "OK", null, null, null);
        }
        if (!phraseArray.isEmpty())
        {
            for (int i = 0; i < phraseArray.size() - 1; i++)
            {
                dialogViewTextAdapter.add(new DialogDisplayListAdapter(phraseArray.get(i)));
            }
        }

        int cardImage = R.drawable.tsb_action_null_image;

        final ArrayList<Card> cards = new ArrayList<Card>();
        final CardListView listView = (CardListView) findViewById(R.id.myFakebookList);
            Card card = new Card(this.getApplicationContext());
            card.setId(Integer.toString(0));

            // Create a CardHeader
            CardHeader header = new CardHeader(getApplicationContext());
            // Add Header to card
            header.setTitle("Danny McAden is married to Gregory Adams");
            card.setTitle("I just created my new FakeBook account! WOOT!");
            card.addCardHeader(header);

            CardThumbnail thumb = new CardThumbnail(getApplicationContext());
            thumb.setDrawableResource(cardImage);
            card.addCardThumbnail(thumb);

            cards.add(card);
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    System.out.println("Card Clicked: " + card.getId());
                }
            });
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getApplicationContext(), cards);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

        dialogAdapter = new DialogAdapter();
        dialogAdapter.dialogOut(this, "To answer, please click on the GREY comments and choose an answer.", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);

        dialogAdapter1 = new DialogAdapter();
        dialogAdapter1.dialogOut(this, "STORY THREE\nWedding Date", true, false, true, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter1.dialogOutDialog.dismiss();
            }
        }, null);

        //lv.getItemAtPosition(0).
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        //
                        if(!questionOneAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group = new RadioGroup(Story3.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb1 = new RadioButton(Story3.this);
                            final RadioButton rb2 = new RadioButton(Story3.this);
                            //Set the radio text of the choices.
                            rb1.setText("happened on");
                            rb2.setText("happened to");
                            //Now we add the radio buttons to the view (Radio Group)
                            group.addView(rb1);
                            group.addView(rb2);
                            //Change the view's gravity.
                            group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb1.setGravity(Gravity.FILL_HORIZONTAL);
                            rb2.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag = new AlertDialog.Builder(Story3.this);
                            //Set the RadioGroup to the dialog's view.
                            diag.setView(group);
                            //We now show the message and the speaker in the dialog.
                            diag.setMessage("Ravi Gupta" + ": " + "...I’m so fond of the two of you.  I couldn’t be happier.  This couldn’t have happened __________ a pair of nicer people..." + "\n\nCHOICES: ");
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
                                        questionOneAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    *//*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*//*
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered)
                                        {
                                            showResultingQuestion();
                                        }
                                        dialog.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    *//*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*//*
                                    }
                                }
                            });
                        }
                        break;
                    case 3:
                        //
                        if(!questionTwoAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group1 = new RadioGroup(Story3.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb4 = new RadioButton(Story3.this);
                            final RadioButton rb5 = new RadioButton(Story3.this);
                            final RadioButton rb6 = new RadioButton(Story3.this);
                            //Set the radio text of the choices.
                            rb4.setText("turn off");
                            rb5.setText("turn in");
                            rb6.setText("turn on");
                            //Now we add the radio buttons to the view (Radio Group)
                            group1.addView(rb4);
                            group1.addView(rb5);
                            group1.addView(rb6);
                            //Change the view's gravity.
                            group1.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb4.setGravity(Gravity.FILL_HORIZONTAL);
                            rb5.setGravity(Gravity.FILL_HORIZONTAL);
                            rb6.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag1 = new AlertDialog.Builder(Story3.this);
                            //Set the RadioGroup to the dialog's view.
                            diag1.setView(group1);
                            //We now show the message and the speaker in the dialog.
                            diag1.setMessage("Ravi gupta" + ": " + "You can turn __________ the excuses.   I know you don’t have work..." + "\n\nCHOICES: ");
                            //Make sure the user cannot cancel the Dialog until the user answers it.
                            diag1.setCancelable(false);
                            diag1.setPositiveButton("CHECK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            final AlertDialog dialog1 = diag1.create();
                            dialog1.show();
                            //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
                            dialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //The radio button number 3 is our answer, so check if the user chose it.
                                    if (rb5.isChecked()) {
                                        questionTwoAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    *//*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*//*
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered)
                                        {
                                            showResultingQuestion();
                                        }
                                        dialog1.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    *//*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*//*
                                    }
                                }
                            });
                        }
                        break;
                    case 5:
                        //
                        if(!questionThreeAnswered) {
                            // Set an EditText view to get user input
                            //final EditText getChoice = new EditText(Story2.this);
                            //getChoice.setPadding(10, 0, 10, 0);

                            //Over here, we'll create a RadioGroup, which will be
                            //A view for our Dialog Box.
                            final RadioGroup group2 = new RadioGroup(Story3.this);
                            //We will have three radio buttons. Each radio button will contain a choice
                            final RadioButton rb7 = new RadioButton(Story3.this);
                            final RadioButton rb8 = new RadioButton(Story3.this);
                            final RadioButton rb9 = new RadioButton(Story3.this);
                            //Set the radio text of the choices.
                            rb7.setText("capable with");
                            rb8.setText("capable in");
                            rb9.setText("capable of");
                            //Now we add the radio buttons to the view (Radio Group)
                            group2.addView(rb7);
                            group2.addView(rb8);
                            group2.addView(rb9);
                            //Change the view's gravity.
                            group2.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                            //Set the gravity of the radio buttons to be in the middle.
                            rb7.setGravity(Gravity.FILL_HORIZONTAL);
                            rb8.setGravity(Gravity.FILL_HORIZONTAL);
                            rb9.setGravity(Gravity.FILL_HORIZONTAL);
                            //Initialize the dialog
                            AlertDialog.Builder diag2 = new AlertDialog.Builder(Story3.this);
                            //Set the RadioGroup to the dialog's view.
                            diag2.setView(group2);
                            //We now show the message and the speaker in the dialog.
                            diag2.setMessage("Ravi gupta" + ": " + "I didn’t think you were capable __________ that type of prejudice" + "\n\nCHOICES: ");
                            //Make sure the user cannot cancel the Dialog until the user answers it.
                            diag2.setCancelable(false);
                            diag2.setPositiveButton("CHECK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            final AlertDialog dialog2 = diag2.create();
                            dialog2.show();
                            //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
                            dialog2.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //The radio button number 3 is our answer, so check if the user chose it.
                                    if (rb7.isChecked()) {
                                        questionThreeAnswered = true;
                                        Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                    *//*sp += 10;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 1;
                                    co++;*//*
                                        if (questionOneAnswered && questionTwoAnswered && questionThreeAnswered)
                                        {
                                            showResultingQuestion();
                                        }
                                        dialog2.dismiss();
                                    } else {
                                        //The dialog should stay open here...
                                        Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                    *//*sp -= 15;
                                    setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                    cont = 0;*//*
                                    }
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
            }
        });*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //onBackPressed();
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.story3_phrasal_verb_menu:
                doPhrasalVerbLook();
                return true;
            /*case R.id.home:
                onBackPressed();
                return true;*/
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }

    public void doPhrasalVerbLook()
    {
        //final DialogAdapter dialogAdapter = new DialogAdapter();
        listView = new ListView(Story3.this);
        //dialogViewTextAdapter = new DialogViewTextAdapter(getApplicationContext(), R.layout.dialog_view_list);
        listView.setAdapter(dialogViewTextAdapter);
        /*for(int i = 0; i <= 15; i++)
        {
            dialogViewTextAdapter.add(new DialogDisplayListAdapter("TEST " + i));
        }*/
        dialogAdapter.dialogOutWithCustomView(Story3.this, "Phrasal Verbs", true, false, false, /*VIEW*/listView, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }

    private void addFakebookComments(int position)
    {
        switch (position) {
            case 0:
                adapter.add(new FakebookDisplayTextAdapter(false,
                        "If I could \"like\" this a million times, I would.  " +
                                "I'm so fond of the two of you.  " +
                                "I couldn't be happier.  " +
                                "This couldn't have happened __________ a pair of nicer people.  " +
                                "I can't wait until the party. ", "Ravi Gupta ", R.drawable.tsb_action_null_image, 0));
                break;
            case 1:
                adapter.add(new FakebookDisplayTextAdapter(false,
                        "Hey Trevor, do you happen to be free on Saturday night? " +
                                "We're invited to a wedding reception-" +
                                "-Danny McAden is now married to Gregory Adams, and they are having a party.", "Ravi Gupta", R.drawable.tsb_action_null_image, 0));
                break;
            case 2:
                adapter.add(new FakebookDisplayTextAdapter(false,
                        "Two guys!   No way.  " +
                                "I don't agree to that stuff.  " +
                                "I don't want to be responsible for society coming to an end!  " +
                                "I won't be a part of it.   " +
                                "And, oh, I'm busy with work that night—Steve is on vacation and asked if I could work for him.", "Trevor", R.drawable.tsb_action_null_image, 0));
                break;
            case 3:
                adapter.add(new FakebookDisplayTextAdapter(false,
                        "You can turn __________ the excuses.   " +
                                "I know you don't have work.  " +
                                "These people are in love. " +
                                "I'm disappointed with your attitude.  " +
                                "I should be more careful with who I ask…and who I date. ", "Ravi Gupta", R.drawable.tsb_action_null_image, 0));
                break;
            case 4:
                adapter.add(new FakebookDisplayTextAdapter(false,
                        "Come on.  I'm a good person.  Two guys getting married--Yuck.", "Trevor", R.drawable.tsb_action_null_image, 0));
                break;
            case 5:
                adapter.add(new FakebookDisplayTextAdapter(false,
                        "I didn't think you were capable __________ that type of prejudice.  " +
                                "I'll just go by myself.  Don't bother calling me again.", "Ravi Gupta", R.drawable.tsb_action_null_image, 0));
                break;
        }
    }

    private void showMainQuestions(int position) throws NotAChoiceException
    {
        switch (position)
        {
            case 0:
                questions.showTwoChoices(
                        Story3.this,
                        "on",
                        "to",
                        "If I could \"like\" this a million times, I would.  " +
                                "I'm so fond of the two of you.  " +
                                "I couldn't be happier.  " +
                                "This couldn't have happened __________ a pair of nicer people.  " +
                                "I can't wait until the party.",
                        1);
                break;
            case 3:
                questions.showThreeChoices(
                        Story3.this,
                        "off",
                        "in",
                        "on",
                        "You can turn __________ the excuses.   " +
                                "I know you don't have work.  " +
                                "These people are in love. " +
                                "I'm disappointed with your attitude.  " +
                                "I should be more careful with who I ask ... and who I date.  ",
                        0
                );
                break;
            case 5:
                questions.showThreeChoices(
                        Story3.this,
                        "with",
                        "in",
                        "of",
                        "I didn't think you were capable __________ that type of prejudice.  " +
                                "I'll just go by myself.  " +
                                "Don't bother calling me again.",
                        0
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
        final RadioGroup group3 = new RadioGroup(Story3.this);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(Story3.this);
        final RadioButton rb11 = new RadioButton(Story3.this);
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
        AlertDialog.Builder diag3 = new AlertDialog.Builder(Story3.this);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage("Do you think Ravi is still interested in a relationship with Trevor?");
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
                    intent = new Intent(Story3.this, StoryViewer.class);
                    startActivity(intent);
                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(Story3.this, StoryViewer.class);
                    startActivity(intent);
                }
            }
        });
    }
}
