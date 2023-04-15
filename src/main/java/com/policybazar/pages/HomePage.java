package com.policybazar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.policybazar.base.BaseTest;

public class HomePage extends BaseTest{

	@FindBy(css = ".logo")
	private WebElement homeLogo;
	
	@FindBy(xpath ="//p[text()='Health']/ancestor::a")
	private WebElement healthLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyUserIsOnHomePage() {
		return homeLogo.isDisplayed() && healthLink.isDisplayed();
	}

	public NewPreQuotePage navigateToHealtNewPreQuotePage() {
		waitUntileElementVisibleAndClickBy(healthLink);
		return new NewPreQuotePage();
	}
	
	public static void addNewMethod() {
		System.out.println("changes in existing class");
	}
	
}
