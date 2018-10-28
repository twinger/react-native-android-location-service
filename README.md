
# react-native-android-location-service

## Getting started

`$ npm install react-native-android-location-service --save`

### Mostly automatic installation

`$ react-native link react-native-android-location-service`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-android-location-service` and add `RNAndroidLocationService.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAndroidLocationService.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

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

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNAndroidLocationService.sln` in `node_modules/react-native-android-location-service/windows/RNAndroidLocationService.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Android.Location.Service.RNAndroidLocationService;` to the usings at the top of the file
  - Add `new RNAndroidLocationServicePackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNAndroidLocationService from 'react-native-android-location-service';

// TODO: What to do with the module?
RNAndroidLocationService;
```
  