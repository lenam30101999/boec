package com.spring.boec.services;

import com.spring.boec.dtos.ElectronicDTO;
import com.spring.boec.dtos.ElectronicDTO;
import com.spring.boec.entities.Manufacturer;
import com.spring.boec.entities.Electronic;
import com.spring.boec.entities.Publisher;
import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.ElectronicRepository;
import com.spring.boec.repositories.ElectronicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Transactional
@Service
public class ElectronicService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ElectronicRepository electronicRepository;

    public ElectronicDTO addElectronic(ElectronicDTO ElectronicDTO){
        Manufacturer manufacturer = Manufacturer.builder()
                .id(ElectronicDTO.getManufacturer().getId())
                .build();
        Publisher publisher = Publisher.builder()
                .id(ElectronicDTO.getPublisher().getId())
                .build();
        Electronic electronic = Electronic.builder()
                .name(ElectronicDTO.getName())
                .power(ElectronicDTO.getPower())
                .manufacturer(manufacturer)
                .publisher(publisher)
                .build();
        electronic = electronicRepository.save(electronic);
        return modelMapper.convertToElectronicDTO(electronic);
    }

    public List<ElectronicDTO> getListElectronic(String textSearch){
        List<Electronic> electronics = electronicRepository.findAllElectronic(textSearch.trim());
        return modelMapper.convertListElectronic(electronics);
    }

    public ElectronicDTO updateElectronic(ElectronicDTO ElectronicDTO){
        Electronic electronic = electronicRepository.findById(ElectronicDTO.getId()).orElse(null);
        Manufacturer manufacturer = Manufacturer.builder()
                .id(ElectronicDTO.getManufacturer().getId())
                .build();
        Publisher publisher = Publisher.builder()
                .id(ElectronicDTO.getPublisher().getId())
                .build();
        if (Objects.nonNull(electronic)){
            electronic.setName(ElectronicDTO.getName());
            electronic.setPower(ElectronicDTO.getPower());
            electronic.setManufacturer(manufacturer);
            electronic.setPublisher(publisher);
            electronicRepository.saveAndFlush(electronic);
        }
        return modelMapper.convertToElectronicDTO(electronic);
    }

    public ElectronicDTO deleteElectronicDTO(int electronicId){
        Electronic electronic  = electronicRepository.findById(electronicId).orElse(null);
        if (Objects.nonNull(electronic)){
            electronicRepository.delete(electronic);
            return modelMapper.convertToElectronicDTO(electronic);
        }else
            return null;
    }
}
