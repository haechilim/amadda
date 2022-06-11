package com.example.a_matta_library.helper;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public static void toast(Context context, String message, boolean isShort) {
        Toast.makeText(context, message, isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG).show();
    }
}
