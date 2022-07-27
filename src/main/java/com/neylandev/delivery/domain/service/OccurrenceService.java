package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.repository.OccurrenceRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OccurrenceService {

    private final FindDeliveryService findDeliveryService;
    private final OccurrenceRepository occurrenceRepository;

    @Transactional
    public OccurrenceResponseDto registerOccurrence(Long deliveryId, String description) {
        var delivery = findDeliveryService.find(deliveryId);
        var occurrence = delivery.addAndGetOccurrence(description);
        return ParseObjects.occurrenceToOccurrenceResponseDto(occurrenceRepository.save(occurrence));
    }

    public List<OccurrenceResponseDto> findAllOccurrencesOfDelivery(Long deliveryId) {
        var delivery = findDeliveryService.find(deliveryId);
        return ParseObjects.listOccurrenceToListOccurrenceResponseDto(delivery.getOccurrences());
    }

}
