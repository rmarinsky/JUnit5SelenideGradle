import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.HtmlReportExtension;
import utils.TextReportExtension;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith({HtmlReportExtension.class, TextReportExtension.class})
public class SimpleTest {

    static {
        Configuration.baseUrl = "https://github.com";
        Configuration.browser = WebDriverRunner.CHROME;
        ChromeDriverManager.getInstance().setup();
    }

    @BeforeAll
    static void beforeAllInClass(){
        System.out.println("Before all in class");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("Before each test method");
        open("/");
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each test method");
    }

    @AfterAll
    static void afterAllInClass(){
        System.out.println("After all in class");
    }

    @Test
    void firstTest(){
        $(by("name","q")).setValue("selenide").pressEnter();
        $(".repo-list li h3").shouldHave(text("codeborne/selenide"));
    }

}
