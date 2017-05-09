Fuse Integration Services (FIS) Use Case One - RestDSL wrapper around a datasource
====================================

Demonstration of a RestDSL wrapper with Swagger Docs around a RDBS datasource using the Fuse Integration Services SpringBoot image.  The demo uses an in-memory Apache Derby relational database, which can easily be replaced with any relational database.

## Overview

This project demonstrates a Restful wrapper around a relational database.  Using an in-memory Apache Derby relational database as the datasource, the usecase can easily be updated to connect to any relational database (see the commented bean hooks for Oracle in the camel-context.xml).  Additionally, the REST API is documented using Swagger.  The project makes use of camel-servlet component listening on port 8080 and configured using Spring Boot.

## Prerequisites

The project can run either as a standalone Java process or in a cloud environment.  An OpenShift environment must be present for deployment to to a cloud environment.  For the purpose of testing, I prefer to use [Minishift](https://fabric8.io/guide/getStarted/minishift.html)

## Deployment

This project can be deployed using two methods:

* Standalone Spring-Boot container
* On an Openshift environment using Fuse Integration Services 2.0

## Standalone Spring Boot Container

The standalone method takes advantage of the [Camel Spring Boot Plugin](http://camel.apache.org/spring-boot.html) to build and run the microservice.

Execute the following command from the root project directory:

```
mvn spring-boot:run -Dspring.profiles.active=dev
```

Once the spring boot service has started, you can test the REST API by executing the following command

```
curl http://localhost:8080/api/orgNameSearch/findAll
```

A complete list of Banks with corresponding ORG_ID is returned in JSON format.  You can also retrieve individual banks by specifying the corresponding ORG_ID:

```
curl http://localhost:8080/api/orgNameSearch/123
```

Additionally, it's possible to view paginated results using a partial organization name.  A good example of this would be:

```
curl http://localhost:8080/api/orgNameSearch/Bank/1/5
```

where "Bank" is the partial search word, "1" is the page number and "5" is the number of results per page.

Lastly, it's possible to add a new organization to database using the following command:

```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "org_id": 888, "org_name": "Bank of Simon" }' 'http://localhost:8080/api/orgNameSearch'
 ```

It's also possible to navigate the REST service using the Swagger documentation [here](http://localhost:8080/index.html).

## Openshift / Minishift Deployment

First, create a new OpenShift project called *fis-org-lookup-service*

```
oc new-project fis-org-lookup-service --description="Fuse Integration Services Datasource Rest Wrapper Demo" --display-name="Datasource Rest Wrapper"
```

Execute the following command which will execute the *ocp* profile that executes the `clean fabric8:deploy` maven goal:

```
mvn -P ocp
```

The fabric8 maven plugin will perform the following actions:

* Compiles and packages the Java artifact
* Creates the OpenShift API objects
* Starts a Source to Image (S2I) binary build using the previously packaged artifact
* Deploys the application using binary streams

## Swagger UI

A [Swagger User Interface](http://swagger.io/swagger-ui/) is available within the fis-org-lookup-service application to view and invoke the available services. 

Select the hyperlink for the gateway application to launch the Swagger UI

![](images/swagger.png "Swagger User Interface")

The raw swagger definition can also be found at the context path `api/api-doc` 

## Command Line Testing

Using a command line, execute the following to query the definition service

```
curl -s http://$(oc get routes fis-org-lookup-service --template='{{ .spec.host }}')/api/citiesByCountry/Australia | python -m json.tool
```
	
A successful response will output the following

```
[
    {
        "City": "Archerfield Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Amberley Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Alice Springs Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Brisbane Airport M. O",
        "Country": "Australia"
    },
    {
        "City": "Coolangatta Airport Aws",
        "Country": "Australia"
    },
    {
        "City": "Cairns Airport",
        "Country": "Australia"
    },
    {
        "City": "Charleville Airport",
        "Country": "Australia"
    },
    {
        "City": "Gladstone",
        "Country": "Australia"
    },
    {
        "City": "Longreach Airport",
        "Country": "Australia"
    },
    {
        "City": "Mount Isa Amo",
        "Country": "Australia"
    },
    {
        "City": "Mackay Mo",
        "Country": "Australia"
    },
    {
        "City": "Oakey Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Proserpine Airport",
        "Country": "Australia"
    },
    {
        "City": "Rockhampton Airport",
        "Country": "Australia"
    },
    {
        "City": "Broome Airport",
        "Country": "Australia"
    },
    {
        "City": "Townsville Amo",
        "Country": "Australia"
    },
    {
        "City": "Weipa City",
        "Country": "Australia"
    },
    {
        "City": "Gove Airport",
        "Country": "Australia"
    },
    {
        "City": "Tennant Creek Airport",
        "Country": "Australia"
    },
    {
        "City": "Yulara Aws",
        "Country": "Australia"
    },
    {
        "City": "Albury Airport",
        "Country": "Australia"
    },
    {
        "City": "Devonport East",
        "Country": "Australia"
    },
    {
        "City": "Goldstream Aws",
        "Country": "Australia"
    },
    {
        "City": "East Sale Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Hobart Airport",
        "Country": "Australia"
    },
    {
        "City": "Launceston Airport",
        "Country": "Australia"
    },
    {
        "City": "Laverton Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Moorabbin Airport Aws",
        "Country": "Australia"
    },
    {
        "City": "Mount Gambier Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Mildura Airport",
        "Country": "Australia"
    },
    {
        "City": "Melbourne Airport",
        "Country": "Australia"
    },
    {
        "City": "Macquarie Island",
        "Country": "Australia"
    },
    {
        "City": "Wynyard West",
        "Country": "Australia"
    },
    {
        "City": "Adelaide Airport",
        "Country": "Australia"
    },
    {
        "City": "Albany Airport",
        "Country": "Australia"
    },
    {
        "City": "Broken Hill Patton Street",
        "Country": "Australia"
    },
    {
        "City": "Ceduna Airport",
        "Country": "Australia"
    },
    {
        "City": "Derby",
        "Country": "Australia"
    },
    {
        "City": "Darwin Airport",
        "Country": "Australia"
    },
    {
        "City": "Bullsbrook Pearce Amo",
        "Country": "Australia"
    },
    {
        "City": "Edinburgh M. O.",
        "Country": "Australia"
    },
    {
        "City": "Forrest Airport",
        "Country": "Australia"
    },
    {
        "City": "Geraldton Airport",
        "Country": "Australia"
    },
    {
        "City": "Kalgoorlie Boulder Amo",
        "Country": "Australia"
    },
    {
        "City": "Kununurra Kununurra Aws",
        "Country": "Australia"
    },
    {
        "City": "Leigh Creek Airport",
        "Country": "Australia"
    },
    {
        "City": "Learmonth Airport",
        "Country": "Australia"
    },
    {
        "City": "Meekatharra Airport",
        "Country": "Australia"
    },
    {
        "City": "Port Hedland Pardoo",
        "Country": "Australia"
    },
    {
        "City": "Parafield Airport",
        "Country": "Australia"
    },
    {
        "City": "Belmont Perth Airport",
        "Country": "Australia"
    },
    {
        "City": "Katherine Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Woomera Aerodrome",
        "Country": "Australia"
    },
    {
        "City": "Bankstown Airport Aws",
        "Country": "Australia"
    },
    {
        "City": "Canberra",
        "Country": "Australia"
    },
    {
        "City": "Coffs Harbour Mo",
        "Country": "Australia"
    },
    {
        "City": "Cooma",
        "Country": "Australia"
    },
    {
        "City": "Camden Airport",
        "Country": "Australia"
    },
    {
        "City": "Dubbo",
        "Country": "Australia"
    },
    {
        "City": "Norfolk Island Airport",
        "Country": "Australia"
    },
    {
        "City": "Nowra Ran Air Station",
        "Country": "Australia"
    },
    {
        "City": "Richmond Aus-Afb",
        "Country": "Australia"
    },
    {
        "City": "Sydney Airport",
        "Country": "Australia"
    },
    {
        "City": "Tamworth Airport",
        "Country": "Australia"
    },
    {
        "City": "Wagga Airport",
        "Country": "Australia"
    },
    {
        "City": "Williamtown Aerodrome",
        "Country": "Australia"
    }
]
```
