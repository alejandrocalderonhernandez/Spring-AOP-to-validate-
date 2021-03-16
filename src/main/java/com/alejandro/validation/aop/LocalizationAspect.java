package com.alejandro.validation.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alejandro.validation.entity.Transaction;
import com.alejandro.validation.exception.NoLocalizationException;

@Component
@Aspect
public class LocalizationAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LocalizationAspect.class);

	
	//@Before("execution(* com.alejandro.validation.service.TransactionServiceImpl.create(..))")
	@Before("@annotation(com.alejandro.validation.annotation.EnableLocalization)")
	public void validateLocation(JoinPoint joinPoint) throws NoLocalizationException{
		Arrays.stream( joinPoint.getArgs()).forEach(o -> {
			if (o instanceof Transaction) {
				Transaction t = (Transaction) o;
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
