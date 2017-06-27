package runner;

import rmi.RemoteHelper;

/**
 * 开启服务器 开启远程服务
 * 
 * @author qwe
 */
public class ServerRunner
{

	public static void main(String[] args)
	{
		new ServerRunner();
	}

	public ServerRunner()
	{
		new RemoteHelper();
	}
}
