package com.spontorg

class StatusOut {

    String tweetOutputText
    String description
	String typeCode 
    User owner
	ExternalApp externalApp
	boolean active = true

    static constraints = {
		tweetOutputText size: 1..140, unique: true, blank:false
		description size: 0..250
		typeCode nullable: true
		owner nullable: true
		externalApp nullable: true
    }
	String toString() {
		int displayLen =15
		if (tweetOutputText==null)
			return "null"
		if (displayLen>tweetOutputText.length())
			displayLen=tweetOutputText.length()-1
        return tweetOutputText.substring(0,displayLen)
    }
}
