package com.medicalstoreapp.ordermodule.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.medicalstoreapp.ordermodule.dto.OrderDetails;
import com.medicalstoreapp.ordermodule.dto.StockDetails;
import com.medicalstoreapp.ordermodule.entities.CreatedOrder;

@Component
public class OrderUtil {

	@Autowired
	private DateConverter dateConverter;
	
	@Value("${stock.baseurl}")
	private String stockBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Double fetchStockPrice(Long stockId) {
		StockDetails desired = fetchPrice(stockId);//restTemplate required
		
		return desired.getPrice();
	}
	
	public OrderDetails toOrderDetails(CreatedOrder order) {
		OrderDetails desired = new OrderDetails();
		desired.setOrderId(order.getOrderId());
		desired.setOrderPrice(order.getOrderPrice());
		desired.setOrderDate(dateConverter.toText(order.getOrderDate()));
		
		return desired;
	}
	
	public List<OrderDetails> toOrderDetailsList(Collection<CreatedOrder> orders)
	{
		return orders.stream().map(n -> toOrderDetails(n)).collect(Collectors.toList());
	}
	
	public StockDetails fetchPrice(Long stockId) {
		String url = stockBaseUrl+"/stocks/bystockid/"+stockId;
		StockDetails details = restTemplate.getForObject(url, StockDetails.class);
		return details;
	}
	
}
