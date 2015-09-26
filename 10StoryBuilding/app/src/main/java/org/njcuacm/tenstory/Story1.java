package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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

    ArrayList<String> settings = new ArrayList<String>();
    ArrayList<String> currentSettings = new ArrayList<String>();

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
                /*//We won't need the ROUNDED PROGRESSBAR here.
                waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                //We will need the HORIZONTAL PROGRESSBAR HERE, so set them to visible
                progressBar.setVisibility(View.VISIBLE);
                downloadText.setVisibility(View.VISIBLE);
                downloadText.setText("Downloading required resources...");

                *//**
                 * Now we want to make sure that we download our files using another thread,
                 * so that our MAIN thread is not interrupted by the download.
                 *//*
                new Thread() {
                    *//**
                     * This is a required method needed for the Thread class.
                     * *//*
                    @Override
                    public void run() {
                        //We're going to retrieve our file size first so we can put the correct
                        //information in the progress bar.
                        int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress);
                        System.out.println("File Size Retrieved is: " + pseudoFileSize);
                        //We're going to set the max of our progressbar to the file size retrieved.
                        progressBar.setMax(pseudoFileSize);
                        *//******************************************************************************//*
                        //Get the location of the LAST SLASH in out file address URL path
                        int slashIndex = fileAddress.lastIndexOf('/');
                        //Now get the last PERIOD location from the same URL
                        int periodIndex = fileAddress.lastIndexOf('.');
                        //Now get the file name AFTER the last SLASH in the URL.
                        String fileName = fileAddress.substring(slashIndex + 1);
                        //Check if there's actually a file using the location INTEGERS
                        if (periodIndex >= 1 && slashIndex >= 0
                                && slashIndex < fileAddress.length() - 1) {

                            //Initialize our output stream. This is what we're going to use to write to a file
                            OutputStream outStream = null;
                            //Initialize the URL connection
                            URLConnection uCon = null;
                            //Initialize the input stream. This is where we're going to read the online file.
                            InputStream is = null;
                            //Gather the FULL storage location we're going to save our file inside the device.
                            String fullDestination = Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory;
                            //Initialize the FILE using our FULL PATH.
                            File dest = new File(fullDestination);
                            //Now make sure our DIRECTORIES inf the FULL PATH exist before download the file.
                            //If they don't exist, the `mkdirs()` method will automatically create Directories for us.
                            dest.mkdirs();
                            try {
                                //dest.createNewFile();
                                //Initialize the ONLINE url path.
                                URL Url;
                                //INITIALIZE the buffer bytes we're going to read from the online file.
                                byte[] buf;
                                //This is where we're going to COUNT the bytes we read from the online file
                                int ByteRead, ByteWritten = 0;
                                //Now set the online file path.
                                Url = new URL(fileAddress);
                                //We're going to tell the Output Stream to start writing to the file on the device now.
                                outStream = new BufferedOutputStream(new FileOutputStream(fullDestination + "/" + fileName));

                                //We first open connection to the ONLINE FILE.
                                uCon = Url.openConnection();
                                //Now we're going to tell input stream to read the online file
                                is = uCon.getInputStream();
                                //Set the buffer size
                                buf = new byte[size];
                                //While our input stream actually has bytes to read, we're going to READ the file
                                while ((ByteRead = is.read(buf)) != -1) {
                                    //Start writing our input stream bytes to our device.
                                    outStream.write(buf, 0, ByteRead);
                                    //Make sure our bytes read and written are in SYNC.
                                    ByteWritten += ByteRead;
                                    //Have the thread to sleep for a bit
                                    Thread.sleep(300);
                                    //These are just for debugging.
                                    System.out.println(ByteWritten);
                                    progressBar.setProgress(ByteWritten);
                                }
                                System.out.println("Downloaded Successfully.");

                                System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory + "/" + fileName + "\"\nNo ofbytes :" + ByteWritten);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                //THE FNALLY blocks are to flush/close your resources so we don't have a memory leak.
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (outStream != null) {
                                        outStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.err.println("Error on Path or File Name.");
                        }

                        *//******************************************************************************//*
                        //Looper.prepare();
                        //Start the Spinner animation in 1.5 seconds.
                        //Execute the handler code to have the buttons show up. so the user can move on the to the story viewer.
                        handler.postDelayed(new Runnable() {
                            //Run the thread
                            public void run() {
                                sv.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                downloadText.setVisibility(View.INVISIBLE);
                            }
                        }, 500);
                        //Start our RUN method of RUNNABLE CLASS.
                        super.run();
                    }
                    //NOW start the Thread BEFORE the code starts to run.
                }.start();*/
            }
            /**
             * SAME AS ABOVE IF STATEMENT.
             * All File check/download statements are the same as above.
             * Only difference is that the file name is changed.
             */

            if((!file1.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress1, size, destinationDirectory1, handler, true, sv, button);
                /*waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                progressBar.setVisibility(View.VISIBLE);
                downloadText.setVisibility(View.VISIBLE);
                downloadText.setText("Fetching additional properties...");

                new Thread() {
                    @Override
                    public void run() {
                        int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress1);
                        System.out.println("File Size Retrieved is: " + pseudoFileSize);
                        progressBar.setProgress(0);
                        progressBar.setMax(pseudoFileSize);
                        *//******************************************************************************//*
                        int slashIndex = fileAddress1.lastIndexOf('/');
                        int periodIndex = fileAddress1.lastIndexOf('.');
                        String fileName1 = fileAddress1.substring(slashIndex + 1);

                        if (periodIndex >= 1 && slashIndex >= 0
                                && slashIndex < fileAddress1.length() - 1) {


                            OutputStream outStream = null;
                            URLConnection uCon = null;
                            InputStream is = null;
                            String fullDestination1 = Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory1;
                            File dest1 = new File(fullDestination1);
                            dest1.mkdirs();
                            try {
                                //dest.createNewFile();
                                URL Url;
                                byte[] buf;
                                int ByteRead, ByteWritten = 0;
                                Url = new URL(fileAddress1);
                                outStream = new BufferedOutputStream(new FileOutputStream(fullDestination1 + "/" + fileName1));

                                uCon = Url.openConnection();
                                is = uCon.getInputStream();
                                buf = new byte[size];
                                while ((ByteRead = is.read(buf)) != -1) {
                                    outStream.write(buf, 0, ByteRead);
                                    ByteWritten += ByteRead;
                                    Thread.sleep(300);
                                    System.out.println(ByteWritten);
                                    progressBar.setProgress(ByteWritten);
                                }
                                System.out.println("Downloaded Successfully.");

                                System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory1 + "/" + fileName1 + "\"\nNo ofbytes :" + ByteWritten);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (outStream != null) {
                                        outStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.err.println("Error on Path or File Name.");
                        }

                        *//******************************************************************************//*
                        //Looper.prepare();
                        //Start the Spinner animation in 1.5 seconds.
                        handler.postDelayed(new Runnable() {
                            //Run the thread
                            public void run() {
                                sv.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                downloadText.setVisibility(View.INVISIBLE);
                            }
                        }, 500);
                        super.run();
                    }
                }.start();*/
            }
            if((!file2.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress2, size, destinationDirectory2, handler, true, sv, button);
                /*waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                progressBar.setVisibility(View.VISIBLE);
                downloadText.setVisibility(View.VISIBLE);
                downloadText.setText("Fetching additional settings...");

                new Thread() {
                    @Override
                    public void run() {
                        int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress2);
                        System.out.println("File Address: " + fileAddress2);
                        System.out.println("File Size Retrieved is: " + pseudoFileSize);
                        progressBar.setProgress(0);
                        progressBar.setMax(pseudoFileSize);
                        *//******************************************************************************//*
                        int slashIndex = fileAddress2.lastIndexOf('/');
                        int periodIndex = fileAddress2.lastIndexOf('.');
                        String fileName2 = fileAddress2.substring(slashIndex + 1);

                        if (periodIndex >= 1 && slashIndex >= 0
                                && slashIndex < fileAddress2.length() - 1) {


                            OutputStream outStream = null;
                            URLConnection uCon = null;
                            InputStream is = null;
                            String fullDestination2 = Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory2;
                            System.out.println("Full Destination: " + fullDestination2);
                            File dest2 = new File(fullDestination2);
                            dest2.mkdirs();
                            try {
                                //dest.createNewFile();
                                URL Url;
                                byte[] buf;
                                int ByteRead, ByteWritten = 0;
                                //Make sure to change dat file address for every check!
                                Url = new URL(fileAddress2);
                                outStream = new BufferedOutputStream(new FileOutputStream(fullDestination2 + "/" + fileName2));

                                uCon = Url.openConnection();
                                is = uCon.getInputStream();
                                buf = new byte[size];
                                while ((ByteRead = is.read(buf)) != -1) {
                                    outStream.write(buf, 0, ByteRead);
                                    ByteWritten += ByteRead;
                                    Thread.sleep(300);
                                    System.out.println(ByteWritten);
                                    progressBar.setProgress(ByteWritten);
                                }
                                System.out.println("Downloaded Successfully.");

                                System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory2 + "/" + fileName2 + "\"\nNo of bytes :" + ByteWritten);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (outStream != null) {
                                        outStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.err.println("Error on Path or File Name.");
                        }

                        *//******************************************************************************//*
                        //Looper.prepare();
                        //Start the Spinner animation in 1.5 seconds.
                        handler.postDelayed(new Runnable() {
                            //Run the thread
                            public void run() {
                                sv.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                downloadText.setVisibility(View.INVISIBLE);
                            }
                        }, 500);
                        super.run();
                    }
                }.start();*/
            }
            if((!file3.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress3, size, destinationDirectory2, handler, true, sv, button);
                /*waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                progressBar.setVisibility(View.VISIBLE);
                downloadText.setVisibility(View.VISIBLE);
                downloadText.setText("Fetching additional settings...");

                new Thread() {
                    @Override
                    public void run() {
                        int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress3);
                        System.out.println("File Address: " + fileAddress3);
                        System.out.println("File Size Retrieved is: " + pseudoFileSize);
                        progressBar.setProgress(0);
                        progressBar.setMax(pseudoFileSize);
                        *//******************************************************************************//*
                        int slashIndex = fileAddress3.lastIndexOf('/');
                        int periodIndex = fileAddress3.lastIndexOf('.');
                        String fileName3 = fileAddress3.substring(slashIndex + 1);

                        if (periodIndex >= 1 && slashIndex >= 0
                                && slashIndex < fileAddress3.length() - 1) {


                            OutputStream outStream = null;
                            URLConnection uCon = null;
                            InputStream is = null;
                            String fullDestination3 = Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory2;
                            System.out.println("Full Destination: " + fullDestination3);
                            File dest3 = new File(fullDestination3);
                            dest3.mkdirs();
                            try {
                                //dest.createNewFile();
                                URL Url;
                                byte[] buf;
                                int ByteRead, ByteWritten = 0;
                                //Make sure to change dat file address for every check!
                                Url = new URL(fileAddress3);
                                outStream = new BufferedOutputStream(new FileOutputStream(fullDestination3 + "/" + fileName3));

                                uCon = Url.openConnection();
                                is = uCon.getInputStream();
                                buf = new byte[size];
                                while ((ByteRead = is.read(buf)) != -1) {
                                    outStream.write(buf, 0, ByteRead);
                                    ByteWritten += ByteRead;
                                    Thread.sleep(300);
                                    System.out.println(ByteWritten);
                                    progressBar.setProgress(ByteWritten);
                                }
                                System.out.println("Downloaded Successfully.");

                                System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory2 + "/" + fileName3 + "\"\nNo of bytes :" + ByteWritten);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (outStream != null) {
                                        outStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.err.println("Error on Path or File Name.");
                        }

                        *//******************************************************************************//*
                        //Looper.prepare();
                        //Start the Spinner animation in 1.5 seconds.
                        handler.postDelayed(new Runnable() {
                            //Run the thread
                            public void run() {
                                sv.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                downloadText.setVisibility(View.INVISIBLE);
                            }
                        }, 500);
                        super.run();
                    }
                }.start();*/
            }
            if((!file4.exists()) && NetConnector.isConnected(getApplicationContext())) {
                downloadFilesManager.downloadFile(waiter, progressBar, downloadText, fileAddress4, size, destinationDirectory2, handler, true, sv, button);
                /*waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                progressBar.setVisibility(View.VISIBLE);
                downloadText.setVisibility(View.VISIBLE);
                downloadText.setText("Downloading required resources...");

                new Thread() {
                    @Override
                    public void run() {
                        int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress4);
                        System.out.println("File Size Retrieved is: " + pseudoFileSize);
                        progressBar.setMax(pseudoFileSize);
                        *//******************************************************************************//*
                        int slashIndex = fileAddress4.lastIndexOf('/');
                        int periodIndex = fileAddress4.lastIndexOf('.');
                        String fileName4 = fileAddress4.substring(slashIndex + 1);

                        if (periodIndex >= 1 && slashIndex >= 0
                                && slashIndex < fileAddress4.length() - 1) {


                            OutputStream outStream = null;
                            URLConnection uCon = null;
                            InputStream is = null;
                            String fullDestination = Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory2;
                            File dest = new File(fullDestination);
                            dest.mkdirs();
                            try {
                                //dest.createNewFile();
                                URL Url;
                                byte[] buf;
                                int ByteRead, ByteWritten = 0;
                                Url = new URL(fileAddress4);
                                outStream = new BufferedOutputStream(new FileOutputStream(fullDestination + "/" + fileName4));

                                uCon = Url.openConnection();
                                is = uCon.getInputStream();
                                buf = new byte[size];
                                while ((ByteRead = is.read(buf)) != -1) {
                                    outStream.write(buf, 0, ByteRead);
                                    ByteWritten += ByteRead;
                                    Thread.sleep(300);
                                    System.out.println(ByteWritten);
                                    progressBar.setProgress(ByteWritten);
                                }
                                System.out.println("Downloaded Successfully.");

                                System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory2 + "/" + fileName4 + "\"\nNo ofbytes :" + ByteWritten);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (outStream != null) {
                                        outStream.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.err.println("Error on Path or File Name.");
                        }

                        *//******************************************************************************//*
                        //Looper.prepare();
                        //Start the Spinner animation in 1.5 seconds.
                        handler.postDelayed(new Runnable() {
                            //Run the thread
                            public void run() {
                                sv.setVisibility(View.VISIBLE);
                                button.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                downloadText.setVisibility(View.INVISIBLE);
                            }
                        }, 500);
                        super.run();
                    }
                }.start();*/
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

            //Read the Settings File to check the VERWIONS.
            Scanner scanner;
            String crs;
            try {
                scanner = new Scanner(file5);
                while ((crs = scanner.nextLine()) != null)
                {
                    String[] split = crs.split("=");
                    currentSettings.add(split[1]);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            URL url = null;
            BufferedReader bufferedReader = null;
            String s;

            try {
                url = new URL(DownloadFilesManager.SERVER_ADDRESS + "settings/" + st_ch_str);
                while ((s = bufferedReader.readLine()) != null)
                {
                    String[] splitted = s.split("=");
                    settings.add(splitted[1]);
                }

            } catch (IOException e) {
                e.printStackTrace();
                final DialogAdapter dialogAdapter = new DialogAdapter();
                dialogAdapter.dialogOut(Story1.this, "Error check for updates", true, false, true, "OK", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogAdapter.dialogOutDialog.dismiss();
                    }
                }, null);
            }
            finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!settings.isEmpty())
            {

            }

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
