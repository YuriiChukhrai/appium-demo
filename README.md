# Appium Demo


## Dependencies
Make sure you have installed on your operating system:<br/>
1. [JDK. Oracle](http://www.java.com/) OR [OpenJDK](https://openjdk.java.net/)
2. [Git](https://git-scm.com/)
3. [Maven](https://maven.apache.org/)
4. [Xcode](https://developer.apple.com/xcode/)
5. [Android Studio](https://developer.android.com/studio)
6. [Appium](https://appium.io/)
7. [Appium Inspector](https://github.com/appium/appium-inspector)


## Appium installation

`$> brew install node ` <br/>
`$> npm i -g npm ` <br/>
`$> npm install -g appium ` <br/>
`$> npm install wd ` <br/>
`$> npm install appium-doctor -g ` <br/>

### Verification

`$> appium-doctor --android ` <br/>
`$> adb devices ` -> Android. List of devices attached.<br/>
`$> emulator -list-avds`

`$> appium-doctor --ios ` <br/>
`$> xcrun xctrace list devices ` -> iOS. List of devices attached.<br/>



## Environment variables (_.bash_profile_)
```bash
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-18.0.1.jdk/Contents/Home

export ANDROID_HOME=/Users/{user-name}/Library/Android/sdk
export M2_HOME=/Library/Maven
export M2=$M2_HOME/bin

export PATH=$M2:$ANDROID_HOME:$PATH
export PATH=$ANDROID_HOME/emulator:$PATH
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export PATH=$ANDROID_HOME/tools/bin:$PATH
```

## Run Appium
`$> appium --allow-insecure chromedriver_autodownload ` <br/>



## Test execution
`$> mvn clean verify -Dappium.uri=http://localhost -Dappium.port=4723 -Dios.udid=AB2253B4-CF68-4A89-AE2F-84BE7ECF472B ` </br></br>

Optional CLO
```
-Dmobile.driver=ios -Dios.udid=AB2253B4-CF68-4A89-AE2F-84BE7ECF472B
-Dmobile.driver=android
```

### Optional (debugging)
```java
    System.setProperty("appium.uri", "http://localhost");
    System.setProperty("appium.port", "4723");
    System.setProperty("mobile.driver", "android");
    System.setProperty("ios.udid", "AB2253B4-CF68-4A89-AE2F-84BE7ECF472B");
```


## Reporting
`$> mvn allure:report `
If for some a reason you are not able to run tests, you can find example of the [report](./doc/allure-maven-plugin.zip) in the current project. A one test case for iOS and Android device. <br/>

* [Allure report](https://github.com/allure-framework)  An open-source framework designed to create test execution reports clear to everyone in the team. <br/>
  > **_NOTE:_** To run the report (HTML + JS) in Firefox You need leverage the restriction by going to `about:config` url and then **uncheck** `privacy.file_unique_origin` **boolean** value.

