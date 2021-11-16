package xyz.esion.blog.global;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 命名参数处理器
 * @author Esion
 * @since 2021/11/16
 */
@Setter
@Getter
public class NameConvertModelProcessor extends ServletModelAttributeMethodProcessor {
    private static final Map<Class<?>, Map<String, String>> PARAM_MAPPINGS_CACHE = new ConcurrentHashMap<>(256);
    protected PropertyNamingStrategy.PropertyNamingStrategyBase propertyNamingStrategyBase;
    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public NameConvertModelProcessor(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        super(false);
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(NameConvertModel.class);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest nativeWebRequest) {
        Object target = binder.getTarget();
        Map<String, String> paramMappings = this.getParamMappings(target.getClass());
        NameConvertModelDataBinder paramNameDataBinder = new NameConvertModelDataBinder(target, binder.getObjectName(), paramMappings);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(paramNameDataBinder);
        super.bindRequestParameters(paramNameDataBinder, nativeWebRequest);
    }

    private Map<String, String> getParamMappings(Class<?> targetClass) {
        if (PARAM_MAPPINGS_CACHE.containsKey(targetClass)) {
            return PARAM_MAPPINGS_CACHE.get(targetClass);
        }
        Field[] fields = targetClass.getDeclaredFields();
        Map<String, String> paramMappings = new HashMap<>(32);
        for (Field field : fields) {
            paramMappings.put(propertyNamingStrategyBase.translate(field.getName()),
                    field.getName());
        }
        PARAM_MAPPINGS_CACHE.put(targetClass, paramMappings);
        return paramMappings;
    }
}