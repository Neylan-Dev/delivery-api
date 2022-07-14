package com.algaworks.algalog.application.configuration;

import com.algaworks.algalog.domain.DeliveryDto;
import com.algaworks.algalog.domain.model.Delivery;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.typeMap(Delivery.class, DeliveryDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getRecipient().getStreet(),
                    DeliveryDto::setRecipientStreet);
            mapper.map(src -> src.getRecipient().getComplement(),
                    DeliveryDto::setRecipientComplement);
            mapper.map(src -> src.getRecipient().getName(),
                    DeliveryDto::setRecipientName);
            mapper.map(src -> src.getRecipient().getNumber(),
                    DeliveryDto::setRecipientNumber);
            mapper.map(src -> src.getRecipient().getNeighborhood(),
                    DeliveryDto::setRecipientNeighborhood);
        });
        return modelMapper;
    }
}
