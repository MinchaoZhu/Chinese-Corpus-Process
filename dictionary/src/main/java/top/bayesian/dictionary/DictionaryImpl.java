package top.bayesian.dictionary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import top.bayesian.sql.MySqlConnection;
//Service Implementation Bean

@WebService(endpointInterface = "top.bayesian.dictionary.Dictionary")
public class DictionaryImpl implements Dictionary {
	String url = "localhost:3306";
	String dbName = "chinese_dictionary";
	String user = "dictionary";
	String password = "5ePTosKZbLKCERfB";

	@Override
	public String getRandomIdiom() {
		MySqlConnection conn = new MySqlConnection(url, dbName, user, password);
		ResultSet resultSet;
		String result;
		int min = 0, max=1, randId=0;
		String sql = "sql";
		try {
		    sql = "SELECT MIN(idiom_id),MAX(idiom_id) FROM idiom";
			resultSet = conn.sqlStatementQuery(sql);resultSet.next();
			min = resultSet.getInt("MIN(idiom_id)");
			max = resultSet.getInt("MAX(idiom_id)");
			randId = (int)(Math.random()*(max-min+1)+min);
			sql = "SELECT idiom From idiom WHERE idiom_id = "+randId;
			resultSet = conn.sqlStatementQuery(sql);resultSet.next();
			result = resultSet.getString("idiom");
		} catch (SQLException e) {
			result = "Fail ";
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getIdiomsByFirstpinyin(String firstPinyin) {
		MySqlConnection conn = new MySqlConnection(url, dbName, user, password);
		ResultSet resultSet;
		StringBuilder sb = new StringBuilder();
		String sql;
		try{
			firstPinyin = removeTone(firstPinyin);
			sql = "SELECT * FROM idiom WHERE first_pinyin = '" + firstPinyin +"'";
			resultSet = conn.sqlStatementQuery(sql);
			if(resultSet!=null){
				while(resultSet.next()){
					sb.append(resultSet.getString("idiom")).append(" ");
				}
			}
		}catch(SQLException e){e.printStackTrace();}
		return sb.toString();
	}
	
	@Override
	public String getIdiomsByLastpinyin(String lastPinyin) {
		MySqlConnection conn = new MySqlConnection(url, dbName, user, password);
		ResultSet resultSet;
		StringBuilder sb = new StringBuilder();
		String sql;
		try{
			lastPinyin = removeTone(lastPinyin);
			sql = "SELECT * FROM idiom WHERE last_pinyin = '" + lastPinyin +"'";
			resultSet = conn.sqlStatementQuery(sql);
			if(resultSet!=null){
				while(resultSet.next()){
					sb.append(resultSet.getString("idiom")).append(" ");
				}
			}
		}catch(SQLException e){e.printStackTrace();}
		return sb.toString();
	}

	@Override
	public String getRandomIdiomByFirstPinyin(String firstPinyin){
		MySqlConnection conn = new MySqlConnection(url, dbName, user, password);
		ResultSet resultSet;
		String sql;
		String result = new String();
		int size, randIndex=0;
		try{
			firstPinyin = removeTone(firstPinyin);
			sql = "SELECT * FROM idiom WHERE first_pinyin = '" + firstPinyin +"'";
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.last();
			size = resultSet.getRow();
			if(size>0){
				randIndex = (int)(size*Math.random());
				resultSet.beforeFirst();
				for(int i = 0; i<=randIndex;++i){
					resultSet.next();
				}
				result += resultSet.getString("idiom");
			}
		}catch(SQLException e){e.printStackTrace();}
		return result;
	}

	@Override
	public String getRandomIdiomByLastPinyin(String lastPinyin){
		MySqlConnection conn = new MySqlConnection(url, dbName, user, password);
		ResultSet resultSet;
		String sql;
		String result = new String();
		int size, randIndex=0;
		try{
			lastPinyin = removeTone(lastPinyin);
			sql = "SELECT * FROM idiom WHERE last_pinyin = '" + lastPinyin +"'";
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.last();
			size = resultSet.getRow();
			if(size>0){
				randIndex = (int)(size*Math.random());
				resultSet.beforeFirst();
				for(int i = 0; i<=randIndex;++i){
					resultSet.next();
				}
				result = resultSet.getString("idiom");
			}
		}catch(SQLException e){e.printStackTrace();}
		return result;
	}


	private String removeTone(String pinyin){
        String result = pinyin;
        result = result.replace('ā', 'a');result = result.replace('á', 'a');result = result.replace('ǎ', 'a');result = result.replace('à', 'a');
        result = result.replace('ō', 'o');result = result.replace('ó', 'o');result = result.replace('ǒ', 'o');result = result.replace('ò', 'o');
        result = result.replace('ē', 'e');result = result.replace('é', 'e');result = result.replace('ě', 'e');result = result.replace('è', 'e');
        result = result.replace('ī', 'i');result = result.replace('í', 'i');result = result.replace('ǐ', 'i');result = result.replace('ì', 'i');
        result = result.replace('ū', 'u');result = result.replace('ú', 'u');result = result.replace('ǔ', 'u');result = result.replace('ù', 'u');
        result = result.replace('ǖ', 'u');result = result.replace('ǘ', 'u');result = result.replace('ǚ', 'u');result = result.replace('ǜ', 'u');
        return result;
    }

}