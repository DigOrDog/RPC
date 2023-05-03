package org.example;

import org.example.math.SumService;
import sun.plugin2.os.windows.FLASHWINFO;

import java.util.ArrayList;
public class Main implements SumService {


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

    public void testResult() throws Exception {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<Number> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            integers.add(i);
            doubles.add(i + 0.5);
            numbers.add(i); numbers.add(i + 0.5);
        }
        System.out.println(getSum(integers).intValue()); // 55
        System.out.println(getSum(doubles).doubleValue()); // 60
        System.out.println(getSum(numbers).doubleValue()); // 115
    }
    public static void main(String[] args) throws Exception {
        new Main().testResult();
    }
}
