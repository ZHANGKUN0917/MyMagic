package com.shidonghui.mymagic.widget.livewidget;

import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.WatermarkSetting;

import java.io.Serializable;

import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_HIGH1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_HIGH2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_LOW1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_LOW2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_MEDIUM1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.AUDIO_QUALITY_MEDIUM2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_HIGH1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_HIGH2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_HIGH3;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_LOW1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_LOW2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_LOW3;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_MEDIUM1;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_MEDIUM2;
import static com.qiniu.pili.droid.streaming.StreamingProfile.VIDEO_QUALITY_MEDIUM3;

public class EncodingConfig implements Serializable {
    public AVCodecType mCodecType;
    public boolean mIsAudioOnly;

    public boolean mIsVideoQualityPreset;
    public int mVideoQualityPreset;
    public int mVideoQualityCustomFPS;
    public int mVideoQualityCustomBitrate;
    public int mVideoQualityCustomMaxKeyFrameInterval;
    public StreamingProfile.H264Profile mVideoQualityCustomProfile;
    public StreamingProfile.YuvFilterMode mYuvFilterMode;

    public boolean mIsVideoSizePreset;
    public int mVideoSizePreset;

    public int mVideoSizeCustomWidth;
    public int mVideoSizeCustomHeight;

    public boolean mVideoOrientationPortrait;//屏幕方向

    public boolean mVideoRateControlQuality;

    public StreamingProfile.BitrateAdjustMode mBitrateAdjustMode;
    public int mAdaptiveBitrateMin = -1;
    public int mAdaptiveBitrateMax = -1;

    public boolean mVideoFPSControl;

    public boolean mIsWatermarkEnabled;
    public int mWatermarkAlpha;
    public WatermarkSetting.WATERMARK_SIZE mWatermarkSize;
    public boolean mIsWatermarkLocationPreset;
    public WatermarkSetting.WATERMARK_LOCATION mWatermarkLocationPreset;
    public float mWatermarkLocationCustomX;
    public float mWatermarkLocationCustomY;

    public boolean mIsPictureStreamingEnabled;
    public String mPictureStreamingFilePath;

    public boolean mIsAudioQualityPreset;
    public int mAudioQualityPreset;
    public int mAudioQualityCustomSampleRate;
    public int mAudioQualityCustomBitrate;

    private static final String[] VIDEO_SIZE_PRESETS = {
            "240p(320x240 (4:3), 424x240 (16:9))",
            "480p(640x480 (4:3), 848x480 (16:9))",
            "544p(720x544 (4:3), 960x544 (16:9))",
            "720p(960x720 (4:3), 1280x720 (16:9))",
            "1080p(1440x1080 (4:3), 1920x1080 (16:9))"
    };

    private static final String[] VIDEO_QUALITY_PRESETS = {
            "LOW1(FPS:12, Bitrate:150kbps)",
            "LOW2(FPS:15, Bitrate:264kbps)",
            "LOW3(FPS:15, Bitrate:350kbps)",
            "MEDIUM1(FPS:30, Bitrate:512kbps)",
            "MEDIUM2(FPS:30, Bitrate:800kbps)",
            "MEDIUM3(FPS:30, Bitrate:1000kbps)",
            "HIGH1(FPS:30, Bitrate:1200kbps)",
            "HIGH2(FPS:30, Bitrate:1500kbps)",
            "HIGH3(FPS:30, Bitrate:2000kbps)"
    };

    private static final int[] VIDEO_QUALITY_PRESETS_MAPPING = {
            VIDEO_QUALITY_LOW1,
            VIDEO_QUALITY_LOW2,
            VIDEO_QUALITY_LOW3,
            VIDEO_QUALITY_MEDIUM1,
            VIDEO_QUALITY_MEDIUM2,
            VIDEO_QUALITY_MEDIUM3,
            VIDEO_QUALITY_HIGH1,
            VIDEO_QUALITY_HIGH2,
            VIDEO_QUALITY_HIGH3
    };

    private static final String[] AUDIO_QUALITY_PRESETS = {
            "LOW1(SampleRate:44.1kHZ, Bitrate:18kbps)",
            "LOW2(SampleRate:44.1kHZ, Bitrate:24kbps)",
            "MEDIUM1(SampleRate:44.1kHZ, Bitrate:32kbps)",
            "MEDIUM2(SampleRate:44.1kHZ, Bitrate:48kbps)",
            "HIGH1(SampleRate:44.1kHZ, Bitrate:96kbps)",
            "HIGH2(SampleRate:44.1kHZ, Bitrate:128kbps)"
    };

    private static final int[] AUDIO_QUALITY_PRESETS_MAPPING = {
            AUDIO_QUALITY_LOW1,
            AUDIO_QUALITY_LOW2,
            AUDIO_QUALITY_MEDIUM1,
            AUDIO_QUALITY_MEDIUM2,
            AUDIO_QUALITY_HIGH1,
            AUDIO_QUALITY_HIGH2
    };

    private static final String[] WATERMARK_SIZE_PRESETS = {
            "SMALL",
            "MEDIUM",
            "LARGE",
    };

    private static final WatermarkSetting.WATERMARK_SIZE[] WATERMARK_SIZE_PRESETS_MAPPING = {
            WatermarkSetting.WATERMARK_SIZE.SMALL,
            WatermarkSetting.WATERMARK_SIZE.MEDIUM,
            WatermarkSetting.WATERMARK_SIZE.LARGE
    };

    private static final String[] WATERMARK_LOCATION_PRESETS = {
            "NORTH-WEST",
            "NORTH-EAST",
            "SOUTH-EAST",
            "SOUTH-WEST",
    };

    private static final WatermarkSetting.WATERMARK_LOCATION[] WATERMARK_LOCATION_PRESETS_MAPPING = {
            WatermarkSetting.WATERMARK_LOCATION.NORTH_WEST,
            WatermarkSetting.WATERMARK_LOCATION.NORTH_EAST,
            WatermarkSetting.WATERMARK_LOCATION.SOUTH_EAST,
            WatermarkSetting.WATERMARK_LOCATION.SOUTH_WEST
    };

    public static EncodingConfig buildEncodingConfig() {
        EncodingConfig encodingConfig = new EncodingConfig();

        encodingConfig.mCodecType = AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC;
        // quality
        encodingConfig.mIsVideoQualityPreset = true;
        if (encodingConfig.mIsVideoQualityPreset) {
            encodingConfig.mVideoQualityPreset = VIDEO_QUALITY_PRESETS_MAPPING[6];
        } else {
            encodingConfig.mVideoQualityCustomFPS = 30;
            encodingConfig.mVideoQualityCustomBitrate = 1000;
            encodingConfig.mVideoQualityCustomMaxKeyFrameInterval = 48;
        }

        // size
        encodingConfig.mIsVideoSizePreset = true;
        if (encodingConfig.mIsVideoSizePreset) {
            encodingConfig.mVideoSizePreset = 1;
        } else {
            encodingConfig.mVideoSizeCustomWidth = 480;
            encodingConfig.mVideoSizeCustomHeight = 848;
        }

        // misc
        encodingConfig.mVideoOrientationPortrait = true;
        encodingConfig.mVideoRateControlQuality = true;
        boolean isAdaptiveBitrate = true;
        boolean isManualBitrate = false;
        encodingConfig.mBitrateAdjustMode = isAdaptiveBitrate ? StreamingProfile.BitrateAdjustMode.Auto :
                (isManualBitrate ? StreamingProfile.BitrateAdjustMode.Manual : StreamingProfile.BitrateAdjustMode.Disable);
        if (isAdaptiveBitrate) {
            encodingConfig.mAdaptiveBitrateMin = 150;
            encodingConfig.mAdaptiveBitrateMax = 800;
        }

        encodingConfig.mVideoFPSControl = true;

        // watermark
        encodingConfig.mIsWatermarkEnabled = false;
        if (encodingConfig.mIsWatermarkEnabled) {
            encodingConfig.mWatermarkAlpha = 100;
            encodingConfig.mWatermarkSize = WATERMARK_SIZE_PRESETS_MAPPING[0];
            encodingConfig.mIsWatermarkLocationPreset = true;
            if (encodingConfig.mIsWatermarkLocationPreset) {
                encodingConfig.mWatermarkLocationPreset = WATERMARK_LOCATION_PRESETS_MAPPING[1];
            } else {
                encodingConfig.mWatermarkLocationCustomX = 0.5f;
                encodingConfig.mWatermarkLocationCustomY = 0.5f;
            }
        }

        // picture streaming
        encodingConfig.mIsPictureStreamingEnabled = true;
        String mPictureFilePath = null;
        if (mPictureFilePath != null) {
            encodingConfig.mPictureStreamingFilePath = mPictureFilePath;
        }

        // jianjie_setpop audio
        encodingConfig.mIsAudioQualityPreset = true;
        if (encodingConfig.mIsAudioQualityPreset) {
            encodingConfig.mAudioQualityPreset = AUDIO_QUALITY_PRESETS_MAPPING[3];
        } else {
            encodingConfig.mAudioQualityCustomSampleRate = 44100;
            encodingConfig.mAudioQualityCustomBitrate = 96;
        }
        return encodingConfig;
    }
}
