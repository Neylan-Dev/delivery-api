package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.model.Occurrence;
import com.neylandev.delivery.domain.repository.OccurrenceRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OccurrenceService {

    private final OrderService orderService;
    private final OccurrenceRepository occurrenceRepository;

    @Transactional
    public OccurrenceResponseDto registerOccurrence(Long orderId, String description) {
        var orderResponseDto = orderService.findById(orderId);
//        var occurrence = delivery.addAndGetOccurrence(description);
        return ParseObjects.occurrenceToOccurrenceResponseDto(occurrenceRepository.save(Occurrence.builder().build()));
    }

    public List<OccurrenceResponseDto> findAllOccurrencesOfDelivery(Long orderId) {
        var orderResponseDto = orderService.findById(orderId);
        return orderResponseDto.getOccurrenceResponseDtos();
    }

}
