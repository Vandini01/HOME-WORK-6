package Snowman;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IntegerConvertor {

    public Integer convertTextToInteger (Stage primaryStage, TextField TextString) {
        Integer integerValue;
        int value = 0;

        String StringToConvert = TextString.getText();

        try {
            value = Integer.parseInt(StringToConvert);
        }
        catch (Exception ex) {
            System.out.println("Вы ввели что-то не то (или оставили поля незаполненными) !");
            ExceptionProcessing exeption = new ExceptionProcessing();
            exeption.exceptionWindowShow(primaryStage);
        }

        if (value < 0) {value = -(value);}
        if (value > 250) {
            value = 250;      //  Так урежем же осетра наших желаний к возможностям нашей сцены <scene>.  (с) Старинный грузинский тост. :)
        }

        integerValue = (Integer) (value);

        return integerValue;
    }


}
