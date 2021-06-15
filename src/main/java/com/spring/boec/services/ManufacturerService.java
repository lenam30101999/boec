package com.spring.boec.services;

import com.spring.boec.dtos.AuthorDTO;
import com.spring.boec.dtos.ManuFacturerDTO;
import com.spring.boec.entities.Author;
import com.spring.boec.entities.Manufacturer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ManufacturerService extends BaseService {

    public List<ManuFacturerDTO> getAll(){
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        if (Objects.nonNull(manufacturers)){
            return modelMapper.convertListManufacturer(manufacturers);
        }
        return null;
    }
}
