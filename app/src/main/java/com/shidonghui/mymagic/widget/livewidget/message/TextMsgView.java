package com.shidonghui.mymagic.widget.livewidget.message;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.shidonghui.mymagic.R;
import com.shidonghui.mymagic.utils.EmojiManager;

import io.rong.imlib.model.MessageContent;

public class TextMsgView extends BaseMsgView {

    private TextView msgText;

    public TextMsgView(Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.msg_text_view, this);
        msgText = (TextView) view.findViewById(R.id.msg_text);
    }

    @Override
    public void setContent(MessageContent msgContent) {
        TextMessage msg = (TextMessage) msgContent;
        String name = msg.getName() + ": ";
        String content = EmojiManager.parse(msg.getContent(), msgText.getTextSize()).toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableStringBuilder colorFilterStr = new SpannableStringBuilder(name);
        colorFilterStr.setSpan(new ForegroundColorSpan(Color.parseColor("#ffe484")), 0, name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(colorFilterStr);
        spannableStringBuilder.append(content);
        msgText.setText(spannableStringBuilder);
    }
}
