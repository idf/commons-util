package io.deepreader.java.commons.util.reflect;

import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 20/07/2016.
 */
public class DynamicProxySample {
    public interface FontProvider {
        Font getFont(String name);
    }

    public static class FontProviderFromDisk implements FontProvider {
        @Override
        public Font getFont(String name) {
            return null;
        }
    }
    /**
     * Static Proxy
     * AOP concept, add cache support
     * Implements an interface while delegating to an object with that interface
     */
    public static class StaticCachedFontProvider implements FontProvider {
        private FontProvider fontProvider;
        private Map<String, Font> cached;

        public StaticCachedFontProvider(FontProvider fontProvider) {
            this.fontProvider = fontProvider;
        }

        public Font getFont(String name) {
            if (!cached.containsKey(name)) {
                cached.put(name, fontProvider.getFont(name));
            }
            return cached.get(name);
        }
    }

    /**
     * This is Dynamic Proxy compared to static proxy
     */
    public static class DynamicCachedProviderHandler implements InvocationHandler {
        private Map<String, Object> cached = new HashMap<>();
        private Object target;

        public DynamicCachedProviderHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Type[] types = method.getParameterTypes();
            if (method.getName().matches("get.+") && (types.length == 1) &&
                    (types[0] == String.class)) {
                String key = (String) args[0];
                Object value = cached.get(key);
                if (value == null) {
                    value = method.invoke(target, args);
                    cached.put(key, value);
                }
                return value;
            }
            return method.invoke(target, args);
        }
    }

    public static abstract class ProviderFactory {
        public static FontProvider getFontProvider() {
            Class<FontProvider> targetClass = FontProvider.class;
            return (FontProvider) Proxy.newProxyInstance(targetClass.getClassLoader(),
                    new Class[] {targetClass},
                    new DynamicCachedProviderHandler(new FontProviderFromDisk()));
        }
    }
}
