package top.bayesian.dictionary.idiom;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebService;
import top.bayesian.sql.MySqlConnection;
//Service Implementation Bean

@WebService(endpointInterface = "top.bayesian.dictionary.idiom.Idiom")
public class IdiomImpl implements Idiom {
	String url = "localhost:3306";
	String dbName = "chinese_dictionary";
	String user = "dictionary";
	String password = "5ePTosKZbLKCERfB";
	MySqlConnection conn = new MySqlConnection(url, dbName, user, password);

	@Override
	public String getRandomIdiom() {
		ResultSet resultSet;
		String result;
		int min = 0, max = 1, randId = 0;
		String sql = "sql";
		try {
			sql = "SELECT MIN(idiom_id),MAX(idiom_id) FROM idiom";
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.next();
			min = resultSet.getInt("MIN(idiom_id)");
			max = resultSet.getInt("MAX(idiom_id)");
			randId = (int) (Math.random() * (max - min + 1) + min);
			sql = "SELECT idiom From idiom WHERE idiom_id = " + randId;
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.next();
			result = resultSet.getString("idiom");
		} catch (SQLException e) {
			result = "Fail ";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getIdiomsByFirstpinyin(String firstPinyin) {
		ResultSet resultSet;
		StringBuilder sb = new StringBuilder();
		String sql;
		try {
			firstPinyin = removeTone(firstPinyin);
			sql = "SELECT * FROM idiom WHERE first_pinyin = '" + firstPinyin + "'";
			resultSet = conn.sqlStatementQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {
					sb.append(resultSet.getString("idiom")).append(" ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String getIdiomsByLastpinyin(String lastPinyin) {
		ResultSet resultSet;
		StringBuilder sb = new StringBuilder();
		String sql;
		try {
			lastPinyin = removeTone(lastPinyin);
			sql = "SELECT * FROM idiom WHERE last_pinyin = '" + lastPinyin + "'";
			resultSet = conn.sqlStatementQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {
					sb.append(resultSet.getString("idiom")).append(" ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String getRandomIdiomByFirstPinyin(String firstPinyin) {
		ResultSet resultSet;
		String sql;
		String result = new String();
		int size, randIndex = 0;
		try {
			firstPinyin = removeTone(firstPinyin);
			sql = "SELECT * FROM idiom WHERE first_pinyin = '" + firstPinyin + "'";
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.last();
			size = resultSet.getRow();
			if (size > 0) {
				randIndex = (int) (size * Math.random());
				resultSet.beforeFirst();
				for (int i = 0; i <= randIndex; ++i) {
					resultSet.next();
				}
				result += resultSet.getString("idiom");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getRandomIdiomByLastPinyin(String lastPinyin) {
		ResultSet resultSet;
		String sql;
		String result = new String();
		int size, randIndex = 0;
		try {
			lastPinyin = removeTone(lastPinyin);
			sql = "SELECT * FROM idiom WHERE last_pinyin = '" + lastPinyin + "'";
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.last();
			size = resultSet.getRow();
			if (size > 0) {
				randIndex = (int) (size * Math.random());
				resultSet.beforeFirst();
				for (int i = 0; i <= randIndex; ++i) {
					resultSet.next();
				}
				result = resultSet.getString("idiom");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findIdiom(String idiom) {
		ResultSet resultSet;
		String sql = "SELECT * FROM `idiom` WHERE idiom ='" + idiom + "'";
		StringBuilder result = new StringBuilder();
		try {
			resultSet = conn.sqlStatementQuery(sql);
			resultSet.last();
			if (resultSet.getRow() != 0) {
				resultSet.first();
				result.append("{");
				result.append("\"idiom\":\"").append(resultSet.getString("idiom")).append("\",");
				result.append("\"derivation\":\"").append(resultSet.getString("derivation")).append("\",");
				result.append("\"explanation\":\"").append(resultSet.getString("explanation")).append("\",");
				result.append("\"example\":\"").append(resultSet.getString("example")).append("\",");
				result.append("\"pinyin\":\"").append(resultSet.getString("pinyin")).append("\",");
				result.append("\"abbreviation\":\"").append(resultSet.getString("abbreviation")).append("\"");
				result.append("}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public String findJielongIdioms(String idiom) {
		String result = new String();
		if (findIdiom(idiom).equals(""))
			;
		else {
			ResultSet resultSet;
			String sql = "SELECT * FROM `idiom` WHERE idiom ='" + idiom + "'";
			try {
				resultSet = conn.sqlStatementQuery(sql);
				resultSet.next();
				String lastPinyin = resultSet.getString("last_pinyin");
				result = getIdiomsByFirstpinyin(lastPinyin);
			} catch (SQLException e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}

	@Override
	public String findJielongIdiom(String idiom) {
		String result = new String();
		if (findIdiom(idiom).equals(""))
			;
		else {
			ResultSet resultSet;
			String sql = "SELECT * FROM `idiom` WHERE idiom ='" + idiom + "'";
			try {
				resultSet = conn.sqlStatementQuery(sql);
				resultSet.next();
				String lastPinyin = resultSet.getString("last_pinyin");
				result = getRandomIdiomByFirstPinyin(lastPinyin);
			} catch (SQLException e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}

	@Override
	public String getFirstPinyin(String idiom){
		String result = new String();
		if (idiom==""||findIdiom(idiom).equals(""))
			;
		else {
			ResultSet resultSet;
			String sql = "SELECT `first_pinyin` FROM `idiom` WHERE idiom ='" + idiom + "'";
			try {
				resultSet = conn.sqlStatementQuery(sql);
				resultSet.next();
				result = resultSet.getString("first_pinyin");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String getLastPinyin(String idiom){
		String result = "";
		if (idiom==""||findIdiom(idiom).equals(""))
			;
		else {
			ResultSet resultSet;
			String sql = "SELECT `last_pinyin` FROM `idiom` WHERE idiom ='" + idiom + "'";
			try {
				resultSet = conn.sqlStatementQuery(sql);
				resultSet.next();
				result = resultSet.getString("last_pinyin");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String checkJielong(String idiom1, String idiom2){
		String result = "";
		String lastOfIdiom1 = getLastPinyin(idiom1);
		String firstOfIdiom2 = getFirstPinyin(idiom2);
		if(lastOfIdiom1.equals("")||firstOfIdiom2.equals("")){
			result = "";
		}
		else{
			result = lastOfIdiom1.equals(firstOfIdiom2)?"1":"";
		}
		return result;
	}


	private String removeTone(String pinyin) {
		String result = pinyin;
		result = result.replace('ā', 'a');
		result = result.replace('á', 'a');
		result = result.replace('ǎ', 'a');
		result = result.replace('à', 'a');
		result = result.replace('ō', 'o');
		result = result.replace('ó', 'o');
		result = result.replace('ǒ', 'o');
		result = result.replace('ò', 'o');
		result = result.replace('ē', 'e');
		result = result.replace('é', 'e');
		result = result.replace('ě', 'e');
		result = result.replace('è', 'e');
		result = result.replace('ī', 'i');
		result = result.replace('í', 'i');
		result = result.replace('ǐ', 'i');
		result = result.replace('ì', 'i');
		result = result.replace('ū', 'u');
		result = result.replace('ú', 'u');
		result = result.replace('ǔ', 'u');
		result = result.replace('ù', 'u');
		result = result.replace('ǖ', 'u');
		result = result.replace('ǘ', 'u');
		result = result.replace('ǚ', 'u');
		result = result.replace('ǜ', 'u');
		return result;
	}

	@Override
	protected void finalize() throws IOException {
		conn.close();
	}

}