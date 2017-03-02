package utils;

import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SelenideLogger;

public class HtmlReport {

    private String fontFamily = "Consalas";
    private String fontSize = "16px";
    private String fontColor = "#383737";
    private String border = "1px solid #383737";
    private String borderCollapse = "collapse";
    private String thTdPadding = "3px 10px 3px 10px";

    /**
     * Constructor for set default styles
     */
    HtmlReport(){
    }

    /**
     * Constructor for customize table's styles
     */
    public HtmlReport(String fontFamily, String fontSize, String fontColor, String border, String borderCollapse, String thTdPadding) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.border = border;
        this.borderCollapse = borderCollapse;
        this.thTdPadding = thTdPadding;
    }

    void start() {
        SelenideLogger.addListener(HtmlReport.class.getName(), new EventsCollector());
    }

    void finish(String title) {
        EventsCollector logEventListener = SelenideLogger.removeListener(HtmlReport.class.getName());

        StringBuilder html = new StringBuilder();

        html.append("<html>\n<head>\n<style type=\"text/css\">\n");
        html.append(String.format("table, th, td {\nfont-family: %s;\nfont-size: %s;\ncolor: %s;\nborder: %s;\nborder-collapse: %s;\n}\n",
                this.fontFamily, this.fontSize, this.fontColor, this.border, this.borderCollapse));
        html.append(String.format("th, td {\npadding: %s;\n}\n", this.thTdPadding));
        html.append("</style>\n</head>\n");
        html.append("<body>\n<table>\n<tbody>\n");
        html.append("<tr>\n");
        html.append("<th>Element</th>\n<th>Subject</th>\n<th>Status</th>\n<th>ms</th>\n");
        html.append("</tr>\n");

        for (LogEvent event : logEventListener.events()){
            html.append("<tr>\n");
            html.append(String.format("<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n", event.getElement(), event.getSubject(), event.getStatus(), event.getDuration()));
            html.append("</tr>\n");
        }

        html.append("</tbody>\n</table>\n</body>");
        html.append("</html>");

        AllureReportUtil.attachLog(html.toString());
    }

    void clean() {
        SelenideLogger.removeListener(HtmlReport.class.getName());
    }

}
