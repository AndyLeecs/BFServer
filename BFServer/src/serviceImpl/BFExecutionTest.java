package serviceImpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import service.ExecuteService;
import serviceImpl.ExecuteServiceImpl;

/**
 * 本文件是BrainFuck解析器的测试用例，用于测试你的解析器实现是否正确
 * 由于每个人的解析器很可能类名不同或者包名不同，这样在测试程序里就不知道该怎么调用。
 * 为了解决这个问题，特别引入了一个ProxyImpl的类并对其进行调用。
 * 请修改ProxyImpl这个类，在其实现中调用你自己的解析器代码并返回运行结果。
 * 
 * 注意：不要修改本文件
 */
public class BFExecutionTest {
	private ExecuteService executor;
	
	@Before
	public void setUp() throws Exception {
		executor = new ExecuteServiceImpl();
	}

	@Test
	public void test_single_char_echo() throws Exception{
		String code = ",.";
		assertEquals("A", executor.execute(code, "A")); 
	}
	
	@Test
	public void test_add_to_char() throws Exception{
		String code = ",+++++.";
		assertEquals("F", executor.execute(code, "A"));
	}
	
	@Test
	public void test_add() throws Exception{
		String code = ",>++++++[<-------->-],,[<+>-]<.";
		assertEquals("7", executor.execute(code, "4+3"));
	}
	
	@Test
	public void test_hi_nju() throws Exception{
		String code = "++++++++[>+++++++++[>+>+>+>+>+>+<<<<<<-]<-]++++++[>++++++[>>+>-<<<-]<-]>>>--->---->++++++>++>+++++++++++++<<<<<.>.>.>.>.>.";
		assertEquals("Hi NJU", executor.execute(code, ""));
	}
	
	@Test
	public void test_multiplication() throws Exception{
		String code = ",>,,>++++++++[<------<------>>-]<<[>[>+>+<<-]>>[<<+>>-]<<<-]>>>++++++[<++++++++>-]<.";
		assertEquals("8", executor.execute(code, "2*4"));
	}

	
}

	
