package Snowman;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Snowman extends GridPane {

private Button fillCirclesColourRed;
private Button fillCirclesColourRdm;
private Button gradientGrey;
private Button drawStar;
private Integer amountOfCircles;
private Integer minCircleRadius;
private Integer maxCircleRadius;

Snowman (Integer amountOfCircles, Integer minCircleRadius, Integer maxCircleRadius)  {
    this.amountOfCircles = amountOfCircles;
    this.maxCircleRadius = maxCircleRadius;
    this.minCircleRadius = minCircleRadius;

    checkingForReasonableValues();

    this.fillCirclesColourRed = new Button("Залить красным");
    this.fillCirclesColourRdm = new Button("Залить случайным цветом");
    this.gradientGrey = new Button("Градиент");
    this.drawStar = new Button("Нарисовать звезду");
}

    public void drawSnowman (Stage stage) {

        System.out.println("\nSnowman !");

        System.out.println("Количество кругов  =>  " + amountOfCircles);
        System.out.println("Минимальный радиус круга   =>  " + minCircleRadius);
        System.out.println("Максимальный радиус круга  =>  " + maxCircleRadius);


        GridPane group = new GridPane();

        Scene scene = new Scene(group,880,620);

        stage.setTitle(" Snowman ");
        stage.setScene(scene);


        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(9.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.2, 0.2, 0.3));
        fillCirclesColourRed.setEffect(dropShadow);
        fillCirclesColourRdm.setEffect(dropShadow);
        gradientGrey.setEffect(dropShadow);

        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setRadius(10.0);
        dropShadow2.setOffsetX(8.0);
        dropShadow2.setOffsetY(8.0);
        dropShadow2.setColor(Color.color(0.1, 0.8, 0.9));
        drawStar.setEffect(dropShadow2);

        drawStar.setOnAction(e -> {
            CreateStar star = new CreateStar();                //  Добавить кнопку "Нарисовать звезду"
            star.Star(stage);                                  //  (открывается соответствующая сцена)
        });


        fillCirclesColourRed.setOnAction(e -> {
            for (Node node : group.getChildren()) {            //  Закрасить все ноды которые являются фигурами
                if (node instanceof Shape) {                   // (т.е. наследуются от класса Shape)
                    ((Shape) node).setFill(Color.RED);
                }
            }
        });


        fillCirclesColourRdm.setOnAction(e -> {
            for (Node node : group.getChildren()) {           //  Закрасить все ноды которые являются фигурами
                if (node instanceof Shape) {
                    ((Shape) node).setFill(Color.color(randomColor()[0], randomColor()[1], randomColor()[2]));
                }
            }
        });


        Circle circle = new Circle();

        AtomicReference<Shape> snowman = new AtomicReference<>(Shape.union(circle, circle));

        gradientGrey.setOnAction(e -> {                            //  Добавить кнопку градиента  (фигура <snowman> уже создана)
            snowman.set(gradientSnowmanGrey(snowman.get()));
        });


        //  Создание снеговика

        Group snowmanHead = new Group();           //  Создается группа фигур головы (должна содержать 3 круга -> глаза и нос)

        int firstCircleX = 200;                    //  Подготовка начальных координат для головы снеговика
        int firstCircleY = 200;
        int firstCircleRadius = (int) ((Math.random() * maxCircleRadius + minCircleRadius));


        int previousCircleRadius = firstCircleRadius;   //  Это рабочие переменные, нужны чтобы сохранить первоначальные координаты первого круга (головы снеговика)
        int circleY = firstCircleY;
        int circleRadius;


        //  Прорисовка всех кругов

        for (int i = 0; i < amountOfCircles; i++) {

           Circle circleNext = new Circle();

           circleRadius = (int) ((Math.random() * maxCircleRadius + minCircleRadius));

           circleY = circleY + circleRadius + previousCircleRadius + ((int) (circleNext.getStrokeWidth()));

           previousCircleRadius = circleRadius;

           circleNext.setCenterY(circleY);
           circleNext.setCenterX(firstCircleX);    //  так как ось X для всех кругов одинакова
           circleNext.setRadius(circleRadius);

           circleNext.setStroke(Color.color(randomColor()[0], randomColor()[1], randomColor()[2]));

           snowman.set(Shape.union(snowman.get(), circleNext));


      /*  Фрагмент кода, отвечающий за прорисовку носа снеговика  (а с образованием цикла - и глаз)

            //  Если это первый круг (голова снеговика)
            if (i == 0) {

                Circle circle1 = new Circle();        //  прорисовываем нос

                int x;   //  Эти переменные понадобятся для вычисления функции Math.random()
                int y;

                circle1.setCenterX(firstCircleRadius);    //  пока что в центре головы
                circle1.setCenterY(firstCircleY);
                circle1.setRadius((int) (firstCircleRadius / 7));   //  радиусом в 1/7 радиуса головы

                circle1.setStroke(Color.color(Snowman.randomColor()[0], Snowman.randomColor()[1], Snowman.randomColor()[2]));
                circle1.setFill(Color.RED);

                snowmanHead.getChildren().add(circle1);   //  Добавляем этот круг (нос) в группу фигур головы

              // group.getChildren().add(snowmanHead);   //   Потом нужно добавить группу <snowmanHead> к фигуре snowman

                 snowman.set(Shape.union(snowman.get(), circle1));    //  Пока что простое слияние фигур
                                                                      //  но проблема в том, что текущий круг (как бы нос снеговика)
                                                                      //  рисуется не в круге головы (как должен), а за её пределами
                }

       //  Конец фрагмента, отвечающего за прорисовку носа (позже и глаз) снеговика
       //  Результат - нос рисуется за пределами головы снеговика
 //  */


        }

        snowman.get().setStroke(Color.color(randomColor()[0], randomColor()[1], randomColor()[2]));
        snowman.get().setFill(Color.WHITE);


        group.add(snowman.get(), 0, 0);
        group.add(fillCirclesColourRed, 1, 0);
        group.add(fillCirclesColourRdm, 2,0);
        group.add(gradientGrey, 3,0);
        group.add(drawStar, 5, 0);


        stage.centerOnScreen();

        stage.show();

    }


    private Shape gradientSnowmanGrey (Shape shape) {

       LinearGradient lg = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.BLACK), new Stop(1, Color.WHITE));

        shape.setFill(lg);
        shape.setStroke(Color.GREY);

        return shape;
    }


    public static double[] randomColor() {

        double[] randomColor = new double [3];  // rgb colours

        randomColor[0] = Math.random();
        randomColor[1] = Math.random();
        randomColor[2] = Math.random();

        return randomColor;
    }


    private void checkingForReasonableValues () {

        if (this.amountOfCircles > 100) {this.amountOfCircles = 100;}        //  не в человеческих возможностях строить такие снеговики  :)

        if (this.maxCircleRadius < 2) {this.maxCircleRadius = 2;}   //  т.к. минимальное значение должно быть одновременно и меньше, и хотя бы единицей

        if ((this.amountOfCircles <= 5) && (this.minCircleRadius < 20) && (this.maxCircleRadius > 100)) {    //  зачем нам снеговик который не влезает в экран? :)
            this.maxCircleRadius = 100;}    //  теперь должен поместиться
        if ((this.amountOfCircles <= 5) && (this.minCircleRadius >= 20) && (this.minCircleRadius <= 50) && (this.maxCircleRadius > 80)) {
            this.maxCircleRadius = 80;}
        if ((this.amountOfCircles <= 5) && (this.minCircleRadius > 50) && (this.minCircleRadius < 70) && (this.maxCircleRadius > 70)) {
            this.maxCircleRadius = 70;} else {this.maxCircleRadius = 72;}


        //  Процесс утрамбовки снеговика в экран
        if ((this.amountOfCircles > 5)  &&  (this.amountOfCircles <= 10) && (this.maxCircleRadius > 35))  {this.maxCircleRadius = 35;}
        if ((this.amountOfCircles > 10)  &&  (this.amountOfCircles <= 20) && (this.maxCircleRadius > 18))  {this.maxCircleRadius = 18;}
        if ((this.amountOfCircles > 20)  &&  (this.amountOfCircles <= 50) && (this.maxCircleRadius > 8))  {this.maxCircleRadius = 8;}
        if ((this.amountOfCircles > 50)  &&  (this.amountOfCircles <= 80) && (this.maxCircleRadius > 5))  {this.maxCircleRadius = 5;}
        if ((this.amountOfCircles > 80)  &&  (this.amountOfCircles <= 100) && (this.maxCircleRadius > 4))  {this.maxCircleRadius = 4;}


        //  Минимальный радиус должен быть хоть на чуть-чуть, но меньше максимального (а максимальный не может быть меньше 2)
        if (this.minCircleRadius >= this.maxCircleRadius)  {this.minCircleRadius = (this.maxCircleRadius - 1);}

    }


}

