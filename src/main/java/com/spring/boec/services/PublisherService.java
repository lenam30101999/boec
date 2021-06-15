package com.spring.boec.services;

import com.spring.boec.dtos.AuthorDTO;
import com.spring.boec.dtos.PublisherDTO;
import com.spring.boec.entities.Author;
import com.spring.boec.entities.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PublisherService extends BaseService {

    public List<PublisherDTO> getAll(){
        List<Publisher> publishers = publisherRepository.findAll();
        if (Objects.nonNull(publishers)){
            return modelMapper.convertListPublisher(publishers);
        }
        return null;
    }
}
