package com.neylandev.delivery.domain.service;

import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.model.Occurrence;
import com.neylandev.delivery.domain.repository.OccurrenceRepository;
import com.neylandev.delivery.domain.utils.ParseObjects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OccurrenceService {

    private final OrderService orderService;
    private final OccurrenceRepository occurrenceRepository;

    @Transactional
    public OccurrenceResponseDto registerOccurrence(Long orderId, String description) {
        var order = orderService.findById(orderId);
        Occurrence occurrence = Occurrence.builder()
                .description(description)
                .order(order)
                .registerDate(LocalDateTime.now())
                .build();
        return ParseObjects.occurrenceToOccurrenceResponseDto(occurrenceRepository.save(occurrence));
    }

    public List<OccurrenceResponseDto> findAllOccurrencesOfOrder(Long orderId) {
        var orderResponseDto = orderService.findOrderResponseDtoById(orderId);
        return orderResponseDto.getOccurrenceResponseDtos();
    }

}
