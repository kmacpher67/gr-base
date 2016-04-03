package com.spontorg

import grails.rest.*
import grails.plugin.springsecurity.annotation.Secured


/**
 * remote json access to data 
 * curl -i -H "Accept: application/json" localhost:8080/appstatus/1
 *   {"id":1,"title":"The Stand"}
 */ 
 //'ROLE_ADMIN','ROLE_USER'])
//'ROLE_ADMIN','ROLE_USER'])
// @ Secured(['permitAll']) 

@Resource(uri='/appstatus')
class ExternalApp {

	String name 
	String description
	String accessKey
	int appOrder 
	boolean isActive 
	Date createDate
	User owner 
	
	Map statusOuts
    static hasMany = [statusOuts: StatusOut]

	ExternalApp(String name, String description, String accessKey) {
		this()
		this.name = name
		this.description = description
        this.accessKey = accessKey
		this.createDate = new Date()
		this.isActive = true
	}
	
	ExternalApp(String name, String description, String accessKey, int appOrder, boolean isActive,  Date createDate, User owner   ){
			this()
		this.name = nameDate createDate
		this.description = description
        this.accessKey = accessKey
		this.createDate = createDate
		this.isActive = isActive
		this.owner = owner
	}
	
	
	
    static constraints = {
	
		name size: 1..30, unique: true, blank:false
		description size: 0..250
		accessKey size: 4..50
		owner nullable:true
		statusOuts nullable:true
    }
}
