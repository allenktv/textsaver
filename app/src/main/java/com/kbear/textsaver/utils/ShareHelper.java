package com.kbear.textsaver.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import com.kbear.textsaver.R;

/**
 * Created by allen on 8/9/14.
 */
public class ShareHelper {


    public static void copyToClipboard(Context c, String s) {
        ClipboardManager clipboardManager = (ClipboardManager)c.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(s,s);
        clipboardManager.setPrimaryClip(clipData);
    }

    public static void shareMessage(Context c, String s) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
        c.startActivity(Intent.createChooser(sharingIntent, c.getResources().getString(R.string.share_using)));
    }
}
