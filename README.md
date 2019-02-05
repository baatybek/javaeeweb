# javaeeweb

Breifly into project
Application can make CRUD operation using RESTful web services.

/*--------------------------------------------------------------------------------------------*/
CarEvidenceServer is a server part of my java ee web application.
It is connected to JDBC resource, my JDBC resource was created in GlassFish admin console.
CarEvidenceServer has 3 entities, which represent business layer of application. Entities have @OneToMany and @ManyToOne relationships.
RESTful Web Services API was used for CarEvidenceServer for CRUD operations.

/*--------------------------------------------------------------------------------------------*/
CarEvidenceClient is a client part of my java ee web application was created using Vaadin.
It consumes Server application using RESTful java client apps.
UI was created to interact with application using Web Browser.

/*In case you want to try the application:
  * Requirements NetBeans IDE 8.2, GlassFish 5.
  1. You have to create JDBC GlassFish resource.
  2. Replace the exisiting persintence with new one that you create.
  3. Make sure javaee-web-api is not missing
  4. Clean and build and make sure GlassFish is running.
  5. Go to the client application.
  6. Make sure javaee-web-api is not missing
  7. Clean and build and run
*/
