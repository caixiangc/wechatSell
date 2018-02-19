package com.test.test.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author 浙外吴彦祖
 * @data 2018/2/9 下午9:10
 */
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override  //要重写 serialize 方法否者会标红
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime() / 1000); //把结果返回回去
    }
}
