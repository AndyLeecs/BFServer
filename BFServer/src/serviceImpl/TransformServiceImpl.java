package serviceImpl;

import java.rmi.RemoteException;

import service.Language;
import service.TransformService;

/**
 * 其他语言转为bf语言
 * 
 * @author qwe
 * 
 */

public class TransformServiceImpl implements TransformService
{

	/**
	 * bf语言的单词列表
	 */
	private final String[] bf_words = new String[]
	{ ">", "<", "+", "-", ".", ",", "[", "]" };

	/**
	 * 要被翻译的语言的单词列表
	 */
	private String[] words = new String[]
	{ ">", "<", "+", "-", ".", ",", "[", "]" };

	/**
	 * 要被翻译的语言的单词长度
	 */
	private int gap = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.TransformService#transform(java.lang.String,
	 * java.lang.String, service.Language)
	 */
	@Override
	public String transform(String code, String param, Language l) throws RemoteException
	{
		// TODO Auto-generated method stub
		// 去除换行符
		code = code.replaceAll("\r|\n", "");
		param = param.replaceAll("\r|\n", "");

		if (l.equals(Language.bf))
			return code;

		// 将要被替换的单词和每个单词的长度初始化
		if ((l != null) && (l.equals(Language.ook)))
		{
			this.words = new String[]
			{ "Ook. Ook? ", "Ook? Ook. ", "Ook. Ook. ", "Ook! Ook! ", "Ook! Ook. ", "Ook. Ook! ", "Ook! Ook? ",
					"Ook? Ook! " };
			this.gap = 10;
		}

		if (code.length() % gap != 0)
			return "Error:Illegal input.";

		// 转换为bf语言
		String bf_code = "";
		int length = code.length() / gap;
		System.out.println(code.length() / gap + "");
		for (int i = 0; i < length; i++)
		{
			System.out.println(i + "");
			for (int j = 0; j < gap; j++)
			{
				System.out.println(code.substring(0, gap) + i + j);
				System.out.println("param:" + words[j]);
				if (code.substring(0, gap).equals(words[j]))
				{
					code = code.substring(gap);
					bf_code = bf_code + bf_words[j];
					System.out.println("now the code is:" + code);
					System.out.println(bf_code);
					break;
				} else if (code.equals(words[j]))
				{
					code = "";
					bf_code = bf_code + bf_words[j];
					System.out.println("now the code is:" + code);
					System.out.println(bf_code);
					break;

				}

			}
		}
		System.out.println(code);
		System.out.println(bf_code);
		// 判断是否合法，从内容
		if (code.length() > 0)
			return "Error:Illegal input.";

		System.out.println(bf_code + param);

		return bf_code;
	}

}
