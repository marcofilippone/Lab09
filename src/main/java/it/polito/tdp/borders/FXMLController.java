
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class FXMLController {

	private Model model;
	private int cont = 0;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> cmbStati;

    @FXML
    private Button btnStati;


    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	String a = txtAnno.getText();
    	int anno;
    	try {
    		anno = Integer.parseInt(a);
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire un numero intero");
    		return;
    	}
    	if(anno<1816 || anno>2016) {
    		txtResult.setText("L'anno deve essere compreso tra 1816 e 2016");
    		return;
    	}
    	if(cont!=0) {
    		cmbStati.getItems().removeAll(model.getVertici());
    	}
    	model.creaGrafo(anno);
    	cmbStati.getItems().addAll(model.getVertici());
    	cont++;
    	String s = "Grafo creato con "+model.getVertici().size()+" vertici e "+model.nArchi()+" archi\n\n";
    	s+= "Componenti connesse: "+model.componentiConnesse()+"\n\n";
    	for(Country c : model.getVertici()) {
    		s += c.getName()+": "+model.calcolaGrado(c)+" stati confinanti\n";
    	}
    	txtResult.setText(s);
    }
    
    
    @FXML
    void doStatiRaggiungibili(ActionEvent event) {
    	txtResult.clear();
    	Country country = cmbStati.getValue();
    	if(country == null) {
    		txtResult.setText("Selezionare uno stato");
    		return;
    	}
    	String s = "Stati raggiungibili:\n";
    	for(Country c : model.statiRaggiungibili(country)) {
    		s += c.getName()+"\n";
    	}
    	txtResult.setText(s);
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
