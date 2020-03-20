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
	String getIdiomsByFirstpinyin(String firstPinyin);
	@WebMethod 
	String getIdiomsByLastpinyin(String lastPinyin);
	@WebMethod
	String getRandomIdiomByFirstPinyin(String firstPinyin);
	@WebMethod
	String getRandomIdiomByLastPinyin(String lastPinyin);
}