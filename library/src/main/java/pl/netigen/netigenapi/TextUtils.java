package pl.netigen.netigenapi;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TextUtils {
    public static String concatenateAboutUs(List<String> stringArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringArrayList.size(); i++) {
            stringBuilder.append(stringArrayList.get(i));
        }
        return stringBuilder.toString();
    }
}
