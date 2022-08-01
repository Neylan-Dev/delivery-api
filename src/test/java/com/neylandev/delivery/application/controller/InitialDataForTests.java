package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.ClientRequestDto;
import com.neylandev.delivery.application.request.DeliveryRequestDto;
import com.neylandev.delivery.application.request.OccurrenceRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.application.response.DeliveryResponseDto;
import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.repository.DeliveryRepository;
import com.neylandev.delivery.domain.repository.OccurrenceRepository;
import com.neylandev.delivery.domain.service.ClientService;
import com.neylandev.delivery.domain.service.DeliveryCreationService;
import com.neylandev.delivery.domain.service.OccurrenceService;

import static com.neylandev.delivery.DataForTests.VALID_DESCRIPTION;
import static com.neylandev.delivery.DataForTests.clientRequestDtoValid;
import static com.neylandev.delivery.DataForTests.deliveryRequestDtoValid;

public class InitialDataForTests {
    
    private ClientService clientService;
    private DeliveryCreationService deliveryCreationService;
    private OccurrenceService occurrenceService;
    private DeliveryRepository deliveryRepository;
    private ClientRepository clientRepository;
    private OccurrenceRepository occurrenceRepository;

    public InitialDataForTests(ClientService clientService){
        this.clientService = clientService;
    }

    public InitialDataForTests(ClientService clientService, ClientRepository clientRepository, DeliveryCreationService deliveryCreationService, DeliveryRepository deliveryRepository){
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.deliveryCreationService = deliveryCreationService;
        this.deliveryRepository = deliveryRepository;
    }

    public InitialDataForTests(ClientService clientService, ClientRepository clientRepository, DeliveryCreationService deliveryCreationService, DeliveryRepository deliveryRepository, OccurrenceService occurrenceService, OccurrenceRepository occurrenceRepository){
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.deliveryCreationService = deliveryCreationService;
        this.deliveryRepository = deliveryRepository;
        this.occurrenceService = occurrenceService;
        this.occurrenceRepository = occurrenceRepository;
    }

    public OccurrenceResponseDto createOccurrence(OccurrenceRequestDto occurrenceRequestDto) {
        var deliveryResponseDto = createDelivery(deliveryRequestDtoValid());
        return occurrenceService.registerOccurrence(deliveryResponseDto.getId(), VALID_DESCRIPTION);
    }

    public void deleteOccurrence() {
        occurrenceRepository.deleteAll();
        deliveryRepository.deleteAll();
        clientRepository.deleteAll();
    }

    public DeliveryResponseDto createDelivery(DeliveryRequestDto deliveryRequestDto) {
        var clientResponseDto = createClient(clientRequestDtoValid());
        deliveryRequestDto.setClientId(clientResponseDto.getId());
        return deliveryCreationService.save(deliveryRequestDto);
    }


    public void deleteDelivery() {
        deliveryRepository.deleteAll();
        clientRepository.deleteAll();
    }

    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {
        return clientService.create(clientRequestDto);
    }

    public void deleteClient(Long id) {
        clientService.delete(id);
    }
    
}
