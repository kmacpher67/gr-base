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

	public static String DEFAULT_STATUS = "#Robotics #innovation #makerspace #3dprinting neighborhood community center #OPENSOURCE open to the people @OHCollaborative #Youngstown";
	public ExternalApp expDef = new ExternalApp()

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

	/**
	 * default=[{"class":"com.spontorg.StatusOut","id":99,"tweetOutputText":"Nothing in life is to be feared, it is only to be understood. Now is the time to understand more, so that we may fear less. ? Marie Curie","description":"Insp 13","typeCode":null,"owner":null,"externalApp":{"class":"com.spontorg.ExternalApp","id":1},"active":true}]
	 *
	 * @param key
	 * @return
     */

	def index2(String key){
		try {
			System.out.println(" GETTTING SHOUTS key index2=" + key)
			def now = new Date()
			def statusOutList = []
			def ok = ExternalApp.findByAccessKey(key)
			System.out.println("ok=" + ok)
			def statusOut1 = []
			if (ok != null && ok.accessKey == key) {
				System.out.println("Getting ScheduleShout Instead! =" + ok.id)
				statusOutList = StatusOut.findAllByExternalAppAndActive(ok, true)
				System.out.println("statusOutList ok,true, size statusoutlist=" + statusOutList.size())
				System.out.println(" date now= " + now)
				def shoutCurrent = ScheduledShout.findByStatusOutInListAndReleaseDateLessThanEqualsAndDeliveredNotEqual(statusOutList, now, true, [max: 1, sort: "releaseDate"])
				if (shoutCurrent != null && shoutCurrent.statusOut != null) {
					System.out.println("Shout current not null status is:" + shoutCurrent.statusOut)
					statusOut1.add(0, shoutCurrent.statusOut)
					def statusTimeLine = StatusTimeLine.findOrSaveByScheduleShout(shoutCurrent)
					createTimeLine(shoutCurrent)
					System.out.println("statusTimeLine create NEW" + statusTimeLine)
					shoutCurrent.delivered = true
					shoutCurrent.save(flush: true);
				} else {
					System.out.println("shoutCurrent IZ NULL! DEFULT");
					if (ok.defaultValue != null) {
						System.out.println("SETTING EXTERNAL app DEFAULT..." + ok.defaultValue);
						ScheduledShout defShout = new ScheduledShout("name":ScheduledShout.DEFAULTNAME,"eventTitle":"default none available","statusOut":ok.defaultValue)
						defShout.setStatusOut(ok.defaultValue)
						defShout.delivered = true
						defShout.save()
						createTimeLine(defShout)
						statusOut1.add(0, ok.defaultValue)
					}
				}
				System.out.println("shoutCurrent in list" + statusOut1)
			} else {
				System.out.println("bogus key")
				render(status: 401, text: 'Failed provide valid authkey this key is bogus= ${key}')
			}
			render statusOut1 as JSON
		}catch(Exception exp){
			System.out.println("ERROR in codelist default:" + exp);
			def DefaultStatusOut1 = []
			def ok = new ExternalApp("defaulttest", "description","1234")
			def exAppDefault = new ExternalApp("Exception Default","Exception Default", "Exception default")
			def statusOut1 = new StatusOut(tweetOutputText: DEFAULT_STATUS, description:DEFAULT_STATUS, typeCode:"Exception", "active":true)
			statusOut1.id=0
			statusOut1.externalApp=exAppDefault
			DefaultStatusOut1.add(statusOut1)
			render DefaultStatusOut1 as JSON
		}
	}

	private createTimeLine(ScheduledShout scheduleShout){
		def statusTimeLine = StatusTimeLine.create()
		statusTimeLine.deliveryIndex = StatusTimeLine.count()+1
		statusTimeLine.scheduleShout=scheduleShout
		statusTimeLine.save(flush:true)
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