package utils;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;


public class AllureReportUtil {

    @Attachment(value = "Screenshot", type = "image/png")
    static byte[] attachScreenshot() {
        try {
            return Files.toByteArray(Screenshots.takeScreenShotAsFile());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Attachment(value = "Log for test", type = "text/html")
    static String attachLog(String text) {
        return text;
    }

    @Step
    public static void executedScript(String script){
    }

    @Step
    public static void currentUrl(String url){
    }

}
