package com.shidonghui.mymagic.widget.livewidget;

import java.nio.FloatBuffer;
/**
 * @author ZhangKun
 * @create 2019/6/3
 * @Describe
 */
public interface IFilter {
    int getTextureTarget();

    void setTextureSize(int width, int height);

    void onDraw(float[] mvpMatrix, FloatBuffer vertexBuffer, int firstVertex, int vertexCount,
                int coordsPerVertex, int vertexStride, FloatBuffer texBuffer,
                int textureId, int texStride);

    void releaseProgram();
}
