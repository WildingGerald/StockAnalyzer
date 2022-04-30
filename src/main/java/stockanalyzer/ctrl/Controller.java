package stockanalyzer.ctrl;

import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;
import yahooApi.beans.YahooResponse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class Controller {
		
	public String process(String ticker) {
		System.out.println("Start process");

		StringBuilder output = new StringBuilder();
		try {
			//1) Daten laden
			QuoteResponse quoteResponse = getData(ticker).getQuoteResponse();
			quoteResponse.getResult().stream().forEach(quote -> output.append(quote.getAsk()+"\n"));
			//2) Daten Analyse
			output.append("highest quote: " + highestValue(quoteResponse));
			output.append("\naverage quote: " + averageValue(quoteResponse));
			output.append("\namount of quotes: " + amountOfValues(quoteResponse));
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			closeConnection();
		}
		return output.toString();
	}
	

	public YahooResponse getData(String searchString) throws Exception{
		YahooFinance yahooFinance = new YahooFinance();
		return yahooFinance.getCurrentData(Arrays.asList(searchString.split(",")));
	}

	public double highestValue(QuoteResponse quoteResponse) throws Exception{
		return (quoteResponse.getResult().stream().max(Comparator.comparing(Result::getAsk))).get().getAsk();
	}

	public Double averageValue(QuoteResponse quoteResponse) throws Exception{
		return quoteResponse.getResult().stream().mapToDouble(Result::getAsk).average().orElseThrow(() -> new Exception("Calculate Average failed"));
	}

	public long amountOfValues(QuoteResponse quoteResponse) throws Exception{
		return quoteResponse.getResult().stream().count();
	}

	public void closeConnection() {

	}
}
