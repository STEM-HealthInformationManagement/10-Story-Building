package org.njcuacm.tenstory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import org.njcuacm.adapters.TextAdapter;
import org.njcuacm.net.FileDownloader;
import org.njcuacm.net.NetConnector;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Story1 extends ActionBarActivity {
    public static String story1 = "story1.vpr";
    public static String ch1 = "ch1.vpr";
    private String fileAddress = "http://www.njcuacm.org/restricted/stem_test/app/10StoryBuilding/internal/stories/" + story1;
    private String fileAddress1 = "http://www.njcuacm.org/restricted/stem_test/app/10StoryBuilding/internal/chs/" + ch1;
    private String destinationDirectory = "str";
    private String destinationDirectory1 = "ch";
    final static int size = 1024;
    static Logger log;
    static FileWriter fw;
    static FileHandler f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story1);
        final Button button = (Button) findViewById(R.id.button);
        final ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.downloadProgress);
        final ProgressBar waiter = (ProgressBar) findViewById(R.id.waiter);
        final TextView downloadText = (TextView) findViewById(R.id.downloadText);
        sv.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        waiter.setVisibility(View.VISIBLE);
        waiter.animate();
        downloadText.setVisibility(View.INVISIBLE);
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/tsb/internal/str/" + story1);
        File file1 = new File(Environment.getExternalStorageDirectory().toString() + "/tsb/internal/ch/" + ch1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Story1.this, Story2.class);
                startActivity(i);
            }
        });
        final Handler handler = new Handler();
    fchk:
        if(!NetConnector.isConnected(getApplicationContext()))
        {
            waiter.setVisibility(View.INVISIBLE);
            waiter.clearAnimation();
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
        else if((!file.exists()) && NetConnector.isConnected(getApplicationContext()))
        {
            waiter.setVisibility(View.INVISIBLE);
            waiter.clearAnimation();
            progressBar.setVisibility(View.VISIBLE);
            downloadText.setVisibility(View.VISIBLE);
            downloadText.setText("Downloading required resources...");

            new Thread() {
                @Override
                public void run() {
                    int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress);
                    System.out.println("File Size Retrieved is: " + pseudoFileSize);
                    progressBar.setMax(pseudoFileSize);
                    /******************************************************************************/
                    int slashIndex = fileAddress.lastIndexOf('/');
                    int periodIndex = fileAddress.lastIndexOf('.');
                    String fileName = fileAddress.substring(slashIndex + 1);

                    if (periodIndex >= 1 && slashIndex >= 0
                            && slashIndex < fileAddress.length() - 1) {


                        OutputStream outStream = null;
                        URLConnection uCon = null;
                        InputStream is = null;
                        String fullDestination = Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory;
                        File dest = new File(fullDestination);
                        dest.mkdirs();
                        try {
                            //dest.createNewFile();
                            URL Url;
                            byte[] buf;
                            int ByteRead, ByteWritten = 0;
                            Url = new URL(fileAddress);
                            outStream = new BufferedOutputStream(new FileOutputStream(fullDestination + "/" + fileName));

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

                            System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory + "/" + fileName + "\"\nNo ofbytes :" + ByteWritten);
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

                    /******************************************************************************/
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
            }.start();
            break fchk;
        }
        else if((!file1.exists()) && NetConnector.isConnected(getApplicationContext()))
        {
            waiter.setVisibility(View.INVISIBLE);
            waiter.clearAnimation();
            progressBar.setVisibility(View.VISIBLE);
            downloadText.setVisibility(View.VISIBLE);
            downloadText.setText("Fetching additional properties...");

            new Thread() {
                @Override
                public void run() {
                    int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress);
                    System.out.println("File Size Retrieved is: " + pseudoFileSize);
                    progressBar.setMax(pseudoFileSize);
                    progressBar.setProgress(50);
                    /******************************************************************************/
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

                    /******************************************************************************/
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
            }.start();
        }
        else
        {
            /*if(!NetConnector.isConnected(getApplicationContext()))
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                alert.setMessage("Error retrieving resources.\n" +
                        "Please check your internet connection and try again.");
            }
            else
            {*/
                sv.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                waiter.setVisibility(View.INVISIBLE);
                waiter.clearAnimation();
                downloadText.setVisibility(View.INVISIBLE);
            /*}*/
        }

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
