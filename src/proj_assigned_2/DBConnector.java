
package proj_assigned_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnector 
{
    static Connection c ;
    static Statement st ;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver") ;
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ashuthesmartest", "ashutosha") ;
            st = c.createStatement(); 
        }    
        catch(Exception ex)
        {
         JOptionPane.showMessageDialog(null, "Database Error");
        }
    }
}