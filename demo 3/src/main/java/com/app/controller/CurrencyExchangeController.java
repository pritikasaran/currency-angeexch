package com.app.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javatpoint.microservice.demo.ExchangeValue;
import javatpoint.microservice.demo.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}") // where {from} and {to} are path variable
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) // from map to USD
																									// and to map to INR
	{
		ExchangeValue exchangeValue=repository.findByFromAndTo(from,to);  
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	@GetMapping("/currency-exchange/getCurrency") // where {from} and {to} are path variable
	public List<ExchangeValue> retrieveAllExchangeValue() // from map to USD
																									// and to map to INR
	{
		List<ExchangeValue> exchangeValues=repository.findAll();  
		exchangeValues.forEach(i->i.setPort(Integer.parseInt(environment.getProperty("local.server.port"))));
		return exchangeValues;
	}

	@PostMapping("/currency-exchange/update") // where {from} and {to} are path variable
	public ExchangeValue updateExchangeValue(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal conversionMultiple) // from map to USD
																									// and to map to INR
	{
		ExchangeValue exchangeValue=repository.findByFromAndTo(from,to);  
		exchangeValue.setConversionMultiple(conversionMultiple);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		repository.save(exchangeValue);
		return exchangeValue;
	}
	
	@PostMapping("/currency-exchange/create") // where {from} and {to} are path variable
	public ExchangeValue createExchangeValue(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal conversionMultiple) // from map to USD
																									// and to map to INR
	{
		ExchangeValue exchangeValue=repository.findByFromAndTo(from,to);  
		if(exchangeValue!=null) {
			return updateExchangeValue(from,to,conversionMultiple);
		}
		exchangeValue = new ExchangeValue(10005l,from,to,conversionMultiple);
		exchangeValue.setConversionMultiple(conversionMultiple);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		repository.save(exchangeValue);
		return exchangeValue;
	}
	@PostMapping("/currency-exchange/delete") // where {from} and {to} are path variable
	public String deleteExchangeValue(@RequestParam String from, @RequestParam String to) // from map to USD
																									// and to map to INR
	{
		ExchangeValue exchangeValue=repository.findByFromAndTo(from,to);  
		if(exchangeValue!=null) {
			 repository.delete(exchangeValue);
			 return "deleted";
		}
		return "Does Not EXist";
	}
}
