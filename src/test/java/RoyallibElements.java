import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.screenshot;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;
import static org.openqa.selenium.support.How.CSS;

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

    public String baseUrl = "https://royallib.com/";
    public String author = "Фейхтвангер";


    public void chooseLetters (WebDriver driver, String letters) throws IOException {
        Configuration.browser = "chrom";

        writer.write("\n Проверка букв ___" + letters);
        log.info(letters);
        SelenideElement box = $(letterBox).shouldBe(visible);
        boolean choise =  box.getText().contains(letters);
        if (choise) {
            writer.write("\n ____" + letters + " ___ можно выбрать");
            writer.flush();
        }
      else  {
           screenshot("eee.png");
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
