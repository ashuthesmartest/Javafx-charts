package proj_assigned_2;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class FXMLDocumentController implements Initializable {
        
    @FXML
    ComboBox cb = new ComboBox(); 
    
    @FXML
    Pane ap = new Pane() ;
   
    @FXML
    CategoryAxis xAxis1 = new CategoryAxis() ;
  
    @FXML
    CategoryAxis xAxis2 = new CategoryAxis() ;
      
    @FXML
    CategoryAxis xAxis3 = new CategoryAxis() ;
    
    @FXML
    NumberAxis yAxis1 = new NumberAxis() ;
    
    @FXML
    NumberAxis yAxis2 = new NumberAxis() ;
    
    @FXML
    NumberAxis yAxis3 = new NumberAxis() ;
    
    @FXML
    LineChart<String,Number> lc = new LineChart<String, Number>(xAxis1, yAxis1)  ;

    @FXML
    BarChart<String,Number> bc = new BarChart<String, Number>(xAxis2, yAxis2)  ;

    @FXML
    AreaChart<String,Number> ac = new AreaChart<String, Number>(xAxis3, yAxis3)  ; 
    
    Stage stage = new Stage();

    
    @FXML
    private ObservableList data ;
    
    private XYChart.Series s1 = new XYChart.Series() ;
    private XYChart.Series s2 = new XYChart.Series() ;
    private XYChart.Series s3 = new XYChart.Series() ;  
       
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException, InterruptedException 
    {
        String out = cb.getSelectionModel().getSelectedItem().toString() ;
        if(out.equals("PieChart"))
        {
            ap.getChildren().clear();
            PieChart pc = new PieChart() ;
            createPie() ;
            pc.getData().addAll(data) ;
            ap.getChildren().add(pc) ;
            ap.setVisible(true);
        }
        if(out.equals("LineChart"))
        {
            lc.getData().removeAll(s1) ;
            ap.getChildren().clear();
            createLine() ;
            lc.getData().addAll(s1);
            ap.getChildren().add(lc) ;
            ap.setVisible(true);
        }
        if(out.equals("BarChart"))
        {
            bc.getData().removeAll(s2) ;   
            ap.getChildren().clear();
            createBar() ;
            bc.getData().addAll(s2);
            ap.getChildren().add(bc) ;
            ap.setVisible(true);
        }
        if(out.equals("AreaChart"))
        {
            ac.getData().removeAll(s3) ;   
            ap.getChildren().clear();
            createArea() ;
            ac.getData().addAll(s3);
            ap.getChildren().add(ac) ;
            ap.setVisible(true);
        }                   

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        cb.getItems().addAll("PieChart", "LineChart", "BarChart", "AreaChart") ;
        ap.getStylesheets().add(this.getClass().getResource("graph_css.css").toExternalForm()) ;
        ap.setVisible(false);
    }    
    
    
    public void createPie() throws SQLException
    {
        data = FXCollections.observableArrayList();
        ResultSet rs = DBConnector.st.executeQuery("select country,"+" count(*) from stats" + " group by country");
        while(rs.next())
        {    
            data.add(new PieChart.Data(rs.getString(1), rs.getDouble(2))) ;
        }    
    }

    private void createLine() throws SQLException 
    {
        ResultSet rs = DBConnector.st.executeQuery("select country,"+" count(*) from stats" + " group by country");
        while(rs.next())
        {    
            s1.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2))) ;
        }
    }
    
    private void createBar() throws SQLException 
    {
        ResultSet rs = DBConnector.st.executeQuery("select country,"+" count(*) from stats" + " group by country");
        while(rs.next())
        {    
            s2.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2))) ;            
        }
    }
    
    private void createArea() throws SQLException 
    {
        ResultSet rs = DBConnector.st.executeQuery("select country,"+" count(*) from stats" + " group by country");
        while(rs.next())
        {    
            s3.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2))) ;
        }
    }   
}
