package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
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
		try{
		BufferedReader br = new BufferedReader(new FileReader("D:\\BFServer\\git\\BFServer\\src\\files"+username+"//"+"password"));
		if((br.readLine() != null)&&(password == br.readLine()))
			valid = true;
		br.close();
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
		
		return valid;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		State.setUsername("");
		return true;
	}

	@Override
	public boolean register(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+username+"//"+"password");
		try
		{
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
