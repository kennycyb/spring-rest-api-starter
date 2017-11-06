# spring-rest-api-starter
Starter application for REST API with spring framework

# Openshift

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
