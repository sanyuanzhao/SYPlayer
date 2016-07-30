package com.sy.syplayer.decoder;

/**
 * Created by ASUS User on 2016/7/26.
 */
public class AudioProperty{
    public int mSample = 0;
    public int mChannel = 0;
    public int mEncode = 0;
    public long mDuration = 0;


    public enum AVSampleFormat {
        AV_SAMPLE_FMT_U8,          ///< unsigned 8 bits
        AV_SAMPLE_FMT_S16,         ///< signed 16 bits
        AV_SAMPLE_FMT_S32,         ///< signed 32 bits
        AV_SAMPLE_FMT_FLT,         ///< float
        AV_SAMPLE_FMT_DBL,         ///< double

        AV_SAMPLE_FMT_U8P,         ///< unsigned 8 bits, planar
        AV_SAMPLE_FMT_S16P,        ///< signed 16 bits, planar
        AV_SAMPLE_FMT_S32P,        ///< signed 32 bits, planar
        AV_SAMPLE_FMT_FLTP,        ///< float, planar
        AV_SAMPLE_FMT_DBLP,        ///< double, planar

        AV_SAMPLE_FMT_NB           ///< Number of sample formats. DO NOT USE if linking dynamically
    };

    @Override
    public String toString() {
        String format = "sample : %s(Hz)\n"
                + "channel : %s\n"
                + "encode : %s\n"
                + "duration : %d(s)\n";
        return String.format(format, mSample, mChannel,  AVSampleFormat.values()[mEncode].name(), mDuration);
    }
}