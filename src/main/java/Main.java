package main.java;

import main.java.model.bean.Temperatura;
import main.java.util.ArduinoSerial;
import main.java.model.dao.TemperaturaDAO;

public class Main {
    public static void main(String[] args) {
        ArduinoSerial arduino = new ArduinoSerial("COM5");
        arduino.initialize();
        TemperaturaDAO dao = new TemperaturaDAO();
        Temperatura temperatura = new Temperatura();

        while (true){
            System.out.println(arduino.read());
            if (arduino.read() != null) {
                temperatura.setValor(Double.parseDouble(arduino.read()));
                dao.save(temperatura);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
