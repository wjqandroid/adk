package com.visionvera.psychologist.c.utils.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Arrays;
import java.util.List;

public class XAxisFormatter extends ValueFormatter {

    private String[] dayArray={"1/11","2/11","3/11","4/11","5/11","6/11","7/11","8/11","9/11","10/11","11/11","12/11","13/11","14/11","15/11","16/11"};
//    private String[] dayArray={"1/11","2/11","3/11","4/11","5/11"};
    private List<String> days= Arrays.asList(dayArray);

    public XAxisFormatter(String[] dayArray) {
        this.dayArray = dayArray;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return days.get((int) value);
    }
}
