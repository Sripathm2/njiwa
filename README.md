# Njiwa -  Open Source Embedded M2M UICC Remote Subscription Management Server

## What Is It

 Njiwa (Swahili for homing pigeon) is an implementation of the GSMA's
 [Embedded SIM Remote Provisioning Manager](https://www.gsma.com/iot/embedded-sim/) for M2M devices. Because M2M devices are likely installed in remote or 
 unreachable areas, the GSMA came up with with specifications to enable remote
 activation of network profiles (and other settings) on the SIMs in these devices. Please read through the [this site](https://www.gsma.com/iot/embedded-sim/how-it-works/) to familiarise yourself with the GSMA Embedded SIM Architecture. 

## Status & Roadmap
  The diagram below is taken from the GSMA specification. It shows the main
  elements in the embedded SIM ecosystem.

![RSP Architecture Elements](https://raw.githubusercontent.com/bagyenda/njiwa/master/etc/arch.png)

  Currently Njiwa implements the SM-DP and SM-SR in the above architecture. 

  Mostly these are feature complete as per the [specifications](https://www.gsma.com/newsroom/all-documents/sgp-02-v3-2-remote-provisioning-architecture-for-embedded-uicc-technical-specification/). Please consult the issues list for more information on what's not quite done or needs improvement.
 

## System Requirements
 Njiwa is a Java-based server Web Service. It has been developed and tested
 on [JBOSS Wildfly v21.x](http://www.wildfly.org), but should run well under
 your favourite Web Services container. The main requirements for developing with or running Njiwa are:

* Java 8 (JDK/JRE 1.8) or better
* A relational SQL database (we use [PostgreSQL](http://www.postgresql.org))
* [Redis](http://redis.io) is used for caching transactional data
* [Gradle](https://gradle.org) is used as the build tool. 
  
## Getting Started

 In order to build Njiwa after downloading the source:

* Change to the folder *build*
* Run *gradle init* if this is the first time you are performing a build
* Run *gradle war* to build the *WAR* file. This will be placed in the sub-directory *build/libs* if all goes well.

## Deploying the WAR file to Wildfly

If this the first time you are deploying the WAR file to Wildfly/JBOSS, you must create 
certain resources needed to run Njiwa. Take note of the Wildfly/JBOSS home directory, referred to 
 as *${WILDFLYHOME}* below.

* If it does not exit, create the directory *${WILDFLYHOME}/modules/io/njiwa/main* and copy the 
  files *njiwa.settings* and *modules.xml* from the source sub-directory *etc/wildfly* into it. 
  These files are respectively the module registration and the server settings (for now very minimalist).
* Create a basic security domain called *njiwa* in Wildfly, with authorization policy set to *PermitAll*. Your *security-domains* configurations in the Wildfly XML configuration file should have an entry like this:
```
<security-domain name="njiwa" cache-type="default">
   <authorization>
     <policy-module code="PermitAll" flag="optional"/>
    </authorization>
</security-domain>
```

* Create the database for Njiwa and then create the JTA JDBC data source in Wildfly that will enable
  Njiwa connect to the database. The JNDI name must be *java:/njiwa*. [Here is the documentation for doing this in Wildfly 10.x](https://docs.jboss.org/author/display/WFLY10/DataSource%20configuration). 

 You may deploy the WAR file in the usual JBOSS/Wildfly manner (usually a matter of copying it to
 *${WILDFLYHOME}/standalone/deployments*. 


## Detailed setup.

1. First have java jdk > 8, gradle, postgresql, redis installed.

2. get the wildfly and set it up - https://websiteforstudents.com/how-to-install-wildfly-on-ubuntu-20-04-18-04/ note the version to be 21 and stop after the "sh /opt/wildfly/bin/jboss-cli.sh --connect" command. 

3. get a postgres sql JDBC jar file.  Make a new user and database. Use the following swebsite to deploy the JAR file to wildfly - https://www.learn-it-with-examples.com/middleware/wildfly/common-tasks/install-jdbc-driver-in-wildfly-for-postgresql.html

4. If you follow the step 2 without changing the installation path  then you will have wildfly in /opt/wildfl. Go to /opt/wildfly/standalone/configuration and open the standalone.xml and add:
```
<security-domain name="njiwa" cache-type="default">
   <authorization>
     <policy-module code="PermitAll" flag="optional"/>
    </authorization>
</security-domain>
```
After the security domain named "other". Repeat for /opt/wildfly/domain/configuration/domain.xml

6. Open the wildfly console(localhost:9990) and go to configurations-> add datascourse and add the database created in step 3 and JNDI name must be *java:/njiwa*.

7. Go to the repo location and execute the following code - 
```
cd build
gradle init
gradle war
```

8. Deploy the War file in "{RepoFolder}/build/build/libs".

## Getting Help, Helping Out, etc.

 We welcome feedback, assistance, etc. Please use the issues tab on github to send any of these, 
 or ping us directly at dev@njiwa.io - we'd love to hear from you.

 Thank you!

 
