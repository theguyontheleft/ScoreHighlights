package com.example.scorehighlights;

import java.nio.channels.AsynchronousCloseException;

import com.example.scorehighlights.MainActivity.SpinnerData;

import android.os.AsyncTask;
import android.widget.Spinner;

/**
 * @author Jimmy Dagres
 * 
 * @version Oct 2, 2013
 * 
 *          Thread for the spinner combo box
 */
public class ParserThread extends AsyncTask<Void, Void, String>
{
    // Reference the main activity object
    private DataPullThread asyncThread_;
    
    /**
     * @param asyncTaskThread
     */
    public ParserThread( DataPullThread asyncTaskThread )
    {
        asyncThread_ = asyncTaskThread;
    }

    @Override
    protected String doInBackground( Void... arg0 )
    {
        asyncThread_.activity_.headlines_.renderSportScores();
        return null;
    }
    
    @Override
    protected void onPostExecute(String arg0)
    {
        asyncThread_.displayParsedChanges();
    }
}
