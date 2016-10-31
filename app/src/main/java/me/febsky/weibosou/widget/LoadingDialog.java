package me.febsky.weibosou.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.febsky.weibosou.R;


/**
 * Author: liuqiang
 * Date: 2016-09-19
 * Time: 17:37
 * Description:
 */
public class LoadingDialog extends Dialog {

    private AnimationDrawable animDrawable;

    public LoadingDialog(Context context) {
        this(context, R.style.CircleProgressDialog);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(getContext());
        this.setCancelable(true);
    }


    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_circle_progress, null);// 得到加载view
        // main.xml中的ImageView
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_progress_logo);

        animDrawable = (AnimationDrawable) imageView.getDrawable();
        imageView.setBackgroundResource(R.drawable.bg_loading_dialog);

        setContentView(v,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );


    }

    @Override
    public void show() {
        super.show();
        if (animDrawable != null) {
            animDrawable.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (animDrawable != null) {
            animDrawable.stop();
        }
    }
}
