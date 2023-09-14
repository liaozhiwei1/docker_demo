package com.lzw.demo.asm;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Set;

import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static org.objectweb.asm.Opcodes.*;

public class MyClassTransformer implements ClassFileTransformer {
    // basePackages是需要增强的类所在的包的集合
    private final Set<String> basePackages;
    // 获取该ClassTransformer类的全限定名，将包名中的点号替换为文件路径中的分隔符
    private static final String OWNER = MyClassTransformer.class.getCanonicalName().replace(".", File.separator);
    public MyClassTransformer(Set<String> basePackages) {
        this.basePackages = basePackages;
    }
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 判断是否需要对该类进行增强，如果不需要直接返回原字节码数据
        if (!needEnhance(className)) {
            return classfileBuffer;
        }
        System.out.println("Transforming class: " + className);
        // 利用ASM对字节码进行增强
        try {
            // 创建ClassReader对象
            ClassReader cr = new ClassReader(className);
            // 创建ClassWriter对象
            ClassWriter cw = new ClassWriter(cr,COMPUTE_MAXS);
            // 创建ClassVisitor对象，对字节码进行访问
            ClassVisitor cv = new ClassAdapter(cw) {
                @Override
                public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                    // 对每个方法进行访问，返回MethodVisitor对象进行访问
                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                    // 创建MethodVisitor对象，对方法字节码进行访问
                    if (name.equals("method")){
                        return new MethodAdapter(mv) {
                            private int nowTime;
                            @Override
                            public void visitCode() {
                                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
                                mv.visitVarInsn(LSTORE, nowTime);
                                super.visitCode();
                            }

                            @Override
                            public void visitInsn(int opcode) {
                                if (((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW)) {
                                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                                    mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
                                    mv.visitVarInsn(LLOAD, nowTime);
                                    mv.visitInsn(LSUB);
                                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V");
                                }
                                super.visitInsn(opcode);
                            }
                        };
                    }
                    return mv;
                }
            };
            // 开始访问ClassReader中的字节码
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            // 返回增强后的字节码数据
            byte[] byteArray = cw.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream("sample.class");
            fileOutputStream.write(byteArray);
            return byteArray;
        } catch (Exception e) {
            System.out.println("MyClassTransformer e=" + e);
        }
        // 出现异常时返回原字节码数据
        return classfileBuffer;
    }

    private boolean needEnhance(String className) {
        for (String basePackage : basePackages) {
            if (className.startsWith(basePackage)) {
                return true;
            }
        }
        return false;
    }
}