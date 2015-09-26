package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.njcuacm.adapters.DialogAdapter;
import org.njcuacm.adapters.DialogDisplayListAdapter;
import org.njcuacm.adapters.DialogViewTextAdapter;
import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.filemanager.InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Story3 extends ActionBarActivity {

    DialogAdapter dialogAdapter = new DialogAdapter();
    ListView listView;
    private DialogViewTextAdapter adapter;
    ArrayList<String> phraseArray;
    String phrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story3);
        setTitle("Story Two");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new DialogViewTextAdapter(getApplicationContext(), R.layout.dialog_view_list);
        /*for(int i = 0; i <= 15; i++)
        {
            adapter.add(new DialogDisplayListAdapter("TEST " + i));
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
                adapter.add(new DialogDisplayListAdapter(phraseArray.get(i)));
            }
        }
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

    private void doPhrasalVerbLook()
    {
        //final DialogAdapter dialogAdapter = new DialogAdapter();
        listView = new ListView(Story3.this);
        //adapter = new DialogViewTextAdapter(getApplicationContext(), R.layout.dialog_view_list);
        listView.setAdapter(adapter);
        /*for(int i = 0; i <= 15; i++)
        {
            adapter.add(new DialogDisplayListAdapter("TEST " + i));
        }*/
        dialogAdapter.dialogOutWithCustomView(Story3.this, "Phrasal Verbs", true, false, false, /*VIEW*/listView, "OK", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdapter.dialogOutDialog.dismiss();
            }
        }, null);
    }
}
