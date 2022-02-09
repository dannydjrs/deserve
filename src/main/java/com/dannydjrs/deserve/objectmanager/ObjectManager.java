package com.dannydjrs.deserve.objectmanager;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dannydjrs
 */
public class ObjectManager {
    private static ObjectManager instance = null;
    private Map<String, Object> cache;

    /**
     * 
     */
    private ObjectManager() {
        this.cache = new HashMap<>();
    }

    /**
     * 
     * @return
     */
    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
        }

        return instance;
    }

    /**
     * 
     * @param className
     * @return
     */
    public Object get(String className) {
        if (this.cache.get(className) == null) {
            this.cache.put(className, this.create(className));
        }
        
        return this.cache.get(className);
    }

    /**
     * 
     * @param className
     * @return
     */
    public Object create(String className) {
        try {
            Constructor<?> constructor = Class.forName(className).getConstructors()[0];
                    
            return constructor.newInstance(
                Arrays.stream(constructor.getParameterTypes())
                .map(p -> this.get(p.toString().replace("class ", ""))) //TODO: NEEDS TO BE A BETTER WAY TO GET CLASSNAME FROM PARAMETER TYPES
                .toArray()
            );
        } catch (Exception ex) {
            return null;
        }
    }
}
