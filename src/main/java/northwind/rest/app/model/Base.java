package northwind.rest.app.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Base class for all models
 */
public class Base {
    protected <T> void genericSetFromObject(Class<T> cls, T obj, List<String> fields) {
        for (String f : fields) {
            Method set, get;
            String fUCfirst = f.substring(0, 1).toUpperCase() + f.substring(1);
            try {
                get = cls.getMethod("get" + fUCfirst);
                Object val = get.invoke(obj);
                set = cls.getMethod("set" + fUCfirst, val.getClass());
                set.invoke(this, val);
            } catch (SecurityException|NoSuchMethodException|IllegalAccessException|IllegalArgumentException|InvocationTargetException  e) {
                throw new RuntimeException("Error, cannot get/set field: " + f);
            }
        }
    }

    public Object getId() { return null; }
}
