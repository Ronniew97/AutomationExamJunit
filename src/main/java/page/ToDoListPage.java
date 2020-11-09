package page;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import util.BasePage;

public class ToDoListPage extends BasePage {

	WebDriver driver;

	public ToDoListPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.NAME, using = "allbox") WebElement TOGGLE_ALL_BUTTON;
	@FindBy(how = How.NAME, using = "todo[3]") WebElement THIRD_NAME_CHECKBOX;
	@FindBy(how = How.XPATH, using = "//input[@value='Remove']") WebElement REMOVE_BUTTON;
	@FindBy(how = How.NAME, using = "data") WebElement NAME_TEXTBOX;
	@FindBy(how = How.NAME, using = "category") WebElement CATEGORY_DROPDOWN;
	@FindBy(how = How.NAME, using = "due_day") WebElement DAY_DROPDOWN;
	@FindBy(how = How.NAME, using = "due_month") WebElement MONTH_DROPDOWN;
	@FindBy(how = How.NAME, using = "due_year") WebElement YEAR_DROPDOWN;
	@FindBy(how = How.XPATH, using = "//input[@value='Add']") WebElement ADD_BUTTON;
	@FindBy(how = How.XPATH, using = "//ul[@style='list-style-type: none; padding-left:0']")
	WebElement LIST;

	public void clickToggleAll() {
		reAddNamesInListIfBlank();
		TOGGLE_ALL_BUTTON.click();
	}

	public void removeThirdName() {
		reAddNamesInListIfBlank();
		THIRD_NAME_CHECKBOX.click();
		REMOVE_BUTTON.click();
	}

	public void clickRemoveButton() {
		REMOVE_BUTTON.click();
		driver.navigate().back();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void createUser(String name, String category, String day, String month, String year) {
		NAME_TEXTBOX.sendKeys(name);
		dropDown(CATEGORY_DROPDOWN, category);
		dropDown(DAY_DROPDOWN, day);
		dropDown(MONTH_DROPDOWN, month);
		dropDown(YEAR_DROPDOWN, year);
		ADD_BUTTON.click();
		driver.navigate().refresh();
	}

	public void reAddNamesInListIfBlank() {
		String list = LIST.getText();
		String errorMessage = "Warning: Invalid argument supplied for foreach() in "
				+ "/home2/techfios/public_html/test/101/index.php on line 121";
		
		if (list.equalsIgnoreCase(errorMessage)) {
			createUser("Ronnie", "Junit", "28", "Oct", "2021");
			createUser("James", "Junit", "15", "Apr", "2021");
			createUser("Lisa", "Junit", "12", "Dec", "2020");
			createUser("Rahul", "Junit", "31", "Dec", "2020");
			createUser("Rebecca", "Junit", "14", "Feb", "2021");
		}
	}

	public void verifyListIsBlank() {
		System.out.println(LIST.getText());
	}

	public void takeScreenshotAtTheEndOfTesting(WebDriver driver, String testname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) (driver);
		File SourceFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SourceFile, new File("screenshots\\" + testname + System.currentTimeMillis() + ".png"));

	}
}
