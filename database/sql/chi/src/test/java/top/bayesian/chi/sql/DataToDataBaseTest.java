package top.bayesian.chi.sql;

import static org.junit.Assert.assertTrue;

import java.io.File;

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
        //MySqlConnection conn = new MySqlConnection("192.168.176.130:3306", "test", "root", "Qwerasdf123.");

        DataToDataBase ddd = new DataToDataBase("192.168.176.130:3306", "test", "root", "Qwerasdf123.");
        ddd.close();
    }
}
