package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService{

	//暂时没有处理初次登录的问题
	@Override
	public boolean login(String username, String password) throws RemoteException {
		
		boolean valid = false;
		String line = "";
		// TODO Auto-generated catch block
//		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username+"\\"+"password.txt");

		System.out.println("create a file");
		if(!new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username+"\\"+"password.txt").exists()){
			System.out.println("file not found");
			return false;
		} 
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username+"\\"+"password.txt"));
			System.out.println(password);
//		System.out.println(br.readLine());
			if(((line = br.readLine()) != null)&&(password.equals(line))){
				valid = true;
				System.out.println("right password");
			}
			br.close();
		} 

			
		catch(IOException e){
			e.printStackTrace();
			
		}
		
		
		return valid;

	}

	@Override
	public boolean logout(String username) throws RemoteException {
		State.setUsername("");
		//临时文件的保存不是登出就消失的
//		File file = new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username);
//		file.deleteOnExit();
		return true;
	}

	@Override
	public boolean register(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username+"\\"+"password.txt");
		try
		{
			if(!new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username).exists())
			{
				new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username).mkdir();
					//f.createNewFile();
			}else{
				return false;
			}

			FileWriter fw = new FileWriter(f,false);
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
