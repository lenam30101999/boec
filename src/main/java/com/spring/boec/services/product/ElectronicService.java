package com.spring.boec.services.product;

import com.spring.boec.dtos.ElectronicDTO;
import com.spring.boec.model.product.Manufacturer;
import com.spring.boec.model.product.Electronic;
import com.spring.boec.model.product.Publisher;
import com.spring.boec.services.BaseService;
import com.spring.boec.utils.Helper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Transactional
@Service
public class ElectronicService extends BaseService {

    public ElectronicDTO addElectronic(ElectronicDTO electronicDTO){
        Manufacturer manufacturer = Manufacturer.builder()
                .id(electronicDTO.getManufacturer().getId())
                .build();
        Publisher publisher = Publisher.builder()
                .id(electronicDTO.getPublisher().getId())
                .build();
        Electronic electronic = Electronic.builder()
                .name(electronicDTO.getName())
                .power(electronicDTO.getPower())
                .manufacturer(manufacturer)
                .publisher(publisher)
                .build();

        electronic.setUrlImage(electronicDTO.getUrlImage());
        electronic.setPrice(electronicDTO.getPrice());
        electronic.setStock(electronicDTO.getStock());
        electronic = electronicRepository.save(electronic);
        return modelMapper.convertToElectronicDTO(electronic);
    }

    public List<ElectronicDTO> getListElectronic(String textSearch){
        List<Electronic> electronics = electronicRepository.findAllElectronic(textSearch.trim());
        return modelMapper.convertListElectronic(electronics);
    }

    public ElectronicDTO updateElectronic(ElectronicDTO electronicDTO){
        Electronic electronic = electronicRepository.findById(electronicDTO.getId()).orElse(null);
        Manufacturer manufacturer = Manufacturer.builder()
                .id(electronicDTO.getManufacturer().getId())
                .build();
        Publisher publisher = Publisher.builder()
                .id(electronicDTO.getPublisher().getId())
                .build();
        if (Objects.nonNull(electronic)){
            electronic.setName(electronicDTO.getName());
            electronic.setPower(electronicDTO.getPower());
            electronic.setManufacturer(manufacturer);
            electronic.setPublisher(publisher);
            electronic.setUrlImage(electronicDTO.getUrlImage());
            electronic.setPrice(electronicDTO.getPrice());
            electronic.setStock(electronicDTO.getStock());
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

    public ElectronicDTO getElectronicDTO(int electronicId){
        List<Float> rateList = new ArrayList<>();
        Electronic electronic  = electronicRepository.findById(electronicId).orElse(null);
        if (Objects.nonNull(electronic)){
            electronic.getRatings().stream().forEach(p->rateList.add(p.getRate()));
            float calculateRating = Helper.calculateRating(rateList);
            ElectronicDTO electronicDTO =  modelMapper.convertToElectronicDTO(electronic);
            electronicDTO.setAvgRating(calculateRating);
            return electronicDTO;
        }else
            return null;
    }

    public List<ElectronicDTO> getAllElectronic(){
        List<Electronic> electronics = electronicRepository.findAll();
        return electronics.stream().map(modelMapper::convertToElectronicDTO).collect(Collectors.toList());
    }

}
