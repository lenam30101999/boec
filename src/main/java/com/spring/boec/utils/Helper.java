package com.spring.boec.utils;

import com.spring.boec.services.BaseService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper extends BaseService {

    public static boolean regexPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String regex = "^0[37859]{1}[0-9]{1}\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

    public static float calculateRating(List<Float> rateList){
        float calculate = (float) rateList.stream().mapToDouble(p -> p).average().orElse(0);
        return Float.parseFloat(new DecimalFormat("#.#").format(calculate));
    }
}
