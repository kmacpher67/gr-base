package bootstrap

import groovy.xml.MarkupBuilder

/**
 * Created by kraus on 13/01/2016.
 */
class TableTagLib {
    static namespace = 'bootstrap'

    def table = {attrs, body ->
        StringWriter output = new StringWriter()
        def builder = new MarkupBuilder(output)
        if (!attrs.class) attrs.class = ''
        attrs.class = 'table table-hover table-striped ' + attrs.class
        builder.table(attrs) {
            mkp.yieldUnescaped(body())
        }
        out << output.toString()
    }
}
