package com.crayu.ui.controller;

import com.crayu.sorting.SortingAlgorithm;
import com.crayu.ui.service.SortingTimesService;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;


public class ChartUIController {

    private static final String X_AXIS_LABEL = "Array size";
    private static final String Y_AXIS_LABEL = "Nanoseconds";

    private static final int DEFAULT_INIT_SIZE = 20_000;
    private static final int DEFAULT_MAX_SIZE = 60_000;
    private static final int DEFAULT_GAP = 4_000;
    private static final int DEFAULT_REPEAT_COUNT = 2;


    @FXML
    private TextField initialSizeTextField;

    @FXML
    private TextField maxSizeTextField;

    @FXML
    private TextField sizeGapTextField;

    @FXML
    private TextField repeatCountTextField;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private VBox algorithmsVBox;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private CheckBox regressionCheckBox;

    private LineChart<Number,Number> lineChart;

    private EnumMap<SortingAlgorithm,CheckBox> algorithmCheckBoxEnumMap;

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

        mainGridPane.add(lineChart,0,0);
    }

    @FXML
    private void generateButtonHandler() {
        SortingTimesService service = sortingTimesService();
        bindWithProgressIndicator(service);
        service.setOnSucceeded(successEventHandler());
        service.start();
    }

    private void bindWithProgressIndicator(SortingTimesService service) {
        progressIndicator.progressProperty().bind(service.progressProperty());
        progressIndicator.visibleProperty().bind(service.runningProperty());
    }

    @SuppressWarnings("unchecked")
    private EventHandler<WorkerStateEvent> successEventHandler() {
        return workerStateEvent -> {
            clearChart();
            Object result = workerStateEvent.getSource().getValue();
            ((Map<SortingAlgorithm, Map<Number, Number>>) result).forEach((k, v) -> {
                var series = seriesForData(v);
                series.setName(k.name());
                lineChart.getData().add(series);
            });
        };
    }

    private XYChart.Series<Number,Number> seriesForData(Map<Number, Number> data) {
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        data.forEach((k, v) -> {
            series.getData().add(new XYChart.Data<>(k, v));
        });
        return series;
    }

    private SortingTimesService sortingTimesService() {
        int initialSize = intFromTextField(initialSizeTextField);
        int sizeGap = intFromTextField(sizeGapTextField);
        int maxSize = intFromTextField(maxSizeTextField);
        int repeatCount = intFromTextField(repeatCountTextField);
        boolean regression = regressionCheckBox.isSelected();
        return new SortingTimesService(initialSize, maxSize, sizeGap, repeatCount, checkedSortingAlgorithms(), regression);
    }

    private List<SortingAlgorithm> checkedSortingAlgorithms() {
        return algorithmCheckBoxEnumMap.entrySet()
                .stream()
                .filter(x -> x.getValue().isSelected())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private void clearChart() {
        lineChart.getData().clear();
    }

    private int intFromTextField(TextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void setValidation() {
        setNumericValidation(initialSizeTextField);
        setNumericValidation(maxSizeTextField);
        setNumericValidation(sizeGapTextField);
        setNumericValidation(repeatCountTextField);
    }

    private void setNumericValidation(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,9}")) textField.setText(oldValue);
        });
    }

    private void setDefaultFieldsValues() {
        initialSizeTextField.setText(String.valueOf(DEFAULT_INIT_SIZE));
        maxSizeTextField.setText(String.valueOf(DEFAULT_MAX_SIZE));
        sizeGapTextField.setText(String.valueOf(DEFAULT_GAP));
        repeatCountTextField.setText(String.valueOf(DEFAULT_REPEAT_COUNT));
    }

}
