package com.spontorg

import grails.rest.*

@Resource(uri='/appstatus')
class ExternalApp {

	String name 
	String description
	String accessKey
	User owner 
	
	Map statusOuts
    static hasMany = [statusOuts: StatusOut]

    static constraints = {
	
		name size: 1..30, unique: true, blank:false
		description size: 0..250
		accessKey size: 4..50
		owner nullable:true
		statusOuts nullable:true
    }
}
