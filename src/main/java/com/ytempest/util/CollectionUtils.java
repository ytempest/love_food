package com.ytempest.util;

import java.util.Collection;

/**
 * @author ytempest
 * Description：
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
