// Smart Music Player
abstract class MusicPlayer {

    // Abstract behavior 
    abstract void play(String song);

    // Common behavior
    public void stop() {
        System.out.println("Music stopped");
    }
}

// 1: Local music player
class LocalMusicPlayer extends MusicPlayer {

    @Override
    void play(String song) {
        System.out.println("Playing from local storage: " + song);
    }
}


// 2: Streaming music player
class StreamingMusicPlayer extends MusicPlayer {

    @Override
    void play(String song) {
        System.out.println("Streaming online: " + song);
    }
}
public class Main2 {
    public static void main(String[] args) {

        MusicPlayer player;

        player = new LocalMusicPlayer();
        player.play("Shape of You");
        player.stop();

        System.out.println();

        player = new StreamingMusicPlayer();
        player.play("SkyFall");
        player.stop();
    }
}
