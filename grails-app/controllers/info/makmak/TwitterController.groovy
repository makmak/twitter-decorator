package info.makmak

import twitter4j.ResponseList
import twitter4j.Status

class TwitterController {
	
	def accessTwitterService

    def index = {
		
	}
	
	def timeline = {
		StringBuilder output = new StringBuilder()
		output.append("Timeline:<br/>")
		
		ResponseList<Status> responseList = accessTwitterService.timeline()
		responseList.each() { twitterItem ->
			println twitterItem
			output.append("<img src='${twitterItem.user.profileImageUrl}' title='${twitterItem.user.name}'/>")
			output.append(twitterItem.user.name)
			output.append(": ")
			output.append("</br>")
			output.append(twitterItem.text)
			output.append("<br/>")
			output.append(twitterItem.createdAt)
			output.append("<hr/>")
		}
		

		render output.toString()
	}
}
