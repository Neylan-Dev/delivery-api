package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.dto.DeliveryRequestDto;
import com.algaworks.algalog.domain.dto.DeliveryResponseDto;
import com.algaworks.algalog.domain.service.DeliveryCreationService;
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
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryCreationService deliveryCreationService;
    private final OccurrenceService occurrenceService;

    @PostMapping
    public ResponseEntity<DeliveryResponseDto> create(@RequestBody @Valid DeliveryRequestDto deliveryRequestDto) {
        return new ResponseEntity<>(deliveryCreationService.save(deliveryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponseDto>> listAll() {
        return ResponseEntity.ok(deliveryCreationService.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponseDto> findById(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(deliveryCreationService.findById(deliveryId));
    }

}
