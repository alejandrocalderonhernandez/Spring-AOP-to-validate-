package com.alejandro.validation.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.alejandro.validation.annotation.Iva;
import com.alejandro.validation.entity.Transaction;
import com.alejandro.validation.exception.NoLocalizationException;

@Aspect
@Configuration
public class LocalizationAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LocalizationAspect.class);
	
	//@Before("execution(* com.alejandro.validation.service.TransactionServiceImpl.create(..))")
	@Before("@annotation(com.alejandro.validation.annotation.EnableLocalization)")
	public void validateLocation(JoinPoint joinPoint) throws NoLocalizationException{
		Arrays.stream( joinPoint.getArgs()).forEach(o -> {
			if (o instanceof Transaction) {
				Transaction t = (Transaction) o;
				Field[]  fields = t.getClass().getDeclaredFields();
				Method[] methods =  t.getClass().getMethods();

			    List<Object> elements = Arrays.stream(fields)
			    	.filter(f -> f.isAnnotationPresent(Iva.class))
			    		.peek(f -> {  
			    			try {
			    				double value = f.getAnnotation(Iva.class).value();
			    				List<Method> method = Arrays.stream(methods)
			    						.filter(m -> m.getName().equalsIgnoreCase("get"+f.getName())|| m.getName().equalsIgnoreCase("set"+f.getName()))
			    						.collect(Collectors.toList());
			    				       double result =  (double) method.get(0).invoke(o);
			    				       System.out.println(method.get(1).getName());
			    				       method.get(1).invoke(o, (result+ (result*value)));
							} catch (IllegalArgumentException  | InvocationTargetException | IllegalAccessException  e) {
								System.err.println(e.getMessage());
							} 
			    			
			    	})
			    	.collect(Collectors.toList());
			    if(!elements.isEmpty()) {
			    	System.out.println("Elemento interceptado: " + elements.get(0));
			    }
				if(t.getLocation() == null) {
					throw new NoLocalizationException("Location cant be empty");
				} else if(t.getLocation().getLat() == null || t.getLocation().getLon() == null) {
					throw new NoLocalizationException("Location not complete");
				}
			}
		});
		log.info("Success request");
	}
}
