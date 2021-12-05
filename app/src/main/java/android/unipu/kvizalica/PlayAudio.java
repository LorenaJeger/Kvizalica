package android.unipu.kvizalica;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayAudio {
    private Context mContext;
    private MediaPlayer mediaPlayer;

    public PlayAudio(Context mContext) {
        this.mContext = mContext;
    }

    public void setAudioforAnswer(int flag){

    switch (flag){

        case 1:
            int correctAudio=R.raw.yayy_kvizalica_aud;
            playMusic(correctAudio);
            break;

        case 2:
            int wrongAudio=R.raw.wrong_kvizalica;
            playMusic(wrongAudio);
            break;

        case 3:
            int gameOverAudio=R.raw.gameover_;
            playMusic(gameOverAudio);
            break;
    }

    }

    private void playMusic(int audiofile) {
        mediaPlayer=MediaPlayer.create(mContext,audiofile);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
            }
        });

    }
}
