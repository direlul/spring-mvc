package ru.saburov.springmvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ControllerUtils {

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errorsMap =  bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField() + "Error",
                        FieldError::getDefaultMessage
                ));
        return errorsMap;
    }

    public static int[] merge(int[]... intArrays) {
        return Arrays.stream(intArrays).flatMapToInt(Arrays::stream)
                .toArray();
    }
}
