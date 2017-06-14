package database;

import java.sql.Time;

/**  
* 用户的模型实体
*  
* @author Andy
* @version  
*/

public class Model
{
	private Integer id;
	private String userName;
	private String passWord;
	
	private Document document;
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassWord()
	{
		return passWord;
	}
	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}
	public Document getDocument()
	{
		return document;
	}
	public void setDocument(Document document)
	{
		this.document = document;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
}
