/*
 * Copyright (C) 2015 tyrantgit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shidonghui.mymagic.widget.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.shidonghui.mymagic.R;


public class HeartView extends android.support.v7.widget.AppCompatImageView {

    private int[] mHeartResId = {R.drawable.anim_heart1, R.drawable.anim_heart2, R.drawable.anim_heart3, R.drawable.anim_heart4, R.drawable.anim_heart5};

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HeartView(Context context) {
        super(context);
    }

    public void setHeartView(int position) {
        Bitmap heart = createHeart(position);
        setImageDrawable(new BitmapDrawable(getResources(), heart));
    }

    public Bitmap createHeart(int position) {
        Bitmap heart = BitmapFactory.decodeResource(getResources(), mHeartResId[position]);
        Bitmap bm = createBitmapSafely(heart.getWidth(), heart.getHeight());
        if (bm == null) {
            return null;
        }
        Canvas canvas = new Canvas();
        canvas.setBitmap(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(heart, 0, 0, p);
        return bm;
    }

    private static Bitmap createBitmapSafely(int width, int height) {
        try {
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
        return null;
    }

}
