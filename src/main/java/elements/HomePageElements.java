package elements;

import org.openqa.selenium.By;

public class HomePageElements {

    protected final By popupCancelButton = By.cssSelector("#ppialog-popover-cancel-button");

    protected final By makeFilter = By.cssSelector("#filter-make");
    protected final By makeResult = By.cssSelector("#select2-filter-make-results");
    protected final By resultTitles = By.cssSelector("a div span.card-title.bold");

    protected final By priceMinFilter = By.cssSelector("select[name='usdprice[gt]']");
    protected final By priceMaxFilter = By.cssSelector("select[name='usdprice[lt]']");

    protected final By filterInput = By.cssSelector("input[role='textbox']");
    protected final By applyButton = By.cssSelector("#research-btn");
    protected final By searchButton = By.cssSelector("#search-btn");


    protected final By priceTag = By.cssSelector(".price.bold.blue-text");

    protected final By about = By.cssSelector("a[href='/hy/about']");

}
