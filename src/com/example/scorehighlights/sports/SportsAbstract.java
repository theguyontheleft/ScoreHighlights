package com.example.scorehighlights.sports;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.scorehighlights.Headlines;

//import com.example.scorehighlights.Headlines;

/**
 * @author Jimmy Dagres
 * 
 * @version Oct 1, 2013
 * 
 *          This is the abstract sports class, it must be overwritten by the
 *          specific derived sports classes
 * @param <String>
 *            the league
 * @param <JSONObject>
 *            the object with all the info
 * 
 */
@SuppressWarnings( "hiding" )
public abstract class SportsAbstract<String, JSONObject>
{

    // The instance of the event object and league string
    protected JSONObject eventObject_ = null;
    protected String leagueString_ = null;
    protected JSONArray competitionArray_ = null;
    protected JSONArray competitorsArray_ = null;

    protected JSONObject linkObject_ = null;

    // Stores the name of the event
    protected String eventInfo = null;

    // Stores the name of the competitors
    protected String homeCompetitor_ = null;
    protected String visitingCompetitor_ = null;

    // Stores the score of the competitors
    protected String homeCompetitorScore_ = null;
    protected String visitingCompetitorScore_ = null;

    // Stores the date of the event
    protected String date_ = null;

    // Gets the time remaining in the game
    protected String gameTime_ = null;

    // Gets the active play/drive/possession
    protected String currentPossession_ = null;
    protected String currentPossessionInfo_ = null;

    /**
     * @param league
     * @param object
     */
    public SportsAbstract( String league, JSONObject object )
    {
        eventObject_ = object;
        leagueString_ = league;

        intializeEventVariables();
    }

    /**
     * @return the league
     */
    public String getLeague()
    {
        return leagueString_;
    }

    // Initializes the next two objects, competitionObject_ and linkObject_
    protected abstract void intializeEventVariables();

    /**
     * @return Gets the two teams or athletes names
     */
    public String getHomeContender()
    {
        return homeCompetitor_;
    }

    /**
     * @return the visiting team
     */
    public String getVisitingContender()
    {
        return visitingCompetitor_;
    }

    /**
     * @return Gets the current score
     */
    public String getHomeContenderScore()
    {
        return homeCompetitorScore_;
    }

    /**
     * @return the visiting contenders score
     */
    public String getVisitingContenderScore()
    {
        return visitingCompetitorScore_;
    }

    /**
     * @return Gets the time remaining in the game
     */
    public String getGameTime()
    {
        return gameTime_;
    }

    /**
     * @return Gets the active play/drive/possession
     */
    public String getCurrentPossession()
    {
        return currentPossession_;
    }

    /**
     * @return the current possesion info
     */
    public String getCurrentPossessionInfo()
    {
        return currentPossessionInfo_;
    }
}
