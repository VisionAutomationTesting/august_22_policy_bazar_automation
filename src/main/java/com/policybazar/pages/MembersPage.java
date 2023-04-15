package com.policybazar.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.policybazar.base.BaseTest;

public class MembersPage extends BaseTest {

	@FindBy(xpath = "//h1[contains(text(),'select members')]")
	private WebElement membHeading;

	@FindBy(xpath = "//a[text()='More members']")
	private WebElement moreMembLink;

	@FindBy(xpath = "//div[@class='memberSelection__block']//label/p")
	private List<WebElement> listOfMemb;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	private WebElement submitBtn;

	@FindBy(xpath = "//small[contains(@class,'text-error')]")
	private WebElement errorMsg;
	
	private WebElement getMemberEle(String name) {
		return driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]"));
	}

	public MembersPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateUserIsOnMembrsPage() {
		waitUntileElementVisibleBy(membHeading);
		return membHeading.isDisplayed();
	}

	public boolean validateUserNameOnTheHeading(String userName) {
		System.out.println("Member page heading - " + membHeading.getText());
		System.out.println("User name =" + userName);
	
		return membHeading.getText().contains(userName.split(" ")[0]);
	}

	public List<String> validatePepoleOnMembPage() {
		moreMembLink.click();
		return getListBsedOnElements(listOfMemb);
	}

	public String validateTheErrorForNoAnyMemberSelected() {
		getMemberEle("Self").click();
		scrollTillElement(submitBtn);
		clickByJs(submitBtn);
		//submitBtn.submit();
		waitUntileElementVisibleBy(errorMsg);
		return errorMsg.getText();
	}
	
	public String validateSelfOrWifeErrorMsgBySelectingOnlyOneChild() {
		scrollTillElement(getMemberEle("Son"));
		getMemberEle("Son").click();
		scrollTillElement(submitBtn);
		clickByJs(submitBtn);
		//submitBtn.submit();
		waitUntileElementVisibleBy(errorMsg);
		return errorMsg.getText();
	}


}
