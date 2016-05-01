# ken's base-gr starter project for coolness 

borrowed from several sources and FB plug-ins and hours of hacking. 
https://github.com/splix/grails-spring-security-facebook

## @TODO for features
1. End User interaction and OAuth user login
2.

## TADONE features 
1. Spring Security for my own special mix. [roles]
2. This project is a skeleton for Grails 3 (.0.9 and 0.11) applications. It already has Bootstrap and Font Awesome embedded.
3. I borrowed this from here: https://github.com/willcrisis/grails3-bootstrap-layouts

## How to use
1. Clone this repo
2. Remove `.git` folder
3. Update property `grailsVersion` in `gradle.properties` file to match the desired Grails version
4. Update properties `group` and `version` in `build.gradle` file to match your app's properties
5. Update property `grails.profile.codegen.defaultPackage` in `application.yml` file to your app's package
6. Rename folder to match your app's name
7. Run `git init` in your app folder and push the project to any git repository

## Versions used
* [Grails](http://grails.org/): 3.0.11 or .9 works
* [Bootstrap Framework Plugin](https://github.com/kensiprell/bootstrap-framework): 1.0.3
* [Bootstrap](http://getbootstrap.com/): 3.3.6
* [Font Awesome](http://fortawesome.github.io/Font-Awesome/): 4.3.0
* [bootstrap-layouts] (https://github.com/willcrisis/grails3-bootstrap-layouts.git): 0.1

## RAW notes: 
I built this using grails 3.0.9, windows, JDK 1.7.0.3 

set the grails home
GRAILS_HOME
C:\Users\ken\.grails\3.0.9

CHECK 
PATH= 
C:\RailsInstaller\Git\cmd;C:\RailsInstaller\Ruby2.0.0\bin;C:\Users\ken\.grails\3.0.9\bin;C:\Program Files\Java\jdk1.7.0_03\bin;c:\Python27\;C:\Python27\Scripts;C:\apache-maven-3.3.9\bin

I was trying to use a IDE like IntellJ, the GGTS is a pig and runs slow, takes a ton of memory, well spring, java Eclipse all are memory hogs. 

End up just using command line to make it work, after trying to get IDE to work. 
2> https://grails-plugins.github.io/grails-spring-security-core/

3> get a clean project running in grails 3. git clone https://github.com/willcrisis/grails3-bootstrap-layouts.git
