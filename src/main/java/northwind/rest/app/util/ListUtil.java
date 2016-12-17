package northwind.rest.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListUtil {
    public static <T> List<T> castList(Class<? extends T> cls, Collection<?> col) {
        List<T> res = new ArrayList<T>(col.size());
        for(Object obj : col) {
            res.add( cls.cast(obj) );
        }
        return res;
    }
}
