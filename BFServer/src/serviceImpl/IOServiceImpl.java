package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import service.IOService;
import service.Language;

/**
 * @author qwe 文件读写的类
 */

public class IOServiceImpl implements IOService
{

	public static int MAX_STORE = 10;// 记录最多允许保存的历史版本个数

	/**
	 * @return 格式化后的当前时间
	 */
	public String getTime()
	{
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		// lastModified
		return df.format(date.getTime());

	}

	/**
	 * @param time
	 * @return 格式化后的文件最后编辑时间
	 */
	public String getTime(long time)
	{
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(time);// System.currentTimeMillis()替换成long
									// lastModified
		return df.format(date.getTime());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#readFile(java.lang.String, java.lang.String,
	 * service.Language)
	 */
	@Override
	public String readFile(String userId, String fileName, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		String file = "";
		String line = "";
		try
		{
			BufferedReader br = new BufferedReader(
					new FileReader("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + userId + "\\" + fileName));
			while (((line = br.readLine()) != null) && (line.length() != 0))

				file = file + line;
			br.close();
			return file;

		} catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#readFileList(java.lang.String, service.Language)
	 */
	@Override
	public ArrayList<String> readFileList(String username, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub

		if (!new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).exists())
			new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).mkdir();
		File[] files = new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username).listFiles();
		ArrayList<String> result = new ArrayList<String>();

		if (files != null)
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].isFile() && (!files[i].getName().contains("password"))
						&& (!files[i].getName().contains("login")))
					result.add(files[i].getName().replace("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + username,
							""));

			}

		return result;

	}

	@Override
	public boolean check(String code) throws RemoteException
	{
		String file = "";
		String line = "";
		try
		{
			// 临时文件夹的大小
			int version = new File("D:\\BFServer\\git\\BFServer\\src\\temp").listFiles().length;
			if (version > 0)
			{
				BufferedReader br = new BufferedReader(
						new FileReader("D:\\BFServer\\git\\BFServer\\src\\temp\\" + (version - 1) + ".txt"));
				while (((line = br.readLine()) != null) && (line.length() != 0))

					file = file + line;
				br.close();

			}
		} catch (IOException e)
		{
			e.printStackTrace();

		}

		if (file.equals(code))
			return false;
		else
			return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#writeTemp(java.lang.String)
	 */
	@Override
	public int writeTemp(String code) throws RemoteException
	{
		System.out.println("start to write temp");
		// 如果临时文件夹不存在，创建
		if (!new File("D:\\BFServer\\git\\BFServer\\src\\temp").exists())
			new File("D:\\BFServer\\git\\BFServer\\src\\temp").mkdirs();
		// 临时文件夹的大小
		int version = new File("D:\\BFServer\\git\\BFServer\\src\\temp").listFiles().length;
		System.out.println("version is" + version);

		// //确认本次和上次存的内容不同
		// if(version>0){
		// String file = "";
		// String line = "";
		// try
		// {
		// BufferedReader br = new BufferedReader(
		// new FileReader("D:\\BFServer\\git\\BFServer\\src\\temp\\" + (version
		// -1) + ".txt"));
		// while (((line = br.readLine()) != null) && (line.length() != 0))
		//
		// file +=line;
		// br.close();
		//
		//
		// } catch (IOException e)
		// {
		// e.printStackTrace();
		//
		// }
		//
		// if(file.equals(code))
		// return version-1;
		// }

		File f = new File("D:\\BFServer\\git\\BFServer\\src\\temp", (version) + ".txt");

		try
		{

			FileWriter fw = new FileWriter(f, false);
			fw.write(code);
			fw.flush();
			fw.close();

		} catch (IOException e)
		{
			e.printStackTrace();
			// return false;
		}
		return version;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#readTempBack(int)
	 */
	@Override
	public String readTempBack(int version)
	{
		String file = "";
		String line = "";
		try
		{
			BufferedReader br = new BufferedReader(
					new FileReader("D:\\BFServer\\git\\BFServer\\src\\temp\\" + (version + 1) + ".txt"));
			while (((line = br.readLine()) != null) && (line.length() != 0))

				file = file + line;
			br.close();
			return file;

		} catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#readTempForward(int)
	 */
	@Override
	public String readTempForward(int version)
	{
		String file = "";
		String line = "";
		try
		{
			BufferedReader br = new BufferedReader(
					new FileReader("D:\\BFServer\\git\\BFServer\\src\\temp\\" + (version - 1) + ".txt"));
			while (((line = br.readLine()) != null) && (line.length() != 0))

				file = file + line;
			br.close();
			return file;

		} catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#clearTemp()
	 */
	@Override
	public void clearTemp() throws RemoteException
	{
		// 获得文件夹下的所有文件
		File[] tempFiles = new File("D:\\BFServer\\git\\BFServer\\src\\temp").listFiles();
		// 逐个删除文件
		if ((tempFiles != null) && (tempFiles.length != 0))
			for (int i = 0; i < tempFiles.length; i++)
			{
				System.out.println(tempFiles[i].getName());
				tempFiles[i].delete();
			}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOService#writeFile(java.lang.String, java.lang.String,
	 * java.lang.String, service.Language)
	 */
	@Override
	public String[] writeFile(String file, String userId, String fileName, Language l) throws RemoteException
	{
		System.out.println(l.toString());
		// 创建新文件
		if (!new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + userId).exists())
			new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + userId).mkdir();
		System.out.print(
				"D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + userId + "\\" + fileName + "." + l.toString());
		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + userId, fileName + "." + l.toString());

		try
		{

			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();

		} catch (IOException e)
		{
			e.printStackTrace();
			// return false;
		}

		// 一个临时的文件夹，文件夹名为文件名，存该文件的历史版本
		if (!new File("D:\\BFServer\\git\\BFServer\\src\\files\\" + userId + "\\" + l).isDirectory())
		{
			new File("D:\\BFServer\\git\\BFServer\\src\\files\\" + userId + "\\" + l).mkdir();
			System.out.println("dir made");
		}

		if (!new File("D:\\BFServer\\git\\BFServer\\src\\files\\" + userId + "\\" + l + "\\" + fileName).isDirectory())
		{
			new File("D:\\BFServer\\git\\BFServer\\src\\files\\" + userId + "\\" + l + "\\" + fileName).mkdir();
			System.out.println("dir made");
		}

		File newfile = new File("D:\\BFServer\\git\\BFServer\\src\\files\\" + userId + "\\" + l + "\\" + fileName,
				getTime() + "." + l.toString());

		try
		{

			FileWriter fw = new FileWriter(newfile, false);
			fw.write(file);
			fw.flush();
			fw.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 倒序
		File[] files = new File("D:\\BFServer\\git\\BFServer\\src\\files" + "\\" + userId + "\\" + l + "\\" + fileName)
				.listFiles();
		if (files == null)
			System.out.println("null");
		if ((files != null) && (files.length >= 2))
			Arrays.sort(files, new Comparator<File>()
			{
				public int compare(File f1, File f2)
				{
					System.out.println(f1.lastModified() + "");
					System.out.println(f2.lastModified() + "");
					long diff = f1.lastModified() - f2.lastModified();
					if (diff > 0)
						return -1;
					else if (diff == 0)
						return 0;
					else
						return 1;
				}

			});
		System.out.println("fileslength:" + files.length);
		String[] result;

		// 需要删除文件时删除文件
		if (files.length == MAX_STORE + 1)
		{

			if (files[MAX_STORE].delete())
				System.out.println(files[MAX_STORE].getName() + "is deleted");

			result = new String[MAX_STORE];
		} else
			result = new String[files.length];

		// 返回文件列表
		for (int i = 0; (i < MAX_STORE) && (result.length > i) && (i < files.length); i++)
		{

			result[i] = getTime(files[i].lastModified());
			System.out.print(result[i]);
		}
		return result;

	}

}
