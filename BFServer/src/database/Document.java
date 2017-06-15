package database;

import java.util.Date;

/**  
* 每一个用户管理的是自己的文件，其中有文件名，文件内容和文件时  
*  
* @author Andy
* @version  
*/

public class Document
{
	private Integer id;
	private String name;
	private String code;
	
//	private String param;
	
	private Date date;

	public Document(String code, Date date)
	{
		super();
		this.code = code;
		this.date = date;
	}

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
