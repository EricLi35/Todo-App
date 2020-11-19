package ui;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class RoddyRicch {

    public static void main(String[] args) {
        File theBox = new File("C:\\Users\\hello\\IdeaProjects\\CPSC210\\labs\\project_y5y7z\\src\\Roddy2.wav");
        playSound(theBox);
    }

    static void playSound(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            System.out.println("Exception thrown");
        }
    }


}
