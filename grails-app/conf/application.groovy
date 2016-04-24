grails.databinding.dateFormats = ["dd/MM/yyyy HH:mm", "dd/MM/yyyy"]


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.spontorg.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.spontorg.UserRole'
grails.plugin.springsecurity.authority.className = 'com.spontorg.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/dbconsole/**',   access: ['ROLE_ADMIN']],
	[pattern: '/appstatus/**', access: ['permitAll']],
	[pattern: '/appStatus/**', access: ['permitAll']],
	[pattern: '/stats/**', access: ['permitAll']],
	[pattern: '/stat/**', access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]

]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'com.spontorg.PersistentLogin'


grails.plugin.springsecurity.roleHierarchyEntryClassName = 'com.spontorg.RoleHierarchyEntry'
grails.plugin.springsecurity.logout.postOnly = false

//environments {
//  production {
//    dataSource {
//      dbCreate = "update"
//      driverClassName = "org.postgresql.Driver"
//      dialect = org.hibernate.dialect.PostgreSQLDialect
//     uri = new URI(System.env.DATABASE_URL?:"postgres://localhost:5432/test")
//      url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require"
//      username = uri.userInfo.split(":")[0]
//      password = uri.userInfo.split(":")[1]
//    }
//  } 
//}

grails.mime.types = [
    json:          ['application/json', 'text/json'],
    xml:           ['text/xml', 'application/xml']
]