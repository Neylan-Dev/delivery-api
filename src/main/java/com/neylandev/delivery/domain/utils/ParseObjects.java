package com.neylandev.delivery.domain.utils;

import com.neylandev.delivery.application.request.ClientRequestDto;
import com.neylandev.delivery.application.request.OrderRequestDto;
import com.neylandev.delivery.application.request.ProductRequestDto;
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
        return modelMapper.map(order, OrderResponseDto.class);
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public static List<ProductResponseDto> listProductToListProductResponseDto(List<Product> products) {
        return products.stream().map(ParseObjects::productToProductResponseDto).collect(Collectors.toList());
    }

    public static StockResponseDto stockToStockResponseDto(Stock stock) {
        return modelMapper.map(stock, StockResponseDto.class);
    }

    public static List<StockResponseDto> listStockToListStockResponseDto(List<Stock> stocks) {
        return stocks.stream().map(ParseObjects::stockToStockResponseDto).collect(Collectors.toList());
    }

    public static Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto) {
        return modelMapper.map(orderRequestDto, Order.class);
    }

    public static List<OrderResponseDto> listOrderToListOrderResponseDto(List<Order> orders) {
        return orders.stream().map(ParseObjects::orderToOrderResponseDto).collect(Collectors.toList());
    }

    public static OccurrenceResponseDto occurrenceToOccurrenceResponseDto(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceResponseDto.class);
    }

    public static List<OccurrenceResponseDto> listOccurrenceToListOccurrenceResponseDto(List<Occurrence> occurrences) {
        return occurrences.stream().map(ParseObjects::occurrenceToOccurrenceResponseDto).collect(Collectors.toList());
    }
}
