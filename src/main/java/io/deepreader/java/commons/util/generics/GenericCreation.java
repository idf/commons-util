package io.deepreader.java.commons.util.generics;

/**
 * Created by Daniel on 10/08/15.
 */
interface GenericCreation<T> {
    default T[] createGenericArray(int n) {
        return (T[]) new Object[n];
    }

    /**
     * T.class returns Object.class due to erasure
     * @param cls
     * @return an instance of T
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    default T newGenericInstance(Class<T> cls) throws IllegalAccessException, InstantiationException {
        return cls.newInstance();
    }
}
