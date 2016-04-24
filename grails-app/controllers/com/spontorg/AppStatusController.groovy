package com.spontorg

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON
import com.spontorg.StatusOut
import com.spontorg.ExternalApp


@Transactional(readOnly = true)
class AppStatusController  extends RestfulController {
    static responseFormats = ['json', 'xml']
	
    AppStatusController() {
        super(StatusOut)
    }

	def index1(String key){
		System.out.println("key index1=" + key)
	    def ok = ExternalApp.findByAccessKey(key)
		System.out.println("ok="+ok)
		def statusOut1 = []
		if (ok!=null && ok.accessKey==key){
			System.out.println("AppStatusController there="+ ok.id)
			 statusOut1 = StatusOut.findAllByExternalAppAndActive(ok,true)
			 System.out.println("byid" +statusOut1)
		}
        render statusOut1 as JSON
	}
	
	def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [statusOutList: StatusOut.list(params), statusOutCount: StatusOut.count()]
	}	
}