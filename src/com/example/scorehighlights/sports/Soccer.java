package com.example.scorehighlights.sports;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Jimmy Dagres
 * 
 * @version Oct 1, 2013
 *
 * This is the soccer derivation of the SportsAbstract class
 *
 */
public class Soccer extends SportsAbstract<String, JSONObject>
{

    /**
     * @param headlineString
     * @param object 
     */
    public Soccer( String headlineString, JSONObject object )
    {
        super( headlineString ,object);
    }

    @Override
    public void intializeEventVariables()
    {
        try
        {
            // Get the next two objects
            if ( null != eventObject_ )
            {
                // Get the competitor names and the result
                competitionArray_ =
                        eventObject_.getJSONArray( "competitions" );

                JSONObject competitorsObject =
                        competitionArray_.getJSONObject( 0 );

                // Get the current status of the game
                JSONObject statisObject =
                        competitorsObject.getJSONObject( "status" );
                if ( null != statisObject )
                {
                    currentPossession_ =
                            statisObject.getString( "description" );
                    gameTime_ = competitorsObject.getString( "date" );
                }

                JSONArray competitorsArray =
                        competitorsObject.getJSONArray( "competitors" );
                JSONObject competitors1Object =
                        competitorsArray.getJSONObject( 0 ).getJSONObject(
                                "team" );
                JSONObject competitors2Object =
                        competitorsArray.getJSONObject( 1 ).getJSONObject(
                                "team" );

                if ( null != competitors1Object )
                {
                    homeCompetitor_ =
                            competitors2Object.getString( "name" );
                }

                if ( null != competitors2Object )
                {
                    visitingCompetitor_ =
                            "@ " + competitors1Object.getString( "name" );
                }
            }
        }
        catch ( JSONException e )
        {
            e.printStackTrace();
        }
    }
}
