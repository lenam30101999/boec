package com.spring.boec.utils;

import com.spring.boec.services.BaseService;

import java.text.DecimalFormat;
import java.util.List;

public class Helper extends BaseService {



    public static float calculateRating(List<Float> rateList){
        float calculate = (float) rateList.stream().mapToDouble(p -> p).average().orElse(0);
        return Float.parseFloat(new DecimalFormat("#.#").format(calculate));
    }
}
