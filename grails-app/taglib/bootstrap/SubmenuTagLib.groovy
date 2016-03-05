package bootstrap

import groovy.xml.MarkupBuilder

/**
 * Created by kraus on 13/01/2016.
 */
class SubmenuTagLib {
    static namespace = "bootstrap"

    def submenuList = { attrs, body ->
        out << submenu(attrs) {
            StringWriter output = new StringWriter()
            MarkupBuilder builder = new MarkupBuilder(output)

            if (!attrs.override) {
                builder.li(role: 'presentation') {
                    mkp.yieldUnescaped(renderCreateButton())
                }

            }
            builder.print(body())
            out << output.toString()
        }
    }

    def submenuCreate = { attrs, body ->
        out << submenu(attrs) {
            StringWriter output = new StringWriter()
            MarkupBuilder builder = new MarkupBuilder(output)

            if (!attrs.override) {
                builder.li(role: 'presentation') {
                    mkp.yieldUnescaped(renderListButton())
                }

            }
            builder.print(body())
            out << output.toString()
        }
    }

    def submenuEdit = { attrs, body ->
        out << submenu(attrs) {
            StringWriter output = new StringWriter()
            MarkupBuilder builder = new MarkupBuilder(output)

            if (!attrs.override) {
                builder.li(role: 'presentation') {
                    mkp.yieldUnescaped(renderListButton())
                }
                builder.li(role: 'presentation') {
                    mkp.yieldUnescaped(renderCreateButton())
                }

            }
            builder.print(body())
            out << output.toString()
        }
    }

    def submenuShow = { attrs, body ->
        out << submenu(attrs) {
            StringWriter output = new StringWriter()
            MarkupBuilder builder = new MarkupBuilder(output)

            if (!attrs.override) {
                builder.li(role: 'presentation') {
                    mkp.yieldUnescaped(renderListButton())
                }
                builder.li(role: 'presentation') {
                    mkp.yieldUnescaped(renderCreateButton())
                }

            }
            builder.print(body())
            out << output.toString()
        }
    }

    private def renderCreateButton() {
        return g.link(action: 'create') {
            out << '<i class="fa fa-fw fa-plus"></i>'
            out << g.message(code: 'default.create.label', args: [request.entityName])
        }
    }

    private def renderListButton() {
        return g.link(action: 'index') {
            out << '<i class="fa fa-fw fa-list"></i>'
            out << g.message(code: 'default.list.label', args: [request.entityNamePlural])
        }
    }

    def submenu = { attrs, body ->
        StringWriter output = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(output)
        builder.div(class: 'well well-sm') {
            ul(class: 'nav nav-pills') {
                mkp.yieldUnescaped(body())
            }
        }
        out << output.toString()
    }
}
