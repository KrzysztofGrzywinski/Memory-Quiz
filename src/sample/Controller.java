package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;

    private Button[] arrayButtons;

    private boolean firstPressedButton = true;
    private int idSelectedButtonFirst;
    private int idImageFirst;

    @FXML
    void initialize(){

        initializeButtonArray();

        List<Integer> listImage = new LinkedList<>();

        for (int i = 0; i < 2; i++) {
            int sizeArrayButtons = (int)(arrayButtons.length * 0.5f);
            for (int j = 0; j < sizeArrayButtons; j++) {
                listImage.add(j);
            }
        }
        Collections.shuffle(listImage);

        for (int i = 0; i < arrayButtons.length; i++) {
            final int j = i;

            arrayButtons[i].setOnAction(e -> {
                buttonSelect(j, listImage.get(j));
            });
        }
    }

    private void buttonSelect(int idSelectedButton,int idImage){

        if(firstPressedButton){
            firstPressedButton = false;
            idSelectedButtonFirst = idSelectedButton;
            idImageFirst = idImage;
            setImageButton(idSelectedButton,idImage);
        }
        else {
            setImageButton(idSelectedButtonFirst, idImageFirst);
            firstPressedButton = true;
            if (idImage == idImageFirst && idSelectedButton != idSelectedButtonFirst){
                setImageButton(idSelectedButton, idImageFirst);
                arrayButtons[idSelectedButton].setDisable(true);
                arrayButtons[idSelectedButtonFirst].setDisable(true);

            }else {

                arrayButtons[idSelectedButtonFirst].setGraphic(null);
                arrayButtons[idSelectedButton].setGraphic(null);
                buttonSelect(idSelectedButton, idImage);
            }
        }
    }

    private void initializeButtonArray() {
        arrayButtons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12};
    }

    private void setImageButton(int idButton, int idImage){
        Image imageDecline = new Image(getClass().getResourceAsStream(idImage + ".jpg"));
        arrayButtons[idButton].setGraphic(new ImageView(imageDecline));
    }
}
