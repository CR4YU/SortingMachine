package com.crayu.statistics;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


class Regression {

    private static final int DEGREE = 2;

    private PolynomialFunction function;

    Regression(Map<Number, Number> data) {
        double[] x = new double[data.size()];
        double[] y = new double[data.size()];

        int i = 0;
        for (Map.Entry<Number,Number> entry : data.entrySet()) {
            x[i] = entry.getKey().doubleValue();
            y[i++] = entry.getValue().doubleValue();
        }

        regress(x, y);
    }

    private void regress(double[] x, double[] y) {
        AbstractCurveFitter fitter = PolynomialCurveFitter.create(DEGREE);
        List<WeightedObservedPoint> points = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            points.add(new WeightedObservedPoint(1, x[i], y[i]));
        }
        function = new PolynomialFunction(fitter.fit(points));
    }


    double predict(double x) {
        return function.value(x);
    }

}
