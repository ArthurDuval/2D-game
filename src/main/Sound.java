package main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;
    private URL soundURL[] = new URL[30];
    private FloatControl gainControl;

    public Sound() {
        try {
            soundURL[0] = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/sound/Pokemon Platinum - Route 201.wav").toURI().toURL();
            soundURL[1] = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/sound/me_obtained_key_item.wav").toURI().toURL();
            soundURL[2] = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/sound/Pkmn get.wav").toURI().toURL();
            soundURL[3] = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/sound/Rock Smash.wav").toURI().toURL();
            soundURL[4] = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/sound/Player bump.wav").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            setVolume(0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
    public void setVolume(float volume) {
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
