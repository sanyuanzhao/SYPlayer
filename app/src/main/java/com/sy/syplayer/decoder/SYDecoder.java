package com.sy.syplayer.decoder;

/**
 * Created by ASUS User on 2016/7/19.
 */
public class SYDecoder {
    public static final int  MAX_AUDIO_FRAME_SIZE = 192000;
    static{
        System.loadLibrary("ffmpeg_jni");
        System.loadLibrary("swresample-2");
        System.loadLibrary("swscale-4");
        System.loadLibrary("avutil-55");
        System.loadLibrary("avformat-57");
        System.loadLibrary("avfilter-6");
        System.loadLibrary("avcodec-57");
    }

    public native int native_audio_open_demux(String strAudioUrl, AudioProperty oAudioProperty);
    public native int native_audio_read_data_decoded(int sessionId, byte[] buffer, int size);
    public native int native_audio_seek(int sessionId, int seek);
    public native void native_audio_close_demux(int sessionId);
}
