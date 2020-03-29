package com.moving.kotlin.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 打开或关闭软键盘
 */
public class KeyBoardUtil {
    /**
     * 弹出键盘
     *
     * @param editText
     */
    public static void showKeyboard(final Context context, final EditText editText) {
        if (editText != null) {
            // 设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            // 请求获得焦点
            editText.requestFocus();
            // 调用系统输入法
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    InputMethodManager mInputManager = (InputMethodManager) context
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    mInputManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
                }
            }, 300);
        }
    }

    // 输入法是否显示着
    public static boolean isShowKeyBoard(EditText edittext) {
        boolean bool = false;
        InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            bool = true;
        }
        return bool;

    }


    /**
     * 此方法只是关闭软键盘
     */
    public static void closeKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && ((Activity) context).getCurrentFocus() != null) {
            if (((Activity) context).getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * //此方法判断键盘是否显示  返回true代表键盘显示 适用于任何界面
     *
     * @param rootView 传入根布局
     * @return
     */
    public static boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        if (null != editText) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     * right设置为屏幕宽度，适用于屏幕宽输入框
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int width = v.getContext().getResources().getDisplayMetrics().widthPixels;
            if (width < l[0] + v.getWidth()) width = l[0] + v.getWidth();
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = width;
//                    right = left + v.getWidth();
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }
}
