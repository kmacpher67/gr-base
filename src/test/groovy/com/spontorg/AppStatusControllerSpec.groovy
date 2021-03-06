package com.spontorg

import grails.test.mixin.*
import spock.lang.*
import com.spontorg.ExternalApp

@TestFor(AppStatusController)
@Mock(AppStatus)
class AppStatusControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index2() method"() {

        println("default="+AppStatusController.DEFAULT_STATUS)

        setup:
        def ok = new ExternalApp("defaulttest", "description","1234")
        GroovyMock(ExternalApp, global: true)
        ExternalApp.findByAccessKey("1234")  >> ok
        new ExternalApp("defaulttest", "description","1234") >> ok
        new ExternalApp("Exception Default","Exception Default", "Exception default") >> ok

        println("ExternalApp="+ok)

        when:"The index2 action is executed"
        controller.index2("1234")

        then:"The default is correct"
        println("model="+model)
        model.size() == 1
    }


    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.appStatusList
            model.appStatusCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.appStatus!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def appStatus = new AppStatus()
            appStatus.validate()
            controller.save(appStatus)

        then:"The create view is rendered again with the correct model"
            model.appStatus!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            appStatus = new AppStatus(params)

            controller.save(appStatus)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/appStatus/show/1'
            controller.flash.message != null
            AppStatus.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def appStatus = new AppStatus(params)
            controller.show(appStatus)

        then:"A model is populated containing the domain instance"
            model.appStatus == appStatus
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def appStatus = new AppStatus(params)
            controller.edit(appStatus)

        then:"A model is populated containing the domain instance"
            model.appStatus == appStatus
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/appStatus/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def appStatus = new AppStatus()
            appStatus.validate()
            controller.update(appStatus)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.appStatus == appStatus

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            appStatus = new AppStatus(params).save(flush: true)
            controller.update(appStatus)

        then:"A redirect is issued to the show action"
            appStatus != null
            response.redirectedUrl == "/appStatus/show/$appStatus.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/appStatus/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def appStatus = new AppStatus(params).save(flush: true)

        then:"It exists"
            AppStatus.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(appStatus)

        then:"The instance is deleted"
            AppStatus.count() == 0
            response.redirectedUrl == '/appStatus/index'
            flash.message != null
    }
}
