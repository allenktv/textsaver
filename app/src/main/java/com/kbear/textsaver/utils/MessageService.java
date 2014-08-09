package com.kbear.textsaver.utils;

import java.util.Set;

/**
 * Created by allen on 8/9/14.
 */
public class MessageService {
    private static final String key = "TextSaverTexts";

    public static void saveMessage(String old, String s) {
        Set<String> strings = getAllMessages();
        strings.remove(old);
        strings.add(s);
        saveAllMessages(strings);
    }

    public static void deleteMessage(String s) {
        Set<String> strings = getAllMessages();
        strings.remove(s);
        saveAllMessages(strings);
    }


    public static Set<String> getAllMessages() {
        return SharedPreferencesHelper.getInstance().getStringSet(key);
    }

    public static void saveAllMessages(Set<String> strings) {
        SharedPreferencesHelper.getInstance().setPreference(key, strings);
    }

    public static void deleteAllMessages() {
        SharedPreferencesHelper.getInstance().deleteAll();
    }
}