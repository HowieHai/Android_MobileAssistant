package com.example.shenhaichen.mobileassistant.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shenhaichen.mobileassistant.R;

/**
 * Created by shenhaichen on 26/03/2018.
 */

public class LoadingButton extends RelativeLayout {

    //region Variables
    static final int DEFAULT_COLOR = android.R.color.white;
    public static final int IN_FROM_RIGHT = 0;
    public static final int IN_FROM_LEFT = 1;

    private int mDefaultTextSize;
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private String mLoadingText;
    private String mButtonText;
    private float mTextSize;
    private int mTextColor;
    private boolean mIsLoadingShowing;
    private Typeface mTypeface;
    private Animation inRight;
    private Animation inLeft;
    private int mCurrentInDirection;
    private boolean mTextSwitcherReady;

    //region Constructors
    public LoadingButton(Context context) {
        super(context);
        init(context, null);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 自定义view中的属性初始化
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        //默认的文本字体大小
        mDefaultTextSize = getResources().getDimensionPixelSize(R.dimen.text_default_size);
        mIsLoadingShowing = false;
        //布局文件，应用merge标签，减少层节点
        LayoutInflater.from(getContext()).inflate(R.layout.view_loading_button, this, true);

        mProgressBar = findViewById(R.id.progress);
        mTextView =  findViewById(R.id.text);

        if (attrs != null) {
            //设置所需的属性的format
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.LoadingButton,0, 0);
            try {
                float textSize = a.getDimensionPixelSize(R.styleable.LoadingButton_textSize, mDefaultTextSize);
                setTextSize(textSize);

                String text = a.getString(R.styleable.LoadingButton_text);
                setText(text);

                mLoadingText = a.getString(R.styleable.LoadingButton_loadingText);

                if (mLoadingText == null) {
                    mLoadingText = getContext().getString(R.string.default_loading);
                }

                int progressColor = a.getColor(R.styleable.LoadingButton_progressColor, getResources().getColor(DEFAULT_COLOR));
                setProgressColor(progressColor);

                int textColor = a.getColor(R.styleable.LoadingButton_textColor, getResources().getColor(DEFAULT_COLOR));

                setTextColor(textColor);

            } finally {
                a.recycle();
            }
        } else {
            int white = getResources().getColor(DEFAULT_COLOR);
            mLoadingText = getContext().getString(R.string.default_loading);
            setProgressColor(white);
            setTextColor(white);
            setTextSize(mDefaultTextSize);
        }


    }

    public float getTextSize() {
        return mTextSize;
    }

    public Typeface getTypeface() {
        return mTypeface;
    }

    public void setProgressColor(int colorRes) {
        mProgressBar.getIndeterminateDrawable()
                .mutate()
                .setColorFilter(colorRes, PorterDuff.Mode.SRC_ATOP);
    }

    public void setTypeface(@NonNull Typeface typeface) {
        this.mTypeface = typeface;
    }

    public void setAnimationInDirection(int inDirection) {
        mCurrentInDirection = inDirection;
    }

    public void setText(String text) {
        if (text != null) {
            mButtonText = text;
            mTextView.setText(text);
        }
    }

    public void setLoadingText(String text) {
        if (text != null) {
            mLoadingText = text;
        }
    }

    public void showLoading() {
        if (!mIsLoadingShowing) {
            mProgressBar.setVisibility(View.VISIBLE);
            mTextView.setText(mLoadingText);
            mIsLoadingShowing = true;
            setEnabled(false);
        }
    }

    public void showButtonText() {
        if (mIsLoadingShowing) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mTextView.setText(mButtonText);
            mIsLoadingShowing = false;
            setEnabled(true);
        }
    }

    public boolean isLoadingShowing() {
        return mIsLoadingShowing;
    }

    private void setTextSize(float size) {
        mTextSize = size;
    }

    private void setTextColor(int textColor) {
        this.mTextColor = textColor;
        mTextView.setTextColor(textColor);
    }
}
