package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.njcuacm.adapters.DialogAdapter;
import org.njcuacm.adapters.DialogDisplayListAdapter;
import org.njcuacm.adapters.DialogViewTextAdapter;
import org.njcuacm.adapters.DisplayTextAdapter;
import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.filemanager.InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class StoryViewer extends ActionBarActivity {

    private TextAdapter adapter;
    public ListView lv;
    private ArrayList<String> storyList;
    private Scanner s;
    public int sp = InputOutput.ReadInteger("stn/" + Story1.sys);
    int p = 0;
    ListView listView;
    private DialogViewTextAdapter dialogViewTextAdapter;
    ArrayList<String> phraseArray;
    String phrases;
    DialogAdapter dialogAdapter = new DialogAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_viewer);
        //setTitle("Stories \t\t\t\t\t POINTS: " + Integer.toString(sp));
        setTitle("Stories");
        lv = (ListView) findViewById(R.id.storyViews);
        File file = new File(InputOutput.getFileDirectory() + "stn/" + Story1.stories);
        if (!file.exists())
        {
            AlertDialog.Builder diag = new AlertDialog.Builder(StoryViewer.this);
            diag.setMessage("Additional files are required to proceed...");
            diag.setCancelable(false);
            diag.setPositiveButton("DOWNLOAD THEM", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });

            final AlertDialog dialog = diag.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(StoryViewer.this, Story1.class);
                    startActivity(i);
                    finish();
                }
            });
        }
        adapter = new TextAdapter(getApplicationContext(), R.layout.story_list);
        lv.setAdapter(adapter);
        try {
            s = new Scanner(new File(InputOutput.getFileDirectory() + "stn/" + Story1.stories));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            AlertDialog.Builder diag = new AlertDialog.Builder(StoryViewer.this);
            diag.setMessage("Additional files are required to proceed...");
            diag.setCancelable(false);
            diag.setPositiveButton("DOWNLOAD THEM", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });

            final AlertDialog dialog = diag.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(StoryViewer.this, Story1.class);
                    startActivity(i);
                    finish();
                }
            });
        }
        while (s.hasNextLine())
        {
            String title = s.hasNextLine() ? s.nextLine() : "";
            String subTitle = s.hasNextLine() ? s.nextLine() : "";
            adapter.add(new DisplayTextAdapter(false, subTitle, title));
            /*switch (p){
                case 0:

                case 1:
                    if(sp <= 120)
                    {
                        lv.getChildAt(p).setEnabled(false);
                        lv.getChildAt(p).setClickable(false);
                    }
            }
            p++;*/
        }
        lv.setOnItemClickListener(new StoryItemListener());
        for (int i = 0; i <= lv.getAdapter().getCount(); i++)
        {
            ViewGroup viewGroup;
            switch (i){
                case 0:
                    break;
                case 1:
                    if (sp <= 120)
                    {
                        /*viewGroup = (ViewGroup) lv.getChildAt(i);
                        viewGroup.setEnabled(false);
                        viewGroup.setClickable(false);*/
                        lv.getAdapter().getView(i, null, lv).setEnabled(true);
                        lv.getAdapter().getView(i, null, lv).setClickable(true);
                        System.out.println("In IF");
                        /*lv.getChildAt(i).setEnabled(false);
                        lv.getChildAt(i).setClickable(false);*/
                    }
                    else
                    {
                        /*viewGroup = (ViewGroup) lv.getChildAt(i);
                        viewGroup.setEnabled(true);
                        viewGroup.setClickable(true);*/
                        lv.getAdapter().getView(i, null, null).setEnabled(false);
                        lv.getAdapter().getView(i, null, null).setClickable(false);
                        System.out.println("In ELSE");
                        /*lv.getChildAt(i).setEnabled(true);
                        lv.getChildAt(i).setClickable(true);*/
                    }
                    break;
                default:
                    break;
            }
            System.out.println(i);
        }
        /*storyList = InputOutput.readStringIntoArrayList("stn/" + Story1.stories);
        for (int s = 0; s <= storyList.size(); s++) {
            adapter.add(new DisplayTextAdapter(false, storyList.get(s), storyList.get(s)));
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
            dialogAdapter.dialogOut(StoryViewer.this, "One or more files were not found.\n" +
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
    }

    /* The click listener for ListView in the navigation drawer */
    public class StoryItemListener implements ListView.OnItemClickListener
    {
        AdapterView<?> parent;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            selectStory(position);
        }
    }

    private void selectStory(int position)
    {
        Intent i;
        switch(position){
            case 0:
                //i = new Intent("org.njcuacm.tenstory.Story" + (position + 2));
                i = new Intent(StoryViewer.this, Story2.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(StoryViewer.this, Story4.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(StoryViewer.this, Story3.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(StoryViewer.this, Story5.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(StoryViewer.this, Story6.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(StoryViewer.this, Story7.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(StoryViewer.this, Story8.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(StoryViewer.this, Story9.class);
                startActivity(i);
                break;
            case 8:
                i = new Intent(StoryViewer.this, Story10.class);
                startActivity(i);
                break;
            case 9:
                i = new Intent(StoryViewer.this, Story11.class);
                startActivity(i);
                break;
            case 10:
                i = new Intent(StoryViewer.this, StoryConclusion.class);
                startActivity(i);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_story_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.aboutInfo:
                showAbout();
                return true;
            case R.id.phrasal_verb_look:
                doPhrasalVerbLook();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doPhrasalVerbLook()
    {

        //final DialogAdapter dialogAdapter = new DialogAdapter();
        listView = new ListView(StoryViewer.this);
        //dialogViewTextAdapter = new DialogViewTextAdapter(getApplicationContext(), R.layout.dialog_view_list);
        listView.setAdapter(dialogViewTextAdapter);
        /*for(int i = 0; i <= 15; i++)
        {
            dialogViewTextAdapter.add(new DialogDisplayListAdapter("TEST " + i));
        }*/
        dialogAdapter.dialogOutWithCustomView(StoryViewer.this, "Phrasal Verbs", true, false, false, /*VIEW*/listView, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);

        /*if(p <= 120)
        {
            AlertDialog.Builder diag = new AlertDialog.Builder(StoryViewer.this);



            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            diag.setMessage("\n\nAn unexpected error occurred\n\n\n\n[ERROR_CODE_"+lineNumber+"]");
            diag.setCancelable(false);
            diag.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });

            final AlertDialog dialog = diag.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }*/



    }

    private void showAbout()
    {
        Intent intent = new Intent(StoryViewer.this, About.class);
        startActivity(intent);
    }
}