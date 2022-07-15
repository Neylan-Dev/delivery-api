package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.dto.OccurrenceResponseDto;
import com.algaworks.algalog.domain.repository.OccurrenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OccurrenceService {

    private final OccurrenceRepository occurrenceRepository;

    public OccurrenceResponseDto registerOccurrence(Long deliveryId, String description) {
        return null;
    }

}
