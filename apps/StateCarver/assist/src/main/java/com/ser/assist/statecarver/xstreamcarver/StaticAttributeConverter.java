package com.ser.assist.statecarver.xstreamcarver;

import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.mapper.Mapper;

public class StaticAttributeConverter extends ReflectionConverter {

    public StaticAttributeConverter(Mapper mapper,
                                ReflectionProvider reflectionProvider) {
        super(mapper, reflectionProvider);
    }

    public boolean canConvert(Class clazz) {
        return true;
    }
}
