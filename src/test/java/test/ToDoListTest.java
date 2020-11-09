package test;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import page.ToDoListPage;
import util.BrowserFactory;

public class ToDoListTest {

	WebDriver driver;
	ToDoListPage todolist;

	@Test
	public void userClickingToggleAllCheckButtonShouldSelectAllItems() throws IOException {
		driver = BrowserFactory.init();
		todolist = PageFactory.initElements(driver, ToDoListPage.class);
		todolist.clickToggleAll();
		todolist.takeScreenshotAtTheEndOfTesting(driver,"clicked_toggle_all_button");
	}

	@Test
	public void userShouldBeAbleToRemoveASingleListItem() throws IOException {
		driver = BrowserFactory.init();
		todolist = PageFactory.initElements(driver, ToDoListPage.class);
		todolist.removeThirdName();
		todolist.takeScreenshotAtTheEndOfTesting(driver,"removed_item_three");
	}

	@Test
	public void userShouldBeAbleToRemoveAllItemsWithTheRemoveButton() {
		driver = BrowserFactory.init();
		todolist = PageFactory.initElements(driver, ToDoListPage.class);
		todolist.clickToggleAll();
		todolist.clickRemoveButton();
		todolist.verifyListIsBlank();
		todolist.reAddNamesInListIfBlank();
	}

	@After
	public void closeChromeBrowser() {
		
		try {
			BrowserFactory.closeBrowser();
		} catch (NoSuchSessionException n) {
			System.out.println("");
		}

	}
}
