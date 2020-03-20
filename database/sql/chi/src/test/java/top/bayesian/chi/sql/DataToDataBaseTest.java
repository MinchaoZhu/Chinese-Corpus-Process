package top.bayesian.chi.sql;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DataToDataBaseTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        // Scanner s = new Scanner(System.in); 
        // System.out.println("Please enter url, dbName, user, password and path");
        // String url = "23.101.2.0:3306";
        String url = "192.168.176.130:3306";
        String dbName = "chinese_dictionary";
        String user = "root";
        String password = "Qwerasdf123.";
        String path = "C:/Users/Edward/OneDrive/Desktop/test"; 
        DataToDataBase runner = new DataToDataBase(url, dbName, user, password, path);
    
    }
}
