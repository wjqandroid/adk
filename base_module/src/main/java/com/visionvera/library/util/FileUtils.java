package com.visionvera.library.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileUtils {
    public static String getFromAssets(Context context, String fileName) {
        String line = "";
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            while ((line = bufReader.readLine()) != null)
                Result += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }
}
