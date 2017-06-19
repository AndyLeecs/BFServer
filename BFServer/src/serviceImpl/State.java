package serviceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

/**  
* 存储现在程序的情况
*  
* @author Andy
* @version  
*/

public class State
{
	private static String username;


	private static File[] files = new File(username).listFiles();//获取该文件夹下所有的文件(夹)名
	
	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		State.username = username;
	}

	public static File[] getFiles()
	{
		return files;
	}

	public void setFiles(File[] files)
	{
		this.files = files;
	}
}
