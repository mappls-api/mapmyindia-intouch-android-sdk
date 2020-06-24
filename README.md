
# MapmyIndia Intouch Android SDK
## Introduction

MapmyIndia InTouch SDK provides the Real Time Location Tracking for your apps. Track your app user's live location with simplified SDK integration for android. Highly customizable to specific needs.

InTouch SDK provides variety of events to provide better control and power to tracking. It gives readymade events to create Geo-fence, Event Alerts and Trails of Telematics/phone devices. Get location benefits catered to different domains like Logistics, delivery tracking, employee tracking, live location sharing etc.

You can begin with [InTouch Demo App](#InTouchDemoApp) for your use cases. If you have an app already and want to leverage powerful features of InTouch,  [Integrate the InTouch SDK](#IntegrateIntouchSDK) with your application.


-  [Publishable Key](#publishablekey): Please contact apisupport@mapmyindia.com to get your Publishable Key.
-   [Quick Start](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk#intouchdemo-app): Start with a ready-to-go app
-   [Integrate the SDK](#IntegrateIntouchSDK): Integrate the SDK into your app
-   [Dashboard](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki#-intouch-telematics-apis): See all your devices' locations on MapmyIndia InTouch Dashboard
- [InTouch Telematics APIs](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki): Use InTouch APIs to get the details of the devices.

## <a name="publishablekey">Get your Publishable Key. 

We use your Publishable key to identify your account details and assign all your user's device under single account. 

Please contact [apisupport@mapmyindia.com](mailto:apisupport@mapmyindia.com) to get your Publishable Key.

After getting the publishable key, you can [start with the InTouchDemo app](#InTouchDemoApp), or [Integrate the InTouch SDK](#IntegrateIntouchSDK) in your app.

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
- [InTouch Telematics APIs](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki#-intouch-telematics-apis)
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

Add following lines to your applications  `build.gradle`:
```java
// Import the SDK within your repositories block
allprojects {  
    repositories {  
        google()  
        jcenter()  
        maven {  
            url 'https://maven.mapmyindia.com/repository/mapmyindia/'  
  
  }  
    }  
}

//Add to build.gradle
android{
	-----
	compileOptions {
        	sourceCompatibility 1.8
        	targetCompatibility 1.8
    	}
  	-----
}
dependencies {
    implementation 'com.mapmyindia.sdk:intouch-sdk:0.3.0'
    ...
}
```
```
 Required Minimum sdk version
 minSdkVersion 16  
 ```
## Step 3: Initialize InTouch SDK

Initialize the SDK with your [Publishable Key](Test)

~~~xml
// Add the following to your AndroidManifest.xml file.
<uses-permission android:name="android.permission.INTERNET" />
~~~
~~~java
// IAuthListener - returns authorization results in the forms of callbacks.
InTouch.initialize(<device name>, <your client id>, <your client secret>, new IAuthListener() {
	@Override
	public void onSuccess() {
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


## <a name="StartTracking">Step 4: Start Tracking</a> 


Track your app user's phone live location by using the below method.

 ```java
 InTouch.startTracking();
```



