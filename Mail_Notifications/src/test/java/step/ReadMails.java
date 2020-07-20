package step;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;
import service.MailSender;
import service.ScreenShot;

public class ReadMails {
	
	
	
	@Given("^I want to write a step with precondition$")
	public void i_want_to_write_a_step_with_precondition() throws Throwable {
	    try {
	    	  System.setProperty("webdriver.chrome.driver", "C:\\Work\\eclipse-workspace\\chromedriver.exe");  
	    	  WebDriver driver=new ChromeDriver();    
	    	  driver.navigate().to("http://mail.simtechitsolutions.com/webmail/?_task=mail");  
	          driver.manage().window().maximize();
	          WebElement userName = driver.findElement(By.id("rcmloginuser"));
	          userName.sendKeys("Lihal.k@simtechitsolutions.com");
	          WebElement password = driver.findElement(By.id("rcmloginpwd"));
	          password.sendKeys("simtech@123");
	          WebElement login = driver.findElement(By.className("login-button"));
	          login.click();
	          Thread.sleep(2000);
//	          WebElement table = driver.findElement(By.xpath(".//table[@id='messagelist']/tbody"));
//	          List< WebElement > rows_table = table.findElements(By.tagName("tr"));
//	          System.out.println("Rows Count"+rows_table.size());
//	          for(int i=0;i<1;i++) {
//	        	  List< WebElement > columns= rows_table.get(i).findElements(By.tagName("td")); 
//	        	  String dateTime = columns.get(3).getText();
//	        	  List<WebElement> childs = columns.get(1).findElements(By.xpath(".//*"));
//	        	  String subject = childs.get(0).getText();
//	        	  System.out.println("Column Count :"+columns.size()+dateTime+" Childs count :"+childs.size());
//	          }
	          String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	          String fileName = "C:\\Work\\eclipse-workspace\\Mail_Images\\"+"Image"+timeStamp+".png";
	          System.out.println("File Name"+fileName);
	          File file = new File(fileName);
	          new ScreenShot().takeSnapShot(driver, fileName);
	          String host = "smtp.gmail.com";
		        String port = "587";
		        String mailFrom = "simtech.celebrations@gmail.com";
		        String passwordtext = "simtech@1234";
		 
		        // message info
		        String mailTo = "kopparapulihaltej.46@gmail.com";
		        String subject = "Simtech Mail Notification";
		        String timeStamp_body = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		        String message = "Last Updated Time "+timeStamp_body;
		 
		        // attachments
		        String attachFiles = fileName;
		        
		        try {
		            new MailSender().sendEmailWithAttachments(host, port, mailFrom, passwordtext, mailTo, subject, message, attachFiles);
		            System.out.println("Email sent.");
		        } catch (Exception ex) {
		            System.out.println("Could not send email.");
		            ex.printStackTrace();
		        }
	          
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

	@When("^I complete action$")
	public void i_complete_action() throws Throwable {
		System.out.println("When");
		
	}

	@Then("^I validate the outcomes$")
	public void i_validate_the_outcomes() throws Throwable {
		System.out.println("Then");
	}

}
