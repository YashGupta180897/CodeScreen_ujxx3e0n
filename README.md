# Java-CodeScreen-Films-API

The CodeScreen Film API is a service that contains one endpoint,<br/>
`GET https://app.codescreen.dev/api/assessments/films`, which returns the details of a large number of different films.

When you send an `HTTP GET` request to the endpoint, the response will be a `200 OK`, which includes a body containing a list of film data in `JSON` format. 

For authentication, you need to send your API token in the `Authorization HTTP header` using the [Bearer authentication scheme](https://tools.ietf.org/html/draft-ietf-oauth-v2-bearer-20#section-2.1). Your API token is `8c5996d5-fb89-46c9-8821-7063cfbc18b1`.

Here is an example of how to send the request from cURL:

    curl -H "Authorization: Bearer 8c5996d5-fb89-46c9-8821-7063cfbc18b1" \
    https://app.codescreen.dev/api/assessments/films
    
An example response is the following:

     [
       {
         "name": "Batman Begins",
         "length": 140,
         "rating": 8.2,
         "releaseDate": "2006-06-16",
         "directorName": "Christopher Nolan"
       },
       {
         "name": "Alien",
         "length": 117,
         "rating": 8.4,
         "releaseDate": "1979-09-06",
         "directorName": "Ridley Scott"
       }
     ]


The `name` field represents the name of the film. The `length` field represents the duration of the film in minutes. The `rating` is the <a href="https://www.imdb.com/" target="_blank">`IMDb`</a> rating for the film, out of 10.
And the `releaseDate` is the date in which the film was released in the United Kingdom, and the `directorName` field is the name of the film's director.

## Your Task

You are required to implement the methods in the [FilmsAPIService](src/main/java/dev/codescreen/films/FilmsAPIService.java) and [FilmDataStatsGenerator](src/main/java/dev/codescreen/films/FilmDataStatsGenerator.java) classes in such a way that
all the unit tests in [FilmDataStatsGeneratorTest](src/test/java/dev/codescreen/films/FilmDataStatsGeneratorTest.java) pass.

[FilmsAPIService](src/main/java/dev/codescreen/films/FilmsAPIService.java) should be implemented in such a way that you only need to call out to the CodeScreen Films API
endpoint once per full run of the [FilmDataStatsGeneratorTest](src/test/java/dev/codescreen/films/FilmDataStatsGeneratorTest.java) test suite.

## Requirements

The [FilmDataStatsGeneratorTest](src/test/java/dev/codescreen/films/FilmDataStatsGeneratorTest.java) file class should not be modified. If you would like to add your own unit tests you
can add these in a separate class.

The [pom.xml](pom.xml) file should only be modified to add any third-party dependencies required for your solution.

You are free to use whichever libraries you want (JDK or third-party) when implementing your solution. </br>
Note we recommend using the <a href="https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html" target="_blank">`Java.time`</a> JDK library for working with dates & times, and the <a href="https://square.github.io/okhttp/" target="_blank">`OkHttp`</a> client library to interact with the CodeScreen Film API service.

Note the data returned from the films endpoint is small enough to fit within the default JVM heap size.

Your solution also must use/be compatible with `Java 11`.

## Tests
Run `mvn clean test` to run the unit tests. These should all pass if your solution has been implemented correctly.<br>
**Please** check that all the unit tests pass when you run `mvn clean test`, rather than solely running them from inside your IDE.

##

This test should take no longer than 2 hours to complete successfully.

Good luck!

## Submitting your solution

Please push your changes to the `master branch` of this repository. You can push one or more commits. <br>

Once you are finished with the task, please click the `Complete task` link on <a href="https://app.codescreen.dev/#/codescreentest5c4af98f-aba2-4933-b82c-3354b4715a69" target="_blank">this screen</a>.