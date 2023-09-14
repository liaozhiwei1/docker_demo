package com.lzw.demo.asm;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class cglib extends sample{

    public void ok(String xxx){
        System.out.println(xxx);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cglib.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("before method run...");
            Object result = proxy.invokeSuper(obj, args1);
            System.out.println("after method run...");
            return result;
        });
        cglib sample = (cglib) enhancer.create();
        sample.ok("xxx");
    }
}
