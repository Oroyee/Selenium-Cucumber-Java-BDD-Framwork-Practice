package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class GoogleSearchSteps {

	WebDriver driver = null;
	
	@Given("browser is open")
	public void browser_is_open() {
	    System.out.println("Inside Step - browser is open");
	    
	    String projectPath = System.getProperty("user.dir");
	    System.out.println("Project path is :"+projectPath);
	    
	    System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/drivers/chromedriver.exe");
	    
	    driver = new ChromeDriver();
	    
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	    
	    //driver.manage().window().maximize();
	}

	@And("user is on google seach page")
	public void user_is_on_google_seach_page() {
		System.out.println("Inside Step - user is on google seach page");
		
		driver.navigate().to("https://google.com");
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/div[1]/button[2]")).click();
	}

	@When("user enters a text in search box")
	public void user_enters_a_text_in_search_box() {
		System.out.println("Inside Step - user enters a text in search box");
		
		driver.findElement(By.name("q")).sendKeys("Automation Step by Step");
	}

	@And("hits enter")
	public void hits_enter() {
		System.out.println("Inside Step - hits enter");
		
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}

	@Then("user is navigated to seach results")
	public void user_is_navigated_to_seach_results() {
		System.out.println("Inside Step - user is navigated to seach results");
		
		driver.getPageSource().contains("Onlie Courses");
		
		driver.close();
		driver.quit();
	}
}
