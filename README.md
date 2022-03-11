# Neuro-ID Android Appplication Demo
This project contains the demo application, which already has the Neuro ID library integrated. The library is in release mode and any interaction with the application will send the events to the production server

## Getting Started

### Requirements
* minSDK 21 Supported

### Download Demo
There are two ways to download the project
1. Clone the repository to your local computer with git:
   `git clone https://github.com/Neuro-ID/neuroid-android-sdk-sandbox.git`
   or
   `git clone git@github.com:Neuro-ID/neuroid-android-sdk-sandbox.git`    

2. Just download zip project

### Signature Setup
The application is in release mode, it is necessary to create a file called "keystore.properties" which will contain the data to be able to sign the application locally
>If you don't want to sign the app with your own key, you can comment out the "signingConfigs" part of gradle and pass the build variants to debug as it appears below
```gradle
   /*signingConfigs {
        release {
            storeFile file(keystoreProperties['storeFile'])
            storePassword keystoreProperties['storePassword']
            keyAlias keystoreProperties['keyAlias']
            keyPassword keystoreProperties['keyPassword']
        }
    }*/
    
    buildTypes {
        debug {
            minifyEnabled false
        }
        release {
            minifyEnabled true
            shrinkResources = true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            //signingConfig signingConfigs.release
        }
    }
```

To launch in release mode, follow the steps below:
1. Create the "keystore.properties" file in the root folder of the project
```
   /neuroid-android-sdk-sandbox
      app
      build.gradle
      gradle.properties
      settings.gradle
      keystore.properties
```
2. Inside the file, it should contain the following:
```
storePassword=Your store password
keyPassword=Key password
keyAlias=Key Alias
storeFile=/Users/.../yourKeystore. keystore path where the file is located 
```
> For more information about creating your own signing key, see the following link:
> [Sign your app - Google Developers](https://developer.android.com/studio/publish/app-signing)
> [Stack overflow - Create keystore](https://stackoverflow.com/questions/3997748/how-can-i-create-a-keystore)

3. Clean and rebuild the project.