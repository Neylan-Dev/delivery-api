package com.neylandev.delivery.domain.utils;

import com.neylandev.delivery.application.request.*;
import com.neylandev.delivery.application.response.*;
import com.neylandev.delivery.domain.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParseObjects {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ClientResponseDto clientToClientResponseDto(Client client) {
        return modelMapper.map(client, ClientResponseDto.class);
    }

    public static Client clientRequestDtoToClient(ClientRequestDto clientRequestDto) {
        return modelMapper.map(clientRequestDto, Client.class);
    }

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        return modelMapper.map(productRequestDto, Product.class);
    }

    public static List<ClientResponseDto> listClientToListClientResponseDto(List<Client> clients) {
        return clients.stream().map(ParseObjects::clientToClientResponseDto).collect(Collectors.toList());
    }

    public static OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        orderResponseDto.setOrderItemResponseDtos(listOrderItemToListOrderItemResponseDto(order.getOrderItems()));
        orderResponseDto.setOccurrenceResponseDtos(listOccurrenceToListOccurrenceResponseDto(order.getOccurrences()));
        orderResponseDto.setPaymentResponseDtos(listPaymentToListPaymentResponseDto(order.getPayments()));
        return orderResponseDto;
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public static List<ProductResponseDto> listProductToListProductResponseDto(List<Product> products) {
        return products.stream().map(ParseObjects::productToProductResponseDto).collect(Collectors.toList());
    }

    public static StockResponseDto stockToStockResponseDto(Stock stock) {
        StockResponseDto stockResponseDto = modelMapper.map(stock, StockResponseDto.class);
        stockResponseDto.setProductResponseDto(productToProductResponseDto(stock.getProduct()));
        return stockResponseDto;
    }

    public static List<StockResponseDto> listStockToListStockResponseDto(List<Stock> stocks) {
        return stocks.stream().map(ParseObjects::stockToStockResponseDto).collect(Collectors.toList());
    }

    public static Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto) {
        return modelMapper.map(orderRequestDto, Order.class);
    }

    public static Client clientResponseDtoToClient(ClientResponseDto clientResponseDto) {
        return modelMapper.map(clientResponseDto, Client.class);
    }

    public static Payment paymentRequestDtoToPayment(PaymentRequestDto paymentRequestDto) {
        return modelMapper.map(paymentRequestDto, Payment.class);
    }

    public static OrderItem orderItemRequestDtoToOrderItem(OrderItemRequestDto orderItemRequestDto) {
        return modelMapper.map(orderItemRequestDto, OrderItem.class);
    }

    public static OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem) {
        OrderItemResponseDto orderItemResponseDto = modelMapper.map(orderItem, OrderItemResponseDto.class);
        orderItemResponseDto.setProductResponseDto(productToProductResponseDto(orderItem.getProduct()));
        return orderItemResponseDto;
    }

    public static PaymentResponseDto paymentToPaymentResponseDto(Payment payment) {
        return modelMapper.map(payment, PaymentResponseDto.class);
    }

    public static List<OrderResponseDto> listOrderToListOrderResponseDto(List<Order> orders) {
        return orders.stream().map(ParseObjects::orderToOrderResponseDto).collect(Collectors.toList());
    }
    
    public static List<OrderItemResponseDto> listOrderItemToListOrderItemResponseDto(List<OrderItem> orderItems) {
        return orderItems.stream().map(ParseObjects::orderItemToOrderItemResponseDto).collect(Collectors.toList());
    }

    public static List<PaymentResponseDto> listPaymentToListPaymentResponseDto(List<Payment> payments) {
        return payments.stream().map(ParseObjects::paymentToPaymentResponseDto).collect(Collectors.toList());
    }

    public static OccurrenceResponseDto occurrenceToOccurrenceResponseDto(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceResponseDto.class);
    }

    public static List<OccurrenceResponseDto> listOccurrenceToListOccurrenceResponseDto(List<Occurrence> occurrences) {
        return occurrences.stream().map(ParseObjects::occurrenceToOccurrenceResponseDto).collect(Collectors.toList());
    }
}
