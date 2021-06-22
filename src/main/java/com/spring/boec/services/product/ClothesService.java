package com.spring.boec.services.product;

import com.spring.boec.dtos.ClothesDTO;
import com.spring.boec.entities.product.Clothes;
import com.spring.boec.entities.product.Publisher;
import com.spring.boec.services.BaseService;
import com.spring.boec.utils.Helper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class ClothesService extends BaseService {

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

    clothes.setUrlImage(clothesDTO.getUrlImage());
    clothes.setPrice(clothesDTO.getPrice());
    clothes.setStock(clothesDTO.getStock());
    clothes = clothesRepository.save(clothes);
    return modelMapper.convertToClothesDTO(clothes);
  }

  public List<ClothesDTO> getListClothes(String textSearch){
    List<Clothes> clothes = clothesRepository.getAllClothes(textSearch.trim());
    return convertToListClothesDTOs(clothes);
  }

  public List<ClothesDTO> getAllClothes(){
    List<Clothes> clothes = clothesRepository.findAll();
    return modelMapper.convertToListClothesDTO(clothes);
  }

  public ClothesDTO updateClothes(ClothesDTO clothesDTO){
    Clothes clothes = clothesRepository.findById(clothesDTO.getId()).orElse(null);
    Publisher publisher = Publisher.builder()
        .id(clothesDTO.getPublisher().getId())
        .build();
    if (Objects.nonNull(clothes)){
      clothes.setName(clothesDTO.getName());
      clothes.setPublisher(publisher);
      clothes.setUrlImage(clothesDTO.getUrlImage());
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

  public ClothesDTO getClothesDTO(int clothesDTO){
    List<Float> rateList = new ArrayList<>();
    Clothes clothes = clothesRepository.findById(clothesDTO).orElse(null);
    if (Objects.nonNull(clothes)){
      clothes.getRatings().forEach(p->rateList.add(p.getRate()));
      ClothesDTO clothesDTO1 = modelMapper.convertToClothesDTO(clothes);
      float calculateRating = Helper.calculateRating(rateList);
      clothesDTO1.setAvgRating(calculateRating);
      return clothesDTO1;
    }else
      return null;
  }

  private List<ClothesDTO> convertToListClothesDTOs(List<Clothes> clothes){
    return clothes.stream().map(modelMapper::convertToClothesDTO).collect(Collectors.toList());
  }
}
