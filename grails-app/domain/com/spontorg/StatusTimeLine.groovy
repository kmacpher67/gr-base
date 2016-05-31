package com.spontorg

class StatusTimeLine {

    long deliveryIndex = new Date().getTime()/1000
    ScheduledShout scheduleShout
    Date dateCreated = new Date()
    String details

    static constraints = {
        deliveryIndex editable:false
        scheduleShout  nullable: true, editable:false
        dateCreated nullable:true, editable:false
        details nullable:true
    }

}
