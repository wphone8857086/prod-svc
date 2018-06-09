package com.jt.plt.product.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：JsonUtil工具类
 * 作者： wephone   
 * 创建日期： 2018年3月16日 下午3:58:57
 * 版权：江泰保险经纪股份有限公司
 */
public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String object2Json(Object o) {
        if (o == null) {
        	  return null;
        }
          

        String s = null;

        try {
            s = mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static <T> List<String> listObject2ListJson(List<T> objects) {
        if (objects == null) {
        	return null;
        }
            

        List<String> lists = new ArrayList<String>();
        for (T t : objects) {
            lists.add(JsonUtil.object2Json(t));
        }

        return lists;
    }

    public static <T> List<T> listJson2ListObject(List<String> jsons, Class<T> c) {
        if (jsons == null) {
        	 return null;
        }
           

        List<T> ts = new ArrayList<T>();
        for (String j : jsons) {
            ts.add(JsonUtil.json2Object(j, c));
        }

        return ts;
    }
    public static <T> T json2Object(String json, Class<T> c) {
        if (StringUtils.hasLength(json) == false) {
        	  return null;
        }
          

        T t = null;
        try {
            t = mapper.readValue(json, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Object(String json, TypeReference<T> tr) {
        if (StringUtils.hasLength(json) == false) {
        	return null;
        }
        T t = null;
        try {
            t = (T) mapper.readValue(json, tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) t;
    }
    /**
     * 
     * @param json
     * @param tr
     * @return
     * 描述： 转json并忽略对象中的null
     */
    public static String Object2JsonLoseNull(Object o) {
    	String json = null;
        try {
        	mapper.setSerializationInclusion(Include.NON_NULL);
        	json = mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(json);
        return json;
    }
    @SuppressWarnings("rawtypes")
    public static <T> Collection<T> fromJson(String json,Class<? extends Collection> collectionClazz,Class<T> clazz){
        if(json == null){
            return null;
        }
        try {
            Collection<T> collection =  mapper.readValue(json, getCollectionType(collectionClazz,clazz));
            return collection;
        } catch (IOException e) {
            logger.error(String.format("json=[%s]", json), e);
        }
        return null;
    }
    private static com.fasterxml.jackson.databind.JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}
    
}
