Galaxy - AppDirect Integration
==============================

Purpose
-------

The purpose of this application is to demonstrate the integration of a simple barebones **Java/Spring**
web application with AppDirect's Marketplace.

Runtime Configuration
=====================

Assumptions
-----------

The general idea behind the application implementation was to keep things simple. This way, more
focus and emphasis was made on the actual problem at hand, which is the integration of **AppDirect**
with the application.

Notable examples:
* Data exchanges are done over a non secured **http** connection instead of **https**
* User passwords are stored in clear text in the database
* The application U/I does not qualify as production grade, by far

Again, focus and simpliciy ...

Requirements
------------

The application uses [Gradle](http://www.gradle.org/) for building/packaging itself. 

The application assumes a **PostgreSQL** database server is configured, running and awaiting
connections. Its configuration must be matched in the properties described below.

You will also obviously need a valid **AppDirect** developer account. See
[AppDirect web site](http://info.appdirect.com/developers) for more details.

Configuration
-------------

If you want to build/deploy this code, you will first want to edit the *galaxy-runtime.properties*
file located in the */src/main/resources* folder.

The properties below MUST be updated to reflect your runtime setup. The other properties in the
file can also be *optionally* changed, if needed.

Properties:
* **dbUsername**
* **dbPassword**

* **appDirectOAuthConsumerKey**
* **appDirectOAuthConsumerSecret**

Building
--------

To produce a valid WAR file, simply use the following command line from the root folder of the
project (where the build.gradle file is located):

**gradle build**

The build artefact (*Galaxy.war*) will be produced in the *build/libs* folder. You can grab the WAR from that
location and have it deployed on your app server. The application was tested using a Tomcat 7 application server.

If you want to work with the project inside Eclipse, use the following command line to generate the Eclipse .project
and .classpath files.

**gradle eclipse**

Bootstrapping
-------------

The application needs to be bootstrapped. That is a database schema and some seed data needs to be created. To
do so, use the following Gradle command from the root folder of the project:

**gradle dbInit**

This will drop the existing Galaxy database (if it exists) and re-create it.

To log into the application from the web interface, you can use the user/password **admin/admin**. More users
are also bootstraped, if needed. The actual seed database script can be found @ */db/pg.seed.sql*.

AppDirect Configuration
=======================

In your **AppDirect** account, make sure you have your integration points configured with the following
information. You MUST replace all occurences of **{HOST[+PATH]}** with the actual web host address and
optional web context path where the application will be hosted. Obviously, this host MUST be visible/accessible
from the internet.

Single Sign On
--------------

**Login URL**
http://{HOST[+PATH]}/login-openid?openid_identifier={openid}

**OpenID Realm**
http://{HOST[+PATH]}/*

Subscriptions
-------------

**Subscription Create Notification URL**
http://{HOST[+PATH]}/api/v1/appDirect/subscriptionCreate?eventUrl={eventUrl}

**Subscription Change Notification URL**
http://{HOST[+PATH]}/api/v1/appDirect/subscriptionChange?eventUrl={eventUrl}

**Subscription Cancel Notification URL**
http://{HOST[+PATH]}/api/v1/appDirect/subscriptionCancel?eventUrl={eventUrl}

**Subscription Status Notification URL**
http://{HOST[+PATH]}/api/v1/appDirect/subscriptionStatus?eventUrl={eventUrl}

Access Management
-----------------

**User Assignment Notification URL**
http://{HOST[+PATH]}/api/v1/appDirect/userAssign?eventUrl={eventUrl}

**User Unassignment Notification URL**
http://{HOST[+PATH]}/api/v1/appDirect/userUnassign?eventUrl={eventUrl}

Closing Words
=============

That's it ... That should be everything that's needed to make this "proof of concept" working. If you have
any questions, comments or feedback, please feel free to contact me at yanavery (at) gmail (dot) com.

Thank you!
