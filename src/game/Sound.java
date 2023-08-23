package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public static void playMusic(String music){

        try{
            File musicPath = new File(music);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);


            }
            else{

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static Clip playSound(String music){
        Clip clip = null;
        try{
            File musicPath = new File(music);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.setFramePosition(96500);
                clip.start();
                if(clip.getMicrosecondPosition() == 3000){
                    clip.close();
                }
            }
            else{

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return clip;
    }
}
