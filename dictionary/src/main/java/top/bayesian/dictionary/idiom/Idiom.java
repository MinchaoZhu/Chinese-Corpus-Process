package top.bayesian.dictionary.idiom;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface Idiom {

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

	@WebMethod
	String findIdiom(String idiom);

	@WebMethod
	String findJielongIdioms(String idiom);

	@WebMethod
	String findJielongIdiom(String idiom);

}