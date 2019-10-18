package com.task.classifierWorker.util;

import org.springframework.stereotype.Component;

@Component
public class TreePathCreator {
    public String createTreePath(String code) {
        StringBuilder treePath = new StringBuilder();
        treePath.append(code, 0, 2);
        String[] str = code.split("");

        int i = 2;
        while (i <= code.length() - 3 && !str[i].contains("0")) {
            if (i <= 4) {
                treePath.append(".");
            }
            treePath.append(str[i]);

            i++;
        }
        return treePath.toString();
    }
}
