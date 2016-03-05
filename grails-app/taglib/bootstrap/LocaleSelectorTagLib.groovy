package bootstrap

import groovy.xml.MarkupBuilder
import org.springframework.web.servlet.support.RequestContextUtils

class LocaleSelectorTagLib {
    static namespace = 'bootstrap'

    List<Locale> locales = [Locale.ENGLISH, new Locale('pt', 'BR')]

    def localeSelector = { attrs, body ->
        def builder = new MarkupBuilder(out)
        builder.ul(id: 'locale-selector', class: 'dropdown-menu') {
            locales.each { locale ->
                li {
                    mkp.yieldUnescaped(g.link(controller: controllerName, action: actionName, params: params + [lang: locale.toString()]) {
                        out << locale.getDisplayLanguage(locale).capitalize()
                        if (RequestContextUtils.getLocale(request) == locale) {
                            out << '<i class="fa fa-fw fa-check"></i>'
                        }
                    })
                }
            }
        }
    }
}
