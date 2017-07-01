package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import service.ExecuteService;
import service.IOService;
import service.Language;
import service.TransformService;
import service.UserService;
import serviceImpl.ExecuteServiceImpl;
import serviceImpl.IOServiceImpl;
import serviceImpl.TransformServiceImpl;
import serviceImpl.UserServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject
		implements IOService, UserService, ExecuteService, TransformService
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private ExecuteServiceImpl executeService;
	private TransformServiceImpl transformService;

	protected DataRemoteObject() throws RemoteException
	{
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService = new ExecuteServiceImpl();
		transformService = new TransformServiceImpl();

	}

	@Override
	public String execute(String code, String param) throws RemoteException
	{
		// TODO Auto-generated method stub
		return executeService.execute(code, param);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException
	{
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

	@Override
	public String readFile(String userId, String fileName, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName, l);
	}

	@Override
	public ArrayList<String> readFileList(String userId, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId, l);
	}

	@Override
	public boolean register(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
		return userService.register(username, password);
	}

	@Override
	public String transform(String code, String param, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		return executeService.execute(transformService.transform(code, param, l), param);
	}

	@Override
	public String[] writeFile(String file, String userId, String fileName, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName, l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#writeTemp(java.lang.String)
	 */
	@Override
	public int writeTemp(String code) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.writeTemp(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#readTempBack(int)
	 */
	@Override
	public String readTempBack(int version) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.readTempBack(version);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#readTempForward(int)
	 */
	@Override
	public String readTempForward(int version) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.readTempForward(version);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#clearTemp()
	 */
	@Override
	public void clearTemp() throws RemoteException
	{
		// TODO Auto-generated method stub
		iOService.clearTemp();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#check(java.lang.String)
	 */
	@Override
	public boolean check(String code) throws RemoteException
	{
		// TODO Auto-generated method stub
		return iOService.check(code);
	}

}
