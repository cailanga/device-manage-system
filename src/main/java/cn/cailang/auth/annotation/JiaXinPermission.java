package cn.cailang.auth.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JiaXinPermission {
    String name();
    String description() default "";
}
