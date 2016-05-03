package com.spontorg

import com.the6hours.grails.springsecurity.facebook.FacebookUserDomain
import com.spontorg.User

class FacebookUser {

  Long uid
  String accessToken
  Date accessTokenExpires

  static belongsTo = [user: User] //connected to main Spring Security domain

  static constraints = {
    uid unique: true
  }
}
