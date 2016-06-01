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
	String toString() {

		String externApp = externalApp.name.length()
		if (externApp.length()>5){
			externApp = externalApp.name.substring(0,5);
		}

		int displayLen =20

		if (tweetOutputText==null)
			return "null"

		if (displayLen>tweetOutputText.length())
			displayLen=tweetOutputText.length()
        return externApp+tweetOutputText.substring(0,displayLen)
    }
}
