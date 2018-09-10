import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.*;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;

public class RoyallibTest extends RoyallibPage {

    public RoyallibTest() throws IOException {  }

    RoyallibPage royallibPage = new RoyallibPage();
    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests();


    @Before
    public  void  pFactory() {
        PageFactory.initElements(driver, royallibPage);
        Configuration.reportsFolder = "test-result/reports";
        Configuration.browser = "chrom";
    }


    @Test
    public void searchFeuchtwangerTest() throws IOException {
        royallibPage.searchFeuchtwanger(driver);
    }

    //Проверить, что можно выбрать авторов с фамилией, начинающейся на 'Бе'
//    @Test
//    public void chooseRusBeTest() throws IOException {
//        royallibPage.chooseRusBe(driver);
//    }

//    //Проверить, что можно выбрать авторов с фамилией, начинающейся на 'Фаб'
//    @Test
//    public void chooseRusFabTest() throws IOException {
//        royallibPage.chooseRusFab(driver);
//    }
//
//    //Проверить, что можно выбрать авторов с фамилией, начинающейся на 'Фе'
    @Test
    public void chooseRusFeTest() throws IOException {
        royallibPage.chooseRusFe(driver);
    }

    @AfterClass
    public static void close()  {
        driver.quit();
    }


}
