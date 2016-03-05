package bootstrap

import groovy.xml.MarkupBuilder
import org.grails.taglib.GrailsTagException

class ButtonsTagLib {
    static namespace = "bootstrap"

    def submitButton = {attrs, body ->
        if (!attrs.class) attrs.class = 'success'
        if (!attrs.icon) attrs.icon = 'check'
        attrs.class = "btn btn-${attrs.class}"

        out << writeButton(attrs, 'submit', body)
    }

    def button = {attrs, body ->
        if (!attrs.class) attrs.class = 'default'
        attrs.class = "btn btn-${attrs.class}"

        out << writeButton(attrs, 'button', body)
    }

    def linkAsButton = {attrs, body ->
        if (!attrs.href && !attrs.action) throw new GrailsTagException("At least one of the attributes are required for tag 'bootstrap:linkAsButton': href, action")

        if (!attrs.href) attrs.href = g.createLink(controller: attrs.remove('controller'), action: attrs.remove('action'), id: attrs.remove('id'), params: attrs.remove('params'))
        if (attrs.styleId) attrs.id = attrs.remove('styleId')
        if (!attrs.class) attrs.class = 'default'
        attrs.class = "btn btn-${attrs.class}"

        out << writeLinkButton(attrs,  body)
    }

    def submitButtonInForm = {attrs, body ->
        out << writeButtonInForm(attrs, body, 'submitButton')
    }

    def linkAsButtonInForm = {attrs, body ->
        out << writeButtonInForm(attrs, body, 'linkAsButton')
    }

    private def writeButton(def attrs, def type, def body) {
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        attrs.type = type
        builder.button(attrs) {
            if (attrs.icon && !attrs.icon.equals('none')) {
                i(class: "fa fa-fw fa-${attrs.remove('icon')}") {
                    mkp.yieldUnescaped("")
                }
            }
            mkp.yieldUnescaped(body())
        }
        return output.toString()
    }

    private def writeLinkButton(def attrs, def body) {
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        builder.a(attrs) {
            if (attrs.icon && !attrs.icon.equals('none')) {
                i(class: "fa fa-fw fa-${attrs.remove('icon')}") {
                    mkp.yieldUnescaped("")
                }
            }
            mkp.yieldUnescaped(body())
        }
        return output.toString()
    }

    private def writeButtonInForm(def attrs, def body, String type) {
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        builder.div(class: 'form-group') {
            div(class: 'col-sm-offset-2 col-sm-10') {
                mkp.yieldUnescaped("$type"(attrs, body))
            }
        }
        return output.toString()
    }
}
