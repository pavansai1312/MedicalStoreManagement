package com.medicalstoreapp.ordermodule.service;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.medicalstoreapp.ordermodule.dto.AddOrderRequest;
import com.medicalstoreapp.ordermodule.dto.OrderDetails;

@Validated
public interface IOrderService {
	
	OrderDetails addOrder(@NotNull @Valid AddOrderRequest request);

	OrderDetails findOrderDetailsByOrderId(@NotNull @Min(1) Long id);

	List<OrderDetails> findAllOrders(@NotNull String startDate, @NotNull String endDate);

	List<OrderDetails> fetchAllOrders();

	Set<Long> findAllOrderIds(@NotNull String startDate,@NotNull String endDate);
	
	String deleteById(@NotNull @Min(1) Long id);

}
