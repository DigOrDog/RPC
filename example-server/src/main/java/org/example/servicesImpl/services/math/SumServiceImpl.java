package org.example.servicesImpl.services.math;

import org.example.math.SumService;

import java.util.ArrayList;

public class SumServiceImpl implements SumService {
    @Override
    public Number getSum(ArrayList<? extends Number> arrayList) throws Exception {
        double sum = 0;
        for (Number number : arrayList) {
            if (number instanceof Integer) {
                sum += number.intValue();
            } else if (number instanceof Long) {
                sum += number.longValue();
            } else if (number instanceof Float) {
                sum += number.floatValue();
            } else if (number instanceof Double) {
                sum += number.doubleValue();
            } else if (number instanceof Short) {
                sum += number.shortValue();
            } else {
                throw new Exception("非法类型");
            }
        }
        return new Double(sum);
    }
}
