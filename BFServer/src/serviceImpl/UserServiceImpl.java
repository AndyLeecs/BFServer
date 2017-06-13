package serviceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService{

	//暂时没有处理初次登录的问题
	@Override
	public boolean login(String username, String password) throws RemoteException {
		
		boolean valid = false;
		try{
		BufferedReader br = new BufferedReader(new FileReader(username+"_"+"password"));
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
		return true;
	}

}
