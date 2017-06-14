package database;

import java.sql.Date;

/**  
* 类说明   
*  
* @author Andy
* @version  
*/

public class Document
{
	private String code;
	
//	private String param;
	
	private Date date;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
}
