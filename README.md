# OpenTable Code Challenge

The challenge is to create a simple Android app that exercises a REST-ful API. The API endpoint : http://api.nytimes.com/svc/movies/v2/reviews/dvd-picks.json?order=by-date&api-key=b75da00e12d54774a2d362adddcc9bef results in a json response which is a list of different movie reviews published by ny times. 

## Features Beyond Requirements

* “Endless” scrolling list: When approaching the end of the current list, the app will load more content.
* Review Content View: Upon selecting a list item, the review content will be fetched and displayed as a dialog over the list of reviews.
* Lifecycle awareness to retain data through configuration changes

### Framework & Libraries

* Retrofit for Networking
* RxJava for thread management of network calls
* LiveData for lifecycle aware observable data
* ViewModel of Android Architecture Components
* ButterKnife for resource resolution
* Glide for image loading

### Limitations & Known Issues

* Persistent cache of reviews to reduce network load and support off-line 
* Support for fragments or alternative frameworks such as Conductor
* Espresso tests

