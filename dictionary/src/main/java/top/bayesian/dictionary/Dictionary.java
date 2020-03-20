package top.bayesian.dictionary;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface Dictionary{
	
	@WebMethod 
	String getRandomIdiom();
	@WebMethod 
	String getIdiomByFirstPinyin(String firstPinYin);
	@WebMethod 
	String getIdiomByLastPinyin(String lastPinYin);
}