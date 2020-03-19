package top.bayesian.chi.sql;

import java.nio.charset.Charset;
import java.sql.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * Wrapper Class to call a sql query
 * + MySqlConnection(String url, String dbName, String user, String password) // Constructor Create a connection
 * + ResultSet sqlStatementQuery(String sql) // execute a read query and return mysql query result
 * + void sqlStatementExecute(String sql) //execute a write query
 * + void sqlScriptFileExecute(String file) //execure a sql write script
 * + void close() // close connection
 */
public class MySqlConnection 
{
    private Connection conn;
    private String driver;
    private Statement stmt = null;
    private ScriptRunner runner;
    private String link;
    private String user;
    private String password;

    public MySqlConnection(String url, String dbName, String user, String password){
        String link = "jdbc:mysql://" + url + "/" + dbName+  "?autoReconnect=true&useSSL=false";
        this.driver = "com.mysql.cj.jdbc.Driver";
        createConnection(link, user, password);
        this.link = link;this.user = user;this.password = password;
    }

    private void createConnection(String link, String user, String password){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(link, user, password);
            stmt = conn.createStatement();
        } catch (Exception e) {e.printStackTrace();}
    }

    private void closeConnection(){
        try{
            stmt.close();
            conn.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public ResultSet sqlStatementQuery(String sql){
        try {
            return stmt.executeQuery(sql);
        }catch(Exception e){e.printStackTrace();return null;}
    }

    public void sqlStatementExecute(String sql){
        try {
            stmt.executeUpdate(sql);
        }catch(Exception e){e.printStackTrace();}
    }

    public void sqlScriptFileExecute(String file){
        try{
            this.runner = new ScriptRunner(conn);
            Resources.setCharset(Charset.forName("UTF-8"));
            runner.setLogWriter(null);
            runner.runScript(Resources.getResourceAsReader(file));
            runner.closeConnection();
            close();
            createConnection(link, user, password);
        }catch(Exception e){e.printStackTrace();}
    }

    public void close(){
        closeConnection();
    }
}
