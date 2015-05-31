package com.example.asynctaskexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tView;
	TextView tView2;
	ProgressBar pBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.textView1);
        tView2 = (TextView) findViewById(R.id.textView2);
        pBar = (ProgressBar) findViewById(R.id.progressBar1);
    }

  public void startTask(View v) {
	Log.d("DEB", "MainActivity - startTask Method is called");
	new MyAsyncTask().execute("t1","t2","t3","t4");
  }
  private class MyAsyncTask extends AsyncTask<String,Integer,Integer> {
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		pBar.setProgress(0);
		tView2.setText("Updating...");
		Log.d("DEB", "AsyncTask - onPreExecute is called");
	}
	@Override
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.d("DEB", "AsyncTask - doInBackground is called");
		int count = 0;
		for(int i=1;i<11;i++){
			publishProgress(i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = i;
		}
		return count;
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		Log.d("DEB", "AsyncTask - onProgressUpdate is called");
		pBar.setProgress(values[0]);
		int cnt = values.length;
		Log.d("DEB", "Total size of value[int] array is "+cnt);
		tView.setText(values[0].toString());
	}
	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		Log.d("DEB", "AsyncTask - onPostExecute is called");
		Log.d("DEB", "Completed,  final count is... "+result);
	//	Toast.makeText(this, "Completed,  final count is... "+result,Toast.LENGTH_LONG).show();
	}
  }
}
