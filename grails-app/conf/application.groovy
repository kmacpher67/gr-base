app.name=livecam
grails.app.context = "/livecam"

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/**/error',          access: ['permitAll']],
	[pattern: '/**/index',          access: ['permitAll']],
	[pattern: '/**/register',          access: ['permitAll']],
	[pattern: '/**/failed',          access: ['permitAll']],
	[pattern: '/**/index.gsp',      access: ['permitAll']],
	[pattern: '/**/shutdown',       access: ['permitAll']],
	[pattern: '/**/assets/**',      access: ['permitAll']],
	[pattern: '/**/logout/**',       access: ['permitAll']],
	[pattern: '/**/login/**',       access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/dbconsole/**',   access: ['ROLE_USER']],
	[pattern: '/**/appstatus/**', access: ['permitAll']],
	[pattern: '/appStatus/**', access: ['permitAll']],
	[pattern: '/**/stats/**', access: ['permitAll']],
	[pattern: '/**/stat/**', access: ['permitAll']],
	[pattern: '/**/hello/**', access: ['permitAll']],
	[pattern: '/**/index/**', access: ['permitAll']],
	[pattern: '/**/testing/**', access: ['permitAll']],
    [pattern: '/**/j_spring_security_logout**', access: ['permitAll','ROLE_ADMIN','ROLE_USER','ROLE_FACEBOOK']],
	[pattern: '/**/j_spring_security_facebook_redirect**', access: ['permitAll']],
	[pattern: '/**/j_spring_security_facebook_json**', access: ['permitAll']],
	[pattern: '/j_spring_security_facebook_redirect/**', access: ['permitAll']],
    [pattern: '/**/j_spring_security_facebook_check/**', access: ['permitAll']],
	[pattern: '/**/j_spring_security**', access: ['permitAll']],
    [pattern: '/**/*.htm*', access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]