import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])
logger("com.the6hours", DEBUG, ["CONSOLE"])
logger("org.springframework.social", DEBUG, ["CONSOLE"])
logger("com.spontorg", DEBUG, ["CONSOLE"])
logger("grails.plugins.springsocial", DEBUG, ["CONSOLE"])
logger("grails.plugins.springsecurity", INFO, ["CONSOLE"])
logger("org.springframework.security", INFO, ["CONSOLE"])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
