# android-studio-java-krdae-api-library

[![](https://jitpack.io/v/sqayner/android-studio-java-earthquake-krdae-api-library.svg)](https://jitpack.io/#sqayner/android-studio-java-earthquake-krdae-api-library)

## Implementation
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.sqayner:android-studio-java-earthquake-krdae-api-library:1.0.0'
}
```

```java
new KRDAE(this, new KRDAE.OnEarthquakeLoadListener() {
            @Override
            public void onLoad(List<KrdaeEarthquakeModel> earthquakes) {
                for (KrdaeEarthquakeModel earthquake : earthquakes) {
                  //list earthquakes
                }
            }
        }).load();
```
