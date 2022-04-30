package stockanalyzer.ctrl;

import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;

import java.util.Arrays;
import java.util.OptionalDouble;

public class Controller {
		
	public void process(String ticker) {
		System.out.println("Start process");

		//TODO implement Error handling 

		//TODO implement methods for

		try{
			//1) Daten laden
			QuoteResponse quoteResponse = getData(ticker).getQuoteResponse();
			quoteResponse.getResult().stream().forEach(quote -> System.out.println(quote.getAsk()));
			//2) Daten Analyse
			System.out.println("highest quote: " + highestValue(quoteResponse));
			System.out.println("average quote: " + averageValue(quoteResponse));
			System.out.println("amount of quotes: " + amountOfValues(quoteResponse));
		}catch (Exception e){

		}finally {
			closeConnection();
		}
	}
	

	public YahooResponse getData(String searchString) {
		YahooFinance yahooFinance = new YahooFinance();
		YahooResponse response = yahooFinance.getCurrentData(Arrays.asList(searchString.split(",")));
		return response;
	}

public double highestValue(QuoteResponse quoteResponse){

		return (quoteResponse.getResult().stream().max((i,j) -> i.getAsk().compareTo(j.getAsk()))).get().getAsk();
	}

	public Double averageValue(QuoteResponse quoteResponse) throws Exception{
		return quoteResponse.getResult().stream().mapToDouble(result -> result.getAsk()).average().orElseThrow(() -> new Exception("fk you"));
	}

	public double amountOfValues(QuoteResponse quoteResponse) throws Exception{
		return quoteResponse.getResult().stream().mapToDouble(result -> result.getAsk()).average().orElseThrow(() -> new Exception("fk you"));
	}

	public void closeConnection() {
		
	}
}
