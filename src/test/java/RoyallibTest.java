import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;

public class RoyallibTest extends RoyallibPage {

    public RoyallibTest() throws IOException {  }

    RoyallibPage royallibPage = new RoyallibPage();


    @Before
    public  void  pFactory() {
        PageFactory.initElements(driver, royallibPage);
    }


    @Test
    public void searchFeuchtwangerTest() throws IOException {
        royallibPage.searchFeuchtwanger(driver);
    }

    //Проверить, что можно выбрать авторов с фамилией, начинающейся на 'Бе'
    @Test
    public void chooseRusBeTest() throws IOException {
        royallibPage.chooseRusBe(driver);
    }

    //Проверить, что можно выбрать авторов с фамилией, начинающейся на 'Фаб'
    @Test
    public void chooseRusFabTest() throws IOException {
        royallibPage.chooseRusFab(driver);
    }

    //Проверяем текст на странице Список брендов
    @Test
    public void chooseRusFeTest() throws IOException {
        royallibPage.chooseRusFe(driver);
    }



}