import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MyTeastCases {

	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://automationteststore.com/";
	String[] firstNames = { "sara", "ali", "ahmad", "omar", "aya", "alaa", "noor", "Rama", "mais" };
	String[] LastNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar", "jamil" };
	String password = "Mmmmm@10";
	String GlobalUserNameLogin = "";

	Random rand = new Random();
	Actions action = new Actions(driver);

	@BeforeTest
	public void mySetup() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(myWebsite);

	}

	@Test(priority = 1)
	public void signUp() throws InterruptedException {
		
		int FirstNameIndex = rand.nextInt(firstNames.length);
		int LastNameIndex = rand.nextInt(LastNames.length);
		int randomNumEmail = rand.nextInt(357159);

		String UserFirstName = firstNames[FirstNameIndex];
		String UserLastName = LastNames[LastNameIndex];
		String DomainName = "@gmail.com";
		String UserEmail = UserFirstName + UserLastName + randomNumEmail + DomainName;
		
		driver.findElement(By.linkText("Login or register")).click();
		WebElement signUpBtn = driver.findElement(By.xpath("//button[@title='Continue']"));
		signUpBtn.click();
		
		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(UserFirstName);
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(UserEmail);
		WebElement AdressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AdressInput.sendKeys("SportCity");
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("Capital City");
				
		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector1 = new Select(CountryInput);
		int randomCountry = rand.nextInt(1, 240);
		selector1.selectByIndex(randomCountry);
						
		Thread.sleep(2000);
		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector2 = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 3);
		selector2.selectByIndex(randomState);
		
		WebElement postcodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		postcodeInput.sendKeys("13310");
		
		WebElement LogNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		String LocalUserName = (UserFirstName + UserLastName + randomNumEmail);
		LogNameInput.sendKeys(LocalUserName);
		GlobalUserNameLogin = LocalUserName;
				
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys(password);
		WebElement confirmpaasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		confirmpaasswordInput.sendKeys(password);
		WebElement subscribe = driver.findElement(By.id("AccountFrm_newsletter1"));
		subscribe.click();
		WebElement policy = driver.findElement(By.id("AccountFrm_agree"));
		policy.click();
		WebElement clickContinue = driver.findElement(By.xpath("//button[@title='Continue']"));
		clickContinue.click();
		
	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {

		WebElement hover = driver.findElement(By.xpath("//*[@id=\"customer_menu_top\"]/li/a/div"));
		Thread.sleep(3000);
		action.moveToElement(hover).perform();
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Not ")).click();
		String LogoutURl = "https://automationteststore.com/index.php?rt=account/logout";
		driver.get(LogoutURl);

	}
	
	@Test(priority = 3)
	public void Login() {
		
		WebElement clickLogin = driver.findElement(By.linkText("Login or register"));
		clickLogin.click();
		WebElement userInput = driver.findElement(By.id("loginFrm_loginname"));
		userInput.sendKeys(GlobalUserNameLogin);
		WebElement loginPasswordInput = driver.findElement(By.id("loginFrm_password"));
		loginPasswordInput.sendKeys(password);
		WebElement LoginBtn = driver.findElement(By.xpath("//button[@title='Login']"));
		LoginBtn.click();
	}
	
	@Test(priority = 4)
	
	public void AddToCart() throws InterruptedException {
		
		String[] WebsitesForItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
		"https://automationteststore.com/index.php?rt=product/category&path=36",
		"https://automationteststore.com/index.php?rt=product/category&path=43",
		"https://automationteststore.com/index.php?rt=product/category&path=49",
		"https://automationteststore.com/index.php?rt=product/category&path=58",
		"https://automationteststore.com/index.php?rt=product/category&path=52",
		"https://automationteststore.com/index.php?rt=product/category&path=65"};
		
		int randomIndex = rand.nextInt(WebsitesForItems.length);
	    driver.get(WebsitesForItems[randomIndex]);
	    
	    WebElement ListOfItems = driver.findElement(By.cssSelector(".thumbnails.row"));
	    
	    int NumbersOfProducts = ListOfItems.findElements(By.tagName("li")).size();
	    int RandomItemsIndex = rand.nextInt(NumbersOfProducts);
	    
	    Thread.sleep(3000);	    
	    ListOfItems.findElements(By.tagName("li")).get(RandomItemsIndex).click();
	    
	    WebElement container = driver.findElement(By.cssSelector("thumbnails.grid.row.list-inline"));
	    int NumberOfItems = container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
	    int randomIndexForTheproduct = rand.nextInt(NumberOfItems);
	    container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomIndexForTheproduct).click();
		Thread.sleep(5000);
		WebElement ULList = driver.findElement(By.className("productpagecart"));
		int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();
		
		if (LiItem > 0) {
			driver.get(myWebsite);
			System.out.println("sorry the item out of the stock ");
			String ExpectedResult = "https://automationteststore.com/";
			String ActualResult = driver.getCurrentUrl();
			org.testng.Assert.assertEquals(ActualResult, ExpectedResult, "sosso");

		} 
		else {

			driver.findElement(By.className("cart")).click();
			Thread.sleep(2000);
			String ActualResult = driver.findElement(By.className("heading1")).getText();
			String ExpectedResult = "Shopping Cart";

			org.testng.Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
			boolean ExpectedValueForCheckOut = true;
			boolean ActualValueForCheckOut = driver.findElement(By.id("cart_checkout1")).isDisplayed();
			org.testng.Assert.assertEquals(ActualValueForCheckOut, ExpectedValueForCheckOut);
		}
	    
	    
	
	}
	
}
