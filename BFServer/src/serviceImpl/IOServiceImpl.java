package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class IOServiceImpl implements IOService{
	
//	public static int count = 0;//记录这是同一个文件第一次保存
	public static int MAX_STORE = 10;//记录最多允许保存的历史版本个数
@Override
	public String[] writeFile(String file, String userId, String fileName, Language l) throws RemoteException{
		System.out.println(l.toString());
		if(!new File("D:\\BFServer\\git\\BFServer\\src\\files"+ "\\"+userId).exists())
			new File("D:\\BFServer\\git\\BFServer\\src\\files"+ "\\"+userId).mkdir();	
		System.out.print("D:\\BFServer\\git\\BFServer\\src\\files"+ "\\"+userId + "\\" + fileName + "." + l.toString());
		File f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+ "\\"+userId, fileName + "." + l.toString());

		try {

//			f.createNewFile();
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
//		return null;
//		
//		
//	}
		
//		File temp = new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName +"\\"+Calendar.DATE+Calendar.HOUR+Calendar.MINUTE+Calendar.SECOND+ "." + l);
//		if(f.exists()){
//			count = (++count)%MAX_STORE;
//			f.la
//			f = new File("D:\\BFServer\\git\\BFServer\\src\\files"+userId + "\\" + fileName + count + "." + l);
//		}
//		else
//			count = 0;
		//一个临时的文件夹，文件夹名为文件名，存该文件的历史版本
		if(!new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l).isDirectory()){
			new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l).mkdir();
			System.out.println("dir made");
		}

		if(!new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l + "\\" + fileName).isDirectory()){
			new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l + "\\" + fileName).mkdir();
			System.out.println("dir made");
		}

//		int length = 0;
//		File[] files = new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l + "\\" + fileName).listFiles();
//		D:\BFServer\git\BFServer\src\files\a\bf\cxy
//		if(files == null || files.length == 0)
//			length = 0;
//		else
//			length = files.length;
//		System.out.println(length);
//		if(!new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId + "\\" + fileName +"\\"+(length+1)+ "." + l.toString()).exists())
//			new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId + "\\" + fileName +"\\"+(length+1)+ "." + l.toString()).mkdir();	
//		System.out.print("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l + "\\" + fileName +"\\"+"version"+(length+1)+ "." + l.toString());
		File newfile = new File("D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l + "\\" + fileName,getTime() +"."+ l.toString());
		

		try
		{
			//newfile.createNewFile();
//		copyFile("D:\\BFServer\\git\\BFServer\\src\\files"+ "\\"+userId + "\\" + fileName + "." + l.toString(),"D:\\BFServer\\git\\BFServer\\src\\files\\"+userId+"\\"+l + "\\" + fileName+"\\"+getTime() +"."+ l.toString());
			FileWriter fw = new FileWriter(newfile, false);
			fw.write(file);
			fw.flush();
			fw.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//倒序
		File[] files = new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+userId+"\\"+l + "\\" + fileName).listFiles();
		if(files == null)
			System.out.println("null");
		if((files!= null) && (files.length>=2))
		   Arrays.sort(files,new Comparator<File>(){  
			     public int compare(File f1, File f2) { 
			    	 System.out.println(f1.lastModified()+"");
			    	 System.out.println(f2.lastModified()+"");
			    long diff = f1.lastModified() - f2.lastModified();  
			    if (diff > 0)  
			      return -1;  
			    else if (diff == 0)  
			      return 0;  
			    else  
			      return 1;  
			     } 
			          
	 });
		System.out.println("fileslength:"+files.length); 
		String[] result;
		if(files.length == MAX_STORE + 1){
//			for(File file_finded_to_delete:files){
//				if(file_finded_to_delete.getName().equals("version1."+l)){
//					file_finded_to_delete.delete();
//				System.out.println(file_finded_to_delete.getName()+"is deleted");
			if(files[MAX_STORE].delete())
				System.out.println(files[MAX_STORE].getName()+"is deleted");
				

			
//			for(int i = MAX_STORE-1 ; i >=0 ; i--){
//				for(int j = 2 ; j < MAX_STORE+2; j++){
//					if(files[i].getName().equals("version"+j+"."+l)){
//						System.out.println(files[i].getName());
//						files[i].renameTo((new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+userId+"\\"+l + "\\" + fileName +"\\"+"version"+(j-1)+ "." + l)));
//					System.out.println(files[i].getName());
//					}}
//				}
			
//			for(int i = 0; i < MAX_STORE-1 ; i++)
//				files[i] = files[i+1];
			result = new String[MAX_STORE];
		}else
			result = new String[files.length];
		
		for(int i = 0 ; (i < MAX_STORE)&&(result.length>i)&&(i < files.length); i++){
			
				result[i] = getTime(files[i].lastModified());
			System.out.print(result[i]);
		}
			return result;
//
//		
		
	}

	@Override
	public String readFile(String userId, String fileName, Language l) throws RemoteException{
		// TODO Auto-generated method stub
		String file = "";
		String line = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+userId+"\\"+fileName));
			while(((line = br.readLine()) != null) && (line.length() != 0))

				file = file + line;
			return file;
				
		}catch(IOException e){
			e.printStackTrace();
			return "";
		}
		
	}

	@Override
	public ArrayList<String> readFileList(String username,Language l)throws RemoteException {
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
		if(!new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username).exists())
			new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username).mkdir();
		File[] files = new File("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username).listFiles();
ArrayList<String> result = new ArrayList<String>();

		
		for(int i = 0; i < files.length ; i++){
			if(files[i].isFile()&&(!files[i].getName().contains("password")))
			result.add(files[i].getName().replace("D:\\BFServer\\git\\BFServer\\src\\files"+"\\"+username, "")) ;
			
		}
		
		return result;
			
		
	}
	public String getTime(){
	 DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	  Calendar date = Calendar.getInstance();
	  date.setTimeInMillis(System.currentTimeMillis());//System.currentTimeMillis()替换成long lastModified
	  return df.format(date.getTime());
	  
	  
	}

	public String getTime(long time){
		 DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		  Calendar date = Calendar.getInstance();
		  date.setTimeInMillis(time);//System.currentTimeMillis()替换成long lastModified
		  return df.format(date.getTime());
		  
		  
		}
    public void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }


}
