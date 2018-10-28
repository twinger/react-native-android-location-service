
# react-native-android-location-service

## Getting started

`$ npm install react-native-android-location-service --save`

### Mostly automatic installation

`$ react-native link react-native-android-location-service`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import co.twinger.location.RNAndroidLocationServicePackage;` to the imports at the top of the file
  - Add `new RNAndroidLocationServicePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-location-service'
  	project(':react-native-android-location-service').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-location-service/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-location-service')
  	```

## Usage
```javascript
import RNAndroidLocationService from 'react-native-android-location-service';

// TODO: What to do with the module?
RNAndroidLocationService;
```
  