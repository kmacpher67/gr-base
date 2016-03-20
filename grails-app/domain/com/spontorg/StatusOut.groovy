package com.spontorg

class StatusOut {

    String tweetOutputText
    String description
    User owner
	ExternalApp externalApp

    static constraints = {
		tweetOutputText size: 1..140, unique: true, blank:false
		description size: 0..250
		owner nullable: true
		externalApp nullable: true
    }
}
