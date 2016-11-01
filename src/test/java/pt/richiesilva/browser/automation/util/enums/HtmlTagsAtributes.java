package pt.richiesilva.browser.automation.util.enums;

public enum HtmlTagsAtributes {
	HREF("href"),
	JSCRIPT_ARG_SPEC_CLICK("arguments[0].click();"),
	ANCHOR("a"),
    INNER_TEXT("innerText");

    private String value;

    HtmlTagsAtributes(String url) {
        this.value = url;
    }

    public String value() {
        return value;
    }
}
