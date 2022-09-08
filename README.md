# SAMPLE REST api application

## App purpose
Presents some shortcuts to NewYorkTimes news. It uses Times [Newswire API] provided by NYT developers portal. 

## Project purpose 
Build a project containing modern android technologies and solutions to achieve the conception of a basic app. In other words, it contains mainly all 
what I would be asked to code during an interview.

### Used techno: 
- Lifecycle-aware components: [ViewModel], [LiveData], [Coroutines]
- Json and serialization: [Kotlin serialization][kotlinSerial]
- Web services:  [Retrofit] + [serial converter][JakeRetrofitSerialConverter] 
- Dependencies injection: [Hilt] (see injection with [Koin] on branch save/koin_injection)
- UI: Compose, [Coil] for remote images
- Rest api key management: [secret gradle plugin]


[//]: # (links)
[Newswire API]: https://developer.nytimes.com/docs/timeswire-product/1/overview
[kotlinSerial]: https://github.com/Kotlin/kotlinx.serialization
[Retrofit]: https://github.com/square/retrofit 
[JakeRetrofitSerialConverter]: https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter 
[Hilt]: https://developer.android.com/training/dependency-injection/hilt-android 
[Koin]: https://insert-koin.io/ 
[ViewModel]: https://developer.android.com/topic/libraries/architecture/viewmodel 
[LiveData]: https://developer.android.com/topic/libraries/architecture/livedata 
[Coroutines]: https://developer.android.com/topic/libraries/architecture/coroutines 
[Coil]: https://coil-kt.github.io/coil/ 
[secret gradle plugin]: https://github.com/google/secrets-gradle-plugin 

