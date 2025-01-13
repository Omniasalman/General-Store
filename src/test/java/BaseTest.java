import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService serviceBuilder;

    @BeforeClass
    public void ConfigureAppium()throws MalformedURLException, URISyntaxException{
        serviceBuilder = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Omnia\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723).build();
        UiAutomator2Options options=new UiAutomator2Options();
        options.setDeviceName("Reda");
        options.setApp("C:\\Users\\Omnia\\Downloads\\APKFiles\\resources/General-Store.apk");
        driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    public Double getFormttedAmount(String amount){
        Double price= Double.parseDouble(amount.substring(1));
        return price;

    }
    public void longPressAction(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "duration", 2000));
    }
    @AfterClass
    public void tearDown(){
        driver.quit(); //first you need to close the driver that is responsible to close the app also stop controlling the app and device
        serviceBuilder.stop(); //then stop the server "you can't stop the server first"
    }
}
