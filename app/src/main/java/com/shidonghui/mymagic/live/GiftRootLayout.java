package com.shidonghui.mymagic.live;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.model.GiftEntity;

import java.util.Comparator;
import java.util.TreeMap;

public class GiftRootLayout extends LinearLayout implements Animation.AnimationListener, GiftAnimListener {

    public final String TAG = GiftRootLayout.class.getSimpleName();

    private GiftItemLayout firstItemLayout, lastItemLayout;
    private Animation firstGiftItemInAnim, firstGiftItemOutAnim;
    private Animation lastGiftItemInAnim, lastGiftItemOutAnim;
    private final TreeMap<String, GiftEntity> giftBeanTreeMap = new TreeMap<>(new Comparator<String>() {

        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    });

    public GiftRootLayout(Context context) {
        super(context);
        init(context);
    }

    public GiftRootLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GiftRootLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public GiftRootLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        firstGiftItemInAnim = AnimationUtils.loadAnimation(context, R.anim.gift_in);
        firstGiftItemInAnim.setFillAfter(true);
        firstGiftItemOutAnim = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        firstGiftItemOutAnim.setFillAfter(true);

        lastGiftItemInAnim = AnimationUtils.loadAnimation(context, R.anim.gift_in);
        lastGiftItemInAnim.setFillAfter(true);
        lastGiftItemOutAnim = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        lastGiftItemOutAnim.setFillAfter(true);

        firstGiftItemOutAnim.setAnimationListener(this);
        lastGiftItemOutAnim.setAnimationListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!changed || getChildCount() == 0) {
            return;
        }
        firstItemLayout = findViewById(R.id.firstItemLayout);
        firstItemLayout.setAnimListener(this);
        lastItemLayout = findViewById(R.id.lastItemLayout);
        lastItemLayout.setAnimListener(this);
    }

    public void loadGift(GiftEntity giftBean) {
        if (giftBeanTreeMap == null) {
            return;
        }
        String tag = giftBean.getName() + giftBean.getGiftName();
        if (firstItemLayout.getState() == GiftItemLayout.GIFTITEM_SHOW && firstItemLayout.getMyTag().equals(tag)) {
            firstItemLayout.addCount(giftBean.getNumber());
            return;
        }
        if (lastItemLayout.getState() == GiftItemLayout.GIFTITEM_SHOW && lastItemLayout.getMyTag().equals(tag)) {
            lastItemLayout.addCount(giftBean.getNumber());
            return;
        }
        addGift(giftBean);
    }

    public void addGift(GiftEntity giftBean) {
        if (giftBeanTreeMap == null) {
            return;
        }
        if (giftBeanTreeMap.size() == 0) {
            giftBeanTreeMap.put(giftBean.getUserId(), giftBean);
            showGift();
            return;
        }
        for (String key : giftBeanTreeMap.keySet()) {
            GiftEntity result = giftBeanTreeMap.get(key);
            String tagNew = giftBean.getName() + giftBean.getGiftName();
            String tagOld = result.getName() + result.getGiftName();
            if (tagNew.equals(tagOld)) {
                giftBean.setNumber(result.getNumber() + 1);
                giftBeanTreeMap.remove(result.getUserId());
                giftBeanTreeMap.put(giftBean.getUserId(), giftBean);
                return;
            }
        }
        giftBeanTreeMap.put(giftBean.getUserId(), giftBean);
    }

    public void showGift() {
        if (isEmpty()) {
            return;
        }
        if (firstItemLayout.getState() == GiftItemLayout.GIFTITEM_DEFAULT) {

            firstItemLayout.setData(getGift());

            firstItemLayout.setVisibility(View.VISIBLE);

            firstItemLayout.startAnimation(firstGiftItemInAnim);
            firstItemLayout.startAnimation();
        } else if (lastItemLayout.getState() == GiftItemLayout.GIFTITEM_DEFAULT) {
            lastItemLayout.setData(getGift());
            lastItemLayout.setVisibility(View.VISIBLE);
            lastItemLayout.startAnimation(lastGiftItemInAnim);
            lastItemLayout.startAnimation();
        }
    }

    public GiftEntity getGift() {
        GiftEntity giftBean = null;
        if (giftBeanTreeMap.size() != 0) {
            // 获取队列首个礼物实体
            giftBean = giftBeanTreeMap.firstEntry().getValue();
            // 移除队列首个礼物实体
            giftBeanTreeMap.remove(giftBeanTreeMap.firstKey());
        }

        return giftBean;
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return (giftBeanTreeMap == null || giftBeanTreeMap.size() == 0) ? true : false;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        showGift();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void giftAnimEnd(int position) {
        switch (position) {
            case 1:
                firstItemLayout.startAnimation(firstGiftItemOutAnim);
                break;
            case 0:
                lastItemLayout.startAnimation(lastGiftItemOutAnim);
                break;
        }
    }
}