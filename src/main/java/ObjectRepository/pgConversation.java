package ObjectRepository;

import org.openqa.selenium.By;

public class pgConversation 
{
	public static By EntryPointinputText = By.xpath("//textarea[@class='ChatInput__input']");
	public static By EntryPointTimeText = By.xpath("message-chat__details disabled-avatar static");
	public static By btn_Search = By.xpath("SearchTerm");
	public static By BubbleBox = By.xpath("//div[@class ='bubble-message sc-fjdhpX eqkGDJ']");
	public static By ALL_USER_CHAT_MESSAGES = By.xpath("//div[@class='message-chat transition-done message-chat--user']");
	public static By NEW_CONVERSATION_BUTTON = By.xpath("//a[1]");
	public static By Ele_Click_Contact = By.id("SearchTerm");
	public static By New_Contct_ele = By.xpath("SearchTerm");
	public static By Gmail_link = By.id("gb_70");
	public static By Username = By.id("Email");
	public static By btn_next = By.id("next");
	public static By pasward = By.id("Passwd");
	public static By btn_signIn = By.id("signIn");
	public static By ele_msg = By.id("errormsg_0_Passwd");
	
}
