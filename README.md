# Servlet based Zmanim web app for App Engine

## Requirements
* [Google Cloud SDK](https://cloud.google.com/sdk/)
* `gcloud components install app-engine-java`
* `gcloud components update`

## Setup

* `gcloud init`

## Gradle
[Using Gradle and the App Engine Plugin](https://cloud.google.com/appengine/docs/flexible/java/using-gradle) 
& [Gradle Tasks and Parameters](https://cloud.google.com/appengine/docs/flexible/java/gradle-reference)
### Running locally

    $ gradle jettyRun

### Deploying

    $ gradle appengineDeploy
    
    
Sample instance: https://zmanim-app.appspot.com/
