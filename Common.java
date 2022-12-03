package Common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Common {

    public static AppiumDriver mDriver = null;

    public static void InstallAndLaunchApp(String OS, String ApplicationFileName, String DeviceName, String DeviceID, String PlatformVersion, String Environment) {
        try {
            mDriver = null;
            String ApplicationPath = "";

            ApplicationPath = Paths.get("Tools").toAbsolutePath().toString() + File.separator + ApplicationFileName;

            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            if (OS.equalsIgnoreCase("android")) {
                DesiredCapabilities Capabilities = GetDesiredCapability_Install(OS, ApplicationPath, DeviceName, DeviceID, PlatformVersion);
                mDriver = new AndroidDriver(url, Capabilities);
            } else {
                DesiredCapabilities Capabilities = GetDesiredCapability_Install(OS, ApplicationPath, DeviceName, DeviceID, PlatformVersion);
                mDriver = new IOSDriver(url, Capabilities);
            }
            System.out.println("Application Khealth installed successfully");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void LaunchApp(String AppPackageName) {

        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            DesiredCapabilities Capabilities = new DesiredCapabilities();
            Capabilities.setCapability("deviceName", "Pixel_3a_API_30");
            Capabilities.setCapability("platformName", "android");
            Capabilities.setCapability("platformVersion", "11");
            //Capabilities.setCapability("app", "G://Ravi testing/Apps/Demo.apk");
            Capabilities.setCapability("appPackage", AppPackageName);
            Capabilities.setCapability("appActivity", "ConversationListActivityGmail");

            mDriver = new AndroidDriver(url, Capabilities);

            System.out.println("Application is running");

        } catch (Exception e) {

            System.out.println(e.toString());
        }

    }

    public static void CloseApp() {

        try {

            if(mDriver != null){
                mDriver.quit();
            }

            System.out.println("Application is closed");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    private static DesiredCapabilities GetDesiredCapability_LaunchInstalled(String OS, String DeviceName, String DeviceID, String PlatformVersion, String PackageName, String ActivityName) {

        try {
            int Port = 4723;

            if (OS.toLowerCase().trim().equalsIgnoreCase("ios")) {
                Port = 4724;
            }

            boolean flgPortAvailableFound = false;

            DesiredCapabilities Capabilities = new DesiredCapabilities();

            switch (OS.toLowerCase().trim()) {
                case "android":
                    Capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
                    Capabilities.setCapability(MobileCapabilityType.UDID, DeviceID);
                    Capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    Capabilities.setCapability("platformVersion", PlatformVersion);
                    Capabilities.setCapability("ensureWebviewsHavePages", true);
                    Capabilities.setCapability("usePrebuiltWDA", true);

                    Capabilities.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, 180000);
                    Capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 180);

                    Capabilities.setCapability(MobileCapabilityType.NO_RESET, true);//clears the app data, such as its cache

                    Capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, String.valueOf(Port));
                    Capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10800); //keep appium session alive for 3 hours (in seconds)
                    Capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PackageName);
                    Capabilities.setCapability("appActivity", ActivityName);

                    break;
                case "ios":
                    /*
                    Capabilities.setCapability("deviceName", "Mi_Phone");
                    Capabilities.setCapability("udid", "7e81c476");
                    */
                    break;
            }

            return Capabilities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static DesiredCapabilities GetDesiredCapability_Install(String OS, String ApplicationPath, String DeviceName, String DeviceID, String PlatformVersion) {

        try {

            if (!DeviceName.contains("@")) {
                DeviceName = "@" + DeviceName;
            }

            int Port = 4723;

            if (OS.toLowerCase().trim().equalsIgnoreCase("ios")) {
                Port = 4724;
            }

            DesiredCapabilities Capabilities = new DesiredCapabilities();

            switch (OS.toLowerCase().trim()) {
                case "android":
                    /*Capabilities.setCapability(MobileCapabilityType.APP, ApplicationPath);
                    //Capabilities.setCapability("autoLaunch",  false);

                    Capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
                    Capabilities.setCapability(MobileCapabilityType.UDID, DeviceID);
                    Capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    Capabilities.setCapability("platformVersion", PlatformVersion);
                    Capabilities.setCapability("ensureWebviewsHavePages", true);
                    Capabilities.setCapability("usePrebuiltWDA", true);

                    Capabilities.setCapability(MobileCapabilityType.NO_RESET, true);//clears the app data, such as its cache

                    //Capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
                    Capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, String.valueOf(Port));//4723
                    Capabilities.setCapability(MobileCapabilityType.SUPPORTS_ALERTS, true);
                    Capabilities.setCapability("autoAcceptAlerts", "true");

                    Capabilities.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, 600);//280000
                    Capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 600);//280
                    Capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);//280
                    Capabilities.setCapability(AndroidMobileCapabilityType.DEVICE_READY_TIMEOUT, 600);
                    Capabilities.setCapability(AndroidMobileCapabilityType.AVD_READY_TIMEOUT, 600);
                    Capabilities.setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, 600);
                    Capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 600);
                    //Capabilities.setCapability("idleTimeout", 600);*/

                    Capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
                    Capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "" + OS + "");
                    Capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "" + PlatformVersion + "");//Version is number here
                    Capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "" + DeviceName + "");
                    Capabilities.setCapability(MobileCapabilityType.APP, "" + ApplicationPath + "");
                    Capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
                    Capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);//280

                    break;
                case "ios":

                    Capabilities.setCapability(MobileCapabilityType.APP, ApplicationPath);
                    Capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    Capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PlatformVersion);
                    Capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
                    Capabilities.setCapability(MobileCapabilityType.UDID, DeviceID);
                    Capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
                    Capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10800); //keep appium session alive for 3 hours (in seconds)

                    Capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
                    Capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, String.valueOf(Port));//4724
                    Capabilities.setCapability("useJSONSource", true);
                    Capabilities.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, 220000);
                    Capabilities.setCapability(IOSMobileCapabilityType.WDA_CONNECTION_TIMEOUT, 220000);
                    Capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
                    Capabilities.setCapability(MobileCapabilityType.SUPPORTS_ALERTS, true);
                    Capabilities.setCapability("autoAcceptAlerts", "true");
                    break;
            }

            return Capabilities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
