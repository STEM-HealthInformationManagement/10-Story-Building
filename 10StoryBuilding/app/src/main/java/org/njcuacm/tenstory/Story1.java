package org.njcuacm.tenstory;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import org.njcuacm.adapters.DialogAdapter;
import org.njcuacm.filemanager.DownloadFilesManager;
import org.njcuacm.net.NetConnector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Story1 extends ActionBarActivity {
    //Fetches and/or creates the file name
    public static String story1 = "story1.vpr";
    public static String ch1 = "ch1.vpr";
    public static String stories = "strs.vpr";
    public static String sys = "sys.vpr";
    public static String st2_phrasal_verbs = "st2_phrasal_vbs.vpr";
    public static String st_ch_str = "st_ch_str.vpr";
    public static String sys_change = "sys_change.vpr";
    //The following are addresses that are being fetched from the njcuacm server.
    private String fileAddress = DownloadFilesManager.SERVER_ADDRESS + "stories/" + story1;
    private String fileAddress1 = DownloadFilesManager.SERVER_ADDRESS + "chs/" + ch1;
    private String fileAddress2 = DownloadFilesManager.SERVER_ADDRESS + "settings/" + stories;
    private String fileAddress3 = DownloadFilesManager.SERVER_ADDRESS + "settings/" + sys;
    private String fileAddress4 = DownloadFilesManager.SERVER_ADDRESS + "settings/" + st2_phrasal_verbs;
    private String fileAddress5 = DownloadFilesManager.SERVER_ADDRESS + "settings/" + st_ch_str;
    private String fileAddress6 = DownloadFilesManager.SERVER_ADDRESS + "settings/" + sys_change;
    //All our file retrieved will be saved here.
    private String destinationDirectory = "str";
    private String destinationDirectory1 = "ch";
    private String destinationDirectory2 = "stn";
    //How much we're going to read at a time from the server
    final static int size = 1024;
    //These are just for logging some errors.
    static Logger log;
    static FileWriter fw;
    static FileHandler f;

    //Dat Download Manager
    DownloadFilesManager downloadFilesManager;

    ArrayList<Integer> settings = new ArrayList<>();
    ArrayList<Integer> currentSettings = new ArrayList<>();
    ArrayList<Integer> updatedSettings = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story1);
        //Get our layout
        final Button button = (Button) findViewById(R.id.button);
        final ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.downloadProgress);
        final ProgressBar waiter = (ProgressBar) findViewById(R.id.waiter);
        final TextView downloadText = (TextView) findViewById(R.id.downloadText);
        Intent intent = new Intent(this, Story1.class);
        //Set the visibilities to INVISIBLE so we can first check if the files exist.
        sv.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        waiter.setVisibility(View.VISIBLE);
        waiter.animate();
        downloadText.setVisibility(View.INVISIBLE);

        downloadFilesManager = new DownloadFilesManager();
        //Now we initialize FILE objects for checking them
        final File file = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/str/" + story1);
        final File file1 = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/ch/" + ch1);
        final File file2 = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/stn/" + stories);
        final File file3 = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/stn/" + sys);
        final File file4 = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/stn/" + st2_phrasal_verbs);
        final File file5 = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/stn/" + st_ch_str);
        final File file6 = new File(DownloadFilesManager.DEVICE_EXTERNAL_STORAGE + "/tsb/internal/stn/" + sys_change);
        //Button click listener. This is where we check if all of our files exist as well as allow user to enter the StoryViewer Class.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                //If none of these files exist, we will have to restart this activity
                if(!file.exists()|| !file1.exists() || !file2.exists() || !file3.exists())
                {
                    i = new Intent(Story1.this, Story1.class);
                    startActivity(i);
                    finish();
                }
                //Else we take the user to the Story Viewer list.
                else {
                    i = new Intent(Story1.this, StoryViewer.class);
                    startActivity(i);
                }
            }
        });
        //This Handler will let us execute a code at a given time, using MILLISECONDS
        final Handler handler = new Handler();
        //Are we connected to the internet?
        if(!NetConnector.isConnected(getApplicationContext()))
        {
            //Set our progressbar invisible and clear it's animation so it doesnt take up memory.
            waiter.setVisibility(View.INVISIBLE);
            waiter.clearAnimation();
            /**
             * This block of code will show a dialog for internet connection error
             * */
            AlertDialog.Builder alert = new AlertDialog.Builder(Story1.this);
            alert.setMessage("Error retrieving resources.\n" +
                    "Please check your internet connection and try again.")
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*startActivity(new Intent(Story1.this, Story1.class));
                    finish();*/
                    sv.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            })
            .show();
        }
        //If we are, check for all the files...
        else
        {
            //If FILE 1 doesn't exist, download it and make sure we're connected to the internet
            if((!file.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress, size, destinationDirectory, handler, true, sv, button);
            }
            /**
             * SAME AS ABOVE IF STATEMENT.
             * All File check/download statements are the same as above.
             * Only difference is that the file name is changed.
             */

            if((!file1.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress1, size, destinationDirectory1, handler, true, sv, button);
            }
            if((!file2.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress2, size, destinationDirectory2, handler, true, sv, button);
            }
            if((!file3.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress3, size, destinationDirectory2, handler, true, sv, button);
            }
            if((!file4.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress4, size, destinationDirectory2, handler, true, sv, button);
            }
            if((!file5.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter,
                        progressBar,
                        downloadText,
                        fileAddress5,
                        size,
                        destinationDirectory2,
                        handler,
                        true,
                        sv,
                        button);
            }
            if((!file6.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter,
                        progressBar,
                        downloadText,
                        fileAddress6,
                        size,
                        destinationDirectory2,
                        handler,
                        true,
                        sv,
                        button);
            }

        }
//If all of these files exist and we are also connected to the internet,
        //we then let the user move on to the StoryViewer class by enabling the buttons.
        if(file.exists() &&
                file1.exists() &&
                file2.exists() &&
                file3.exists() &&
                file4.exists() &&
                file5.exists() &&
                file6.exists() &&
                NetConnector.isConnected(getApplicationContext()))
        {

            //Read the Settings File to check the VERSIONS.
            /*Scanner scanner;
            String crs;
            try {
                scanner = new Scanner(file5);
                while ((crs = scanner.nextLine()) != null)
                {
                    String[] split = crs.split("\\+");
                    currentSettings.add(Integer.parseInt(split[1]));
                }

            } catch (FileNotFoundException | NoSuchElementException e) {
                e.printStackTrace();
                final DialogAdapter dialogAdapter = new DialogAdapter();
                dialogAdapter.dialogOut(Story1.this, "There was an error checking for updates.\n" + e.getMessage(), true, false, true, "OK", "QUIT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e.printStackTrace();
                        dialogAdapter.dialogOutDialog.dismiss();
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.exit(0);
                        finish();
                    }
                });
            }
            Thread settingsThread = new Thread() {

                public void run() {
                    URL url = null;
                    Scanner reader = null;
                    String s = "";

                    try

                    {
                        //The Settings URL on the server
                        url = new URL(DownloadFilesManager.SERVER_ADDRESS + "settings/" + st_ch_str);
                        //Open the url connection
                        URLConnection urlConnection = url.openConnection();
                        //Open the input-stream from the url
                        reader = new Scanner(urlConnection.getInputStream());
                        if(reader.hasNextLine()) {
                            while ((s = reader.nextLine()) != null) {
                                String[] splitted = s.split("\\+");
                                settings.add(Integer.parseInt(splitted[1]));
                            }
                        }

                    } catch (IOException | NoSuchElementException e)
                    {
                        e.printStackTrace();
                        Looper.prepare();
                        final DialogAdapter dialogAdapter = new DialogAdapter();
                        dialogAdapter.dialogOut(Story1.this, "Error check for updates\n" + e.getMessage(), true, false, true, "OK", null, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                e.printStackTrace();
                                dialogAdapter.dialogOutDialog.dismiss();
                            }
                        }, null);
                    } finally

                    {
                        if (reader != null) {
                            reader.close();
                        }
                    }

                    if (!settings.isEmpty())

                    {
                        for (int i = 0; i < currentSettings.size(); i++) {
                            if (!Objects.equals(currentSettings.get(i), settings.get(i))) {
                                updatedSettings.add(i);
                            }
                        }
                    }
                }
            };
            settingsThread.start();
            try
            {
                settingsThread.join();
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }*/

            sv.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            waiter.setVisibility(View.INVISIBLE);
            waiter.clearAnimation();
            downloadText.setVisibility(View.INVISIBLE);
        }
        //Else we show a dialog to the user.
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
            alert.setMessage("Error retrieving resources.\n" +
                    "Please check your internet connection and try again.");
        }
        /*else
        {
            *//*if(!NetConnector.isConnected(getApplicationContext()))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                alert.setMessage("Error retrieving resources.\n" +
                        "Please check your internet connection and try again.");
            }
            else
            {*//*
                sv.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                downloadText.setVisibility(View.INVISIBLE);
            *//*}*//*
        }*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
