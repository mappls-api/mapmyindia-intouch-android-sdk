
# MapmyIndia Intouch Android SDK
## Introduction

Get Real-Time Location Tracking for your apps with MapmyIndia InTouch SDK. Track a user's live location with our simplified InTouch SDK integration (for Android), highly customizable to your specific needs.

The InTouch SDK comes with a variety of events that enable better control and power over your tracking needs. Get ready made events to create,,  Geo-Fences, Event Alerts, and Trails of telematics/ phone devices. Also get location benefits built for various applications including logistics, delivery tracking, employee tracking, and live location sharing.

To get started, explore the InTouch Demo App.

Already have an application? Give it a boost with the powerful features of InTouch. Learn how to [Integrate the InTouch SDK](#IntegrateIntouchSDK)


-  [Setup](#Setup): Please contact apisupport@mapmyindia.com to get the Intouch SDK authorisation for your Client ID and Client Secret.
-  [Quick Start](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk#intouchdemo-app): Start with a ready-to-go app
-  [Integrate the SDK](#IntegrateIntouchSDK): Integrate the SDK into your app
-  [Dashboard](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki#-intouch-telematics-apis): See all your devices' locations on MapmyIndia InTouch Dashboard
- [InTouch Telematics APIs](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki): Use InTouch APIs to get the details of the devices.

## <a name="Setup">Setup. 

We use your Client ID to identify your account details and assign all your user's devices under a single account. 

To get your Outh2 Rest API Client ID and Client Secret please login to MapmyIndia [API Dashboard](https://www.mapmyindia.com/api/dashboard)  

Please contact [apisupport@mapmyindia.com](mailto:apisupport@mapmyindia.com) to get InTouch SDK access to your Client ID

After getting the access, you can [start with the InTouchDemo app](#InTouchDemoApp), or [Integrate the InTouch SDK](#IntegrateIntouchSDK) in your app.

## <a name= "InTouchDemoApp">InTouchDemo app</a>
This guide allows you to add live location tracking to an Android app. [Android Studio](https://developer.android.com/studio/index.html) is the recommended development environment for building an app with the MapmyIndia InTouch SDK for Android.
#### Step 1. Download the InTouchDemo App.
[Click here](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/archive/master.zip) to download the InTouchDemo App Project. Open this project in [Android Studio](https://developer.android.com/studio/index.html)

#### Step 2. Set your Publishable key

1.  Add the publishable key to  [SetUpKeyFragment Fragment](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk)  file.
    
2.  Run project on your device using simulator instance.
    
3.  Go through run-time [permission flow](https://developer.android.com/training/permissions/requesting) (applicable for Android M and later).

#### Step 3. Check your location on the InTouch [dashboard](https://intouch.mapmyindia.com/nextgen)

## <a name="IntegrateIntouchSDK">Integrate the InTouch SDK
-  [Add InTouch SDK](#AddInTouchSDK)
-  [Start tracking](#StartTracking)
-  [InTouch Telematics APIs](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki#-intouch-telematics-apis)
-  [Utility methods](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki/Utility-Methods)

## Step 1: Setup a project

1.  Start Android Studio.
2.  Create a new project as follows:
    -   If you see the  **Welcome to Android Studio**  dialog, choose  **Start a new Android Studio project**, available under 'Quick Start' on the right of the dialog.
    -   Otherwise, click  **File**  in the Android Studio menu bar, click  **New**-> **New Project**.
    -   Select the form factors you need for your app. If you're not sure what you need, just select  **Phone and Tablet**.
    -   Select  **Add No Activity**  and click  **Next**.
3.  Enter your app name, package name, project location, language and minimum API version as prompted. Click  **Next**.
4.  Create a  **Basic Activity**  in the 'Add an activity to Mobile' dialog. Click  **Next**.
5.  Enter the activity name, layout name and title as prompted. Click  **Finish**.

Android Studio starts Gradle and builds your project. This may take a few seconds. For more information about creating a project in Android Studio, see the  [Android Studio documentation](https://developer.android.com/studio/projects/create-project.html).

The next few sections contain the code samples that you need to add to your activity's java file as well as its xml layout file for creating an app with MapmyIndia InTouch SDK for live tracking.


## <a name="AddInTouchSDK">Step 2. Add InTouch SDK</a>
Follow these steps to add the SDK to your project –
-   Create a new project in Android Studio
-   Add MapmyIndia repository in your project level  `build.gradle`
```java
allprojects {  
    repositories {  
    
        maven {  
            url 'https://maven.mapmyindia.com/repository/mapmyindia/' 
        }  
    }  
}
```
-  Add below dependency in your app-level `build.gradle`
```java
implementation 'com.mapmyindia.sdk:intouch-sdk:0.9.4'
```

### Add Java 8 Support to the project
```java
compileOptions {
      sourceCompatibility 1.8
      targetCompatibility 1.8
  }
```
```java
 Required Minimum sdk version
 minSdkVersion 18  
 ```
## Step 3: Initialize InTouch SDK

Initialization can be done either of the below mentioned method. Keep the same method accross your project. 

#### Method 1:
Initialize the SDK without unique Id. Intouch SDK will create a unique Id for the device. On every new device Login , a new device will be created and the new device id will be returned. 
####  Java
~~~java
// IAuthListener - returns authorization results in the forms of callbacks.
InTouch.initialize(<device name>, <your client id>, <your client secret>, new IAuthListener() {
	@Override
	public void onSuccess(Long id) {
			  //write your code here                      
	}
	@Override
	public void onError(String reason, String identifier, String description) {
	         // reason gives the error type. 
            // errorIdentifier gives information about error code. 
           // errorDescription gives a message for a particular error.
	}
});

~~~
####  Kotlin
```Kotlin
InTouch.initialize(<device name>, <your client id>, <your client secret>, object : IAuthListener {  
    override fun onSuccess() {  
         //write your code here 
    }  
	override fun onError(reason: String?, errorIdentifier: String?, errorDescription: String?) {  
       // reason gives the error type. 
      // errorIdentifier gives information about error code. 
      // errorDescription gives a message for a particular error. 
    }  
  
})
```
### or 
#### Method 2:
Initialize the SDK with your own Unique Id. It is recommended to use when you maintain the unique Id for your users. Now when ever this Unique Id is passed same device id will be returned in the response, even if the user s logs from different devices. 
####  Java
~~~java
// IAuthListener - returns authorization results in the forms of callbacks.
InTouch.initialize(<device name>, <your client id>, <your client secret>, <uniqueId>, <fcmToken>, new IAuthListener() {
	@Override
	public void onSuccess(Long id) {
			  //write your code here                      
	}
	@Override
	public void onError(String reason, String identifier, String description) {
	         // reason gives the error type. 
            // errorIdentifier gives information about error code. 
           // errorDescription gives a message for a particular error.
	}
});

~~~
####  Kotlin
```Kotlin
InTouch.initialize(<device name>, <your client id>, <your client secret>, <uniqueId>, <fcmToken>, object : IAuthListener {  
    override fun onSuccess(Long entityId) {  
         //write your code here 
    }  
	override fun onError(reason: String?, errorIdentifier: String?, errorDescription: String?) {  
       // reason gives the error type. 
      // errorIdentifier gives information about error code. 
      // errorDescription gives a message for a particular error. 
    }  
  
})
```
On sucessful registration you will get the Id, use this Id in APIs to get the live location or to create trips against the user.

## <a name="StartTracking">Step 4: Start Tracking</a> 


Track your app user's phone live location by using the below method.
####  Java
 ```java
 InTouch.startTracking();
```
####  Kotlin
```Kotlin
InTouch.startTracking
```

## <a name="StopTracking">Step 4: Stop Tracking</a> 

####  Java
 ```java
 InTouch.stopTracking();
```
####  Kotlin
```Kotlin
InTouch.stopTracking
```

## <a name="Supportedplugin">Supported Plugin</a>
### - [MapmyIndia BLE Plugin](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki/MapmyIndia-BLE-Plugin)

