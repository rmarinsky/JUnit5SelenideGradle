package utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;

public class TextReportExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private final TextReport textReport = new TextReport();

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        textReport.start();
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        if(context.getTestException().isPresent()){
            AllureReportUtil.attachScreenshot();
            textReport.finish(context.getDisplayName());
        } else {
            AllureReportUtil.attachScreenshot();
            textReport.finish(context.getDisplayName());
        }
        textReport.clean();
    }

}
