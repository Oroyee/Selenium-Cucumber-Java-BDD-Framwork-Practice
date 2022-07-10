package StepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.*;

public class ExpediaSearchSteps {

	WebDriver driver = null;
	String flightContent;
	String flightTotalContent;
	int flightPrice;
	int flightTotalPrice;
	int numberOfPerson;
	String regEx = "[^0-9]";
	String flight1_from;
	String flight2_from;
	String flight3_from;
	String flight1_to;
	String flight2_to;
	String flight3_to;
	String flight1_departing_date;
	String flight2_departing_date;
	String flight3_departing_date;
	String originalWindow;

	@Given("browser is open for Expedia")
	public void browser_is_open_for_expedia() throws Exception {
		System.out.println("browser is open for Expedia");
		Properties props = System.getProperties();
		String OS = props.getProperty("os.name");
		System.out.println(OS);

		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is :" + projectPath);

		String mac = ".*Mac.*";
		String windows = ".*Windows.*";
		boolean macIsMatch = Pattern.matches(mac, OS);
		boolean windowsIsMatch = Pattern.matches(windows, OS);
		System.out.println("The OS is Mac: " + macIsMatch);
		System.out.println("The OS is Windows: " + windowsIsMatch);

		if (windowsIsMatch == true) {
			System.out.println("is Windows");
			String cmd = "C:/Windows/System32/cmd.exe /k \"cd C:/Program Files/Google/Chrome/Application && chrome.exe --remote-debugging-port=9222\"";
			Runtime.getRuntime().exec(
					"cmd /c start cmd.exe /c \"C: && cd C:\\Program Files\\Google\\Chrome\\Application && chrome.exe --remote-debugging-port=9222\"");
			System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
		}

		else if (macIsMatch == true) {
			System.out.println("is Mac");
			String[] args = new String[] { "/bin/bash", "-c",
					"/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome --remote-debugging-port=9222&",
					"with", "args" };
			Process proc = new ProcessBuilder(args).start();
//			Runtime.getRuntime().exec("/bin/bash -c /Applications/Google\\\\ Chrome.app/Contents/MacOS/Google\\\\ Chrome --remote-debugging-port=9222&");
			System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver");
		}

		System.out.println("Inside Step - browser is open");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9222");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		// driver.manage().window().maximize();
	}

	@And("user is on Expedia home page")
	public void user_is_on_expedia_home_page() {
		System.out.println("user is on Expedia home page");
		try {
			driver.navigate().to("https://www.expedia.com/");
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@When("Home page is loaded")
	public void home_page_is_loaded() {
		System.out.println("Inside Step - Home page is loaded");
		try {
			driver.getPageSource().contains("Flights");
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user click Flighs button")
	public void user_click_flighs_button() {
		System.out.println("Inside Step - Click Flighs button");
		try {
			driver.findElement(By.linkText("Flights")).click();
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user click Multi-city button")
	public void user_click_multi_city_button() {
		System.out.println("Inside Step - Click Multi-city button");
		try {
			driver.findElement(By.linkText("Multi-city")).click();
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user select number of traveler")
	public void user_select_number_of_traveler() throws InterruptedException {
		System.out.println("Inside Step - Select number of traveler");
		try {
			driver.findElement(By.id("adaptive-menu")).click();
			Thread.sleep(2000);
			for (int i = 1; i <= 3; i++) {
				driver.findElement(By.xpath("(//button[@type='button']//span)[2]")).click();
			}
			String numberOfPersonStr = driver
					.findElement(By.xpath("//div[contains(@class,'uitk-layout-flex uitk-layout-flex-item')]/input"))
					.getAttribute("value");
			numberOfPerson = Integer.parseInt(numberOfPersonStr);
			System.out.println("numberOfPerson= " + numberOfPerson);
			driver.findElement(By.xpath("//button[text()='Done']")).click();
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user click Add another flight button")
	public void user_click_add_another_flight_button() {
		System.out.println("Inside Step - Click Add another flight button");
		try {
			driver.findElement(By.xpath("//button[text()='Add another flight']")).click();
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user edit Flights and search")
	public void user_edit_flights_and_search() throws InterruptedException {
		System.out.println("Inside Step - Edit Flights and search");
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		String subString;
		int iend;
		try {
			// Edit Flight1 leaving from
			w.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[@data-stid='location-field-leg1-origin-menu-trigger']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg1-origin-menu-trigger']")).click();
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//input[contains(@class,'uitk-field-input uitk-typeahead-input')]")));
			driver.findElement(By.xpath("//input[contains(@class,'uitk-field-input uitk-typeahead-input')]"))
					.sendKeys("MLA");
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg1-origin-result-item-button']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg1-origin-result-item-button']"))
					.click();

			// Edit Flight1 going to
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg1-destination-menu-trigger']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg1-destination-menu-trigger']")).click();
			w.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//input[@id='location-field-leg1-destination']")));
			driver.findElement(By.xpath("//input[@id='location-field-leg1-destination']")).sendKeys("LON");
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg1-destination-result-item-button']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg1-destination-result-item-button']"))
					.click();

			// Edit Flight1 Departing time
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='d1-btn']")));
			driver.findElement(By.xpath("//button[@id='d1-btn']")).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-day='11'])[2]")));
			driver.findElement(By.xpath("(//button[@data-day='11'])[2]")).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-stid='apply-date-picker']")));
			driver.findElement(By.xpath("//button[@data-stid='apply-date-picker']")).click();
			String flight_departing_date_1 = driver.findElement(By.xpath("//button[@id='d1-btn']")).getText();
			iend = flight_departing_date_1.length();
			if (iend != -1) {
				flight1_departing_date = flight_departing_date_1;
			}

			// Edit Flight2 leaving from
			// Don't needed

			// Edit Flight2 going to
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg2-destination-menu-trigger']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg2-destination-menu-trigger']")).click();
			w.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//input[@id='location-field-leg2-destination']")));
			driver.findElement(By.xpath("//input[@id='location-field-leg2-destination']")).sendKeys("ROM");
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg2-destination-result-item-button']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg2-destination-result-item-button']"))
					.click();

			// Edit Flight2 Departing time
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='d1-btn'])[2]")));
			driver.findElement(By.xpath("(//button[@id='d1-btn'])[2]")).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-day='14'])[2]")));
			driver.findElement(By.xpath("(//button[@data-day='14'])[2]")).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-stid='apply-date-picker']")));
			driver.findElement(By.xpath("//button[@data-stid='apply-date-picker']")).click();
			String flight_departing_date_2 = driver.findElement(By.xpath("(//button[@id='d1-btn'])[2]")).getText();
			iend = flight_departing_date_2.length();
			if (iend != -1) {
				flight2_departing_date = flight_departing_date_2;
			}

			// Edit Flight3 leaving from
			// Don't needed

			// Edit Flight3 going to
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg3-destination-menu-trigger']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg3-destination-menu-trigger']")).click();
			w.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//input[@id='location-field-leg3-destination']")));
			driver.findElement(By.xpath("//input[@id='location-field-leg3-destination']")).sendKeys("MLT");
			w.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@data-stid='location-field-leg3-destination-result-item-button']")));
			driver.findElement(By.xpath("//button[@data-stid='location-field-leg3-destination-result-item-button']"))
					.click();

			// Edit Flight3 Departing time
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='d1-btn'])[3]")));
			driver.findElement(By.xpath("(//button[@id='d1-btn'])[3]")).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-day='18'])[2]")));
			driver.findElement(By.xpath("(//button[@data-day='18'])[2]")).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-stid='apply-date-picker']")));
			driver.findElement(By.xpath("//button[@data-stid='apply-date-picker']")).click();
			String flight_departing_date_3 = driver.findElement(By.xpath("(//button[@id='d1-btn'])[3]")).getText();
			iend = flight_departing_date_3.length();
			if (iend != -1) {
				flight3_departing_date = flight_departing_date_3;
			}

			// Click search button
			driver.findElement(By.xpath("//button[@data-testid='submit-button']")).click();

			String flight_from_1 = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[1]/div/div/section/div/div[2]/div[2]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/button"))
					.getText();
			iend = flight_from_1.indexOf(" ");
			if (iend != -1) {
				subString = flight_from_1.substring(0, iend);
				flight1_from = subString;
			}

			String flight_to_1 = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[1]/div/div/section/div/div[2]/div[2]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/button"))
					.getText();
			iend = flight_to_1.indexOf(" ");
			if (iend != -1) {
				subString = flight_to_1.substring(0, iend);
				flight1_to = subString;
			}

			String flight_from_2 = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[1]/div/div/section/div/div[2]/div[4]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/button"))
					.getText();
			iend = flight_from_2.indexOf(" ");
			if (iend != -1) {
				subString = flight_from_2.substring(0, iend);
				flight2_from = subString;
			}

			String flight_to_2 = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[1]/div/div/section/div/div[2]/div[4]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/button"))
					.getText();
			iend = flight_to_2.indexOf(" ");
			if (iend != -1) {
				subString = flight_to_2.substring(0, iend);
				flight2_to = subString;
			}

			String flight_from_3 = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[1]/div/div/section/div/div[2]/div[6]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/button"))
					.getText();
			iend = flight_from_3.indexOf(" ");
			if (iend != -1) {
				subString = flight_from_3.substring(0, iend);
				flight3_from = subString;
			}

			String flight_to_3 = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[1]/div/div/section/div/div[2]/div[6]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/button"))
					.getText();
			iend = flight_to_3.indexOf(" ");
			if (iend != -1) {
				subString = flight_to_3.substring(0, iend);
				flight3_to = subString;
			}
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user select Flights")
	public void user_select_flights() throws Exception {
		originalWindow = driver.getWindowHandle();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		System.out.println("Inside Step - Select Flights");
		try {
			// first flight
//		WebElement flight = driver.findElement(By.xpath("//li[@data-test-id='offer-listing']"));
			WebElement flight = driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[3]/div[1]/section/main/ul/li[1]/div/div/div/button"));
			String flightPriceStr = "";
			flightContent = driver.findElement(By.xpath("//span[@class='uitk-lockup-price']")).getText();
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(flightContent);
			flightPriceStr = m.replaceAll("").trim();
			System.out.println("priceStr= " + flightPriceStr);
			flightPrice = Integer.parseInt(flightPriceStr);
			System.out.println("priceInt= " + flightPrice);
			flight.click();
			driver.findElement(By.xpath("//button[@data-stid='select-button']")).click();
			Thread.sleep(2000);
			w.until(ExpectedConditions.invisibilityOf(flight));
			// second flight
			driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[3]/div[1]/section/main/ul/li[1]/div/div/div/button"))
					.click();
			driver.findElement(By.xpath("//button[@data-stid='select-button']")).click();
			Thread.sleep(2000);
			w.until(ExpectedConditions.invisibilityOf(flight));
			// last flight
			driver.findElement(By.xpath(
					"/html/body/div[2]/div[1]/div/div[3]/div[3]/div[1]/section/main/ul/li[1]/div/div/div/button"))
					.click();
			driver.findElement(By.xpath("//button[@data-stid='select-button']")).click();
			Thread.sleep(2000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("No. of tabs: " + tabs.size());
			w.until(ExpectedConditions.numberOfWindowsToBe(2));

			for (String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
		} catch (Exception e) {
			driver.close();
			driver.quit();
		}
	}

	@And("user confirm trip details to match the user selections")
	public void user_confirm_trip_details_to_match_the_user_selections() {
		System.out.println("Inside Step - User confirm trip details to match the user selections");
		String flightTotalPriceStr = "";
		String subString;
		boolean flightIsMatch;
		String trip1 = driver.findElement(By.xpath("//h2[@class='uitk-heading uitk-heading-4']")).getText();
		String trip1_date = driver.findElement(By.xpath("//div[@data-test-id='flight-operated']")).getText();
		String trip2 = driver.findElement(By.xpath("(//h2[@class='uitk-heading uitk-heading-4'])[2]")).getText();
		String trip2_date = driver.findElement(By.xpath("(//div[@data-test-id='flight-operated'])[2]")).getText();
		String trip3 = driver.findElement(By.xpath("(//h2[@class='uitk-heading uitk-heading-4'])[3]")).getText();
		String trip3_date = driver.findElement(By.xpath("(//div[@data-test-id='flight-operated'])[3]")).getText();
		int iend;
		int length;
		iend = trip1.indexOf(" ");
		if (iend != -1) {
			subString = ".*" + trip1.substring(0, iend) + ".*";
			flightIsMatch = Pattern.matches(subString, flight1_from);
			if (flightIsMatch == true) {
				System.out.println("1_from:PASS");
			} else {
				System.out.println("1_from:FALSE");
				Assert.fail();
			}
		}
		iend = trip1.lastIndexOf(" ");
		if (iend != -1) {
			length = trip1.length();
			subString = ".*" + trip1.substring(iend + 1, length) + ".*";
			flightIsMatch = Pattern.matches(subString, flight1_to);
			if (flightIsMatch == true) {
				System.out.println("1_to:PASS");
			} else {
				System.out.println("1_to:FALSE");
				Assert.fail();
			}
		}
		iend = trip2.indexOf(" ");
		if (iend != -1) {
			subString = ".*" + trip2.substring(0, iend) + ".*";
			flightIsMatch = Pattern.matches(subString, flight2_from);
			if (flightIsMatch == true) {
				System.out.println("2_from:PASS");
			} else {
				System.out.println("2_from:FALSE");
				Assert.fail();
			}
		}
		iend = trip2.lastIndexOf(" ");
		if (iend != -1) {
			length = trip2.length();
			subString = ".*" + trip2.substring(iend + 1, length) + ".*";
			flightIsMatch = Pattern.matches(subString, flight2_to);
			if (flightIsMatch == true) {
				System.out.println("2_to:PASS");
			} else {
				System.out.println("2_to:FALSE");
				Assert.fail();
			}
		}
		iend = trip3.indexOf(" ");
		if (iend != -1) {
			subString = ".*" + trip3.substring(0, iend) + ".*";
			flightIsMatch = Pattern.matches(subString, flight3_from);
			if (flightIsMatch == true) {
				System.out.println("3_from:PASS");
			} else {
				System.out.println("3_from:FALSE");
				Assert.fail();
			}
		}
		iend = trip3.lastIndexOf(" ");
		if (iend != -1) {
			length = trip3.length();
			subString = ".*" + trip3.substring(iend + 1, length) + ".*";
			flightIsMatch = Pattern.matches(subString, flight3_to);
			if (flightIsMatch == true) {
				System.out.println("3_to:PASS");
			} else {
				System.out.println("3_to:FALSE");
				Assert.fail();
			}
		}

		subString = ".*" + flight1_departing_date + ".*";
		flightIsMatch = Pattern.matches(subString, trip1_date);
		if (flightIsMatch == true) {
			System.out.println("1_date:PASS");
		} else {
			System.out.println("1_date:FALSE");
			Assert.fail();
		}

		subString = ".*" + flight2_departing_date + ".*";
		flightIsMatch = Pattern.matches(subString, trip2_date);
		if (flightIsMatch == true) {
			System.out.println("2_date:PASS");
		} else {
			System.out.println("2_date:FALSE");
			Assert.fail();
		}

		subString = ".*" + flight3_departing_date + ".*";
		flightIsMatch = Pattern.matches(subString, trip3_date);
		if (flightIsMatch == true) {
			System.out.println("3_date:PASS");
		} else {
			System.out.println("3_date:FALSE");
			Assert.fail();
		}

		flightTotalContent = driver.findElement(By.xpath("(//h3[@class='uitk-heading uitk-heading-5'])[2]")).getText();
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(flightTotalContent);
		flightTotalPriceStr = m.replaceAll("").trim();
		System.out.println("priceStr= " + flightTotalPriceStr);
		flightTotalPrice = Integer.parseInt(flightTotalPriceStr);
		flightTotalPrice /= 100;
		System.out.println("priceInt= " + flightTotalPrice);

		System.out.println("flight1from= " + flight1_from);
		System.out.println("flight2from= " + flight2_from);
		System.out.println("flight3from= " + flight3_from);
		System.out.println("flight1to= " + flight1_to);
		System.out.println("flight2to= " + flight2_to);
		System.out.println("flight3to= " + flight3_to);
		System.out.println("flight1departingdate= " + flight1_departing_date);
		System.out.println("flight2departingdate= " + flight2_departing_date);
		System.out.println("flight1departingdate= " + flight3_departing_date);

		if (flightPrice * numberOfPerson == flightTotalPrice) {
			System.out.println("Test Pass");
			driver.close();
			driver.switchTo().window(originalWindow);
			driver.close();
			driver.quit();
			// return true;
		} else {
			System.out.println("Test Failed, the expected price is not match with the final price.");
			driver.close();
			driver.switchTo().window(originalWindow);
			driver.close();
			driver.quit();
			Assert.fail();
			// return false;
		}
	}

}
