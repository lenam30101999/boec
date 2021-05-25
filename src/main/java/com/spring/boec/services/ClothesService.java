package com.spring.boec.services;

import com.spring.boec.dtos.ClothesDTO;
import com.spring.boec.entities.Clothes;
import com.spring.boec.entities.Publisher;
import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.ClothesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class ClothesService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ClothesRepository clothesRepository;

  public ClothesDTO addClothes(ClothesDTO clothesDTO){
    Publisher publisher = Publisher.builder()
        .id(clothesDTO.getPublisher().getId())
        .build();
    Clothes clothes = Clothes.builder()
        .name(clothesDTO.getName())
        .publisher(publisher)
        .size(clothesDTO.getSize())
        .gender(clothesDTO.getGender())
        .build();

    clothes.setPrice(clothesDTO.getPrice());
    clothes.setStock(clothesDTO.getStock());
    clothes = clothesRepository.save(clothes);
    return modelMapper.convertToClothesDTO(clothes);
  }

  public List<ClothesDTO> getListClothes(String textSearch){
    List<Clothes> clothes = clothesRepository.findAllClothes(textSearch.trim());
    return convertToListClothesDTOs(clothes);
  }

  public ClothesDTO updateClothes(ClothesDTO clothesDTO){
    Clothes clothes = clothesRepository.findById(clothesDTO.getId()).orElse(null);
    Publisher publisher = Publisher.builder()
        .id(clothesDTO.getPublisher().getId())
        .build();
    if (Objects.nonNull(clothes)){
      clothes.setName(clothesDTO.getName());
      clothes.setPublisher(publisher);
      clothes.setGender(clothesDTO.getGender());
      clothes.setSize(clothesDTO.getSize());
      clothes.setPrice(clothesDTO.getPrice());
      clothes.setStock(clothesDTO.getStock());

      clothesRepository.saveAndFlush(clothes);
    }
    return modelMapper.convertToClothesDTO(clothes);
  }

  public ClothesDTO deleteClothesDTO(int clothesDTO){
    Clothes clothes = clothesRepository.findById(clothesDTO).orElse(null);
    if (Objects.nonNull(clothes)){
      clothesRepository.delete(clothes);
      return modelMapper.convertToClothesDTO(clothes);
    }else
      return null;
  }

  private List<ClothesDTO> convertToListClothesDTOs(List<Clothes> clothes){
    return clothes.stream().map(modelMapper::convertToClothesDTO).collect(Collectors.toList());
  }
}
