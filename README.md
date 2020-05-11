# MapmyIndia Intouch Android SDK
## Introduction

MapmyIndia InTouch  will enable the live tracking functionality in your mobile app and allows you to get the powerful features from InTouch IoT platform for your telematics devices. InTouch SDK for Android lets you easily add MapmyIndia Telematics cloud services to your own Android application. 

Using this SDK, your app will fetch the live location from the end user mobile phone at the predefined condition based on the movement of the user, or fixed time interval or the combination of both. You can customize the data polling conditions. You can get the details about your other telematics device live location and analytics around it.

You will get seamless location benefits which cater to different domains like logistics, delivery tracking, Employee tracking, live location sharing etc.

-  [Publishable Key](#publishablekey): Please contact apisupport@mapmyindia.com to get your Publishable Key.
-   [Quick Start](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk#intouchdemo-app): Start with a ready-to-go app
-   [Integrate the SDK](#IntegrateIntouchSDK): Integrate the SDK into your app
-   [Dashboard](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki#-intouch-telematics-apis): See all your devices' locations on MapmyIndia InTouch Dashboard
- [InTouch Telematics APIs](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki): Use InTouch APIs to get the details of the devices.

## <a name="publishablekey">Get your Publishable Key. 

We use your Publishable key to identify your account details and assign all your user's device under single account. 

Please contact [apisupport@mapmyindia.com](mailto:apisupport@mapmyindia.com) to get your Publishable Key.

After getting the publishable key, you can [start with the InTouchDemo app](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk), or [Integrate the InTouch SDK](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk) in your app.

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

//Add InTouch as a dependency

dependencies {
    implementation 'com.mapmyindia.sdk:intouch-sdk:0.1.0'
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

<provider
    android:name="com.mapmyindia.sdk.intouch.InTouchProvider"
    android:authorities="${applicationId}.InTouchProvider"
    android:enabled="true"
    android:exported="false" />
~~~
~~~java
InTouch.initialize(<your publishable key>, new InTouchInitCallBack() {
	@Override
	public void onSuccess() {
	                       
	}
	@Override
	public void onError(String reason, String identifier, String description) {
	                           
	}
});

// set your device name for tracking
InTouch.setDeviceName(name); 
~~~


## <a name="StartTracking">Step 4: Start Tracking</a> 


Track your app user's phone live location by using the below method.

 ```java
 InTouch.startTracking();
```



