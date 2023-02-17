//package com.neylandev.delivery.application.controller;
//
//import com.neylandev.delivery.application.request.ClientRequestDto;
//import com.neylandev.delivery.application.request.OccurrenceRequestDto;
//import com.neylandev.delivery.application.request.OrderRequestDto;
//import com.neylandev.delivery.application.response.ClientResponseDto;
//import com.neylandev.delivery.application.response.OccurrenceResponseDto;
//import com.neylandev.delivery.application.response.OrderResponseDto;
//import com.neylandev.delivery.domain.repository.ClientRepository;
//import com.neylandev.delivery.domain.repository.OccurrenceRepository;
//import com.neylandev.delivery.domain.repository.OrderRepository;
//import com.neylandev.delivery.domain.service.ClientService;
//import com.neylandev.delivery.domain.service.OccurrenceService;
//import com.neylandev.delivery.domain.service.OrderService;
//
//import static com.neylandev.delivery.DataForTests.*;
//
//public class InitialDataForIntegrationTests {
//
//    private final ClientService clientService;
//    private OrderService orderService;
//    private OccurrenceService occurrenceService;
//    private OrderRepository orderRepository;
//    private ClientRepository clientRepository;
//    private OccurrenceRepository occurrenceRepository;
//
//    public InitialDataForIntegrationTests(ClientService clientService){
//        this.clientService = clientService;
//    }
//
//    public InitialDataForIntegrationTests(ClientService clientService, ClientRepository clientRepository, OrderService orderService, OrderRepository orderRepository){
//        this.clientService = clientService;
//        this.clientRepository = clientRepository;
//        this.orderService = orderService;
//        this.orderRepository = orderRepository;
//    }
//
//    public InitialDataForIntegrationTests(ClientService clientService, ClientRepository clientRepository, OrderService orderService, OrderRepository orderRepository, OccurrenceService occurrenceService, OccurrenceRepository occurrenceRepository){
//        this.clientService = clientService;
//        this.clientRepository = clientRepository;
//        this.orderService = orderService;
//        this.orderRepository = orderRepository;
//        this.occurrenceService = occurrenceService;
//        this.occurrenceRepository = occurrenceRepository;
//    }
//
//    public OccurrenceResponseDto createOccurrence(OccurrenceRequestDto occurrenceRequestDto) {
//        var deliveryResponseDto = createDelivery(deliveryRequestDtoValid());
//        return occurrenceService.registerOccurrence(deliveryResponseDto.getId(), VALID_DESCRIPTION);
//    }
//
//    public void deleteOccurrence() {
//        occurrenceRepository.deleteAll();
//        orderRepository.deleteAll();
//        clientRepository.deleteAll();
//    }
//
//    public OrderResponseDto createDelivery(OrderRequestDto orderRequestDto) {
//        var clientResponseDto = createClient(clientRequestDtoValid());
//        orderRequestDto.setClientId(clientResponseDto.getId());
//        return orderService.save(orderRequestDto);
//    }
//
//
//    public void deleteDelivery() {
//        orderRepository.deleteAll();
//        clientRepository.deleteAll();
//    }
//
//    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {
//        return clientService.create(clientRequestDto);
//    }
//
//    public void deleteClient(Long id) {
//        clientService.delete(id);
//    }
//
//}
