package StepDefinitions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.*;

public class SwagLabsLoginSteps {

	WebDriver driver = null;
	
	@Given("browser is open for SwagLabs")
	public void browser_is_open_for_swaglabs() throws Exception {
		System.out.println("browser is open for SwagLabs");
		Properties props = System.getProperties();
		String OS = props.getProperty("os.name");
		System.out.println(OS);
		String projectPath = System.getProperty("user.dir");
	    System.out.println("Project path is :"+projectPath);
	    String mac = ".*Mac.*";
	    String windows = ".*Windows.*";
	    boolean macIsMatch = Pattern.matches(mac, OS );
	    boolean windowsIsMatch = Pattern.matches(windows, OS); 
	    System.out.println("The OS is Mac: " + macIsMatch);
	    System.out.println("The OS is Windows: " + windowsIsMatch);
		
		if (windowsIsMatch == true)
		{
			System.out.println("is Windows");
			String cmd = "C:/Windows/System32/cmd.exe /k \"cd C:/Program Files/Google/Chrome/Application && chrome.exe --remote-debugging-port=9222\"";
			Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"C: && cd C:\\Program Files\\Google\\Chrome\\Application && chrome.exe --remote-debugging-port=9222\"");
			System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/drivers/chromedriver.exe");
		}
		
		else if (macIsMatch == true) 
		{
			System.out.println("is Mac");
			String[] args = new String[] {"/bin/bash", "-c", "/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome --remote-debugging-port=9222&", "with", "args"};
			Process proc = new ProcessBuilder(args).start();
//			Runtime.getRuntime().exec("/bin/bash -c /Applications/Google\\\\ Chrome.app/Contents/MacOS/Google\\\\ Chrome --remote-debugging-port=9222&");
			System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/drivers/chromedriver");
		}
		
		System.out.println("Inside Step - browser is open");

	    ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.setExperimentalOption("debuggerAddress","localhost:9222");
	    driver = new ChromeDriver(chromeOptions);
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	    
	    //driver.manage().window().maximize();
	}
	
	@And("user is on SwagLabs login page")
	public void user_is_on_google_seach_page() {
		System.out.println("Inside Step - user is on SwagLabs login page");
		
		driver.navigate().to("https://www.saucedemo.com/");
		
	}

	@When("user sign in with no credentilas")
	public void user_sign_in_with_no_credentilas() {
		System.out.println("Inside Step - user sign in with no credentilas");
	    
		driver.findElement(By.name("login-button")).click();
		
	}

	@Then("Show error message: Epic sadface: Username is required")
	public void show_error_message_epic_sadface_username_is_required() {
		System.out.println("Show error message: Epic sadface: Username is required");
	    
	    boolean error = driver.getPageSource().contains("Epic sadface: Username is required");
	    
	    
	    if(error== true)
	    {
	    	System.out.println("Test Pass");
	    	driver.close();
			driver.quit();
	        //return true;
	    }
	    else {
	    	System.out.println("Test Failed");
	    	driver.close();
			driver.quit();
	    	Assert.fail();
	        //return false;
	    }
	    
	    
		
	}

	@When("user sign in with email and empty password")
	public void user_sign_in_with_email_and_empty_password() {
		System.out.println("Inside Step - user sign in with email and empty password");
	    
		driver.findElement(By.name("user-name")).sendKeys("standard_user");
		driver.findElement(By.name("login-button")).click();
		
	}

	@Then("Show error message: Epic sadface: Password is required")
	public void show_error_message_epic_sadface_password_is_required() {
		System.out.println("Show error message: Epic sadface: Password is required");
		
		boolean error = driver.getPageSource().contains("Epic sadface: Password is required");
	    
	    
	    if(error == true)
	    {
	    	System.out.println("Test Pass");
	    	driver.close();
			driver.quit();
	        //return true;
	    }
	    else {
			System.out.println("Test Failed");
			driver.close();
			driver.quit();
	    	Assert.fail();
	        //return false;
	    }
	}

	@When("user sign in with password and empty email")
	public void user_sign_in_with_password_and_empty_email() {
		System.out.println("user sign in with password and empty email");
	    
		driver.findElement(By.name("password")).sendKeys("secret_sauce");
		driver.findElement(By.name("login-button")).click();
		
	}

	@When("user sign in with incorrect credentilas")
	public void user_sign_in_with_incorrect_credentilas() {
		System.out.println("user sign in with incorrect credentilas");
		
		driver.findElement(By.name("user-name")).sendKeys("oro");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("login-button")).click();
		
	}

	@Then("Show error message: Epic sadface: Username and password do not match any user in this service")
	public void show_error_message_epic_sadface_username_and_password_do_not_match_any_user_in_this_service() {
		System.out.println("Show error message: Epic sadface: Username and password do not match any user in this service");
		
		boolean error = driver.getPageSource().contains("Epic sadface: Username and password do not match any user in this service");
	    
	    
	    if(error == true)
	    {
	    	System.out.println("Test Pass");
	    	driver.close();
			driver.quit();
	        //return true;
	    }
	    else {
	    	System.out.println("Test Failed");
	    	driver.close();
			driver.quit();
	    	Assert.fail();
	        //return false;
	    }
	}
	
}
