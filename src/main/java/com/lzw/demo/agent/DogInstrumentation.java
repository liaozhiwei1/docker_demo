package com.lzw.demo.agent;

import java.lang.instrument.Instrumentation;

public class DogInstrumentation {
    public static void agentmain(String agentArgs, Instrumentation instrumentation) throws Exception {
        instrumentation.addTransformer(new DogClassFileTransformer(),true);
        instrumentation.retransformClasses(Dog.class);
    }
}
