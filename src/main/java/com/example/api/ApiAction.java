package com.example.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiAction {

	public String actionName() default "";
	public String actionDesc() default "";
	public boolean active() default true;

	/*
	 * Its Internal name for mapping with @ApiAction annotated method.
	 * value formate = "ClassName_methodName"
	 *  example : "com.oodles.controller.UserHandler_verify" 
	 */
	public String handlerMethodName() default "";

}
