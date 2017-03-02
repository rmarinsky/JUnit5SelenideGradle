package utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;

public class HtmlReportExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private final HtmlReport report = new HtmlReport();

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        report.start();
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        if(context.getTestException().isPresent()){
            AllureReportUtil.attachScreenshot();
            report.finish(context.getDisplayName());
        } else {
            AllureReportUtil.attachScreenshot();
            report.finish(context.getDisplayName());
        }
        report.clean();
    }


}
