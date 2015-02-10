package org.njcuacm.tenstory;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import org.njcuacm.net.NetConnector;


public class InitViewer extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_viewer);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        //Start a THREAD Handler after a 2 second delay
        Handler handler = new Handler();
        //Start the Spinner animation in 1.5 seconds.
        handler.postDelayed(new Runnable() {
            //Run the thread
            public void run() {
                pb.setVisibility(View.VISIBLE);
                pb.animate();
            }
        }, 1500);
        //Start checking for internet connection after 3 seconds.
        handler.postDelayed(new Runnable() {
            //Run the thread
            public void run() {
                if(NetConnector.isNetworkAvailable(InitViewer.this))
                {
                    Intent i = new Intent(InitViewer.this, Story1.class);
                    i.putExtra("netStat", "true");
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(InitViewer.this, Story1.class);
                    i.putExtra("netStat", "false");
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);
    }
}
