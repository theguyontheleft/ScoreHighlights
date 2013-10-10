package com.example.scorehighlights;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

/**
 * @author Jimmy Dagres
 * 
 * @version Sep 27, 2013
 * 
 */
public class AsyncTaskThread extends AsyncTask<Void, Void, String>
{
    // Reference the main activity object
    protected MainActivity activity_;

    private final static String apiUrlPopularBase_ =
            "http://api.espn.com/v1/now/popular?apikey=6t84mv2wjvv7wapn6gvrk846";

    private final static String apiUrlScoreBase_ =
            "http://api.espn.com/v1/sports/events/top?apikey=6t84mv2wjvv7wapn6gvrk846";

    // Instance the thread that handles the parsing
    private ParserThread ParserThread_ = null;

    /**
     * @param activityToCallBack
     */
    public AsyncTaskThread( MainActivity activityToCallBack )
    {
        activity_ = activityToCallBack;
        ParserThread_ = new ParserThread( this );
    }

    /**
     * @return
     * @throws Exception
     */
    public String connectToPage() throws Exception
    {
        BufferedReader in = null;
        String data = ""; // null;
        try
        {
            // setup http client
            HttpClient client = new DefaultHttpClient();

            // Get data from website
            URI website =
                    new URI( apiUrlScoreBase_ );

            // request using get method
            HttpGet request = new HttpGet( website );

            HttpResponse response = client.execute( request );

            // string using buffered reader
            in =
                    new BufferedReader( new InputStreamReader( response
                            .getEntity().getContent() ) );
            StringBuffer sb = new StringBuffer( "" );

            String l = "";
            String newline = System.getProperty( "line.separator" );
            while ( (l = in.readLine()) != null )
            {
                sb.append( l + newline );
            }
            in.close();
            data = sb.toString();

            return data;
        }
        finally
        {
            if ( in != null )
            {
                try
                {
                    in.close();
                    return data;
                }
                catch ( Exception e )
                {
                    System.out.println( "Error e:" + e.toString() );

                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected String doInBackground( Void... params )
    {
        try
        {
            return connectToPage();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return "Error, no Data returned!!!";
        }
    }

    @Override
    protected void onPostExecute( String result )
    {
        activity_.getNetworkData( result );
        
        // Execute the parsing thread
        ParserThread_.execute( (Void) null );
        ParserThread_ = new ParserThread( this );
    }
    
    /**
     * Displays data from the newly parsed data on the main thread
     */
    @SuppressWarnings( "static-access" )
    protected void displayParsedChanges()
    {
        if ( null == activity_.ArrayOfEvents_ )
        {
            activity_.displayNoRecentEvents();
        }
        else if ( 0 == activity_.ArrayOfEvents_.size() )
        {
            activity_.displayNoRecentEvents();
        }
        else
        {
            activity_.displayScores();
        }
    }
}