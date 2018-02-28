package com.szj.hello.web.ticket;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by sunzengjun on 2018/2/27.
 */
public class TicketServiceAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("AROUND_ADVICE:BEGIN....");
        Object returnValue = invocation.proceed();
        System.out.println("AROUND_ADVICE:END.....");
        return returnValue;
    }
}