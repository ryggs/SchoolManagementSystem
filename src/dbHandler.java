import java.sql.*;
import java.awt.Event;
import java.awt.*;
import javax.swing.*;

public class dbHandler {
    
    public String viewStudent(){
		Connection conn;
		StringBuffer sb = new StringBuffer();
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			
			Statement s1 = conn.createStatement();
			String s2 = "select * from students";
			
			ResultSet rs = s1.executeQuery(s2);
			
			while(rs.next())
			{
				int r = rs.getInt(1);
				String name = rs.getString(2);
                                String email = rs.getString(3);
                                long cno = rs.getLong(4);

				sb.append("Roll No.: "+ r +"   Name: "+ name+"    Email: "+email+"    Contact: "+cno+"\n");
			}
			rs.close();
			conn.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
		}
                //JOptionPane.showMessageDialog(new JDialog(),sb.toString());
		return sb.toString();
	}
    
    public void addStudent(int rollno, String name, String email, long cno){
		Connection conn;		 
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		 	conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		 	String sql = "insert into students values(?,?,?,?)";
		 	PreparedStatement ps = conn.prepareStatement(sql);
		 	ps.setInt(1, rollno);
			ps.setString(2, name);
                        ps.setString(3, email);
                        ps.setLong(4, cno);

			long r = ps.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " rows inserted ");

			conn.close();
		}
		catch(SQLException e){
		 	JOptionPane.showMessageDialog(new JDialog(), "issue "+e);
		}
	}
    
    public void updateStudent(int rollno, String name, String email, long cno){
		Connection conn;
                
                int r=0;
		try{

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");
			conn.setAutoCommit(true);
                        String sql = "update students set name=?, email=?, contact=? where roll_no=?";
			
                        PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(4, rollno);
                        ps.setString(2, email);
                        ps.setLong(3, cno);
			r= ps.executeUpdate();

                        conn.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(), "issue "+e);
		}
		JOptionPane.showMessageDialog(new JDialog(),"Updated Successfully!!!");
	}

	public void deleteStudent(int rollno){
		Connection conn;
		try{

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc123");
			String sql = "delete from students where roll_no=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rollno);
			int r= ps.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),"Record deleted Successfully!!!");
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(), "issue "+e);
		}
	}
        
        public boolean teacherLogin(String id, String pwd)
        {
            int id2;
            Connection conn;
            try{
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");


                String s2 = "select * from teacherLogin where id=? and password=?";
                PreparedStatement stmt = conn.prepareStatement(s2);

                try {
                    id2 = Integer.parseInt(id);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JDialog(), "Invalid Username");
                    return false;
                }
                
                stmt.setInt(1,id2);
                stmt.setString(2,pwd);

                ResultSet rs = stmt.executeQuery();

                if(rs.next())
                {
                    //JOptionPane.showMessageDialog(new JDialog(),"success");
                    return true;
                }
                rs.close();
                conn.close();
            }
            catch(SQLException e){
                    JOptionPane.showMessageDialog(new JDialog(),"Invalid Username or Passsword ");
            }
        return false;
        }
        
        public boolean studentLogin(String id, String pwd)
        {
            int id2;
            Connection conn;
            try{
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");


                String s2 = "select * from studentLogin where id=? and password=?";
                PreparedStatement stmt = conn.prepareStatement(s2);

                try {
                    id2 = Integer.parseInt(id);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JDialog(), "Invalid Username");
                    return false;
                }
                
                stmt.setInt(1,id2);
                stmt.setString(2,pwd);

                ResultSet rs = stmt.executeQuery();

                if(rs.next())
                {
                    return true;
                }
                rs.close();
                conn.close();
            }
            catch(SQLException e){
                    JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
            }
        return false;
        }
}