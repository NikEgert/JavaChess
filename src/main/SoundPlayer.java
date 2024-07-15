package main;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {
    String move = "src/assets/sfx/Chess_move.wav";
    String checkMate = "src/assets/sfx/Chess_checkmate.wav";
    String check = "src/assets/sfx/Chess_check.wav";
    String take = "src/assets/sfx/Chess_taken.wav";

    public void playSound(String filePath) {
        new Thread(() -> {
            try {
                File soundPath = new File(filePath);
                if (soundPath.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                } else {
                    System.out.println("File cannot be found: " + filePath);
                }
            } catch (Exception e) {
                System.out.println("Error playing sound: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public void moveSound() {
        playSound(move);
    }

    public void mateSound() {
        playSound(checkMate);
    }

    public void checkSound() {
        playSound(check);
    }

    public void takeSound() {
        playSound(take);
    }
}
