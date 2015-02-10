package org.njcuacm.tenstory;

/**
 * Created by Saurabh on 2/7/2015.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story2);
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
                    if (editText1.getText().toString() != null) {
                        adapter.add(new DisplayTextAdapter(false, editText1.getText().toString(), "You"));
                        editText1.setText("");
                        scrollListView();
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
                if (editText1.getText().toString() != null || editText1.getText().toString() != "") {
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
                        String speaker = s.hasNextLine() ? s.nextLine() : "";
                        String text = s.hasNextLine() ? s.nextLine() : "";
                        adapter.add(new DisplayTextAdapter(false,
                                text, speaker));
                        scrollListView();
                    }
                    else
                    {
                        editText1.setVisibility(View.VISIBLE);
                        SEND.setVisibility(View.VISIBLE);
                        next.setVisibility(View.INVISIBLE);
                    }


                /*switch (storycount)
                {
                    case 1:
                        adapter.add(new DisplayTextAdapter(false,
                                "Yeah, you and me both.",
                                "Beth Matos"));
                        break;
                    case 2:
                        adapter.add(new DisplayTextAdapter(false,
                                "Believe me when I say that Bennett’s Big Steaks Restaurant is not going to be the same without you.",
                                "Kristen Kosakowski"));
                        break;
                    case 3:
                        adapter.add(new DisplayTextAdapter(false,
                                "Thanks Kristen.",
                                "Beth Matos"));
                        break;
                    case 4:
                        adapter.add(new DisplayTextAdapter(false,
                                "I mean it.",
                                "Kristen Kosakowski"));
                        break;
                    case 5:
                        adapter.add(new DisplayTextAdapter(false,
                                "So did you tell your husband yet?",
                                ""));
                        break;
                    case 6:
                        adapter.add(new DisplayTextAdapter(false,
                                "Oh, yeah.",
                                "Beth Matos"));
                        break;
                    case 7:
                        adapter.add(new DisplayTextAdapter(false,
                                "Of course.",
                                ""));
                        break;
                    case 8:
                        adapter.add(new DisplayTextAdapter(false,
                                "Jeff was really understanding.",
                                ""));
                        break;
                    case 9:
                        adapter.add(new DisplayTextAdapter(false,
                                "And he took your word for it and everything?",
                                "Kristen Kosakowski"));
                        break;
                    case 10:
                        adapter.add(new DisplayTextAdapter(false,
                                "If that was me, my husband would have thought I was full of it! ",
                                ""));
                        break;
                    case 11:
                        adapter.add(new DisplayTextAdapter(false,
                                "But then again he never believes anything I say anyway.",
                                ""));
                        break;
                    case 12:
                        adapter.add(new DisplayTextAdapter(false,
                                "Girl, you are so lucky.",
                                ""));
                        break;
                    case 13:
                        adapter.add(new DisplayTextAdapter(false,
                                "…Lucky?",
                                "Beth Matos"));
                        break;
                    case 14:
                        adapter.add(new DisplayTextAdapter(false,
                                "Yeah! ",
                                "Kristen Kosakowski"));
                        break;
                    case 15:
                        adapter.add(new DisplayTextAdapter(false,
                                "Lucky!",
                                "Not every girl can kiss the boss and then go home to kiss her husband."));
                        break;
                    case 16:
                        adapter.add(new DisplayTextAdapter(false,
                                "----------------------------------------------------------------------",
                                "December 26, 6:21 pm"));
                        break;
                    case 17:
                        adapter.add(new DisplayTextAdapter(false,
                                "So tired…But somebody has to wait these tables at work!",
                                "Beth Matos"));
                        break;
                    case 18:
                        adapter.add(new DisplayTextAdapter(false,
                                "Hey honey, sorry that work is so tough. ",
                                "Jeff Matos"));
                        break;
                    case 19:
                        adapter.add(new DisplayTextAdapter(false,
                                "Is Bennett still driving you girls crazy?",
                                ""));
                        break;
                    case 20:
                        adapter.add(new DisplayTextAdapter(false,
                                "Ha. Yeah. ",
                                "Beth Matos"));
                        break;
                    case 21:
                        adapter.add(new DisplayTextAdapter(false,
                                "You know him, always yelling about how we’re all useless.",
                                ""));
                        break;
                    case 22:
                        adapter.add(new DisplayTextAdapter(false,
                                "Man, I can’t believe I’m related to that guy. ",
                                "Jeff Matos"));
                        break;
                    case 23:
                        adapter.add(new DisplayTextAdapter(false,
                                "If anyone asks at the New Years’ party next week, I’m denying it.",
                                ""));
                        break;
                    case 24:
                        adapter.add(new DisplayTextAdapter(false,
                                "Stepbrothers don’t really count, do they?",
                                ""));
                        break;
                    case 25:
                        adapter.add(new DisplayTextAdapter(false,
                                "Yeah. Right. ",
                                "Beth Matos"));
                        break;
                    case 26:
                        adapter.add(new DisplayTextAdapter(false,
                                "Listen, I can’t talk right now, a bunch of customers came in. ",
                                ""));
                        break;
                    case 27:
                        adapter.add(new DisplayTextAdapter(false,
                                "Can we talk when I get home?",
                                ""));
                        break;
                    case 28:
                        adapter.add(new DisplayTextAdapter(false,
                                "Yeah sure, honey.  ",
                                "Jeff Matos"));
                        break;
                    case 29:
                        adapter.add(new DisplayTextAdapter(false,
                                "See you in a few.",
                                ""));
                        break;
                    case 30:
                        break;
                    *//*case 31:
                        adapter.add(new DisplayTextAdapter(false,
                                "",
                                ""));
                    case 32:
                        adapter.add(new DisplayTextAdapter(false,
                                "",
                                ""));
                    case 33:
                        adapter.add(new DisplayTextAdapter(false,
                                "",
                                ""));
                    case 34:
                        adapter.add(new DisplayTextAdapter(false,
                                "",
                                ""));*//*

                        default:
                            editText1.setVisibility(View.VISIBLE);
                            SEND.setVisibility(View.VISIBLE);
                            next.setVisibility(View.INVISIBLE);
                }
                storycount++;
                System.out.println(storycount);
                scrollListView();*/
            }
        });
        System.out.println(storycount);
        //addItems();
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
}