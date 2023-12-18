package busReservation;
import java.sql.*;
public class JDBCDemo {

	public static void main(String[] args)throws Exception {
		batchdemo();
   
}
	//Employee table display java
	
	public static void readRecords()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		String query="select * from employee";
		
	   Connection con =DriverManager.getConnection(url, userName, passWord);
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(query);
	   
	    while(rs.next()) {
	    System.out.println("ID is " + rs.getInt(1));
	    System.out.println("Name is " + rs.getString(2));
	    System.out.println("Salary is " + rs.getInt(3));
	    }
	    con.close();
	   }
	
	//Insert a new Values in Employee Tables using java to see sql emp_table
	
	public static void insertRecord()throws Exception{
		
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		String query="insert into employee values(6,'Revathi',230000)"; //values are directly in hot coding
		
	   Connection con =DriverManager.getConnection(url, userName, passWord);
	   Statement st=con.createStatement();
	    int rows = st.executeUpdate(query);
	    
	   System.out.println("Number of rows affected: "+rows);
	   
	    con.close();
	   }
	//Insert a values with Variables not used by a hot code
	
	public static void insertVar()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		
		// Variable is Declared
		
		int id=8;
		String name="leela";
		int salary =450000;	
		                                      //insert into employee values(8,'Leela',450000)=>Using String to combine=>concate()
		String query="insert into employee values("+ id + ",'" + name + "'," + salary + ");"; //values are directly in hot coding
		
	   Connection con =DriverManager.getConnection(url, userName, passWord);
	   Statement st=con.createStatement();
	    int rows = st.executeUpdate(query);
	    
	   System.out.println("Number of rows affected: "+rows);
	   
	    con.close();
	   }
	  
	//Prepared Statement is used to easily access the variables 
	public static void insertPst()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		
		// Variable is Declared
		
		int id=9;
		String name="Kowsi";
		int salary =220000;	
		                            //insert into employee values(9,'Kowsi',22000)=>Using Prepared Statement
		String query="insert into employee value(?,?,?)";  //values are pst
		Connection con =DriverManager.getConnection(url, userName, passWord);
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setInt(3, salary);
		int rows =pst.executeUpdate();
		 System.out.println("Number of rows affected: "+rows);
		
	   
	    con.close();
	   }
	 //delete any one rows
	
	public static void delete()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		
		// Variable is Declared
		
		int id=7;
   // Changes only query => delete query
	String query="delete from employee where emp_id =" + id;
	
	   Connection con =DriverManager.getConnection(url, userName, passWord);
	   Statement st=con.createStatement();
	    int rows = st.executeUpdate(query);
	    
	   System.out.println("Number of rows affected: "+rows);
	   
	    con.close();
	   }
	
	public static void update()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		
		// Variable is Declared
		
	
   // query => Update query
		
	String query="update  employee  set salary =700000 where emp_id =1";
	
	   Connection con =DriverManager.getConnection(url, userName, passWord);
	   Statement st=con.createStatement();
	    int rows = st.executeUpdate(query);
	    
	   System.out.println("Number of rows affected: "+rows);
	   
	    con.close();
	   }
	 
//callable Statement =>Stored procedure
	 
	public static void sp()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="admin";
		String query="select * from employee";
		
	   Connection con =DriverManager.getConnection(url, userName, passWord);
	   CallableStatement cst = con.prepareCall("{call GetEmp()}");
	   ResultSet rs = cst.executeQuery();
	   
	   while(rs.next()) {
		    System.out.println("ID is " + rs.getInt(1));
		    System.out.println("Name is " + rs.getString(2));
		    System.out.println("Salary is " + rs.getInt(3));
		    }
	   con.close();
	}
	
//Calling Stored procedure with input parameter=> 
	//id =3 aha  mysql illa iruthu java ku display pandram
public static void sp2()throws Exception{
	String url="jdbc:mysql://localhost:3306/jdbcdemo";
	String userName="root";
	String passWord="admin";
	String query="select * from employee";
	int id =3;
   Connection con =DriverManager.getConnection(url, userName, passWord);
   CallableStatement cst = con.prepareCall("{call GetEmpById(?)}");
   cst.setInt(1, id);
   ResultSet rs = cst.executeQuery();
   
   while(rs.next()) {
	    System.out.println("ID is " + rs.getInt(1));
	    System.out.println("Name is " + rs.getString(2));
	    System.out.println("Salary is " + rs.getInt(3));
	    }
   con.close();
}
  //calling the stored procedure in out parameter

public static void sp3()throws Exception{
	String url="jdbc:mysql://localhost:3306/jdbcdemo";
	String userName="root";
	String passWord="admin";
	
	int id =4;
	
   Connection con =DriverManager.getConnection(url, userName, passWord);
   CallableStatement cst = con.prepareCall("{call GetNameById(?,?)}");
   cst.setInt(1, id);
   cst.registerOutParameter(2,Types.VARCHAR);
   cst.executeUpdate();
   
   System.out.println(cst.getString(2));
 
   con.close();
}
//commit and auto commit

public static void commitdemo()throws Exception{
	
	String url="jdbc:mysql://localhost:3306/jdbcdemo";
	String userName="root";
	String passWord="admin";
	
   String query1="update employee set salary = 3000000 where emp_id=1";
   String query2="update employee set salary = 8000000 where emp_id=2";
   
   Connection con =DriverManager.getConnection(url, userName, passWord);
   con.setAutoCommit(false);
   Statement st=con.createStatement();
   int rows1 = st.executeUpdate(query1);
   System.out.println("Number of rows affected: "+rows1);
   
   int rows2 = st.executeUpdate(query2);
   System.out.println("Number of rows affected: "+rows2);
   
  if(rows1>0 && rows2>0)
	  con.commit();
   con.close();
}


// batch processing=>combine to work quickly

public static void batchdemo()throws Exception{
	
	String url="jdbc:mysql://localhost:3306/jdbcdemo";
	String userName="root";
	String passWord="admin";
	
   String query1="update employee set salary = 8500000 where emp_id=1";
   String query2="update employee set salary = 8500000 where emp_id=2";
   String query3="update employee set salary = 8500000 where emp_id=3";
   String query4="update  employee set salary = 8500000 where emp_id=4";
   
   
   Connection con =DriverManager.getConnection(url, userName, passWord);
   con.setAutoCommit(false);
   Statement st=con.createStatement();
   st.addBatch(query1);
   st.addBatch(query2);
   st.addBatch(query3);
   st.addBatch(query4);
   
   int[] res = st.executeBatch();
   for(int i: res) {
	  if(i>0) 
		  continue;
	  
		  else
			  con.rollback();
   }
   con.commit();
   con.close();
}


}








