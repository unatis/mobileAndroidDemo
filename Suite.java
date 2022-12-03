import Common.Common;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Suite {

    @Test @Order(1)
    public void MobileDemoTest() throws InterruptedException {

        Common.LaunchApp("com.google.android.gm");

        GMail.GmailView.Verify_GmailView_Shown(true);

        GMail.GmailView.Click_GotIt_Button();

        GMail.GmailView.Click_TakeMeToGmail_Button();

        Thread.sleep(5000);

        Common.CloseApp();
    }
}
