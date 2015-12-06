package org.njcuacm.tenstory;

/**
 * Created by Saurabh on 2/7/2015.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.njcuacm.adapters.DisplayTextAdapter;
import org.njcuacm.adapters.Questions;
import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.filemanager.InputOutput;

public class Story2 extends ActionBarActivity {
    //Some variable initializations.
    private TextAdapter adapter;
    private String ipsum;
    private EditText editText1;
    private static Random random;
    public int storycount = 1;
    public ListView lv;
    private Scanner s;
    int co = 0;
    int cont = 0;
    int sp = InputOutput.ReadInteger("stn/" + Story1.sys);
    Questions questions = new Questions();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story2);
        //Rename our title on the ActionBar and have the score show up as well.
        //setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
        setTitle("Story One - Quitting Doesn't End It");
        //Get our action bar on the top and set it to show up as a Back Button Arrow ( <- )
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Now we scan the STORY 1 File from the device
        try {
            s = new Scanner(new File(InputOutput.getFileDirectory() + "/str/" + Story1.story1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //This is our Points system. If the user has points 0 or less, then give the user 100 points.
        //We don't want the user to have 0 points...and then save them to the file.
        if(sp <= 0)
        {
            InputOutput.Write("stn/" + Story1.sys, "100");
        }
        //Now we retrieve the points from the file.
        sp = InputOutput.ReadInteger("stn/" + Story1.sys);
        //Layout all of our stuff.
        final Button next = (Button) findViewById(R.id.next);
        final Button questionButton = (Button) findViewById(R.id.showQuestionButton);
        questionButton.setVisibility(View.INVISIBLE);
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.showResultingQuestion(Story2.this, "Do you think Beth will succeed at her new job?");
            }
        });
        lv = (ListView) findViewById(R.id.listView1);

        //The Following is to customize the Action Bar of Story1 view.

        /*final RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        final TextView pntView = new TextView(this);
        pntView.setLeft(555);
        pntView.setText(Integer.toString(100));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(450, 25, 0, 0);
        pntView.setLayoutParams(lp);
        pntView.setGravity(Gravity.RIGHT);
        relativeLayout.addView(pntView);
        ActionBar bar = getSupportActionBar();
        bar.setCustomView(relativeLayout);
        bar.setDisplayShowCustomEnabled(true);
        bar.setTitle("Story One");*/

        //Show a dialog first showing the characters of the story.
        final AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("")
                .setMessage("Characters are: Beth Matos, Kristen Kosakowski, Amy, Jeff Matos, Bennett")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        //Show the title of the story.
        ab.setTitle("STORY ONE")
                .setMessage("Quitting Doesn’t End It (Beth Matos’ Phone)")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        //We're going to set our TextAdapter to use the `story_list` Layout.
        adapter = new TextAdapter(getApplicationContext(), R.layout.story_list);
        //Now set our ListView's adapter to the TextAdapter.
        lv.setAdapter(adapter);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //We'll check the scanner's input here and make sure the scanner has a next line.
                    if (s.hasNextLine())
                    {
                        try {
                            //Over here we will Scan the Choices file called "ch1.vpr"
                            Scanner scanner = new Scanner(new File(InputOutput.getFileDirectory() + "/ch/" + Story1.ch1));
                            //Make sure the file is readable.
                            if (scanner.hasNextLine()) {
                                //Check the next line again, and assign it to our variable.
                                String choice = scanner.hasNextLine() ? scanner.nextLine() : "";
                                //Now initialize an ArrayList and input the variable into the
                                //Array after splitting them using commas.
                                final ArrayList<String> c1 = new ArrayList<String>(Arrays.asList(choice.split(",")));
                                //Now because our choices are per-question-per-line, the next
                                //ArrayList will have choices for another question.
                                choice = scanner.hasNextLine() ? scanner.nextLine() : "";
                                final ArrayList<String> c2 = new ArrayList<String>(Arrays.asList(choice.split(",")));
                                //Store the Speaker's name
                                String speaker = s.hasNextLine() ? s.nextLine() : "";
                                //Store the message of the person speaking.
                                String text = s.hasNextLine() ? s.nextLine() : "";
                                //Here, we'll check if the message contains an underscore,
                                //and based on that, we'll ask a question.
                                    if (text.contains("__") && co == 0) {
                                        // Set an EditText view to get user input
                                        //final EditText getChoice = new EditText(Story2.this);
                                        //getChoice.setPadding(10, 0, 10, 0);

                                        //Over here, we'll create a RadioGroup, which will be
                                        //A view for our Dialog Box.
                                        final RadioGroup group = new RadioGroup(Story2.this);
                                        //We will have three radio buttons. Each radio button will contain a choice
                                        final RadioButton rb1 = new RadioButton(Story2.this);
                                        final RadioButton rb2 = new RadioButton(Story2.this);
                                        final RadioButton rb3 = new RadioButton(Story2.this);
                                        //Set the radio text of the choices.
                                        rb1.setText(c1.get(0));
                                        rb2.setText(c1.get(1));
                                        rb3.setText(c1.get(2));
                                        //Now we add the radio buttons to the view (Radio Group)
                                        group.addView(rb1);
                                        group.addView(rb2);
                                        group.addView(rb3);
                                        //Change the view's gravity.
                                        group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                                        //Set the gravity of the radio buttons to be in the middle.
                                        rb1.setGravity(Gravity.FILL_HORIZONTAL);
                                        rb2.setGravity(Gravity.FILL_HORIZONTAL);
                                        rb3.setGravity(Gravity.FILL_HORIZONTAL);
                                        //Initialize the dialog
                                        AlertDialog.Builder diag = new AlertDialog.Builder(Story2.this);
                                        //Set the RadioGroup to the dialog's view.
                                        diag.setView(group);
                                        //We now show the message and the speaker in the dialog.
                                        diag.setMessage(speaker + ": " + text + "\n\nCHOICES: ");
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
                                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                //The radio button number 3 is our answer, so check if the user chose it.
                                                if(rb3.isChecked())
                                                {
                                                    Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                                    sp += 10;
                                                    //setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                                    cont = 1;
                                                    co++;
                                                    dialog.dismiss();
                                                }
                                                else
                                                {
                                                    //The dialog should stay open here...
                                                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                                    sp -= 15;
                                                    //setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                                    cont = 0;
                                                }
                                            }
                                        });
                                    }
                                    //This does the same thing as above but for Question 2.
                                else if (text.contains("__") && co == 1) {
                                        // Set an EditText view to get user input
                                        //final EditText getChoice = new EditText(Story2.this);
                                        //getChoice.setPadding(10, 0, 10, 0);
                                        final RadioGroup group = new RadioGroup(Story2.this);
                                        final RadioButton rb1 = new RadioButton(Story2.this);
                                        final RadioButton rb2 = new RadioButton(Story2.this);
                                        final RadioButton rb3 = new RadioButton(Story2.this);
                                        rb1.setText(c2.get(0));
                                        rb2.setText(c2.get(1));
                                        rb3.setText(c2.get(2));
                                        group.addView(rb1);
                                        group.addView(rb2);
                                        group.addView(rb3);
                                        group.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                                        rb1.setGravity(Gravity.FILL_HORIZONTAL);
                                        rb2.setGravity(Gravity.FILL_HORIZONTAL);
                                        rb3.setGravity(Gravity.FILL_HORIZONTAL);
                                        AlertDialog.Builder diag = new AlertDialog.Builder(Story2.this);
                                        diag.setView(group);
                                        diag.setMessage(speaker + ": " + text + "\n\nCHOICES: ");
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
                                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                if(rb1.isChecked())
                                                {
                                                    Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                                    sp += 10;
                                                    //setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                                    cont = 2;
                                                    co++;
                                                    dialog.dismiss();
                                                }
                                                else
                                                {
                                                    //The dialog should stay open here...
                                                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                                    sp -= 15;
                                                    //setTitle("Story One \t\t\t\t\tPOINTS: " + Integer.toString(sp));
                                                    cont = 1;
                                                }
                                            }
                                        });
                                    }
                                //Here we add our Speaker and Text to the Adapter. The adapter will
                                //Do the rest of adding information to the ListView.
                                adapter.add(new DisplayTextAdapter(false,
                                        text, speaker));
                                //Scroll our ListView to the bottom.
                                scrollListView();
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            AlertDialog.Builder a = new AlertDialog.Builder(Story2.this);
                            a.setTitle("Fatal Error Occurred")
                                    .setMessage("Additional files are required to continue.")
                                    .setCancelable(false)
                                    .setPositiveButton("DOWNLOAD THEM", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Story2.this, Story1.class));
                                            finish();
                                        }
                                    })
                                    .show();
                        }

                    }
                    else
                    {
                        next.setVisibility(View.INVISIBLE);
                        questionButton.setVisibility(View.VISIBLE);
                    }
            }
        });
        System.out.println(storycount);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //The onBackPressed() method will go to the previous activity...ONLY if it was not FINISHED.
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method will help the ListView to scroll to the bottom.
     */
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