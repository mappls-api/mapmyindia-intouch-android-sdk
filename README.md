
# MapmyIndia Intouch Android SDK
## Introduction

MapmyIndia Intouch  will enable the live tracking functionality in your mobile app and allows you to get the powerful features from Intouch IoT platform for your telematics devices. Inotuch SDK for Android lets you easily add MapmyIndia Telematics cloud services to your own Android application. 

Using this SDK, our app shall fetch the live location from the end user mobile phone at the predefined condition, It could be based on the movement of the user, or fixed time interval or the combination of both. You can customize the data polling conditions. Along with that  you can get the details about your other telematics device live location and analytics around that.

You will get seemless location benifits which caterted to different domains like logistics, delivery tracking, Employee tracking, live location sharing etc.

-  [Publishable Key](#publishablekey): Please contact apisupport@mapmyindia.com to get your Publishable Key.
-   [Quick Start](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk#intouchdemo-app): Start with a ready-to-go app
-   [Integrate the SDK](#AddIntouchSDK): Integrate the SDK into your app
-   [Dashboard](https://intouch.mapmyindia.com/nextgen/#/home/dashboard): See all your devices' locations on MapmyIndia Intouch Dashboard
-   [FAQs (W.I.P)](): Frequently asked questions

## Get your Publishable Key. 

We use your Publishable key to identify your account details and assign all your users device under single account. 

Please contact [apisupport@mapmyindia.com](mailto:apisupport@mapmyindia.com) to get your Publishable Key.

After getting the publishable key, you can [start with the IntouchDemo app](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk), or [Integrate the Intouch SDK](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk) in your app.

## <a name= "IntouchDemoApp">IntouchDemo app</a>
This guide allows you to add a live location tracking to an Android app. [Android Studio](https://developer.android.com/studio/index.html) is the recommended development environment for building an app with the MapmyIndia Intouch SDK for Android.
#### Step 1. Download the IntouchDemo App.
[Click here](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/archive/master.zip) to download the IntouchDemo App Project. Open this project in [Android Studio](https://developer.android.com/studio/index.html)

#### Step 2. Set your Publishable key

1.  Add the publishable key to  [SetUpKeyFragment Fragment](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk)  file.
    
2.  Run project on your device use simulator instance.
    
3.  Go through run-time [permission flow](https://developer.android.com/training/permissions/requesting) (applicable for Android M and later).

#### Step 3. Check your location on the Intouch [dashboard](https://intouch.mapmyindia.com/nextgen)

## Integrate the Intouch SDK
-  [Add Intouch SDK](#AddIntouchSDK)
-  [Start tracking](#StartTracking)
- [Intouch Telematics APIs](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki#-intouch-telematics-apis)
-  [Utility methods](https://github.com/MapmyIndia/mapmyindia-intouch-android-sdk/wiki/Utility-Methods)

## Step 1: Setup a project

1.  Start Android Studio.
2.  Create a new project as follows:
    -   If you see the  **Welcome to Android Studio**  dialog, choose  **Start a new Android Studio project**, available under 'Quick Start' on the right of the dialog.
    -   Otherwise, click  **File**  in the Android Studio menu bar, then  **New**,  **New Project**.
    -   Select the form factors you need for your app. If you're not sure what you need, just select  **Phone and Tablet**.
    -   Select  **Add No Activity**  and click  **Next**.
3.  Enter your app name, package name, project location, language and minimum API version as prompted. Then click  **Next**.
4.  Create a  **Basic Activity**  in the 'Add an activity to Mobile' dialog. Then click  **Next**.
5.  Enter the activity name, layout name and title as prompted. Then click  **Finish**.

Android Studio starts Gradle and builds your project. This may take a few seconds. For more information about creating a project in Android Studio, see the  [Android Studio documentation](https://developer.android.com/studio/projects/create-project.html).

The next few sections contain the code samples that you may add to your activity's java file as well as its xml layout file for creating an app with MapmyIndia Intouch SDK for live tracking.


## <a name="AddIntouchSDK">Step 2. Add Intouch SDK</a>

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

//Add Intouch as a dependency
dependencies {
    implementation 'com.mapmyindia.sdk:intouch-sdk:1.0.0-alpha03'
    ...
}
```
// Required Minimum sdk version
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
InTouch.initialize(<your publishable key>, new IntouchInitCallBack() {
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
You cannot use the MapmyIndia Intouch SDK without these function calls. You will find your keys in your [API Dashboard](http://www.mapmyindia.com/api/dashboard).
## <a name="StartTracking">Step 4: Start Tracking</a> 


Call the below method to track your app user's phone live location. 
To start tracking use the below method.
 ```java
 InTouch.startTracking();
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
InTouch.initialize(<your publishable key>, new IntouchInitCallBack() {
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
You cannot use the MapmyIndia Intouch SDK without these function calls. You will find your keys in your [API Dashboard](http://www.mapmyindia.com/api/dashboard).
## <a name="StartTracking">Step 4: Start Tracking</a> 


Call the below method to track your app user's phone live location. 
To start tracking use the below method.
 ```java
 InTouch.startTracking();
```

