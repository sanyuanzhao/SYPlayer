package com.sy.syplayer.player;

import android.media.AudioFormat;
import android.media.AudioManager;

import com.sy.syplayer.MyApplication;
import com.sy.syplayer.decoder.AudioProperty;
import com.sy.syplayer.decoder.SYDecoder;

/**
 * Created by ASUS User on 2016/7/30.
 */
public class Player {
    private SYDecoder mDecoder = null;
    private boolean bIsPlaying = false;

    public final static int ERROR_PLAY_OPEN_DEMUX = -1;
    public final static int ERROR_PLAY_ISBUSY = 2;
    public final static int SUCCESS_PLAY = 0;

    public Player() {
        mDecoder = new SYDecoder();
    }

    public int play(String url){
       return play(url, null);
    }

    public int play(String url, AudioProperty oProperty){
        if (bIsPlaying){
            return ERROR_PLAY_ISBUSY;
        }
        if (oProperty == null){
            oProperty = new AudioProperty();
        }
        int sessionId  =mDecoder.native_audio_open_demux(url, oProperty);
        if (sessionId > 0){
            async_decode(oProperty,sessionId);
            return SUCCESS_PLAY;
        }
        return ERROR_PLAY_OPEN_DEMUX;
    }

    public void stop(){
       if (bIsPlaying){
           bIsPlaying = false;
       }
    }

    private void async_decode(final AudioProperty oProperty, final int sessionId){
        Runnable oRun = new Runnable() {
            @Override
            public void run() {
                MyApplication.getAppliction().showMsg(oProperty.toString());
                MyApplication.getAppliction().showMsg("sessionID : " + sessionId);
                bIsPlaying = true;
                byte[] data = new byte[SYDecoder.MAX_AUDIO_FRAME_SIZE];
                if (sessionId > 0){
                    MyApplication.getAppliction().showMsg("start decoded");
                    AudioTrackPlayer player = new AudioTrackPlayer(AudioManager.STREAM_MUSIC, oProperty.mSample, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
                    MyApplication.getAppliction().showMsg("start play...");
                    player.open();
                    int read = 0;
                    do{
                        read = mDecoder.native_audio_read_data_decoded(sessionId, data, data.length);
                        //MyApplication.getAppliction().showMsg("read decoded data : " + read);
                        if (read > 0){
                            player.write(data, read);
                        }
                    }while(read >= 0 && bIsPlaying);
                    MyApplication.getAppliction().showMsg("end decoded");
                    mDecoder.native_audio_close_demux(sessionId);
                    player.close();
                    bIsPlaying = false;
                }

            }
        };
        MyApplication.getAppliction().runOnBackground(oRun, 0);
    }


}
