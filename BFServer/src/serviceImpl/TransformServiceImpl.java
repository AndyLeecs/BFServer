package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.Language;
import service.TransformService;

/**  
* 类说明   
*  
* @author Andy
* @version  
*/

public class TransformServiceImpl implements TransformService
{

	private ExecuteServiceImpl executor;
	
	private final String[] bf_words = new String[]{">","<","+","-",".",",","[","]"};
	//存该语言的单词
	private String[] words = new String[]{">","<","+","-",".",",","[","]"};
	//该语言一个单词的长度
	private int gap = 1;
	
//	public TransformServiceImpl(Language l)
//	{
//		super();
//		if((l!= null) && (l.equals(Language.ook))){
//			this.words = new String[]{"Ook. Ook?","Ook? Ook.","Ook. Ook.",
//					"Ook! Ook!","Ook! Ook.","Ook. Ook!","Ook! Ook?","Ook? Ook!"};
//			this.gap = 8;
//		}
//		
//			
//			
//	}

	@Override
	public String transform(String code, String param, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		//判断是否合法,从长度
		
//		if(l.equals(Language.bf))
//			return code;
		if((l!= null) && (l.equals(Language.ook))){
			this.words = new String[]{"Ook. Ook?","Ook? Ook.","Ook. Ook.",
					"Ook! Ook!","Ook! Ook.","Ook. Ook!","Ook! Ook?","Ook? Ook!"};
			this.gap = 8;
		}

		if(code.length()%gap != 0)
			return "Illegal input.";
		String check = code;
		//转换
		String bf_code = code;
		
			
			for(int i = 0 ; i < bf_words.length ; i++){
			bf_code = bf_code.replace(words[i], bf_words[i]);
			check = check.replace(words[i],"");
			}
		
		//判断是否合法，从内容
			if(check.length()>0)
				return "Illegal input.";
		
		System.out.println(bf_code+param);
//		String result = executor.execute(bf_code,param);
//		System.out.println(result);
//		System.exit(0);
//		return result;
		return bf_code;
	}


}
