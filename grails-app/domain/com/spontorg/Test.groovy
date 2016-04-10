package com.spontorg

import grails.rest.*

@Resource(uri='/test', formats=['json'])
class Test {

   String title

    static constraints = {
        title blank:false
    }
}
