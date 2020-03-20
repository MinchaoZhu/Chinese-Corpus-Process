# 中文语料库
建立SOAP协议的中文预料Web Service.

## 语料库
中华新华字典数据库。收录包括 14032 条歇后语，16142 个汉字，264434 个词语，31648 个成语。

## 功能
### 0. 数据录入数据库
branch sql: 
找一个带main入口的java程序载入DataToDatabase 类
### 1. 成语
Branch idiom:
```
mvn package
```
```java
/**
    * 随机获取一个成语;
    * 
    * @return String: 一个成语的字符串;
    */
public String getRandomIdiom();

/**
    * 根据首字拼音获取数据库内所有符合的成语;
    * 
    * @param arg0 String: 首字拼音, 可以带声调;
    * @return String: 多个成语组成的字符串, 以空格分开;
    */
public String getIdiomsByFirstpinyin(String arg0);

/**
    * 根据最后一个字的拼音获取数据库内所有符合的成语;
    * 
    * @param arg0 String: 尾字拼音, 可以带声调;
    * @return String: 多个成语组成的字符串, 以空格分开;
    */
public String getIdiomsByLastpinyin(String arg0);

/**
    * 根据首字拼音获取数据库内随机一个成语;
    * 
    * @param arg0 String: 首字拼音, 可以带声调;
    * @return String: 一个成语的字符串;
    */
public String getRandomIdiomByFirstPinyin(String arg0);

/**
    * 根据尾字拼音获取数据库内随机一个成语;
    * 
    * @param arg0 String: 尾字拼音, 可以带声调;
    * @return String: 一个成语的字符串;
    */
public String getRandomIdiomByLastPinyin(String arg0);

/**
    * 查询一个成语;
    * 
    * @param arg0 String: 成语字符串;
    * @return String: 若不存在, 则返回空字符串; 若存在, 则返回成语信息的JSON字符串;
    */
public String findIdiom(String arg0);

/**
    * 获取可以和输入成语形成接龙的所有成语;
    * 
    * @param arg0 String: 成语字符串;
    * @return String: 所有可以形成接龙的成语, 以空格分隔. 若无则返回空字符串;
    */
public String findJielongIdioms(String arg0);

/**
    * 获取随机一个和输入成语形成接龙的成语;
    * 
    * @param arg0 String: 成语字符串;
    * @return String: 一个可以形成接龙的成语, 若无则返回空字符串;
    */
public String findJielongIdiom(String arg0);

```

## TODO
- [x] 数据库导入
- [x] Idiom 成语
- [ ] Xiehouyu 歇后语
- [ ] word 汉字
- [ ] ci ciyu