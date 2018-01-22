import com.qcc.annotation.Cache;
import com.qcc.domain.Account;
import com.qcc.utils.CacheMap;
import org.assertj.core.util.Lists;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class NurmalTest {
    public CacheMap<Account> cacheMap;
    public static void main(String []args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        NurmalTest nurmalTest = new NurmalTest();
        Field field = nurmalTest.getClass().getField("cacheMap");
        ParameterizedTypeImpl type = (ParameterizedTypeImpl) field.getGenericType();
       Class clazz = (Class) type.getActualTypeArguments()[0];
        Object o  = clazz.newInstance();
        System.out.println(o);


       Constructor constructor = type.getRawType().getConstructor(String.class);
        Object a =constructor.newInstance("dd");

        int i = 0;

    }
    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("b", "c");
        map.clear();
        System.out.println(map);
    }
}
