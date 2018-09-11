import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.screenshot;


public class RoyallibElements {
    public RoyallibElements() throws IOException { }

    private static org.slf4j.Logger log = (org.slf4j.Logger) LoggerFactory.getLogger(RoyallibElements.class);
    public FileWriter writer = new FileWriter ("output.txt", true);
    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            super.onException(throwable, driver);
            log.error("Exception" + throwable);
        }
    }

    public static String baseUrl = "https://royallib.com/";
    public String author = "Фейхтвангер";

    //Метод для проверки, что можно выбрать буквы 'letters'. Если нельзя выбрать, делается скриншот
    public void chooseLetters (WebDriver driver, String letters) throws IOException {
        writer.write("\n Проверка букв ___" + letters);
        log.info(letters);
        SelenideElement box = $(letterBox).shouldBe(visible);
        boolean choise =  box.getText().contains(letters);
        if (choise) {
            writer.write("\n ____" + letters + " ___ можно выбрать");
            writer.flush();
        }

      else  {
          screenshot(letters + "LLkkk");
            File scrFile = ((TakesScreenshot) driver).
                    getScreenshotAs(OutputType.FILE);
            File screenShotFile = new File(letters + ".png");
            FileHandler.copy(scrFile, screenShotFile);
            writer.write("\n ОШИБКА! ____" + letters + " ___невозможно выбрать");
            writer.flush();
            log.error("Невозможно выбрать буквы __ " + letters);
        }
        Assert.assertTrue(choise);
    }


    @FindBy(how = How.CSS, css = "#q")
    public WebElement search;

    @FindBy(how = How.XPATH, xpath = "*//table//tr[1]//tr[2]/td")
    public WebElement authorSearchedTable;

    @FindBy(how = How.XPATH, xpath = "*/body//div[2]/ul[1]/li[3]")
    public WebElement authorRusLetterB;

    @FindBy(how = How.XPATH, xpath = "*/body//div[2]/ul[1]/li[22]")
    public WebElement authorRusLetterF;

    @FindBy(how = How.CSS, css = "#letter_box")
    public WebElement letterBox;
}
