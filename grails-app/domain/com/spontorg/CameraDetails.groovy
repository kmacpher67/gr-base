package com.spontorg

class CameraDetails {

    String name
    String serialNumb
    String urlWebPic
    String userName
    String password
    String description
    String guid
    String model
    Date purchaseDate

    static constraints = {
        name nullable:true
        urlWebPic  nullable:true
        userName nullable:true
        password nullable:true
        description nullable:true
        guid nullable:true
        model nullable:true
        purchaseDate nullable: true
    }
}
