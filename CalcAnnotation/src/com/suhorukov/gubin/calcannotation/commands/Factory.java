package com.suhorukov.gubin.calcannotation.commands;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class Factory {

    Stack<Double> stack;
    Map<String, Double> defines;

    public Map<String, Command> classesMap = new TreeMap<>();
    public Properties classNamesAsTable;

    public Factory(Stack stack, Map<String, Double> defines) {
        this.stack = stack;
        this.defines = defines;

        try {
            initFromSystemProperties();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getClass().getName() + ": " +
                    ex.getMessage());
        }
    }

    public void initFromSystemProperties() throws Exception {

        classNamesAsTable = new Properties();
        try {
            InputStream in = Factory.class.getResourceAsStream("comm.properties");
            classNamesAsTable.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("couldn't read or load the properties file.");
        }

        for (Map.Entry<Object, Object> objectEntry : classNamesAsTable.entrySet()) {

            String mathOperator = (String) objectEntry.getKey();
            String className = (String) objectEntry.getValue();

            Class cls = Class.forName(className);
            Constructor constructor = cls.getConstructor();
            Command obj = (Command) constructor.newInstance();

            // looking for annotations:
            Field[] fields = cls.getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {

                    if (field.isAnnotationPresent(CResource.class)) {
                        CResource annotation = field.getAnnotation(CResource.class);
                        Type annoType = annotation.type();

                        switch (annoType) {
                            case STACK:
                                field.setAccessible(true);
                                field.set(obj, stack);
                                break;
                            case DEFINE:
                                field.setAccessible(true);
                                field.set(obj, defines);
                                break;
                            default:
                                throw new RuntimeException("Unexpected anno: " + annoType);
                        }
                    }
                }
            }

            classesMap.put(mathOperator, obj);
        }
    }

    public Collection<String> getCmdNames() {
        return Collections.unmodifiableCollection(classesMap.keySet());
    }
}
