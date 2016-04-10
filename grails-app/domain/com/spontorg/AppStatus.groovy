package com.spontorg

import grails.rest.*

@Resource(uri='/appstatus', formats=['json'], readOnly=true)
class AppStatus implements Serializable  {

	String accessKey
	String tweetOutputText
	//ExternalApp externalApp
	//StatusOut statusOut
	Date createDate
	String requestData 

	AppStatus(String accessKey) {

		createDate = new Date()
		externalApp = ExternalApp.findByAccessKey(accessKey) ?: null
		if (externalApp == null) {

			tweetOutputText = "LUCK is the dividend of sweat, #Innovation is hard work. #cityscape liveview " 
			
		}
		else{
		tweetOutputText = externalApp.statusOuts[0].tweetOutputText
		
		}
    }
	
    static constraints = {
    }	
}
