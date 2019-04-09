package com.crayu.ui.controller;

import com.crayu.sorting.SortingAlgorithm;
import com.crayu.statistics.StatisticsEngine;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;


public class ChartUIController {

    private static final String CHART_TITLE = "Sorting times";
    private static final String X_AXIS_LABEL = "Array size";
    private static final String Y_AXIS_LABEL = "Miliseconds";

    private static final int DEFAULT_INIT_SIZE = 0;
    private static final int DEFAULT_MAX_SIZE = 5_000;
    private static final int DEFAULT_GAP = 500;
    private static final int DEFAULT_REPEAT_COUNT = 1;


    @FXML
    TextField initialSizeTextField;

    @FXML
    TextField maxSizeTextField;

    @FXML
    TextField sizeGapTextField;

    @FXML
    TextField repeatCountTextField;

    @FXML
    GridPane mainGridPane;

    @FXML
    VBox algorithmsVBox;

    private LineChart<Number,Number> lineChart;

    private EnumMap<SortingAlgorithm, CheckBox> algorithmCheckBoxEnumMap;

    public void initialize() {
        algorithmCheckBoxEnumMap = new EnumMap<>(SortingAlgorithm.class);
        initAlgorithmsList();
        showEmptyLineChart();
        setValidation();
        setDefaultFieldsValues();
    }

    private void initAlgorithmsList() {
        for (SortingAlgorithm sortingAlgorithm : SortingAlgorithm.values()) {
            CheckBox checkBox = new CheckBox();
            Label label = new Label(sortingAlgorithm.toString());
            HBox row = new HBox(checkBox, label);
            row.setSpacing(3);
            algorithmsVBox.getChildren().add(row);

            algorithmCheckBoxEnumMap.put(sortingAlgorithm, checkBox);
        }
    }

    private void showEmptyLineChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(X_AXIS_LABEL);
        yAxis.setLabel(Y_AXIS_LABEL);

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setPrefSize(900,900);

        lineChart.setTitle(CHART_TITLE);
        mainGridPane.add(lineChart,0,0);
    }

    @FXML
    private void generateButtonHandler() {
        clearChart();
        StatisticsEngine statistics = buildStatistics();

        statistics.getStatistics().forEach( (k, v) -> {
            XYChart.Series<Number,Number> series = new XYChart.Series<>();
            series.setName(k.name());
            v.forEach((a, j) -> {
                series.getData().add(new XYChart.Data<>(a, j));
            });
            lineChart.getData().add(series);
        });
    }

    private StatisticsEngine buildStatistics() {
        return new StatisticsEngine.Builder()
                .initialSize(intFromTextField(initialSizeTextField))
                .sizeGap(intFromTextField(sizeGapTextField))
                .maxSize(intFromTextField(maxSizeTextField))
                .repeatCount(intFromTextField(repeatCountTextField))
                .addSortingAlgorithms(checkedSortingAlgorithms())
                .build();
    }

    private List<SortingAlgorithm> checkedSortingAlgorithms() {
        return algorithmCheckBoxEnumMap.entrySet().stream()
                .filter(map -> map.getValue().isSelected())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private void clearChart() {
        lineChart.getData().clear();
    }

    private int intFromTextField(TextField textField) {
        //no need to handle exception if validation is on
        return Integer.parseInt(textField.getText());
    }

    private void setValidation() {
        setNumericValidation(initialSizeTextField);
        setNumericValidation(maxSizeTextField);
        setNumericValidation(sizeGapTextField);
        setNumericValidation(repeatCountTextField);
    }

    private void setNumericValidation(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d{0,9}")) textField.setText(oldValue);
        });
    }

    private void setDefaultFieldsValues() {
        initialSizeTextField.setText(String.valueOf(DEFAULT_INIT_SIZE));
        maxSizeTextField.setText(String.valueOf(DEFAULT_MAX_SIZE));
        sizeGapTextField.setText(String.valueOf(DEFAULT_GAP));
        repeatCountTextField.setText(String.valueOf(DEFAULT_REPEAT_COUNT));
    }

}
