//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 
	 * 报数组越界错
	 * 报非法字符错
	 * 提示缺少字符
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		//指针的位置
		int ptr = 0;
		//输入代码非空时，转为数组
		char[] codelist;
		//图灵机数组，可变长度
//		ArrayList <Character> list = new ArrayList<Character>();
		char[] list = new char[10000];
		//读取到了第几个字符
		int input_ptr = 0;
		//记录括号的匹配情况
		int count = 0;
		
		//要输出的字符串
		String s = "";
		
		if((code != null) && (code.length() != 0)){
			codelist = code.toCharArray();
			
			
			for(int i = 0 ; i < codelist.length ; i++){
				
			
				switch(codelist[i])
				{
				case'>':
					++ptr;
					break;
				case'<':
					--ptr;
					break;
				case'+':
					try{
//					list.set(ptr, (char) (list.get(ptr).charValue()+1));
						list[ptr]++;
						
					}
				catch(IndexOutOfBoundsException e){
					s = "IndexOutOfBoundsExceptionAt'-': "+ptr;
					return s;

				}
					break;
				case'-':
					try{
						list[ptr]--;
					}
					catch(IndexOutOfBoundsException e){
						s = "IndexOutOfBoundsExceptionAt'-': "+ptr;
						return s;
					}

					break;
				case',':
					try{
						
					char c;
					c = param.charAt(input_ptr);

					}
					catch(IndexOutOfBoundsException e){
						s = "IndexOutOfBoundsExceptionAtInput: "+input_ptr;
						return s;
					}
					
					try{
						list[ptr] = param.charAt(input_ptr);
						++input_ptr;
					}catch(IndexOutOfBoundsException e){
						s = "IndexOutOfBoundsExceptionAt'-': "+ptr;
						return s;

					}
					break;
				case'.':
					try{
					s = s + list[ptr];
					}
					catch(IndexOutOfBoundsException e){
						s = "IndexOutOfBoundsExceptionAt'.': "+ptr;
						return s;
					}

					break;
				case'[':
					try{
					if(list[ptr] == 0){
						
					 count = 1;
						for(int j = i + 1 ; j < codelist.length ; j++){
							if(codelist[j] == '[')
								count++;
							else if(codelist[j] == ']')
								count--;
							if(count == 0){
								i = j ;
								break;
						}
							if(j == codelist.length - 1){
								s = "']'missing.";
							return s;
							}
						
					}
					}
					}
					catch(IndexOutOfBoundsException e){
						s = "IndexOutOfBoundsException: "+ptr;
						return s;
					}

					break;
				case']':
					try{
					if(list[ptr]!= 0){
						count = 1;
					
						for(int j = i-1 ; j >= 0; j--){
							if(codelist[j] == ']')
								count++;
							else if(codelist[j] == '[')
								count--;
							if(count == 0){
								i = j;
								break;
						}
							if(j == 0){
								s = "'['missing.";
							return s;
							}
						
					}
					}
					}
					catch(IndexOutOfBoundsException e){
						s = "IndexOutOfBoundsException: "+ptr;
						return s;
					}

					break;
				default:
					s = "Contains illegal characters.";
					return s;
					
					
				}
			
		}
		}
		return s;
	}

}
