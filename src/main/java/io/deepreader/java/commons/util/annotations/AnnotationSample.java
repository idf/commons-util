package io.deepreader.java.commons.util.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 18/07/2016.
 * Java annotation for metadata
 * Based on https://www.mkyong.com/java/java-custom-annotations-example/
 */
public class AnnotationSample {
    /**
     * @interface tells Java this is a custom annotation
     * annotate it on method level like this @Test(enable=false)
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD) //can use in method only.
    public @interface Test {
        // whether should ignore this test?
        public boolean enabled() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE) //on class level
    public @interface TesterInfo {
        public enum Priority {
            LOW, MEDIUM, HIGH
        }

        Priority priority() default Priority.MEDIUM;
        String[] tags() default "";
        String createdBy() default "authorName";
        String lastModified() default "18/07/2016";
    }

    @TesterInfo(
            priority = TesterInfo.Priority.HIGH,
            createdBy = "authorName",
            tags = {"sales", "test" }
    )
    public static class TestExample {
        @Test
        void testA() {
            if (true) {
                throw new RuntimeException("This test always failed");
            }
        }

        @Test(enabled = false)
        void testB() {
            if (false) {
                throw new RuntimeException("This test always passed");
            }
        }

        @Test(enabled = true)
        void testC() {
            // do nothing, this test always passed.
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Testing...");
        int passed = 0, failed = 0, count = 0, ignore = 0;
        Class<TestExample> obj = TestExample.class;

        // Process @TesterInfo
        if (obj.isAnnotationPresent(TesterInfo.class)) {
            Annotation annotation = obj.getAnnotation(TesterInfo.class);
            TesterInfo testerInfo = (TesterInfo) annotation;
            System.out.printf("%nPriority: %s", testerInfo.priority());
            System.out.printf("%nCreatedBy: %s", testerInfo.createdBy());
            System.out.printf("%nTags: %s",
                    Arrays.asList(testerInfo.tags()).stream().collect(Collectors.joining(", ")));
            System.out.printf("%nLastModified: %s%n%n", testerInfo.lastModified());
        }

        // Process @Test
        for (Method method: obj.getDeclaredMethods()) {
            // if method is annotated with @Test
            if (method.isAnnotationPresent(Test.class)) {
                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;
                // if enabled = true (default)
                if (test.enabled()) {
                    try {
                        method.invoke(obj.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", count++, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", count++, method.getName(), ex.getCause());
                        failed++;
                    }
                } else {
                    System.out.printf("%s - Test '%s' - ignored%n", count++, method.getName());
                    ignore++;
                }
            }
        }
        System.out.printf("%nResult: Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
    }
}
