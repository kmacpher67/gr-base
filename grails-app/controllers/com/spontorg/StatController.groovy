package com.spontorg

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON
import com.spontorg.StatusOut

@Transactional(readOnly = true)
class StatController  extends RestfulController {
    static responseFormats = ['json', 'xml']
		
    StatController() {
        super(StatusOut)
    }
 
	def butter() {
	def s = StatusOut.findAll()
	System.out.println("s="+s)
	respond s
		//[statusOutList: StatusOut.list()]
	    // List<StatusOut> statusOuts = StatusOut.find()
        // render statusOuts as JSON
	}
	
	def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [statusOutList: StatusOut.list(params), statusOutCount: StatusOut.count()]
	}
}