import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class RoyallibPage extends RoyallibElements {
    public RoyallibPage() throws IOException { }

    private static org.slf4j.Logger log = LoggerFactory.getLogger(RoyallibPage.class);
    public FileWriter writer = new FileWriter ("output.txt", true);
      public static WebDriver driver = new ChromeDriver();



    @BeforeClass
    public static void setUp() {
        WebDriverRunner.setWebDriver(driver);
        //Перейти на домашнюю страницу
        open(baseUrl);
        $(By.id("q")).shouldBe(visible);
    }

     //Найти на сайте фамилию автора 'Фейхтвангер' - слово должно быть найдено
    public void searchFeuchtwanger(WebDriver driver) throws IOException {
        writer.write("\n\nПРОВЕРИТЬ, ЧТО ЧЕРЕЗ ПОИСК ПО САЙТУ НАХОДИТСЯ СЛОВО ФЕЙХТВАНГЕР  \n ТЕСТ searchFeuchtwangerTest");
        writer.flush();
        log.info(author);

        //Ввести в поле поиска 'Фейхтвангер'
        $(search).shouldBe(visible).setValue(author).pressEnter();
        String searchResult = $(authorSearchedTable).shouldBe(visible).getText();
        if (searchResult.contains(author)) {
            writer.write("\n Найден автор ____ " + author);
            writer.flush();
        }
        else {
            screenshot(author + ".png");
            writer.write("\\n ОШИБКА! Не найден автор ____ " + author);
            writer.flush();
            log.error("Не найден автор ____" + author);
        }
        writer.close();
    }

    //Выбрать буквы 'Бе'
    public void chooseRusBe(WebDriver driver) throws IOException {
        writer.write("\n\nПРОВЕРИТЬ, ЧТО ЕСЛИ ВЫБРАТЬ 'ПО АВТОРАМ' БУКВУ 'Б', ПОЯВИТСЯ ТАБЛИЦА, В КОТОРОЙ МОЖНО БУДЕТ ВЫБРАТЬ 'Бе' \n ТЕСТ chooseRusBeTest");
        writer.flush();

        //Выбрать букву 'Б'
        $(authorRusLetterB).shouldBe(visible).click();

        //Проверить, что можно выбрать автора, фамилия которого начинается  на Бе
        chooseLetters(driver, "Бе");
        writer.close();
    }

    //Выбрать автора на букву Ф, проверяем, что в табдице можно выбирать фамилию автора с учетом второй буквы

    //Выбрать буквы 'Фаб'
    public void chooseRusFab(WebDriver driver) throws IOException {
        writer.write("\n\nПРОВЕРИТЬ, ЧТО ЕСЛИ ВЫБРАТЬ 'ПО АВТОРАМ' БУКВУ 'Ф', ПОЯВИТСЯ ТАБЛИЦА, В КОТОРОЙ МОЖНО БУДЕТ ВЫБРАТЬ 'Фаб' \n ТЕСТ chooseRusFabTest");
        writer.flush();

        //Выбрать букву 'Ф'
        $(authorRusLetterF).shouldBe(visible).click();

        //Проверить, что можно выбрать автора, фамилия которого начинается  на 'Фаб'
        chooseLetters(driver, "Фаб");
        writer.close();
    }

    //Выбрать букву 'Ф'
    public void chooseRusFe(WebDriver driver) throws IOException {
        writer.write("\n\nПРОВЕРИТЬ, ЧТО ЕСЛИ ВЫБРАТЬ 'ПО АВТОРАМ' БУКВУ 'Ф', ПОЯВИТСЯ ТАБЛИЦА, В КОТОРОЙ МОЖНО БУДЕТ ВЫБРАТЬ 'Фе' \n ТЕСТ chooseRusFeTest");
        writer.flush();

        //Проверить, что можно выбрать автора, фамилия которого начинается  на 'Ф'
        $(authorRusLetterF).shouldBe(visible).click();

        //Проверить, что можно выбрать автора, фамилия которого начинается  на 'Фе'
        chooseLetters(driver, "Фе");

        writer.close();
    }
}
