package application;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.util.StringConverter;

public class HistogramTab extends Tab {

	final BarChart<String,Number> bc;
	
	public HistogramTab()
	{
		super();
		setText("Histogram");
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		bc= new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Distribution of marks");
		xAxis.setLabel("Marks");
		yAxis.setLabel("Count");

		
		
	}
	
	public void updateHistogram(CustomTabPane customTabPane)
	{
		XYChart.Series series1 = new XYChart.Series();
		series1.getData().add(new XYChart.Data("2.0",customTabPane.howMany2()));
		series1.getData().add(new XYChart.Data("3.0",customTabPane.howMany3()));
		series1.getData().add(new XYChart.Data("3.5",customTabPane.howMany3andHalf()));
		series1.getData().add(new XYChart.Data("4.0",customTabPane.howMany4()));
		series1.getData().add(new XYChart.Data("4.5",customTabPane.howMany4andHalf()));
		series1.getData().add(new XYChart.Data("5.0",customTabPane.howMany5()));
		
		
		
		bc.getData().clear();
		bc.getData().add(series1);
		this.setContent(bc);
	}
}
