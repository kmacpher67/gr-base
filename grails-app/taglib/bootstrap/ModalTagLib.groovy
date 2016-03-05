package bootstrap

import groovy.xml.MarkupBuilder
import org.grails.taglib.GrailsTagException

class ModalTagLib {
    static namespace = 'bootstrap'

    def confirmation = { attrs, body ->
        if (!attrs.styleId) throw new GrailsTagException("Attribute 'styleId' is required for tag 'bootstrap:confirmation'")
        if (!attrs.onconfirm && !attrs.href && !attrs.action) throw new GrailsTagException("At least one of the following attribute is required is required for tag 'bootstrap:confirmation': onconfirm, href, action")

        if (!attrs.title) attrs.title = g.message(code: 'default.modal.confirmation.title', default: 'Confirmation')
        if (!attrs.noText) attrs.noText = g.message(code: 'default.boolean.false', default: 'No')
        if (!attrs.yesText) attrs.yesText = g.message(code: 'default.boolean.true', default: 'Yes')
        if (!attrs.href && attrs.action) attrs.href = g.createLink(controller: attrs.remove('controller'), action: attrs.remove('action'), id: attrs.remove('id'), params: attrs.remove('params'))
        if (!attrs.class) attrs.class = 'primary'
        if (!attrs.noClass) attrs.noClass = 'default'
        if (!attrs.yesClass) attrs.yesClass = 'primary'
        if (!attrs.buttonText) attrs.buttonText = ''

        out << modal(attrs) {
            StringWriter output = new StringWriter()
            def builder = new MarkupBuilder(output)
            builder.print(modalHeader(attrs, attrs.remove('title')))
            builder.print(modalBody(attrs, body()))
            builder.print(modalFooter(attrs) {
                StringWriter outputFooter = new StringWriter()
                def builderFooter = new MarkupBuilder(outputFooter)

                builderFooter.print(bootstrap.button(class: attrs.remove('noClass'), 'data-dismiss': 'modal', attrs.remove('noText')))

                if (attrs.href) {
                    builderFooter.print(bootstrap.linkAsButton(href: attrs.remove('href'), class: attrs.yesClass, attrs.yesText))
                } else {
                    builderFooter.print(bootstrap.button(class: attrs.yesClass, onclick: attrs.remove('onconfirm'), attrs.yesText))
                }

                out << outputFooter.toString()
            })
            out << output.toString()
        }
    }

    def dialog = { attrs, body ->
        if (!attrs.styleId) throw new GrailsTagException("Attribute 'styleId' is required for tag 'bootstrap:dialog'")

        if (!attrs.title) attrs.title = g.message(code: 'default.modal.dialog.title', default: 'Information')
        if (!attrs.okText) attrs.okText = g.message(code: 'default.dialog.button.ok', default: 'OK')
        if (!attrs.href && attrs.action) attrs.href = g.createLink(controller: attrs.remove('controller'), action: attrs.remove('action'), id: attrs.remove('id'), params: attrs.remove('params'))
        if (!attrs.class) attrs.class = 'primary'
        if (!attrs.okClass) attrs.yesClass = 'default'
        if (!attrs.buttonText) attrs.buttonText = ''

        out << modal(attrs) {
            StringWriter output = new StringWriter()
            def builder = new MarkupBuilder(output)
            builder.print(modalHeader(attrs, attrs.remove('title')))
            builder.print(modalBody(attrs, body()))
            builder.print(modalFooter(attrs) {
                StringWriter outputFooter = new StringWriter()
                def builderFooter = new MarkupBuilder(outputFooter)

                if (attrs.href) {
                    builderFooter.print(bootstrap.linkAsButton(href: attrs.remove('href'), class: attrs.okClass, attrs.okText))
                } else {
                    def buttonAttrs = [class: attrs.okClass]
                    if (attrs.onconfirm) {
                        buttonAttrs.onclick = attrs.remove('onconfirm')
                        builderFooter.print(bootstrap.button(buttonAttrs, attrs.okText))
                    } else {
                        buttonAttrs['data-dismiss'] = 'modal'
                        builderFooter.print(bootstrap.button(buttonAttrs, attrs.okText))
                    }
                }

                out << outputFooter.toString()
            })

            out << output.toString()
        }
    }

    def modal = { attrs, body ->
        if (!attrs.styleId) throw new GrailsTagException("Attribute 'styleId' is required for tag 'bootstrap:dialog'")

        if (!attrs.class) attrs.class = 'primary'
        if (!attrs.buttonText) attrs.buttonText = ''

        out << renderModalButton(attrs)

        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        builder.div(class: 'modal fade', id: attrs.styleId, tabindex: -1, role: 'dialog') {
            div(class: 'modal-dialog', role: 'document') {
                div(class: 'modal-content') {
                    mkp.yieldUnescaped(body())
                }
            }
        }
        out << output.toString()
    }

    def modalHeader = {attrs, body ->
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        builder.div(class: 'modal-header') {
            h4(class: 'modal-title') {
                mkp.yieldUnescaped(body())
            }
        }
        out << output.toString()
    }

    private def renderModalButton(def attrs) {
        return bootstrap.button(class: attrs.remove('class'), icon: attrs.remove('icon'), 'data-toggle': 'modal', 'data-target': '#' + attrs.styleId, attrs.remove('buttonText'))
    }

    def modalBody = { attrs, body ->
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        builder.div(class: 'modal-body') {
            p {
                mkp.yieldUnescaped(body())
            }
        }
        out << output.toString()
    }

    def modalFooter = {attrs, body ->
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        builder.div(class: 'modal-footer') {
            mkp.yieldUnescaped(body())
        }
        out << output.toString()
    }
}
