package com.example.weexdemo.weex;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weexdemo.R;

/**
 * Created by Hilary on 2016/11/18.
 * 文本输入框
 */

public class TextBoxInputView extends LinearLayout {
    private Context mContext;
    private EditText mInputView;
    private TextView mHintTv;
    private OnBackListener mOnBackListener;
    //    private TextBoxTemplate mTemplate;
//    private TextBoxTemplate.TextBoxInputEntity mEntity;
    private String mName, mValue, mDefaultValue, mWarnning;

    public TextBoxInputView(Context context) {
        super(context);
    }

    public TextBoxInputView(Context context, String name, String value, String defaultValue, String warnning, final OnBackListener onBackListener) {
        super(context);
        this.mContext = context;
        this.mOnBackListener = onBackListener;
        setOrientation(VERTICAL);
        setBackgroundColor(0xfff0f0f0);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1.0f;
        addView(layout, layoutParams);

        ScrollView scrollView = new ScrollView(context);
        mInputView = new EditText(context);
        mInputView.setMinHeight(AndroidUtils.dp2px(context, 120));
//        mInputView.setHideUnderline(true);
        mInputView.setHintTextColor(0xff979797);
        mInputView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mInputView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        mInputView.setBackgroundColor(0xffffffff);
        mInputView.setGravity(Gravity.TOP);
        LayoutParams inputViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        inputViewParams.setMargins(32, 16, 32, 8);
        inputViewParams.gravity = Gravity.TOP;
        scrollView.addView(mInputView, inputViewParams);

        LayoutParams scrollParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        scrollParams.setMargins(32, 32, 32, 8);
        layout.addView(scrollView, scrollParams);
        mHintTv = new TextView(context);
        mHintTv.setTextColor(0xff979797);
        mHintTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        LayoutParams hintParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        hintParams.setMargins(24, 0, 24, 8);
        hintParams.weight = 1.0f;
        hintParams.gravity = Gravity.BOTTOM;
        layout.addView(mHintTv, hintParams);


        TextView finishView = new TextView(context);
        finishView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        finishView.setText("完成");
        finishView.setGravity(Gravity.CENTER);
        finishView.setLines(1);
        finishView.setMaxLines(1);
        finishView.setSingleLine(true);
        finishView.setEllipsize(TextUtils.TruncateAt.END);
        finishView.setBackgroundColor(Color.GREEN);
//        finishView.setBackgroundResource(R.mipmap.ic_launcher);
        finishView.setTextColor(0xffffffff);

        LayoutParams finishParams = new LayoutParams(LayoutParams.MATCH_PARENT, AndroidUtils.dp2px(context, 40));
        finishParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
        finishParams.setMargins(16, 16, 16, 16);
        addView(finishView, finishParams);

        this.mName = name;
        this.mValue = value;
        this.mDefaultValue = defaultValue;
        this.mWarnning = warnning;
        finishView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (value == null || value.equals("")) {
            mInputView.setText(defaultValue);
        } else {
            mInputView.setText(value);
        }
        mHintTv.setText(warnning);
    }

    private void finish() {
        mValue = mInputView.getText().toString();
        if (mValue.length() > 10) {
            Toast.makeText(mContext, "error!!!", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
//            mEntity.setMessText(value);
//            mTemplate.updateValue(mEntity);
//            Bundle bundle = new Bundle();
//            bundle.putString("width", String.valueOf(getLayoutParams().width));
//            bundle.putString("height", String.valueOf(getLayoutParams().height));
            mOnBackListener.setOnBack(mName, mValue, mDefaultValue, mWarnning);
        }
    }


//    @Override
//    public String getHeaderName() {
//        return pageTemplate.name;
//    }
//
//    @Override
//    public void setParams(Bundle var1, StepByStepInput.Template template) {
//        super.setParams(var1, template);
//        this.mTemplate = (TextBoxTemplate) template;
//        this.mEntity = ((TextBoxTemplate) template).getValue();
//        mHintTv.setText(mEntity.getHintText());
//        if (mEntity.getMessText().equals("")) {
//            mInputView.setHint(mEntity.getHintText());
//        } else {
//            mInputView.setText(mEntity.getMessText());
//        }
//    }
//
//    @Override
//    protected void onGoBack(Bundle bundle) {
//        mOnBackListener.setOnBack(-1, true, bundle, null, true);
//    }
//
//    @Override
//    public void onBackPressed() {
//        mOnBackListener.setOnBack(-1, true, null, null, true);
//    }
//
//    @Override
//    public void onShow() {
//    }
//
//    @Override
//    public void onDestroyActivity() {
//    }
//
//    @Override
//    public void onNextPressed() {
//        mEntity.setMessText(mInputView.getText().toString());
//        pageTemplate.updateValue(mEntity);
//        Bundle value = new Bundle();
//        value.putString(StepByStepInput.RESULT_KEY, pageTemplate.key);
//        value.putParcelable(StepByStepInput.RESULT_VALUE, mEntity);
//        onGoBack(value);
//    }
//
//    @Override
//    public boolean needNextButton() {
//        return false;
//    }
//
//    @Override
//    public void saveStateParams(Bundle var1) {
//    }
//
//    @Override
//    public void restoreStateParams(Bundle var1) {
//    }
//
//    @Override
//    public boolean needBackButton() {
//        return true;
//    }

    public interface OnBackListener {
        void setOnBack(String name, String value, String defaultValue, String warnning);
    }
}
