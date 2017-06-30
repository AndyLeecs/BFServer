package serviceImpl;

/**
 * 存储现在程序的情况
 * 
 * @author qwe
 * 
 */

public class State
{
	/**
	 * 用户名
	 */
	private static String username;

	// /**
	// * 该文件夹下所有的文件(夹)名
	// */
	// private static File[] files = new File(username).listFiles();

	// public static File[] getFiles()
	// {
	// return files;
	// }

	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		State.username = username;
	}

	// public static void setFiles(File[] files)
	// {
	// State.files = files;
	// }
	//

}
