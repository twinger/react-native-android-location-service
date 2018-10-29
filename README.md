
# react-native-android-location-service
React Native Android Location using Location Manager 
You should using with 'react-native-android-location-services-dialog-box' & 'react-native-android-settings-library' 
for check all case of turn on/off location in Android device


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

#### Add Permissions and used Google API to your Project

Add this to your AndroidManifest file;

``` xml
// file: android/app/src/main/AndroidManifest.xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-feature android:name="android.hardware.location.gps" />
<uses-feature android:name="android.hardware.location.network" />

```

## Usage
```javascript
import React, { Component } from 'react';
import { View, Text, DeviceEventEmitter, NativeModules } from 'react-native';
import Permissions from 'react-native-permissions';

import RNAndroidLocationService from 'react-native-android-location-service';
export default class extends Component {
    state = {
        lng: 0.0,
        lat: 0.0,
    }
    componentDidMount() {
        this.requestPermission();
        if (!this.eventEmitter) {
            // Register Listener Callback - has to be removed later
            this.eventEmitter = DeviceEventEmitter.addListener('updateLocation', this.onLocationChange.bind(this));
            // Initialize RNGLocation
            RNAndroidLocationService.getLocation();
        }
    }
    onLocationChange(e) {
        this.setState({
            lng: e.Longitude,
            lat: e.Latitude
        });
    }

    componentWillUnmount() {
        // Stop listening for Events
        this.eventEmitter.remove();
    }
    //request permission to access location
    requestPermission = () => {
        Permissions.request('location')
            .then(response => {
                //returns once the user has chosen to 'allow' or to 'not allow' access
                //response is one of: 'authorized', 'denied', 'restricted', or 'undetermined'
                this.setState({ locationPermission: response })
            });
    }
    render() {
        return (
            <View>
                <Text>Test location</Text>
                <Text>Lng: {this.state.lng} Lat: {this.state.lat}</Text>
            </View>

        );
    }
}
```
  