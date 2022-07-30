package com.neylandev.delivery.domain.utils;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.neylandev.delivery.DataForTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseObjectsTest {

    @Test
    void clientToClientResponseDto() {
        var client = clientValid();
        var clientResponseDto = ParseObjects.clientToClientResponseDto(client);
        assertEquals(client.getId(), clientResponseDto.getId());
        assertEquals(client.getEmail(), clientResponseDto.getEmail());
        assertEquals(client.getTelephone(), clientResponseDto.getTelephone());
        assertEquals(client.getName(), clientResponseDto.getName());
    }

    @Test
    void clientRequestDtoToClient() {
        var clientRequestDto = clientRequestDtoValid();
        var client = ParseObjects.clientRequestDtoToClient(clientRequestDto);
        assertEquals(clientRequestDto.getEmail(), client.getEmail());
        assertEquals(clientRequestDto.getTelephone(), client.getTelephone());
        assertEquals(clientRequestDto.getName(), client.getName());
    }

    @Test
    void listClientToListClientResponseDto() {
        var client = clientValid();
        var clientResponseDtoList = ParseObjects.listClientToListClientResponseDto(Collections.singletonList(client));
        assertEquals(client.getId(), clientResponseDtoList.iterator().next().getId());
        assertEquals(client.getEmail(), clientResponseDtoList.iterator().next().getEmail());
        assertEquals(client.getTelephone(), clientResponseDtoList.iterator().next().getTelephone());
        assertEquals(client.getName(), clientResponseDtoList.iterator().next().getName());
    }

    @Test
    void deliveryToDeliveryResponseDto() {
        var delivery = deliveryValid();
        var deliveryResponseDto = ParseObjects.deliveryToDeliveryResponseDto(delivery);
        assertEquals(delivery.getId(), deliveryResponseDto.getId());
        assertEquals(delivery.getClient().getName(), deliveryResponseDto.getClientName());
        assertEquals(delivery.getClient().getEmail(), deliveryResponseDto.getClientEmail());
        assertEquals(delivery.getClient().getTelephone(), deliveryResponseDto.getClientTelephone());
        assertEquals(delivery.getClient().getId(), deliveryResponseDto.getClientId());
        assertEquals(delivery.getDeliveryStatus(), deliveryResponseDto.getDeliveryStatus());
        assertEquals(delivery.getRecipient().getName(), deliveryResponseDto.getRecipientName());
        assertEquals(delivery.getRecipient().getComplement(), deliveryResponseDto.getRecipientComplement());
        assertEquals(delivery.getRecipient().getNeighborhood(), deliveryResponseDto.getRecipientNeighborhood());
        assertEquals(delivery.getRecipient().getNumber(), deliveryResponseDto.getRecipientNumber());
        assertEquals(delivery.getRecipient().getStreet(), deliveryResponseDto.getRecipientStreet());
        assertEquals(delivery.getOrderedDate(), deliveryResponseDto.getOrderedDate());
        assertEquals(delivery.getEndDate(), deliveryResponseDto.getEndDate());
        assertEquals(delivery.getTax(), deliveryResponseDto.getTax());
    }

    @Test
    void deliveryRequestDtoToDelivery() {
        var deliveryRequest = deliveryRequestDtoValid();
        var delivery = ParseObjects.deliveryRequestDtoToDelivery(deliveryRequest);
        assertEquals(deliveryRequest.getClientId(), delivery.getClient().getId());
        assertEquals(deliveryRequest.getRecipientName(), delivery.getRecipient().getName());
        assertEquals(deliveryRequest.getRecipientComplement(), delivery.getRecipient().getComplement());
        assertEquals(deliveryRequest.getRecipientNeighborhood(), delivery.getRecipient().getNeighborhood());
        assertEquals(deliveryRequest.getRecipientNumber(), delivery.getRecipient().getNumber());
        assertEquals(deliveryRequest.getRecipientStreet(), delivery.getRecipient().getStreet());
        assertEquals(deliveryRequest.getTax(), delivery.getTax());
    }

    @Test
    void listDeliveryToListDeliveryResponseDto() {
        var delivery = deliveryValid();
        var deliveryResponseDtoList = ParseObjects.listDeliveryToListDeliveryResponseDto(Collections.singletonList(delivery));
        assertEquals(delivery.getId(), deliveryResponseDtoList.iterator().next().getId());
        assertEquals(delivery.getClient().getName(), deliveryResponseDtoList.iterator().next().getClientName());
        assertEquals(delivery.getClient().getEmail(), deliveryResponseDtoList.iterator().next().getClientEmail());
        assertEquals(delivery.getClient().getTelephone(), deliveryResponseDtoList.iterator().next().getClientTelephone());
        assertEquals(delivery.getClient().getId(), deliveryResponseDtoList.iterator().next().getClientId());
        assertEquals(delivery.getDeliveryStatus(), deliveryResponseDtoList.iterator().next().getDeliveryStatus());
        assertEquals(delivery.getRecipient().getName(), deliveryResponseDtoList.iterator().next().getRecipientName());
        assertEquals(delivery.getRecipient().getComplement(), deliveryResponseDtoList.iterator().next().getRecipientComplement());
        assertEquals(delivery.getRecipient().getNeighborhood(), deliveryResponseDtoList.iterator().next().getRecipientNeighborhood());
        assertEquals(delivery.getRecipient().getNumber(), deliveryResponseDtoList.iterator().next().getRecipientNumber());
        assertEquals(delivery.getRecipient().getStreet(), deliveryResponseDtoList.iterator().next().getRecipientStreet());
        assertEquals(delivery.getOrderedDate(), deliveryResponseDtoList.iterator().next().getOrderedDate());
        assertEquals(delivery.getEndDate(), deliveryResponseDtoList.iterator().next().getEndDate());
        assertEquals(delivery.getTax(), deliveryResponseDtoList.iterator().next().getTax());
    }

    @Test
    void occurrenceToOccurrenceResponseDto() {
        var occurrence = occurrenceValid();
        var deliveryResponseDto = ParseObjects.occurrenceToOccurrenceResponseDto(occurrence);
        assertEquals(occurrence.getId(), deliveryResponseDto.getId());
        assertEquals(occurrence.getDescription(), deliveryResponseDto.getDescription());
        assertEquals(occurrence.getRegisterDate(), deliveryResponseDto.getRegisterDate());
        assertEquals(occurrence.getDelivery().getId(), deliveryResponseDto.getDeliveryId());
    }

    @Test
    void listOccurrenceToListOccurrenceResponseDto() {
        var occurrence = occurrenceValid();
        var deliveryResponseDtoList = ParseObjects.listOccurrenceToListOccurrenceResponseDto(Collections.singletonList(occurrence));
        assertEquals(occurrence.getId(), deliveryResponseDtoList.iterator().next().getId());
        assertEquals(occurrence.getDescription(), deliveryResponseDtoList.iterator().next().getDescription());
        assertEquals(occurrence.getRegisterDate(), deliveryResponseDtoList.iterator().next().getRegisterDate());
        assertEquals(occurrence.getDelivery().getId(), deliveryResponseDtoList.iterator().next().getDeliveryId());
    }
}