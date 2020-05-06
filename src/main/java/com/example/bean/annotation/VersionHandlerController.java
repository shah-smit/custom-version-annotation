package com.example.bean.annotation;

import com.example.bean.annotation.VersionGetHandler;
import com.example.bean.annotation.VersionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class VersionHandlerController {

    @Autowired
    private ApplicationContext applicationContext;


    public ResponseEntity findVersionHandler(String endpoint, String version, HttpMethod httpMethod, Object... args) {

            String[] allBeanNames = applicationContext.getBeanDefinitionNames();
            for(String beanName : allBeanNames) {
                if(beanName.endsWith("Handler")){
                    Class annotatedClass = applicationContext.getType(beanName);
                    VersionHandler versionHandler = (VersionHandler) annotatedClass.getAnnotation(VersionHandler.class);
                    if(versionHandler != null && versionHandler.version().equals(version)){
                        Method[] methods = annotatedClass.getMethods();
                        for(Method method: methods){
                            
                            if(method.isAnnotationPresent(VersionGetHandler.class) &&
                                httpMethod.matches("GET")){
                                VersionGetHandler versionGetHandler = method.getAnnotation(VersionGetHandler.class);

                                if(versionGetHandler.endpoint().equals(endpoint)){
                                    var constructors = annotatedClass.getConstructors();

                                    Object result = null;
                                    try {
                                        result = method.invoke(constructors[0].newInstance(), args);
                                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                                        return ResponseEntity.unprocessableEntity().build();
                                    }

                                    return ResponseEntity.accepted().body(result);
                                }
                            }
                        }
                    }
                }
            }

        return ResponseEntity.notFound().build();
    }
}
