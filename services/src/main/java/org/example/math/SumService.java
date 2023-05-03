package org.example.math;

import org.example.Service;

import java.util.ArrayList;

public interface SumService extends Service {
    public Number getSum(ArrayList<? extends Number> arrayList) throws Exception;
}
