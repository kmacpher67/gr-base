package bootstrap

import groovy.xml.MarkupBuilder

class AlertsTagLib {

    static namespace = "bootstrap"

    def alert = { attrs, body ->
        out << '<div class="alert ' << attrs.class.tokenize().join(" ") << '">'
        out << '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>'
        out << body()
        out << '</div>'
    }

    def messages = { attrs, body ->
        if (flash.message) {
            out << alert(class: 'alert-info', flash.message)
        }

        if (params.message) {
            out << alert(class: 'alert-info', params.message)
        }

        if (attrs.bean && attrs.bean.hasErrors()) {
            out << alert(class: 'alert-danger') {
                attrs.bean.errors.allErrors.each { error ->
                    out << "<p ${if (error in org.springframework.validation.FieldError) 'data-field-id="' + error.field + '"'}>" + g.message(error: error) + '</p>\n'
                }
            }
        }

        if (params.error) {
            out << alert(class: 'alert-danger', params.error)
        }

        if (params.errors) {
            out << alert(class: 'alert-danger') {
                params.errors.each { error ->
                    out << "<p>" + g.message(code: error, default: error) + '</p>\n'
                }
            }
        }
    }
}
