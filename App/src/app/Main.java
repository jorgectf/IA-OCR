package app;

import com.asprise.ocr.Ocr;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("spa", Ocr.SPEED_FASTEST);
        String texto = ocr.recognize(new File[]{new File("test-image.png")}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        System.out.println("Result: " + texto);
        ocr.stopEngine();
    }

}
