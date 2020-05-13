package Snowman;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateStar {

private Button buttonStar;
private Label innerStarRadiusLabel;
private Label outerStarRadiusLabel;
private Label numberOfRaysLabel;
private Label startAngleRLabel;

private TextField innerStarRadius;
private TextField outerStarRadius;
private TextField numberOfRays;
private TextField startAngleR;


CreateStar() {
    this.outerStarRadiusLabel = new Label("Введите внешний радиус звезды : ");
    this.innerStarRadiusLabel = new Label("Введите внутренний радиус звезды : ");
    this.numberOfRaysLabel = new Label("Введите количество лучей звезд : ");
    this.startAngleRLabel = new Label("Введите угол поворота звезды : ");

    this.innerStarRadius = new TextField();
    this.outerStarRadius = new TextField();
    this.numberOfRays = new TextField();
    this.startAngleR = new TextField();

    this.buttonStar = new Button("Нарисовать звезду !");
}


public void Star (Stage primaryStage) {

    DropShadow dropShadow = new DropShadow();
    dropShadow.setRadius(12.0);
    dropShadow.setOffsetX(12.0);
    dropShadow.setOffsetY(12.0);
    dropShadow.setColor(Color.color(0.1, 0.8, 0.9));
    buttonStar.setEffect(dropShadow);


    Reflection reflection = new Reflection();

    reflection.setTopOpacity(0.2);
    reflection.setBottomOpacity(0.5);
    reflection.setTopOffset(10.8);
    reflection.setFraction(12);

    Text text = new Text();
    text.setX(20.0);
    text.setY(200.0);
    text.setCache(true);
    text.setText("   Star ");
    text.setFill(Color.AQUAMARINE);
    text.setFont(Font.font(null, FontPosture.ITALIC, 80));

    text.setEffect(reflection);


    Star star = new Star();
    buttonStar.setOnAction(e -> star.drawStar(primaryStage, innerStarRadius, outerStarRadius, numberOfRays, startAngleR));


    GridPane root = new GridPane();
    root.addRow(0, innerStarRadiusLabel, innerStarRadius);
    root.addRow(1, outerStarRadiusLabel, outerStarRadius);
    root.addRow(2, startAngleRLabel, startAngleR);
    root.addRow(3, numberOfRaysLabel, numberOfRays);
    root.addRow(4 , new Label());
    root.addRow(5, buttonStar);
    root.addRow(6, text);

    primaryStage.setScene(new Scene(root, 800, 500));
    primaryStage.setTitle(" Star ");
    primaryStage.show();


  }

}

