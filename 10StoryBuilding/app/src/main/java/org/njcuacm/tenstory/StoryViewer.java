package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
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

import org.njcuacm.adapters.DisplayTextAdapter;
import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.filemanager.InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class StoryViewer extends ActionBarActivity {

    private TextAdapter adapter;
    public ListView lv;
    private ArrayList<String> storyList;
    private Scanner s;
    public int sp = InputOutput.ReadInteger("stn/" + Story1.sys);
    int p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_viewer);
        setTitle("Stories \t\t\t\t\t POINTS: " + Integer.toString(sp));
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
            case R.id.phrasal_verb_look:
                doPhrasalVerbLook();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void doPhrasalVerbLook()
    {
        if(p <= 120)
        {
            AlertDialog.Builder diag = new AlertDialog.Builder(StoryViewer.this);
            diag.setMessage("You must have more than 120 points to see the phrasal verbs.");
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
        }
    }
}