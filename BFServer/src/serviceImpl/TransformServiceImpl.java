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
			this.gap = 10;
		}

		if(code.length()%gap != 0)
			return "Illegal input.";
		String bf_code ="";
		int length = code.length()/gap;
			System.out.println(code.length()/gap+"");
			for(int i = 0 ; i < length; i++){
				System.out.println(i+"");
				for(int j = 0 ; j < gap ; j++){
					System.out.println(code.substring(0, gap)+i+j);
					System.out.println("param:"+words[j]);
				if(code.substring(0, gap).equals(words[j])){
					code = code.substring(gap);
					bf_code = bf_code+bf_words[j];
					System.out.println("now the code is:"+code);
					System.out.println(bf_code);
					break;
				}else if(code.equals(words[j])){
					code = "";
					bf_code = bf_code+bf_words[j];
					System.out.println("now the code is:"+code);
					System.out.println(bf_code);
					break;

				}
				
			
		
			
			}
			}
		System.out.println(code);
		System.out.println(bf_code);
		//判断是否合法，从内容
			if(code.length()>0)
				return "Illegal input.";
		
		System.out.println(bf_code+param);
//		String result = executor.execute(bf_code,param);
//		System.out.println(result);
//		System.exit(0);
//		return result;
	
//		String result = executor.execute(bf_code,param);
//		System.out.println(result);
//		System.exit(0);
//		return result;
		return bf_code;
	}


}
