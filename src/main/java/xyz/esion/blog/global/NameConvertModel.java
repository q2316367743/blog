package xyz.esion.blog.global;

import java.lang.annotation.*;

/**
 * 类型转换器
 *
 * @author Esion
 * @since 2021/11/16
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NameConvertModel {
}