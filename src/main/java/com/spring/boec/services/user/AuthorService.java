package com.spring.boec.services.user;

import com.spring.boec.dtos.AuthorDTO;
import com.spring.boec.model.user.Author;
import com.spring.boec.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AuthorService extends BaseService {

    public List<AuthorDTO> getAllAuthor(){
        List<Author> authors = authorRepository.findAll();
        if (Objects.nonNull(authors)){
            return modelMapper.convertListAuthor(authors);
        }
        return null;
    }
}
