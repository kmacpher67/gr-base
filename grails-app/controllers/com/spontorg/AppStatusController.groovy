package com.spontorg

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON
import com.spontorg.StatusOut
import com.spontorg.ExternalApp


@Transactional(readOnly = false)
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

	def index2(String key){
		System.out.println(" GETTTING SHOUTS key index2=" + key)
		def now = new Date()
		def statusOutList = []
		def shoutCurrent ={"Meet Make Create @OakHillMkrSpace Tue / Wed @6 PM @507 OakHill Ave "+new Date().getTime()}
		def ok = ExternalApp.findByAccessKey(key)
		System.out.println("ok="+ok)
		def statusOut1 = []
		if (ok!=null && ok.accessKey==key){
			System.out.println("Getting ScheduleShout Instead! ="+ ok.id)
			statusOutList = StatusOut.findAllByExternalAppAndActive(ok,true)
			System.out.println("statusOutList ok,true, size statusoutlist=" +statusOutList.size())
			System.out.println(" date now= " + now)
			shoutCurrent = ScheduledShout.findAllByStatusOutInListAndReleaseDateLessThanEqualsAndDeliveredNotEqual(statusOutList,now,true,[max:1, sort:"releaseDate" ])
			if (shoutCurrent !=null || shoutCurrent.size()>0){
				System.out.println("Shout current not null status is:"+ shoutCurrent.statusOut)
				statusOut1.add(0,shoutCurrent.statusOut)
				//def statusTimeLine = StatusTimeLine.findOrSaveByScheduleShout(shoutCurrent)
				def statusTimeLine = StatusTimeLine.create()
				statusTimeLine.scheduleShout=shoutCurrent.get(0)
				statusTimeLine.save(flush:true)
				System.out.println("statusTimeLine create NEW" + statusTimeLine)
				shoutCurrent[0].delivered=true
				shoutCurrent[0].save(flush: true);
			}
			else{
				System.out.println("shoutCurrent IZ NULL! DEFULT" );
				statusOut1.add(0,shoutCurrent.statusOut)
			}
			System.out.println("shoutCurrent in list" +statusOut1)
		}
		else{
			System.out.println("bogus key" )
			render(status: 401, text: 'Failed provide valid authkey this key is bogus= ${key}')
		}
		render statusOut1 as JSON
	}

	def indexDelivered(String key, int id){
		System.out.println(" GETTTING SHOUTS key index2=" + key)
		def now = new Date()
		def statusOutList = []
		def shoutCurrent ={"Meet Make Create @OakHillMkrSpace Tue / Wed @6 PM @507 OakHill Ave "+new Date().getTime()}
		def ok = ExternalApp.findByAccessKey(key)
		System.out.println("ok="+ok)
		def statusOut1 = []
		if (ok!=null && ok.accessKey==key){
			System.out.println("Getting ScheduleShout Instead! ="+ ok.id)
			statusOutList = StatusOut.findAllByExternalAppAndActive(ok,true)
			System.out.println("statusOutList ok,true" +statusOutList)
			System.out.println(" date now= " + now)
			shoutCurrent = ScheduledShout.findAllByStatusOutInListAndReleaseDateLessThanEquals(statusOutList,now)

			System.out.println("shoutCurrent in list" +shoutCurrent)
		}
		else{
			System.out.println("bogus key" )
			render(status: 401, text: 'Failed provide valid authkey this key is bogus= ${key}')
		}
		render shoutCurrent as JSON
	}

	def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [statusOutList: StatusOut.list(params), statusOutCount: StatusOut.count()]
	}	
}