package com.algaworks.algalog.application.controller;

import com.algaworks.algalog.application.request.OccurrenceRequestDto;
import com.algaworks.algalog.application.response.OccurrenceResponseDto;
import com.algaworks.algalog.domain.service.OccurrenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
@RequiredArgsConstructor
public class OccurrenceController {

    private final OccurrenceService occurrenceService;

    @PostMapping
    public ResponseEntity<OccurrenceResponseDto> registerOccurrence(@PathVariable Long deliveryId, @RequestBody @Valid OccurrenceRequestDto occurrenceRequestDto) {
        return new ResponseEntity<>(occurrenceService.registerOccurrence(deliveryId, occurrenceRequestDto.getDescription()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OccurrenceResponseDto>> listAll(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(occurrenceService.findAllOccurrencesOfDelivery(deliveryId));
    }

}
