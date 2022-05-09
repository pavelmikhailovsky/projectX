package com.projectX.utils.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.ASM9;

public class MethodAdapter extends ClassVisitor {

    private TraceClassVisitor tracer;

    public MethodAdapter(ClassVisitor classVisitor) {
        super(ASM9, classVisitor);
        tracer = new TraceClassVisitor(classVisitor, new PrintWriter(System.out));
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return tracer.visitMethod(access, name, desc, signature, exceptions);
    }

    public void visitEnd(){
        tracer.visitEnd();
    }
}
