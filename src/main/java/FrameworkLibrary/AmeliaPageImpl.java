package FrameworkLibrary;

//package com.allstate.automation.amelia.pages.spi;
import java.util.List;

import org.openqa.selenium.WebElement;
//import org.xframium.page.StepStatus;

//import com.allstate.automation.amelia.pages.AmeliaPage;
//import com.allstate.automation.amelia.utility.ABOAbstractPage;


public class AmeliaPageImpl //extends ABOAbstractPage implements AmeliaPage
{
	
	public AmeliaPageImpl()	{}
	
/*	

	   
	public void initializePage() {	}

	@Override
	public boolean wasThisUserMessageJustPosted(String msg)
	{
		List<WebElement> userMessages = getWebElements(AmeliaPage.ALL_USER_CHAT_MESSAGES);
		String displayed="";
		if(userMessages==null)//cover case when List would be null to avoid exception
		{
			report.logStep("No User messages were posted");
			return false;
		}
		
		if (userMessages.size() > 0)	
		{
				//get text of last user message
				displayed = userMessages.get(userMessages.size()-1).getAttribute("innerText");
				//verify contains wanted user message
				if(displayed.contains(msg))
				{
					report.logStep("APimpl --- 39---User message: " + msg + " was posted");
					return true;
				}
				else
				{
					report.logStep("APimpl --- 44---User message: " + msg + " was NOT posted");
					return false;
				}
		}
		else	
		{
			report.logStep("APimpl --- 50---No User messages were posted");
			return false;
		}
	}
	
	@Override
	public boolean wasThisAmeliaResponseJustReceived(String resp)
	{
    	List<WebElement> ameliaMessages = getWebElements(AmeliaPage.ALL_AMELIA_CHAT_MESSAGES);

    	String displayedResponse = "";
    	if(ameliaMessages==null)//cover case when List would be null to avoid exception
		{
			report.logStep("No Amelia responses were posted");
			return false;
		}
    	
		if (ameliaMessages.size() > 0)	{

				displayedResponse = ameliaMessages.get(ameliaMessages.size()-1).getAttribute("innerText");
				report.logStep("APimpl --- 70---Latest Amelias response displayed: " + displayedResponse);
				System.out.println("APimpl --- 71---AmeliaPageImpl---- Response is -- "+"\t"+displayedResponse);
				if(displayedResponse.contains(resp))
				{
					report.logStep("APimpl --- 74---Amelia's response: " + resp + " was received");
					return true;
				}
				else
				{
					report.logStep("APimpl --- 79---Amelia's response: " + resp + " was NOT received");
					return false;
				}
		}
		else	
		{
			report.logStep("No Amelia responses were posted");
			return false;
		}
	}
	
	@Override
	public boolean wasThisAmeliaResponseReceivedInThisConversation(String resp)
	{
    	List<WebElement> ameliaMessages = getWebElements(AmeliaPage.ALL_AMELIA_CHAT_MESSAGES);

    	String displayedResponse = "";
    	if(ameliaMessages==null)//cover case when List would be null to avoid exception
		{
			report.logStep("No Amelia responses were posted");
			return false;
		}
    	
		if (ameliaMessages.size() > 0)	{
			 for (int j = ameliaMessages.size()-1; j >= 0 ; j--)	
			 {
					displayedResponse = ameliaMessages.get(j).getAttribute("innerText");
					report.logStep("APimpl ---106---About to check Amelias response displayed at line "+j+": " + displayedResponse);
					
					if(displayedResponse.contains(resp))
					{
						report.logStep("APimpl ---110---Expected Amelia's response: " + resp + " was received");
						return true;
					}

			 }
			 report.logStep("APimpl ---115---Expected Amelia's response: " + resp + " was NOT received");
			 return false;
				
				
		}
		else	
		{
			report.logStep("APimpl ---122---No Amelia responses were posted");
			return false;
		}
	}
	
	@Override
	public boolean waitForSpecificResponseFromAmelia(String resp, int timeoutinSeconds)
	{
    	List<WebElement> ameliaMessages = getWebElements(AmeliaPage.ALL_AMELIA_CHAT_MESSAGES);

    	String displayedResponse = "";
    	boolean done = false;
    	int intSecsPassed = 0;
    	
			while (!done)
			{
				if(wasThisAmeliaResponseJustReceived(resp))
				{
					done = true;
					report.logStep("APimpl ---141---Amelia's response: " + resp + " was received in about "+intSecsPassed+" seconds");
					return true;
				}
				else
				{
					hold(1000);
					intSecsPassed = intSecsPassed+1;
					if(timeoutinSeconds == intSecsPassed)
					{
						done = true;
						report.logStep("APimpl ---151---Amelia's response: " + resp + " was NOT received after waiting for "+intSecsPassed+" seconds");
					}
				}
			}
			return false;	
	}
	*/
}