[![Build Status](https://travis-ci.org/kennycyb/spring-rest-api-starter.svg?branch=master)](https://travis-ci.org/kennycyb/spring-rest-api-starter)

# spring-rest-api-starter
Starter application for REST API with spring framework

# Openshift

Deployment with OpenShift

## pom.xml

    <profiles>
         <profile>
            <!-- When built in OpenShift the 'openshift' profile will be used when invoking mvn. -->
            <!-- Use this profile for any OpenShift specific customization your app will need. -->
            <!-- By default that is to put the resulting archive into the 'deployments' folder. -->
            <!-- http://maven.apache.org/guides/mini/guide-building-for-different-environments.html -->
            <id>openshift</id>
            <build>
               <plugins>
                  <plugin>
                     <artifactId>maven-war-plugin</artifactId>
                     <version>2.1.1</version>
                     <configuration>
                        <outputDirectory>deployments</outputDirectory>
                        <warName>ROOT</warName>
                     </configuration>
                  </plugin>
               </plugins>
            </build>
         </profile>
      </profiles>
      
## Add to Project in OpenShift

* Click on "Add to Project" -> Browse Catalog
* Select "Java" -> "Red Hat JBoss Web Server (Tomcat)" -> "Tomcat 8"
* Enter name and git repository url: https://github.com/kennycyb/spring-rest-api-starter.git
* Click on "Create"
      
# Testing with curl

## Response of xml

	curl -X GET http://${url}/greeting -H 'accept: text/xml'
	
## Response of json

	curl -X GET http://${url}/greeting -H 'accept: application/json'
