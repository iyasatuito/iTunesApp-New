![Image description](https://github.com/iyasatuito/ItunesApp/blob/master/new_preview_itunes_app.png)

# ItunesApp 

- By Pia Satuito
- Implemented search function using iTunesAPI
- Developed using Kotlin

# Features
- Displays featured movies, TV shows and top music in homepage
- Clicking on an item takes user to detail page screen
- Search for movies, tv shows
- Has infinite scroll

# Persistence
- used ROOM database for persistence purposes
- showing cached data when not connected to the internet on the main page

# Architecture
- Design Pattern used : MVVM / Clean Architecture
- Benefits : abstraction, maintainability, testability

# Frameworks / Technology Used
- Android Architecture Components
- Dagger2 for Dependency Injection
- Retrofit2 for HTTP Client
- Glide for image loading
- RxJava2
- Database : ROOM 
- GSON for serialization/deserialization
- Used Mockk for unit test
- KenburnsView for aesthetics purposes

# To install
- Download "itunes_app.apk" and install

# Notes
- used different layout / view for search items and main page items to show distiction
- country used for searching = au
- wasn't able to apply UI test 
