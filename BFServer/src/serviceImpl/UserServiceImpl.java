package serviceImpl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import database.DBUtil;
import service.UserService;


public class UserServiceImpl implements UserService{

	//暂时没有处理初次登录的问题
	@Override
	public boolean login(String username, String password) throws RemoteException {
//		
//		boolean valid = false;
//		try{
//		BufferedReader br = new BufferedReader(new FileReader(username+"_"+"password"));
//		if((br.readLine() != null)&&(password == br.readLine()))
//			valid = true;
//		br.close();
//			
//		}catch(IOException e){
//			e.printStackTrace();
//			
//		}
//		
//		
//		return valid;
		String managername,managerpassword;
       String queryString="select * from bf";
        try{
        DBUtil contest = new DBUtil();
        ResultSet resultSet=contest.executeQuery(queryString);
       while(resultSet.next()){
            managername=resultSet.getString("username");
            managerpassword=resultSet.getString("password");
            if(managername.equals(username)&&managerpassword.equals(password))
            	return true;
            
            
        }
        }catch(SQLException q){ 
            System.out.println(q);
        }
       

        return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean register(String username, String password) throws RemoteException
	{
		// TODO Auto-generated method stub
	    
	    	       Connection con=DBUtil.getConnection();//首先拿到数据库的连接
	    	        String sql="" + 
	    	        		"INSERT INTO USER(USERNAME,PASSWORD) VALUES("+"\'"+username+"\'"+","+"\'"+password+"\'"+");";	            //预编译sql语句
	    	         Statement state;
					try
					{
						state = con.createStatement();
						state.executeUpdate(sql);
						State.setUsername(username);
						return true;
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	     
		
		return false;
	}

}
