//需要客户端的Stub
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 文件读写
 * 
 * @author qwe
 */
public interface IOService extends Remote
{

	/**
	 * @param userId
	 *            用户名
	 * @param fileName
	 *            文件名
	 * @param l
	 *            语言类型
	 * @return 文件内容
	 * @throws RemoteException
	 */
	public String readFile(String userId, String fileName, Language l) throws RemoteException;

	/**
	 * @param userId
	 *            用户名
	 * @param l
	 *            语言类型
	 * @return 该用户的文件列表或该文件的历史版本，历史版本数目超过10个时返回10个，将时间最远的一个在文件夹中清空
	 * @throws RemoteException
	 */
	public ArrayList<String> readFileList(String userId, Language l) throws RemoteException;

	/**
	 * @param file
	 *            文件内容
	 * @param userId
	 *            用户名
	 * @param fileName
	 *            文件名
	 * @param l
	 *            语言类型
	 * @return 文件列表
	 * @throws RemoteException
	 */
	public String[] writeFile(String file, String userId, String fileName, Language l) throws RemoteException;
}
