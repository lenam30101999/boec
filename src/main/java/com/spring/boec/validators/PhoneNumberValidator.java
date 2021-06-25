package com.spring.boec.validators;


import com.spring.boec.annotations.PhoneNumberConstraint;
import com.spring.boec.exceptions.BadRequestException;
import com.spring.boec.utils.Helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {

  @Override
  public void initialize(PhoneNumberConstraint phoneNumber) {}

  @Override
  public boolean isValid(String phoneNumberField, ConstraintValidatorContext context) {
    Helper helper = new Helper();
    if (phoneNumberField == null || phoneNumberField.equals("") || phoneNumberField.length() < 10) {
      return true;
    }
    boolean check = helper.regexPhoneNumber(phoneNumberField);
    if (check == false){
      throw new BadRequestException("Sai định dạng điện thoại!");
    }
    return check;
  }
}
