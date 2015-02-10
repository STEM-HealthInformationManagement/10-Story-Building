package org.njcuacm.net;

import android.os.Environment;

import java.io.*;
import java.net.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;




public class FileDownloader {
    final static int size = 1024;

    static Logger log;
    static FileWriter fw;
    static FileHandler f;

    public static void fileUrl(String fileURLAddress, String localFileName, String destinationDirectory) {
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
            Url = new URL(fileURLAddress);
            outStream = new BufferedOutputStream(new FileOutputStream(fullDestination + "/" + localFileName));

            uCon = Url.openConnection();
            is = uCon.getInputStream();
            buf = new byte[size];
            while ((ByteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, ByteRead);
                ByteWritten += ByteRead;
                System.out.println(ByteWritten);
            }
            System.out.println("Downloaded Successfully.");

            System.out.println("File directory/name: \"" + Environment.getExternalStorageDirectory().toString() + "/tsb/internal/" + destinationDirectory + "/" + localFileName + "\"\nNo ofbytes :" + ByteWritten);
        } catch (IOException e) {
            e.printStackTrace();

            /*JOptionPane.showMessageDialog(null, "Error downloading files!\n" +
                            "An error has occured with file name or path during update.\n" +
                            "Or an error has occured with pointern" +
                            "Please try again after an hour. \n" +
                            "And manually download the update throught our website.",
                    "Contact Error!", JOptionPane.ERROR_MESSAGE);*/

            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, e);


            try {
                File file = new File(fullDestination + "err");
                    file.mkdirs();
                    //file.createNewFile();
                f = new FileHandler(fullDestination + "err/cnerdump.vpr", true);
                log.setLevel(Level.ALL);
                log.addHandler(f);


                SimpleFormatter sf = new SimpleFormatter();
                f.setFormatter(sf);

            } catch (SecurityException | IOException e2) {
                e2.printStackTrace();
            }


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

                /*JOptionPane.showMessageDialog(null, "Error contacting update server!\n" +
                                "This may be due to an ongoing scheduled maintenance or high traffic is withheld on the server.\n" +
                                "Please try again after an hour.",
                        "Contact Error!", JOptionPane.ERROR_MESSAGE);*/

                Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, e);


                try {
                    f = new FileHandler(fullDestination + "err/cnerdump.vpr", true);
                    log.setLevel(Level.ALL);
                    log.addHandler(f);


                    SimpleFormatter sf = new SimpleFormatter();
                    f.setFormatter(sf);

                } catch (SecurityException | IOException e2) {
                    e2.printStackTrace();
                }


            }
        }
    }

    public static void fileDownload(String fileAddress, String destinationDirectory) {
        int slashIndex = fileAddress.lastIndexOf('/');
        int periodIndex = fileAddress.lastIndexOf('.');

        String fileName = fileAddress.substring(slashIndex + 1);

        if (periodIndex >= 1 && slashIndex >= 0
                && slashIndex < fileAddress.length() - 1) {
            fileUrl(fileAddress, fileName, destinationDirectory);
        } else {
            System.err.println("Error on Path or File Name.");
            /*JOptionPane.showMessageDialog(null, "Error retrieving file path or file itself.\n" +
                    "Wrong file name or file does not exist.", "Path/File Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Unable to download one or more files.\n" +
                    "Continuing with the rest of the download.", "Corrupted Error", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public static int getOnlineFileSize(String fileURL) {
        HttpURLConnection conn = null;
        try {
            URL url= new URL(fileURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


/*
 *
 * THIS MAIN METHOD IS FOR TESTING ONLY!
 * IT IS NOT RECOMMENDED FOR DIRECT USE.
 *
 */

    public static void main(String[] args) {

        fileDownload("http://www.njcuacm.org/restricted/stem_test/app/10StoryBuilding/internal/stories/story1.vpr", "C:/Users/Saurabh/Documents/");

        if (args.length == 2) {
            for (int i = 1; i < args.length; i++) {
                fileDownload(args[i], args[0]);
            }
        }
    }
}