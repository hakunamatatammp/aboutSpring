package com.zzx.common;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * bean转json格式或者json转bean格式, 项目中我们通常使用这个工具类进行json---java互相转化
 */
public class JacksonUtils {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String bean2Json(Object obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}

	public static <T> T json2Bean(String jsonStr, Class<T> objClass)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStr, objClass);
	}
}

