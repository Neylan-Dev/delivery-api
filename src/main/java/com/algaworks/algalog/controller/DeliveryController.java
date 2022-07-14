package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.DeliveryDto;
import com.algaworks.algalog.domain.service.DeliveryCreationService;
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

    @PostMapping
    public ResponseEntity<DeliveryDto> create(@RequestBody @Valid DeliveryDto deliveryDto){
        return new ResponseEntity<>(deliveryCreationService.save(deliveryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDto>> listAll() {
        return ResponseEntity.ok(deliveryCreationService.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDto> findById(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(deliveryCreationService.findById(deliveryId));
    }

}
