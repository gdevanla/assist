package com.ser.statecarver.xstreamcarver;

import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class StaticReflectionProvider extends Sun14ReflectionProvider {
    @Override
    protected boolean fieldModifiersSupported(Field field) {
        return !(Modifier.isTransient(field.getModifiers()));
    }
}
