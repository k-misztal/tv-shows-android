[![Build Status](https://travis-ci.com/k-misztal/tv-shows-android.svg?branch=master)](https://travis-ci.com/k-misztal/tv-shows-android)
## Tv Shows

Tv Shows is simple sample app that fetches most popular tv shows using [TMDB](https://www.themoviedb.org) public API. In the show details there is list of similar shows. 

This is an example how to build an app with MVVM architecture. Android MVVM + Kotlin + Architecture Components + Dagger2 + RxJava2.

Note that release build, signing and proguard rules are not configured. 

### Architecture

App uses MVVM architecture using [Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html), specifically `ViewModel` and `LiveData`. Thanks to that ViewModel survives configuration chagnes, like orientation chagne. 

App uses Retrofit and RxJava2 to fetch the data from tmdb api. When the data is fetched ViewModel exposes new immutable state to the subscribers. 

App uses Dagger2 for dependency injection. I used new injection API introduced in `2.12` version of dagger. Thanks to that activities/fragments are totally not aware of injection process. 

Before that API was introduced, activity had to hold reference to dagger components. 
