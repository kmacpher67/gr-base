package bootstrap

import groovy.xml.MarkupBuilder


class MainMenuTagLib {
    static namespace = 'bootstrap'
		def springSecurityService

    def mainMenu = {attrs, body ->
        StringWriter output = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(output)
        builder.nav(class: 'navbar navbar-inverse navbar-fixed-top') {
            div(class: 'container-fluid') {
                div(class: 'navbar-header') {
                    a(class: 'navbar-toggle collapsed', 'data-toggle': 'collapse', 'data-target': '#main-menu') {
                        span(class: 'sr-only', 'Toggle navigation')
                        span(class: 'icon-bar', '')
                        span(class: 'icon-bar', '')
                        span(class: 'icon-bar', '')
                    }
                    a(class: 'navbar-brand', href: g.createLink(uri: '/')) {
                        mkp.yieldUnescaped(g.meta(name: 'info.app.name'))
                    }
                }
				
                def user = springSecurityService.isLoggedIn() ? springSecurityService.loadCurrentUser() : null 
				
                if (body()) {
                    mkp.yieldUnescaped(body())
                } else {
                    div(class: 'collapse navbar-collapse', id: 'main-menu') {
                        ul(class: 'nav navbar-nav') {

                            grailsApplication.controllerClasses.sort{ it.fullName }.each { c ->
								def attrsLi = [role: 'presentation']
								if (c.getStaticPropertyValue('linkMe', Boolean) ) {
									attrsLi.class = 'active'
									li(attrsLi) {
									 mkp.yieldUnescaped(g.link(controller: c.logicalPropertyName, g.message(code: "${c.logicalPropertyName}.plural.label", default: c.name)))
									}	
								}
                            }
							def attrsLi1 = [role: 'presentation']
							attrsLi1.class = 'active'
							if (user!=null){
								li(attrsLi1){
									mkp.yieldUnescaped('<a href="/logout/index">Logout</a>')
									}
								}
							else{
								li(attrsLi1){
									mkp.yieldUnescaped('<a href="/login/index">Login</a>')
									}							
							}
                        }

						
                        ul(class: 'nav navbar-nav navbar-right') {
						
							if(user!=null){
								li(role: 'presentation', class: 'active') {
									mkp.yieldUnescaped(g.link(controller: "user", action: "show", id:user.id, g.message(code:user.username, default: user.username)))
								}						
							}
                            li(class: 'dropdown') {
                                a(href: '#', class: 'dropdown-toggle', 'data-toggle': 'dropdown', role: 'button') {
                                    mkp.yieldUnescaped(g.message(code: 'default.language.label', default: 'Language'))
                                    span(class: 'caret', '')
                                }
                                mkp.yieldUnescaped(bootstrap.localeSelector())
                            }

                        }
                    }
                }
            }
        }

        out << output.toString()
    }
}
