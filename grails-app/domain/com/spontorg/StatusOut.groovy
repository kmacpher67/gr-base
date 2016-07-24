package com.spontorg

class StatusOut {

    String tweetOutputText
    String description
	String typeCode 
    User owner
	ExternalApp externalApp
	boolean active = true

    static constraints = {
		tweetOutputText size: 1..140, unique: true, blank:false, widget: 'textarea'
		description size: 0..250, widget: 'textarea'
		typeCode nullable: true
		owner nullable: true
		externalApp nullable: true
    }

//	public static String DEFAULT_STATUS = "#Robotics #innovation #makerspace #3dprinting neighborhood community center #OPENSOURCE open to the people @OHCollaborative #Youngstown"
//
//	StatusOut(){
//		this.tweetOutputText=DEFAULT_STATUS
//		this.description
//
//	}
//
//	StatusOut(String tweetOutputText){
//		if (tweetOutputText==null || tweetOutputText=="")
//			this.tweetOutputText=DEFAULT_STATUS
//		else
//			this.tweetOutputText=tweetOutputText
//	}

	String toString() {

		String externApp = externalApp?.name ?: "NULL"
		if (externApp.length()>5){
			externApp = externalApp.name.substring(0,5);
		}

		int displayLen =24

		if (tweetOutputText==null)
			return externApp+" is NULL"

		if (displayLen>tweetOutputText.length())
			displayLen=tweetOutputText.length()
        return externApp+"-"+tweetOutputText.substring(0,displayLen)
    }
}
