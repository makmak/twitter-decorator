package info.makmak

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList
import twitter4j.Status;
import twitter4j.Trends
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


class AccessTwitterService {
	
	def grailsApplication

    static transactional = true

    def serviceMethod() {

    }

	def timeline = {
		def responseList = null
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			try {
				String consumerKey = grailsApplication.config.oauth.twitter.consumerKey
				String consumerSecret = grailsApplication.config.oauth.twitter.consumerSecret
				String at = grailsApplication.config.oauth.twitter.accessToken
				String ats = grailsApplication.config.oauth.twitter.accessTokenSecret
				
				AccessToken accessToken = new AccessToken(at,ats)
				
				twitter.setOAuthConsumer(consumerKey, consumerSecret)
				twitter.setOAuthAccessToken(accessToken)
				
				//RequestToken requestToken = twitter.getOAuthRequestToken()
				println twitter.getOAuthRequestToken().getAuthorizationURL()
				
				log.info("Got access token.");
				log.info("Access token: " + accessToken.getToken());
				log.info("Access token secret: " + accessToken.getTokenSecret());
				if (!twitter.getAuthorization().isEnabled()) {
					log.error("OAuth consumer key/secret is not set.");
					return;
				}
			} catch (Exception e) {
				log.error ("Some exception occured: " + e)
			}
			responseList = twitter.getHomeTimeline();
			
		} catch (TwitterException te) {
			te.printStackTrace();
			log.error("Failed to get timeline: " + te.getMessage());
		}
		return responseList
	}
}
