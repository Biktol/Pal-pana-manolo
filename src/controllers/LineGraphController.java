/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.JSONRequest;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author Biktol
 */
public class LineGraphController implements Initializable {

    @FXML
    private ComboBox<Byte> max_mag;
    @FXML
    private ComboBox<Byte> min_mag;
    @FXML
    private DatePicker date_picker;
    @FXML
    private LineChart<?, ?> chart_line;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    double maxMagValue = 1, minMagValue = 0.5;
    
    String date = "";
    
    private Byte[] mag = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    
    private Number[] chart_numbers;
    
    ObservableList<Byte> magList = FXCollections.observableArrayList();
    ObservableList<Short> depthList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //System.out.println(new JSONRequest().get("1-1-1", 2, 3)); Esto es una prueba.

        
        
        
        magList.addAll(mag);
        max_mag.setItems(magList);
        min_mag.setItems(magList);
        
        
        /*
        for (int i = 65; i < 91; i++) {
            combo1_list.add(String.valueOf((char)i));
        Esto es para mostrar letras de la A a la Z en mayuscula.
        }*/
     
        
    }    
    
    private Number[] getChartNumbers(){
        
    try {
            
            String json = new JSONRequest().get(date, maxMagValue, minMagValue);
            
            JSONParser jSONParser = new JSONParser();
            
            JSONObject object = (JSONObject) jSONParser.parse(json);
            
            JSONArray features = (JSONArray) object.get("features");
            
            chart_numbers = new Number[features.size()];
            
            int idx = 0;
            for (Object object_feature : features) {
                JSONObject object2_feature = (JSONObject) object_feature;
                
                JSONObject properties = (JSONObject) object2_feature.get("properties");
                
                Object mag = properties.get("mag");
                
                chart_numbers[idx] = (Number) mag;
                
                idx++;
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LineGraphController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(LineGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return chart_numbers;
}

    @FXML
    String getDate() {
        
        LocalDate localDate = date_picker.getValue();
        
        date = localDate.getYear() + "-" + localDate.getMonth() + "-" + localDate.getDayOfMonth();
        
        return date;
        
    }

    @FXML
    private void getMaxMag() {
        maxMagValue = max_mag.getValue();
    }

    @FXML
    private void getMinMag() {
        minMagValue = min_mag.getValue();
    }

    @FXML
    private void fetchDate() {
        
        XYChart.Series[] series = new XYChart.Series[3];
        series[0] = new XYChart.Series();
        
        int idx = 0;
        
        chart_line.getData().removeAll(series[0]);
        
        Number[] numbers = getChartNumbers();
        
        for (Number number : numbers) {
            
            if(idx < 20)
            series[0] .getData().addAll(new XYChart.Data<>(String.valueOf(idx),number));
            idx++;
        }
        
        chart_line.getData().addAll(series[0]);
        
    }
    
}
