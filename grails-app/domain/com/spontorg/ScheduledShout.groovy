package com.spontorg

class ScheduledShout {

    String name = ""
	String eventTitle = ""
	Date releaseDate = new Date()
	int priority=0
	boolean delivered = false
	
	StatusOut statusOut

    static constraints = {
		
		name nullable:false
		eventTitle nullable:true
		statusOut unique: false , nullable: true
		delivered nullable: true
		releaseDate type:"datetime"
		priority nullable:true
    }

	static String DEFAULTNAME ="Default"

	String toString() {
		int displayLen =20
		if (eventTitle==null || name==null)
			return eventTitle+"null"
			
		if (displayLen>(name.length()+eventTitle?.length()))
			displayLen=(name.length()+eventTitle?.length())
        return (name+eventTitle).substring(0,displayLen)
    }
}
