package com.sy.syplayer.player;

import android.media.AudioTrack;

/**
 * Created by ASUS User on 2016/7/30.
 */
public class AudioTrackPlayer {
    private AudioTrack mAudioTrack = null;
    public AudioTrackPlayer(int streamType, int sampleHz, int channel, int encode){
        int minBufferSizeInByte = AudioTrack.getMinBufferSize(sampleHz, channel, encode);
        mAudioTrack = new AudioTrack(streamType, sampleHz, channel, encode, minBufferSizeInByte, AudioTrack.MODE_STREAM);
    }

    public void  open(){
        if (mAudioTrack != null) {
            if (mAudioTrack.getState() == AudioTrack.STATE_INITIALIZED) {
                mAudioTrack.play();
            }
        }
    }

    public void write(byte[] data, int size){
        if (mAudioTrack != null) {
            if (mAudioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING)
            mAudioTrack.write(data, 0, size);
        }
    }

    public void close(){
        if (mAudioTrack != null){
            if (mAudioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
                mAudioTrack.stop();
            }
            mAudioTrack.release();
            mAudioTrack = null;
        }
    }

}
