package top.bayesian.chi.sql;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataToDataBase 
{
    private MySqlConnection conn;
    
    public DataToDataBase(String url, String dbName, String user, String password, String path){
        conn = new MySqlConnection(url, dbName, user, password);
        conn.sqlScriptFileExecute("sql/init.sql");
        xiehouyuToDataBase(path+"/xiehouyu.json");
        idiomToDataBase(path+"/idiom.json");
        wordToDataBase(path+"/word.json");
        ciToDataBase(path+"/ci.json");
        conn.close();
    }

    

    private void ciToDataBase(String path){
        System.out.println(path);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8))
        {

            String sql;
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray ciList = (JSONArray) obj;

            for(int i = 0; i<ciList.size(); ++i){
                JSONObject jo = (JSONObject)ciList.get(i);
                sql = "INSERT INTO `ci` (`ci_id`, `ci`, `explanation`) VALUES (NULL,\""+ jo.get("ci") +"\",\"" + jo.get("explanation")+"\")";
                
                conn.sqlStatementExecute(sql);
                //System.out.println(sql);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void wordToDataBase(String path){
        System.out.println(path);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8))
        {

            String sql;
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray ciList = (JSONArray) obj;
            for(int i = 0; i<ciList.size(); ++i){
                JSONObject jo = (JSONObject)ciList.get(i);
                sql = "INSERT INTO `word` (`word_id`, `word`, `oldword`, `strokes`, `pinyin`, `radicals`, `explanation`, `more`) VALUES (NULL,\""+
                jo.get("word")+"\",\""+jo.get("oldword")+"\",\""+jo.get("strokes")+"\",\""+jo.get("pinyin")+"\",\""+jo.get("radicals")+"\",\""+jo.get("explanation")+"\",\""+jo.get("more")+"\")";
                
                //System.out.println(sql);
                conn.sqlStatementExecute(sql);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void idiomToDataBase(String path){
        System.out.println(path);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8))
        {

            String sql;
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray ciList = (JSONArray) obj;

            for(int i = 0; i<ciList.size(); ++i){
                JSONObject jo = (JSONObject)ciList.get(i);
                String pinyin = jo.get("pinyin").toString();
                String firstPinyin = getFirstPinyin(pinyin);
                String lastPinyin = getLastPinyin(pinyin);
                //System.out.println(firstPinyin);
                sql = "INSERT INTO `idiom` (`idiom_id`, `idiom`, `derivation`, `explanation`, `example`, `pinyin`, `first_pinyin`, `last_pinyin`, `abbreviation`) VALUES (NULL, \""+
                jo.get("word")+"\",\""+jo.get("derivation")+"\",\""+jo.get("explanation")+"\",\""+jo.get("example")+"\",\""+jo.get("pinyin")+"\",\""+firstPinyin+"\",\""+lastPinyin+"\",\""+jo.get("abbreviation") +"\")";

                conn.sqlStatementExecute(sql);
                //System.out.println(sql);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private String getFirstPinyin(String pinyin){
        String result;
        String[] splited = pinyin.split("\\s+");
        result = splited[0];
        return removeTone(result);
    }

    private String getLastPinyin(String pinyin){
        String result;
        String[] splited = pinyin.split("\\s+");
        result = splited[splited.length-1];
        return removeTone(result);
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

    private void xiehouyuToDataBase(String path){
        System.out.println(path);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8))
        {

            String sql;
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray ciList = (JSONArray) obj;

            for(int i = 0; i<ciList.size(); ++i){
                JSONObject jo = (JSONObject)ciList.get(i);
                sql = "INSERT INTO `xiehouyu` (`xiehouyu_id`, `riddle`, `answer`) VALUES (NULL, \"" + jo.get("riddle")  +"\",\""+ jo.get("answer") + "\")";
                
                conn.sqlStatementExecute(sql);
                //System.out.println(sql);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        conn.close();
    }
}
