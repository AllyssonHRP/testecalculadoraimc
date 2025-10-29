package org.testecalculadora.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.testecalculadora.model.Pessoa;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    protected TextField txtNome;
    @FXML
    protected TextField txtPeso;
    @FXML
    protected TextField txtAltura;
    // VARIAVEIS LABEL
    @FXML
    protected Label lbIMC;
    @FXML
    protected Label lbClassificacao;

    // VARIAVEIS TABELA
    @FXML
    TableView<Pessoa> tbPessoas;
    @FXML
    TableColumn<Pessoa, Integer> colId;
    @FXML
    TableColumn<Pessoa, String> colNome;
    @FXML
    TableColumn<Pessoa, Float> colPeso;
    @FXML
    TableColumn<Pessoa, Float> colAltura;
    @FXML
    TableColumn<Pessoa, Float> colIMC;
    private int proximoID = 0;

    Pessoa pessoa;
    List<Pessoa> listaPessoas;
    ObservableList<Pessoa> observableListPessoas;

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pessoa = new Pessoa();
        this.listaPessoas = new ArrayList<>();
        vinculoComTabela();
    }

    public void vinculoComTabela(){
        colId.setCellValueFactory( new PropertyValueFactory<>("ID"));
        colNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colPeso.setCellValueFactory( new PropertyValueFactory<>("peso"));
        colAltura.setCellValueFactory( new PropertyValueFactory<>("altura"));
        colIMC.setCellValueFactory( new PropertyValueFactory<>("imc"));
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // EVENTOS
    @FXML
    public void onClickCalcularIMC(){
        lerFormulario();
        this.pessoa.classificacaoIMC();
        exibirClassificacaoIMC();
        exibirClassificacaoIMC();
        System.out.println(this.pessoa.toString());
    }
    @FXML
    public void onClickSalvarIMC(){
        lerFormulario();
        int novoID = ++proximoID;
        this.pessoa.setID(novoID);
        this.listaPessoas.add(pessoa);
        atualizarTableView();
    }
    @FXML
    public void onClickNovo(){
        this.pessoa = new Pessoa();
        txtNome.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
    }

    public void lerFormulario(){
        this.pessoa.setNome( txtNome.getText() );
        this.pessoa.setPeso( Float.parseFloat(txtPeso.getText()) );
        this.pessoa.setAltura( Float.parseFloat(txtAltura.getText()) );

    }
    public void exibirClassificacaoIMC(){
        DecimalFormat df = new DecimalFormat("#0.00");
        lbIMC.setText(df.format(this.pessoa.calcularIMC()));
        lbClassificacao.setText(this.pessoa.classificacaoIMC() );
    }
    public void atualizarTableView(){
        this.observableListPessoas = FXCollections.observableList(this.listaPessoas);
        this.tbPessoas.setItems(this.observableListPessoas);

    }

}