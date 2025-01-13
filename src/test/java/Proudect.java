import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class Proudect extends BaseTest{
    public String productName;
    @Test
    public void addItem() throws InterruptedException {

        //Login code
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bahrain\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Omnia");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //Product page
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));")).click();
        int productCount =driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i=0;i<productCount;i++){
            productName =  driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
           if (productName.equalsIgnoreCase("Jordan 6 Rings")){
               driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
           }
        }
        //click on add cart icon
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));
        //verify item
       String CartProductItem= driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(CartProductItem,"Jordan 6 Rings");

    }
}
