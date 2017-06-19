package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import runner.ServerRunner;
import service.ExecuteService;
import service.IOService;
import service.Language;
import service.TransformService;
import service.UserService;
import serviceImpl.ExecuteServiceImpl;
import serviceImpl.IOServiceImpl;
import serviceImpl.TransformServiceImpl;
import serviceImpl.UserServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService, ExecuteService, TransformService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private ExecuteServiceImpl executeService;
	private TransformServiceImpl transformService;
	
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService = new ExecuteServiceImpl();
		transformService = new TransformServiceImpl(null);
		
	}

	@Override
	public String[] writeFile(String file, String userId, String fileName, Language l) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName, l);
	}

	@Override
	public String readFile(String userId, String fileName, Language l) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName,l);
	}

	@Override
	public String[] readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

	@Override
	public String transform(String code, String param, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		return transformService.transform(code, param, l);
	}

	@Override
	public String execute(String code, String param) throws RemoteException
	{
		// TODO Auto-generated method stub
		return executeService.execute(code, param);
	}

	@Override
	public boolean register(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
		return userService.register(username,password);
	}

}
