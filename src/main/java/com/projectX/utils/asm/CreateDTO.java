package com.projectX.utils.asm;

import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.objectweb.asm.Opcodes.*;

/*
 * If this class is removed, the functionality of the application will remain the same.
 * This class is an unfinished implementation.
 */
public class CreateDTO {

    private Class<?> clazz1;
    private Class<?> clazz2;
    private ClassReader classReader;
    private ClassWriter classWriter;
    private MethodAdapter methodAdapter;
    private MethodVisitor methodVisitor;
    private Set<Field> fields;
    private String typeClazz1;
    private String typeClazz2;

    public CreateDTO(Class<?> clazz1, Class<?> clazz2) {
        this.clazz1 = clazz1;
        this.clazz2 = clazz2;
        typeClazz1 = Type.getInternalName(this.clazz1);
        typeClazz2 = Type.getInternalName(this.clazz2);
    }

    public byte[] updateClass() throws IOException {
        byte[] bytes = clazz2.getResourceAsStream("").readAllBytes();
        classReader = new ClassReader(bytes);
        classWriter = new ClassWriter(classReader, 0);
        methodAdapter = new MethodAdapter(classWriter);
        classReader.accept(methodAdapter, 0);

        fields = Arrays.stream(clazz1.getFields())
                .filter(f -> f.isAnnotationPresent(ParameterDTO.class))
                .collect(Collectors.toSet());

        for (Field field : fields) {
            String fieldName = field.getName();
            String typeField = Type.getDescriptor(field.getType());

            FieldVisitor fieldVisitor = classWriter.visitField(
                    ACC_PRIVATE,
                    fieldName,
                    typeField,
                    null,
                    null
            );

            fieldVisitor.visitEnd();
        }
        createConstructor();
        methodAdapter.visitEnd();

        return classWriter.toByteArray();
    }

    private void createConstructor() {

        methodVisitor = methodAdapter.visitMethod(
                ACC_PUBLIC,
                "<init>",
                "(L" + typeClazz1 + ";)V",
                null,
                null
        );

        methodVisitor.visitParameter(clazz1.getName().toLowerCase(), 0);
        methodVisitor.visitCode();

        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 0);

        methodVisitor.visitMethodInsn(
                INVOKESPECIAL,
                "java/lang/Object",
                "<init>",
                "()V",
                false
        );

        for (Field field : fields) {
            String fieldName = field.getName();
            String formatFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);

            methodVisitor.visitMethodInsn(
                    INVOKEVIRTUAL,
                    typeClazz1,
                    "get" + formatFieldName,
                    "()Ljava/lang/String;",
                    false
            );

            methodVisitor.visitFieldInsn(PUTFIELD, typeClazz2, fieldName, "Ljava/lang/String;");
        }

        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitInsn(RETURN);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLocalVariable("this", "L" + typeClazz1 + ";", null, label0, label3, 0);
        methodVisitor.visitLocalVariable("user", "L" + typeClazz2 + ";", null, label0, label3, 1);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

}
