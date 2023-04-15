package com.policybazar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.policybazar.base.BaseTest;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class NewPreQuotePage extends BaseTest{

	
	@FindBy(xpath = "//label[@class='male']")
	private WebElement genderMale;
	
	@FindBy(xpath = "//label[@class='female']")
	private WebElement genderFemale;
	
	@FindBy(id = "fullName")
	private WebElement nameInput;
	
	@FindBy(xpath = "//button[@class='primaryButton']")
	private WebElement continueBtn;
	
	
	public NewPreQuotePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyUserISOnPreQuotePage() {
		waitUntileElementVisibleBy(genderMale);
		return genderMale.isDisplayed() && genderFemale.isDisplayed();
	}

	public MembersPage navigateToMembersPage(String name) {
		nameInput.sendKeys(name);
		continueBtn.click();
		return new MembersPage();
	}

	
	
}
