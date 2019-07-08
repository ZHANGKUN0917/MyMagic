package com.shidonghui.mymagic.widget.livewidget;

import android.hardware.Camera;

import com.qiniu.pili.droid.streaming.CameraStreamingSetting;

import java.io.Serializable;

public class CameraConfig implements Serializable {
    public boolean mFrontFacing;
    public CameraStreamingSetting.PREVIEW_SIZE_LEVEL mSizeLevel;
    public CameraStreamingSetting.PREVIEW_SIZE_RATIO mSizeRatio;
    public String mFocusMode;
    public boolean mIsFaceBeautyEnabled;
    public boolean mIsCustomFaceBeauty;
    public float mBeautyLevel;//美颜等级
    public float mWhitenLevel;//美白等级
    public float mReddenLevel;//红润等级
    public boolean mContinuousAutoFocus;
    public boolean mPreviewMirror;
    public boolean mEncodingMirror;

    private static final String[] PREVIEW_SIZE_LEVEL_PRESETS = {
            "SMALL",
            "MEDIUM",
            "LARGE"
    };

    private static final CameraStreamingSetting.PREVIEW_SIZE_LEVEL[] PREVIEW_SIZE_LEVEL_PRESETS_MAPPING = {
            CameraStreamingSetting.PREVIEW_SIZE_LEVEL.SMALL,
            CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM,
            CameraStreamingSetting.PREVIEW_SIZE_LEVEL.LARGE
    };

    private static final String[] PREVIEW_SIZE_RATIO_PRESETS = {
            "4:3",
            "16:9"
    };

    private static final CameraStreamingSetting.PREVIEW_SIZE_RATIO[] PREVIEW_SIZE_RATIO_PRESETS_MAPPING = {
            CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_4_3,
            CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9
    };

    private static final String[] FOCUS_MODE_PRESETS = {
            "AUTO",
            "CONTINUOUS PICTURE",
            "CONTINUOUS VIDEO"
    };

    private static final String[] FOCUS_MODE_PRESETS_MAPPING = {
            Camera.Parameters.FOCUS_MODE_AUTO,
            Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE,
            Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO
    };

    public static CameraConfig buildCameraConfig() {
        CameraConfig cameraConfig = new CameraConfig();

        cameraConfig.mFrontFacing = true;
        cameraConfig.mSizeLevel = PREVIEW_SIZE_LEVEL_PRESETS_MAPPING[1];
        cameraConfig.mSizeRatio = PREVIEW_SIZE_RATIO_PRESETS_MAPPING[1];
        cameraConfig.mFocusMode = FOCUS_MODE_PRESETS_MAPPING[2];
        cameraConfig.mIsFaceBeautyEnabled = true;
        cameraConfig.mIsCustomFaceBeauty = false;
        cameraConfig.mBeautyLevel = 1f;
        cameraConfig.mWhitenLevel = 1f;
        cameraConfig.mReddenLevel = 1f;
        cameraConfig.mContinuousAutoFocus = true;
        cameraConfig.mPreviewMirror = false;
        cameraConfig.mEncodingMirror = false;

        return cameraConfig;
    }
}
