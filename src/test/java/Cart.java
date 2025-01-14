import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Cart extends BaseTest{
    @Test
    public void verifyItem() throws InterruptedException {
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahrain\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Omnia");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Add item to cart
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")).click();
        driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id=\"com.androidsample.generalstore:id/rvProductList\"]/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]")).click();

        //click on add cart icon
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //Wait time
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));

        //Check the price
        List<WebElement> productPrices=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count =productPrices.size();
        double totalSum =0.0;
        for (int i =0; i<count;i++){
           String amountString=productPrices.get(i).getText() ;
          Double price= getFormttedAmount(amountString);
          totalSum=totalSum+price;

        }
        String displayesSum=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double displayFormattedSum= getFormttedAmount(displayesSum);
        Assert.assertEquals(totalSum,displayFormattedSum);

        //Long press to show terms and conditions
        WebElement terms =driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(terms);
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(2000);
    }
}
