package com.spontorg

import grails.rest.*

/**
 *  curl -i -H "Accept: application/vnd.AppStatus.com.spontorg.AppStatus+json;v=1.0" -X GET http://localhost:8080/appStatus.json
 *
 */

// @Resource(uri='/appstatus', formats=['json'], readOnly=true)
// @Resource(uri='/appstatus', superClass=RestfulController)


class AppStatus  {

	String accessKey
	String tweetOutputText
	//ExternalApp externalApp
	StatusOut statusOut
	Date createDate
	String requestData 

	// AppStatus(String accessKey) {

		// createDate = new Date()
		// externalApp = ExternalApp.findByAccessKey(accessKey) ?: null
		// if (externalApp == null) {

			// tweetOutputText = "LUCK is the dividend of sweat, #Innovation is hard work. #cityscape liveview " 
			
		// }
		// else{
		// tweetOutputText = externalApp.statusOuts[0].tweetOutputText
		
		// }
    // }
	
    static constraints = {
    }	
}
