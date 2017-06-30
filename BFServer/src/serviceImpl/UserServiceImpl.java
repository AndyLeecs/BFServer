package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

/**
 * 登入登出注册
 * 
 * @author qwe
 *
 */
public class UserServiceImpl implements UserService
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) throws RemoteException
	{

		boolean valid = false;
		String line = "";
		// TODO Auto-generated catch block
		System.out.println("create a file");

		if (new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username + "\\" + "login.txt").exists())
			return false;
		// 用户名不存在
		if (!new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username + "\\" + "password.txt").exists())
		{
			System.out.println("file not found");
			return false;
		}

		// 用户名存在，查看密码是否正确
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(
					"D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username + "\\" + "password.txt"));
			System.out.println(password);
			// System.out.println(br.readLine());
			if (((line = br.readLine()) != null) && (password.equals(line)))
			{
				valid = true;
				System.out.println("right password");
			}
			br.close();

			// 写入已登录的状态

			File f = new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username, "login.txt");

			if (!new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).exists())
			{
				new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).mkdir();
				// f.createNewFile();
			}

			FileWriter fw = new FileWriter(f, false);
			fw.write("");
			fw.flush();
			fw.close();

		} catch (IOException e)
		{
			e.printStackTrace();

		}

		return valid;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.UserService#logout(java.lang.String)
	 */
	@Override
	public boolean logout(String username) throws RemoteException
	{
		File file = new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username + "\\login.txt");
		file.delete();
		State.setUsername("");

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.UserService#register(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean register(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username + "\\" + "password.txt");
		try
		{
			if (!new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).exists())
			{
				new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).mkdir();
				// f.createNewFile();
			} else
			{
				return false;
			}

			FileWriter fw = new FileWriter(f, false);
			fw.write(password);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
