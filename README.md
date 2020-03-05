# Wessager
Wessager is wear messager
Communication between phone and wear in a simple way by using coroutines.

## Dependencies
```gradle
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
compileOnly "com.google.android.wearable:wearable:$wearable_version"
implementation "com.google.android.gms:play-services-wearable:$gms_wearable_version"
```
## Usage
copy Wessager to your project

### sample:
```kotlin
// get a token from phone or wear
launch {
    val token = Wessager.send(msg = "get token", waitResponse = true)
    updateToken(token)
}
...
// on another device
 override fun onMessageReceived(msg: String, sessionId: Long) {
        Wessager.send(msg = "token:xxx", waitResponse = false, sessionId = sessionId)
 }
```