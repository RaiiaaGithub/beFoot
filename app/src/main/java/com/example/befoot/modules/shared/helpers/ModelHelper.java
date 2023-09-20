package com.example.befoot.modules.shared.helpers;

import java.util.Arrays;

public class ModelHelper {

    private ModelHelper() {}

    public static String[] getFieldsNames(Class<?> clazz) {
        return Arrays.stream(clazz.getFields()).map(field -> field.getName()).toArray(String[]::new);
    }
}
