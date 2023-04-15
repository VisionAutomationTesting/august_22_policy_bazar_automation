package com.policybazar.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.policybazar.base.BaseTest;
import com.policybazar.pages.HomePage;
import com.policybazar.pages.MembersPage;
import com.policybazar.pages.NewPreQuotePage;

public class ETE_HealthInsurenceTest extends BaseTest {

	HomePage home = null;
	NewPreQuotePage prQuote = null;
	MembersPage memb = null;

	@BeforeClass
	void setup() {
		init();
		home = new HomePage();
	}

	@Test(priority = 1)
	void validateHomeScreen() {
		Assert.assertTrue(home.verifyUserIsOnHomePage());
	}

	@Test(priority = 2, description = "This test case is used to test End to End testing of Health")
	void validateE2EForHealth() {

		String userName = "Vison Training";

		Assert.assertTrue(home.verifyUserIsOnHomePage());
		prQuote = home.navigateToHealtNewPreQuotePage();

		Assert.assertTrue(prQuote.verifyUserISOnPreQuotePage());
		memb = prQuote.navigateToMembersPage(userName);
		Assert.assertTrue(memb.validateUserIsOnMembrsPage());

		Assert.assertTrue(memb.validateUserNameOnTheHeading(userName));

		List<String> people = Arrays.asList(new String[] { "Son", "Daughter", "Self", "Wife", "Father", "Mother",
				"Grand Father", "Grand Mother", "Father-in-law", "Mother-in-law" });

		List<String> actual = memb.validatePepoleOnMembPage();
		System.out.println(people);
		System.out.println(actual);

		Collections.sort(people);
		Collections.sort(actual);

		System.out.println(people);
		System.out.println(actual);
		Assert.assertEquals(actual, people);

		String actErr=memb.validateTheErrorForNoAnyMemberSelected();
		String expcError="Please select at least one member";
		
		Assert.assertEquals(actErr,expcError, "Bothe error msg are not match!!!");
		
		expcError="Please select self or wife with child";
		actErr=memb.validateSelfOrWifeErrorMsgBySelectingOnlyOneChild();
		Assert.assertEquals(actErr,expcError, "Bothe error msg are not match!!!");
	}
	
	
	

	@AfterClass
	void tearDown() {
		closeAll();
	}
}
