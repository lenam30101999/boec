package com.spring.boec.services;

import com.spring.boec.mapper.ModelMapper;
import com.spring.boec.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired protected AccountRepository accountRepository;
    @Autowired protected ModelMapper modelMapper;
    @Autowired protected OrderRepository orderRepository;
    @Autowired protected BookRepository bookRepository;
    @Autowired protected ClothesRepository clothesRepository;
    @Autowired protected ElectronicRepository electronicRepository;
    @Autowired protected OrderItemRepository orderItemRepository;
    @Autowired protected CustomerRepository customerRepository;
    @Autowired protected AddressRepository addressRepository;
    @Autowired protected FullNameRepository fullNameRepository;
}
