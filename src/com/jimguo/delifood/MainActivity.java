package com.jimguo.delifood;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jimguo.seatActivity.SeatActivity;

public class MainActivity extends Activity {

	private TextView textView;
	private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("url of server");

        //we need add download data function here 
        
        textView.setText("Downloading data ... " );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	/*
	 * this function download all files from server to tablet 
	 * returns
	 *  -1 : download error
	 *   0 : download success
	 */
    int downloadDataFromServer(String url)
    {
    	return 0;
    }
    
	class DownloadTask extends AsyncTask<String, Integer, Integer>
	{

		@Override
		protected Integer doInBackground(String... arg0)
		{
			int progressState = 0;
			while(progressState < 100)
			{
				progressState++;
				publishProgress(progressState);
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return downloadDataFromServer(arg0[0]);
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			progressBar.setProgress(values[0]);
			textView.setText("Downloading Data : " + values[0].toString() + " / " + progressBar.getMax());
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			String res;
			if(result == 0)
			{
				res = "download is success !";
			}
			else 
			{
				res = "download is failed !";
			}
			
			Toast.makeText(MainActivity.this, res,
							Toast.LENGTH_SHORT).show();
			try
			{
				Thread.sleep(2000);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SeatActivity.class);
			startActivity(intent);
			
		}

	}
}
