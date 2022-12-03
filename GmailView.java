package GMail;

import Common.Common;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GmailView {

    public static void Verify_GmailView_Shown(boolean Expected)
    {
        try{
            Common.mDriver.findElement(AppiumBy.id("com.google.android.gm:id/action_bar_root")).isDisplayed();
            Common.mDriver.findElement(By.id("com.google.android.gm:id/action_bar_root")).isDisplayed();

            if(Expected){

                System.out.println("GmailView Shown as expected");

            }else{

                System.out.println("ProfileView Shown but expected is - not");
            }
        }
        catch(Exception e){

            if(e.getMessage().contains("Can't locate an element")){

                if(Expected){

                    System.out.println("GmailView not found but expected is - shown");

                }else{

                    System.out.println("GmailView not found as expected");
                }
            }
            else{
                System.out.println(e.getMessage());
            }
        }
    }

    public static void Click_GotIt_Button(){

        try{
            Common.mDriver.findElement(AppiumBy.id("com.google.android.gm:id/welcome_tour_got_it")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void Click_Email_ListItem(String Email){

        try{
            boolean flgFound = false;

            List<WebElement> Users = (List<WebElement>) Common.mDriver.findElement(By.id("com.google.android.gm:id/account_address"));

            for(WebElement Element : Users){

                if(Element.getText().trim().toLowerCase().equals(Email.trim().toLowerCase())){

                    Element.click();
                    System.out.println("Email " + Email + " clicked");
                    flgFound = true;
                    break;
                }
            }

            if(!flgFound){

                System.out.println("Email " + Email + " not found");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void Click_TakeMeToGmail_Button(){

        try{
            Common.mDriver.findElement(AppiumBy.id("com.google.android.gm:id/action_done")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void Click_UserProfile_Icon(){

        try{
            Common.mDriver.findElement(AppiumBy.id("com.google.android.gm:id/og_apd_ring_view")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void Click_AccountByEmail_Icon(String Email){
        try{

            Common.mDriver.findElement(AppiumBy.xpath("//android.view.ViewGroup[contains(@content-desc, '"+Email+"')]")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void Select_IncomingMail_ListItem(String StartsWith){

        try{

            boolean flgFound = false;

            List<WebElement> Mails = (List<WebElement>) Common.mDriver.findElement(AppiumBy.id("com.google.android.gm:id/viewified_conversation_item_view"));

            for(WebElement Element : Mails){

                if(Element.getText().trim().startsWith(StartsWith)){

                    Element.click();
                    System.out.println("Email from K clicked");
                    flgFound = true;
                    break;
                }
            }

            if(!flgFound){

                System.out.println("Email from K not found");
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void Click_Login_Button(){
        try{
            Common.mDriver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Login\"]/android.widget.Button")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void Click_ResetPassword_Button(){
        try{
            Common.mDriver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Reset password\"]/android.widget.Button")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
