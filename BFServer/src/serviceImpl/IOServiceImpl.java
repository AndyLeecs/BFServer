package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;
import service.Language;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName, Language l) {
		File f = new File(userId + "_" + fileName + "." + l);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			FileWriter fw_list = new FileWriter((userId + "_" + "filelist"),true);
			fw_list.write(fileName+"@");
			fw_list.flush();
			fw_list.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public String readFile(String userId, String fileName, Language l) {
		// TODO Auto-generated method stub
		String file = "";
		String line = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(userId + "_" + fileName+ "." + l));
			while(((line = br.readLine()) != null) && (line.length() != 0))

				file = file + line;
			return file;
				
		}catch(IOException e){
			e.printStackTrace();
			return "";
		}
		
	}

	@Override
	public String[] readFileList(String userId) {
		// TODO Auto-generated method stub
		//将原来的file split成各种file的名字
		String[] files = {};
		String file = "";
		String line = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(userId + "_" + "filelist"));
			while(((line = br.readLine()) != null) && (line.length() != 0))
				file = file + line;
		}catch(IOException e){
			e.printStackTrace();
			return files;
		}
		
		files = file.split("@");
		
		return files;
	}
	
}
