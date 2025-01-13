package javatpoint.microservice.demo;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ExchangeValueDto {
	

	private Long id;
	  @Size(min = 2, max = 4, message = "please provide correct country code.")
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private int port;
	
	public ExchangeValue toUser() {
		return new ExchangeValue(id,from,to,conversionMultiple);
	  }

}
