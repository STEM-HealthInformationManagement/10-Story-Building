package org.njcuacm.tenstory;

/**
 * Created by Saurabh on 2/7/2015.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.njcuacm.adapters.DisplayTextAdapter;
import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.filemanager.InputOutput;

public class Story2 extends ActionBarActivity {
    private TextAdapter adapter;
    private String ipsum;
    private EditText editText1;
    private static Random random;
    public int storycount = 1;
    public ListView lv;
    private Scanner s;
    int co = 0;
    int cont = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story2);
        setTitle("Story One");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            s = new Scanner(new File(InputOutput.getFileDirectory() + "/str/" + Story1.story1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        random = new Random();
        ipsum = "Lorem Ipsum";
        final Button next = (Button) findViewById(R.id.next);
        lv = (ListView) findViewById(R.id.listView1);
        final Button SEND = (Button) findViewById(R.id.send);
        final AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("")
                .setMessage("Characters are: Beth Matos, Kristen Kosakowski, Amy, Jeff Matos, Bennett")
                .setCancelable(true)
                .show();
        ab.setTitle("STORY ONE")
                .setMessage("Quitting Doesn’t End It (Beth Matos’ Phone)")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        adapter = new TextAdapter(getApplicationContext(), R.layout.story_list);

        lv.setAdapter(adapter);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText1.setVisibility(View.INVISIBLE);
        SEND.setVisibility(View.INVISIBLE);
        editText1.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    if (editText1.getText().toString() != null || !editText1.getText().toString().equals("")) {
                        adapter.add(new DisplayTextAdapter(false, editText1.getText().toString(), "You"));
                        editText1.setText("");
                        scrollListView();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        AlertDialog.Builder diag = new AlertDialog.Builder(Story2.this);
                        diag.setMessage("Congratulations! You've finished Story One!\n\n" +
                                "You have now unlocked Story Two!");
                        diag.setCancelable(false);
                        diag.setPositiveButton("AWESOME!", new DialogInterface.OnClickListener() {

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
                                Intent i = new Intent(Story2.this, Story1.class);
                                startActivity(i);
                                finish();
                            }
                        });
                    }
                    return true;
                }
                return false;
            }
        });
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollListView();
            }
        });
        SEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString() != null || !editText1.getText().toString().equals("")) {
                    adapter.add(new DisplayTextAdapter(false, editText1.getText().toString(), "You"));
                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);

                    editText1.setText("");
                    editText1.clearFocus();
                    imm.hideSoftInputFromInputMethod(editText1.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                    scrollListView();
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (s.hasNextLine())
                    {
                        try {
                            Scanner scanner = new Scanner(new File(InputOutput.getFileDirectory() + "/ch/" + Story1.ch1));
                            if (scanner.hasNextLine()) {
                                String choice = scanner.hasNextLine() ? scanner.nextLine() : "";
                                final ArrayList<String> c1 = new ArrayList<String>(Arrays.asList(choice.split(",")));
                                choice = scanner.hasNextLine() ? scanner.nextLine() : "";
                                final ArrayList<String> c2 = new ArrayList<String>(Arrays.asList(choice.split(",")));
                                String speaker = s.hasNextLine() ? s.nextLine() : "";
                                String text = s.hasNextLine() ? s.nextLine() : "";
                                    if (text.contains("__") && co == 0) {
                                        // Set an EditText view to get user input
                                        //final EditText getChoice = new EditText(Story2.this);
                                        //getChoice.setPadding(10, 0, 10, 0);
                                        final RadioGroup group = new RadioGroup(Story2.this);
                                        final RadioButton rb1 = new RadioButton(Story2.this);
                                        final RadioButton rb2 = new RadioButton(Story2.this);
                                        final RadioButton rb3 = new RadioButton(Story2.this);
                                        rb1.setText(c1.get(0));
                                        rb2.setText(c1.get(1));
                                        rb3.setText(c1.get(2));
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
                                                if(rb3.isChecked())
                                                {
                                                    Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                                                    cont = 1;
                                                    co++;
                                                    dialog.dismiss();
                                                }
                                                else
                                                {
                                                    //The dialog should stay open here...
                                                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                                    cont = 0;
                                                }
                                            }
                                        });
                                    }
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
                                                    cont = 2;
                                                    co++;
                                                    dialog.dismiss();
                                                }
                                                else
                                                {
                                                    //The dialog should stay open here...
                                                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                                                    cont = 1;
                                                }
                                            }
                                        });
                                    }
                                adapter.add(new DisplayTextAdapter(false,
                                        text, speaker));
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
                        editText1.setVisibility(View.VISIBLE);
                        SEND.setVisibility(View.VISIBLE);
                        next.setVisibility(View.INVISIBLE);
                    }
            }
        });
        System.out.println(storycount);
    }

    private void addItems() {
        adapter.add(new DisplayTextAdapter(true, "Girl, I cannot believe you quit today.\nEspecially with the holidays so close.", "Kristen Kosakowski"));
    }

    private static int getRandomInteger(int aStart, int aEnd) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long) aEnd - (long) aStart + 1;
        long fraction = (long) (range * random.nextDouble());
        return (int) (fraction + aStart);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    class DiagListenerForChoices implements View.OnClickListener {
        private final Dialog dialog;
        public DiagListenerForChoices(Dialog dialog) {
            this.dialog = dialog;
        }
        @Override
        public void onClick(View v) {

        }
    }
}