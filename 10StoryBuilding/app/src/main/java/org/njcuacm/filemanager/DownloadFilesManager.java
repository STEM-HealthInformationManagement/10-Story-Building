package org.njcuacm.filemanager;

import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import org.njcuacm.net.FileDownloader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Saurabh on 6/9/2015.
 */
public class DownloadFilesManager {

    public static final String SERVER_ADDRESS = "http://www.njcuacm.org/restricted/stem_test/app/10StoryBuilding/internal/";
    public static final String DEVICE_EXTERNAL_STORAGE = Environment.getExternalStorageDirectory().toString();

    public DownloadFilesManager()
    {

    }

    public void downloadFile(ProgressBar waiter, final ProgressBar progressBar, final TextView downloadText,
                             final String fileAddress, final int bufferSize, final String destinationDirectory, final Handler handler,
                             final boolean hasOtherStuff, final ScrollView sv, final Button button)
    {
        //We won't need the ROUNDED PROGRESSBAR here.
        System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
        if (hasOtherStuff) {
            System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
            waiter.setVisibility(View.INVISIBLE);
            waiter.clearAnimation();
            System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
            //We will need the HORIZONTAL PROGRESSBAR HERE, so set them to visible
            progressBar.setVisibility(View.VISIBLE);
            downloadText.setVisibility(View.VISIBLE);
            System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
            downloadText.setText("Downloading required resources...");
        }

        /**
         * Now we want to make sure that we download our files using another thread,
         * so that our MAIN thread is not interrupted by the download.
         */
        System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Thread() {
            /**
             * This is a required method needed for the Thread class.
             * */
            @Override
            public void run() {
                //We're going to retrieve our file size first so we can put the correct
                //information in the progress bar.
                System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
                int pseudoFileSize = FileDownloader.getOnlineFileSize(fileAddress);
                System.out.println("File Size Retrieved is: " + pseudoFileSize);
                //We're going to set the max of our progressbar to the file size retrieved.
                if (hasOtherStuff) {
                    progressBar.setMax(pseudoFileSize);
                }
                /******************************************************************************/
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
                        buf = new byte[bufferSize];
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
                            if (hasOtherStuff) {
                                progressBar.setProgress(ByteWritten);
                            }

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

                /******************************************************************************/
                if(hasOtherStuff) {
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
                }
                //Start our RUN method of RUNNABLE CLASS.
                super.run();
            }
            //NOW start the Thread BEFORE the code starts to run.
        }.start();
    }
}
