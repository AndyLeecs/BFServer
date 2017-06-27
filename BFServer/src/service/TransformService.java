package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 其他语言转为bf语言
 * 
 * @author qwe
 */

public interface TransformService extends Remote
{
	/**
	 * @param code
	 *            源代码
	 * @param param
	 *            控制台输入
	 * @param l
	 *            语言类型
	 * @return 转换后的bf代码
	 * @throws RemoteException
	 */

	public String transform(String code, String param, Language l) throws RemoteException;
}
