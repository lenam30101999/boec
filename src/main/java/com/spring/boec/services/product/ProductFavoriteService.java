package com.spring.boec.services.product;

import com.spring.boec.dtos.FavoriteDTO;
import com.spring.boec.entities.product.Book;
import com.spring.boec.entities.product.Clothes;
import com.spring.boec.entities.product.Electronic;
import com.spring.boec.entities.product.ProductFavorite;
import com.spring.boec.entities.user.Customer;
import com.spring.boec.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductFavoriteService extends BaseService {
    
    public FavoriteDTO addFavorite(FavoriteDTO favoriteDTO){
        Customer customer = customerRepository.findById(favoriteDTO.getCustomerId()).orElse(null);
        ProductFavorite productFavorite = getProductFavorite(favoriteDTO);
        if (Objects.nonNull(customer) && Objects.nonNull(productFavorite)){
            productFavorite.setCustomer(customer);
            productFavorite = productFavoriteRepository.save(productFavorite);
            return modelMapper.convertToFavoriteDTO(productFavorite);
        }
        return null;
    }

    public List<FavoriteDTO> getAllByCustomer(int customerId){
        List<ProductFavorite> productFavorites = productFavoriteRepository.findAllByCustomerId(customerId);
        List<FavoriteDTO> favoriteDTOS =
                productFavorites.stream().map(modelMapper::convertToFavoriteDTO).collect(Collectors.toList());
        return favoriteDTOS;
    }

    private ProductFavorite getProductFavorite(FavoriteDTO favoriteDTO){
        ProductFavorite productFavorite;
        productFavorite = getBook(favoriteDTO);
        if (productFavorite == null){
            productFavorite = getClothes(favoriteDTO);
        }
        if (productFavorite == null){
            productFavorite = getElectronic(favoriteDTO);
        }
        return productFavorite;
    }

    private ProductFavorite getBook(FavoriteDTO favoriteDTO){
        if (favoriteDTO.getBookDTO() != null &&
                favoriteDTO.getBookDTO().getId() != null){
            Book book = bookRepository.findById(favoriteDTO.getBookDTO().getId()).orElse(null);
            if (book != null){
                return ProductFavorite.builder()
                        .book(book)
                        .electronic(null)
                        .clothes(null)
                        .build();
            }
        }
        return null;
    }

    private ProductFavorite getClothes(FavoriteDTO favoriteDTO){
        if (favoriteDTO.getClothesDTO() != null &&
                favoriteDTO.getClothesDTO().getId() != null) {
            Clothes clothes = clothesRepository.findById(favoriteDTO.getClothesDTO().getId()).orElse(null);
            if (clothes != null){
                ProductFavorite saved = ProductFavorite.builder()
                        .clothes(clothes)
                        .book(null)
                        .clothes(null)
                        .build();
                return saved;
            }
        }
        return null;
    }

    private ProductFavorite getElectronic(FavoriteDTO favoriteDTO){
        if (favoriteDTO.getElectronicDTO() != null &&
                favoriteDTO.getElectronicDTO().getId() != null) {
            Electronic electronic = electronicRepository.findById(favoriteDTO.getElectronicDTO().getId()).orElse(null);
            if (electronic != null){
                ProductFavorite saved = ProductFavorite.builder()
                        .electronic(electronic)
                        .book(null)
                        .clothes(null)
                        .build();
                return saved;
            }
        }
        return null;
    }
    public FavoriteDTO deleteFavoriteItem(int id){
        ProductFavorite productFavorite = productFavoriteRepository.findById(id).orElse(null);
        if (Objects.nonNull(productFavorite)){

                productFavoriteRepository.delete(productFavorite);
            return modelMapper.convertToFavoriteDTO(productFavorite);
        }
        return null;

    }
}
