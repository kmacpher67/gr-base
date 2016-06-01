package com.spontorg

class StatusTimeLine {

    long deliveryIndex = 11
    ScheduledShout scheduleShout
    Date dateCreated = new Date()
    String details

    static constraints = {
        deliveryIndex editable:true
        scheduleShout  nullable: true, editable:false
        dateCreated nullable:true, editable:false
        details nullable:true
    }

}
