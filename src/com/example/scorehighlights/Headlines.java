package com.example.scorehighlights;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.scorehighlights.sports.Baseball;
import com.example.scorehighlights.sports.Basketball;
import com.example.scorehighlights.sports.Football;
import com.example.scorehighlights.sports.Hockey;
import com.example.scorehighlights.sports.Soccer;
import com.example.scorehighlights.sports.SportsAbstract;
import com.example.scorehighlights.sports.Tennis;

import android.util.Log;

/**
 * @author Jimmy Dagres
 * 
 * @version Sep 29, 2013
 * 
 *          This class contains the headlines data parsed from the HTML get
 */
public class Headlines extends MainActivity
{
    // The headline object
    protected static JSONObject jHeadlines_ = null;

    /**
     * Each sport has the following arrays
     */
    public static final String TAG_NAME = "name";

    /**
     * @return sport selected
     */
    public String getSportSelected_()
    {
        return sportSelected_;
    }

    /**
     * @return the list of Events
     */
    public ArrayList<SportsAbstract<String, JSONObject>> getArrayOfEvents_()
    {
        return ArrayOfEvents_;
    }

    /**
     * The constructor
     * 
     * @param headline
     *            the string to create the JSON
     * @param sport
     */
    public Headlines( String headline )
    {
        try
        {
            jHeadlines_ = new JSONObject( headline );
        }
        catch ( JSONException e )
        {
            Log.e( "JSON Parser", "Error parsing headlines " + e.toString() );
        }
    }

    /**
     * This function takes the JSON string and stores the top two headlines into
     * a hashmap, and displays the map.
     */
    void renderSportScores()
    {
        if ( null == jHeadlines_ )
        {
            return;
        }

        getSportsScores();
    }

    /**
     * This method gets all of the events for the relative sport and stores them
     * in a the parent array list ArrayOfEvents_
     */
    private void getSportsScores()
    {
        try
        {
            if ( null != sportSelected_ )
            {
                // Gets an array containing all of the JSON objects
                JSONArray newsFeedArray_ = jHeadlines_.getJSONArray( "sports" );

                if ( null != newsFeedArray_ )
                {
                    processJSONArray( newsFeedArray_ );
                }
            }
        }
        catch ( JSONException e )
        {
            System.out.println( "Error at getSportsScores" );

            e.printStackTrace();
        }
    }

    /**
     * Takes the specified array and returns a map with it's stored data
     * 
     * @param ArrayToProcess
     */
    void processJSONArray( JSONArray ArrayToProcess )
    {
        try
        {
            // Initialize arrayList if null
            if ( null == ArrayOfEvents_ )
            {
                ArrayOfEvents_ =
                        new ArrayList<SportsAbstract<String, JSONObject>>();
            }

            // looping through All headlines and store them in a list of maps
            for ( int i = 0; i < ArrayToProcess.length(); i++ )
            {
                // Get an object that contains an array of all of the leagues
                JSONObject obejctOfSports = ArrayToProcess.getJSONObject( i );

                String sport =
                        obejctOfSports.getString( TAG_NAME ).toString()
                                .toLowerCase().trim();

                if ( sport.contains( sportSelected_.toLowerCase().trim() ) )
                {
                    // Get the object of all of the leagues in that sport
                    JSONArray sportLeaguesArray =
                            obejctOfSports.getJSONArray( "leagues" );

                    // Create an SportsAbstract class object for every event in
                    // every league
                    for ( int j = 0; j < sportLeaguesArray.length(); j++ )
                    {
                        // Create an array of events in the league at position j
                        // Get the object of all of the leagues in that sport
                        JSONArray sportEventsArray =
                                (sportLeaguesArray.getJSONObject( j ))
                                        .getJSONArray( "events" );

                        for ( int k = 0; k < sportEventsArray.length(); k++ )
                        {
                            SportsAbstract<String, JSONObject> newEvent =
                                    createSportsEvent( sport,
                                            sportLeaguesArray
                                                    .getJSONObject( j )
                                                    .getString( TAG_NAME ),
                                            sportEventsArray
                                                    .getJSONObject( k ) );

                            if ( null != newEvent )
                            {
                                // Add event object to list
                                ArrayOfEvents_.add( newEvent );
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            System.out.println( "error at handler parser: " );
            ex.printStackTrace();
        }
    }

    /**
     * Creates the appropriate abstract instance
     * 
     * @param sport
     *            the sport
     * @param league
     *            the league of the sport
     * @param object
     *            the JSONObject
     * @return
     */
    private SportsAbstract<String, JSONObject> createSportsEvent(
            String sport, String league, JSONObject object )
    {
        // Return object
        SportsAbstract<String, JSONObject> newEvent = null;

        try
        {
            if ( sport.contains( "baseball" ) )
            {
                newEvent =
                        new Baseball( league, object );
                return newEvent;
            }
            else if ( sport.contains( "basketball" ) )
            {
                newEvent =
                        new Basketball( league, object );
                return newEvent;
            }
            else if ( sport.contains( "football" ) )
            {
                newEvent =
                        new Football( league, object );
                return newEvent;
            }
            else if ( sport.contains( "hockey" ) )
            {
                newEvent =
                        new Hockey( league, object );
                return newEvent;
            }
            else if ( sport.contains( "soccer" ) )
            {
                newEvent =
                        new Soccer( league, object );
                return newEvent;
            }
            else if ( sport.contains( "tennis" ) )
            {
                newEvent =
                        new Tennis( league, object );
                return newEvent;
            }
        }
        catch ( Exception ex )
        {
            System.out.println( "Error at createSportsEvent" );

            ex.printStackTrace();
        }

        return newEvent;
    }
}
