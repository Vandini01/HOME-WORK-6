package Snowman;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChooseMenu {

private Label amountOfCirclesLabel;
private Label maxCircleRadiusLabel;
private Label minCircleRadiusLabel;
private TextField amountOfCirclesText;
private TextField maxCircleRadiusText;
private TextField minCircleRadiusText;

private Integer amountOfCircles;
private Integer minCircleRadius;
private Integer maxCircleRadius;

private Button button1;
private Button button2;

ChooseMenu() {
    this.amountOfCirclesLabel = new Label("Количество кругов : ");
    this.maxCircleRadiusLabel = new Label("максимальный радиус круга : ");
    this.minCircleRadiusLabel  = new Label("минимальный радиус круга : ");

    this.amountOfCirclesText = new TextField();
    this.maxCircleRadiusText = new TextField();
    this.minCircleRadiusText = new TextField();

    this.button1 = new Button(" Снеговик ");
    this.button2 = new Button(" Звезда ");
}

    public void StartScene(Stage primaryStage){

        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setRadius(5.0);
        dropShadow1.setOffsetX(3.0);
        dropShadow1.setOffsetY(3.0);
        dropShadow1.setColor(Color.color(0.4, 0.5, 0.5));
        button1.setEffect(dropShadow1);

        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setRadius(12.0);
        dropShadow2.setOffsetX(12.0);
        dropShadow2.setOffsetY(12.0);
        dropShadow2.setColor(Color.color(0.1, 0.8, 0.9));
        button2.setEffect(dropShadow2);

        button1.setOnAction(e -> actionSnowmanButton(primaryStage));
        button2.setOnAction(e -> actionStarButton(primaryStage));

        GridPane root = new GridPane();

        root.addRow(0, amountOfCirclesLabel, amountOfCirclesText);
        root.addRow(1, minCircleRadiusLabel, minCircleRadiusText);
        root.addRow(2, maxCircleRadiusLabel, maxCircleRadiusText);
        root.addRow(3, button1);
        root.addRow(4, button2);

        primaryStage.setTitle(" Snowman ");
        primaryStage.setScene(new Scene(root, 800, 300));
        primaryStage.show();

    }


    private void actionSnowmanButton(Stage primaryStage) {

        IntegerConvertor integerConvertor = new IntegerConvertor();

        amountOfCircles = integerConvertor.convertTextToInteger(primaryStage, amountOfCirclesText);    //  Смысл передачи <primaryStage> в том, что в классе-конверторе
        minCircleRadius = integerConvertor.convertTextToInteger(primaryStage, minCircleRadiusText);    //  есть вызов обработчика исключений (ExceptionProcessing),
        maxCircleRadius = integerConvertor.convertTextToInteger(primaryStage, maxCircleRadiusText);    //  которому и понадобится primaryStage

        if ((amountOfCircles == 0)||(minCircleRadius == 0)||(maxCircleRadius == 0)) {  //  Проверка на заполнение полей
         return;                                                                      //  Значения должны обязательно быть целыми числами
        }                                                                             //  Отрицательные числа преобразуются в положительные

        Snowman snowman = new Snowman(amountOfCircles, minCircleRadius, maxCircleRadius);
        snowman.drawSnowman(primaryStage);

    }


    private void actionStarButton(Stage primaryStage) {

       CreateStar star = new CreateStar();
       star.Star(primaryStage);

    }


}
