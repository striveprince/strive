package com.cutv.ningbo.ui.util.rotary;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static Toast toast;

    public ToastUtils() {
    }

    public static void showToast(Context context, String text) {
//        View toastRoot = LayoutInflater.from(context).inflate(R.layout.qupai_common_toast_default_layout, null, false);
//        TextView message = (TextView) toastRoot.findViewById(R.id.toast_info);
//        message.setText(text);
//        if (toast != null) {
//            toast.cancel();
//            toast = null;
//        }
//
//
//        toast = new Toast(context);
//        toast.setView(toastRoot);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.show();
//        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int resID) {
        showToast(context, context.getString(resID));
    }


}
