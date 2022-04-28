package stockanalyzer.ctrl;

import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;

import java.util.Arrays;
import java.util.List;

public class Controller {
		
	public void process(String ticker) {
		System.out.println("Start process");

		//TODO implement Error handling 

		//TODO implement methods for
		//1) Daten laden
		YahooFinance yahooFinance = new YahooFinance();
		List<String> listOfTickers = Arrays.asList(ticker);
		YahooResponse response = yahooFinance.getCurrentData(listOfTickers);
		QuoteResponse quoteResponse = response.getQuoteResponse();
		quoteResponse.getResult().stream().forEach(quote -> System.out.println(quote.getAsk()));


		//2) Daten Analyse

	}
	

	public Object getData(String searchString) {

		
		return null;
	}


	public void closeConnection() {
		
	}
}
