package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import service.IOService;
import service.Language;

public class IOServiceImpl implements IOService{
	
//	public static int count = 0;//记录这是同一个文件第一次保存
	public static int MAX_STORE = 10;//记录最多允许保存的历史版本个数
	@Override
	public String[] writeFile(String file, String userId, String fileName, Language l) {
		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName + "." + l);
		
//		File temp = new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName +"\\"+Calendar.DATE+Calendar.HOUR+Calendar.MINUTE+Calendar.SECOND+ "." + l);
//		if(f.exists()){
//			count = (++count)%MAX_STORE;
//			f.la
//			f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName + count + "." + l);
//		}
//		else
//			count = 0;
		File[] files = new File(fileName).listFiles();
		
		
		File temp = new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName +"\\"+(files.length+1)+ "." + l);		
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
//			FileWriter fw_list = new FileWriter((userId + "_" + "filelist"),true);
//			fw_list.write(fileName+"@");
//			fw_list.flush();
//			fw_list.close();
//			return true;
		} catch (IOException e) {
			e.printStackTrace();
//			return false;
		}
		files = new File(fileName).listFiles();
		String[] result;
		if(files.length == MAX_STORE + 1){
			files[0].delete();
			for(int i = 1 ; i < MAX_STORE + 1 ; i++){
				files[i].renameTo(new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName +"\\"+i+ "." + l));
			}
			
			for(int i = 0; i < MAX_STORE ; i++)
				files[i] = files[i+1];
			result = new String[MAX_STORE];
		}else
			result = new String[files.length];
		
		for(int i = 0 ; files[i]!= null && i < MAX_STORE; i++)
			
				result[i] = files[i].getAbsolutePath();
			
			return result;

		
		
	}

	@Override
	public String readFile(String userId, String fileName, Language l) {
		// TODO Auto-generated method stub
		String file = "";
		String line = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName+ "." + l));
			while(((line = br.readLine()) != null) && (line.length() != 0))

				file = file + line;
			return file;
				
		}catch(IOException e){
			e.printStackTrace();
			return "";
		}
		
	}

	@Override
	public String[] readFileList(String username) {
		// TODO Auto-generated method stub
//		//将原来的file split成各种file的名字
//		String[] files = {};
//		String file = "";
//		String line = "";
//		try{
//			BufferedReader br = new BufferedReader(new FileReader(userId + "_" + "filelist"));
//			while(((line = br.readLine()) != null) && (line.length() != 0))
//				file = file + line;
//		}catch(IOException e){
//			e.printStackTrace();
//			return files;
//		}
//		
//		files = file.split("@");
//		
//		return files;
//	}
		File[] files = new File(username).listFiles();
		String[] result = new String[files.length];
		
		for(int i = 0; i < files.length ; i++)
			result[i] = files[i].getAbsolutePath();
		
		return result;
			
		
	}
	
}
