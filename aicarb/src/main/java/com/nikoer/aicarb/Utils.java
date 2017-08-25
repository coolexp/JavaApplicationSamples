package com.nikoer.aicarb;

import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    private static ObjectMapper jackson = new ObjectMapper();
	public static String toJson(Object obj) {
        try {
            return jackson.writeValueAsString(obj);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
	public static <T> T readValue(String content, Class<T> valueType) {
		try {
			return jackson.readValue(content, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
