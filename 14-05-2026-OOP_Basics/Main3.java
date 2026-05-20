// Smart Home Device System

// Base device
class SmartDevice {

    void powerOn() {
        System.out.println("Device powered ON");
    }

    void powerOff() {
        System.out.println("Device powered OFF");
    }
}

// Child class 1
class SmartLight extends SmartDevice {

    void changeBrightness(int level) {
        System.out.println("Light brightness set to " + level);
    }
}

// Child class 2
class SmartAC extends SmartDevice {

    void setTemperature(int temp) {
        System.out.println("AC temperature set to " + temp);
    }
}

// Child class 3
class SmartSpeaker extends SmartDevice {

    void playMusic(String song) {
        System.out.println("Playing: " + song);
    }
}

public class Main3 {
    public static void main(String[] args) {

        SmartLight light = new SmartLight();
        light.powerOn();          // inherited
        light.changeBrightness(70);
        light.powerOff();         // inherited

        System.out.println();

        SmartAC ac = new SmartAC();
        ac.powerOn();             // inherited
        ac.setTemperature(24);
        ac.powerOff();            // inherited

        System.out.println();

        SmartSpeaker speaker = new SmartSpeaker();
        speaker.powerOn();        // inherited
        speaker.playMusic("Imagine Dragons");
        speaker.powerOff();       // inherited
    }
}
