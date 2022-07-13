package me.oragejuice.eventbus;


import jdk.internal.org.objectweb.asm.Type;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ASMListener extends AbstractListener {

    private static ConcurrentHashMap<Method, EventInvoker> listenerCache = new ConcurrentHashMap();
    private static String interfaceName = Type.getInternalName(EventInvoker.class);
    private static EventClassLoader classLoader = new EventClassLoader(ASMListener.class.getClassLoader());
    EventInvoker invoker;
    Object owner;
    Method method;

    private static int invokerIdx = 1;

    public ASMListener(Object owner, Method method){
        this.owner = owner;
        this.method = method;
        this.target = method.getParameterTypes()[0];

        invoker = getInvoker(method);

    }

    public static EventInvoker createInvoker(Method method) {

        String ownerType = Type.getInternalName(method.getDeclaringClass());
        String eventType = Type.getInternalName(method.getParameterTypes()[0]);
        System.out.println(ownerType);
        String handlerName = String.format("OrageEventWrapper$%d", invokerIdx++);

        ClassWriter w = new ClassWriter(0);

        w.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC, handlerName, null, "java/lang/Object", new String[]{interfaceName});

        MethodVisitor init = w.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        init.visitCode();
        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        init.visitInsn(Opcodes.RETURN);
        init.visitMaxs(1, 1);
        init.visitEnd();

        MethodVisitor m = w.visitMethod(Opcodes.ACC_PUBLIC, "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
        m.visitCode();
        m.visitVarInsn(Opcodes.ALOAD, 1);
        m.visitTypeInsn(Opcodes.CHECKCAST, ownerType);
        m.visitVarInsn(Opcodes.ALOAD, 2);
        m.visitTypeInsn(Opcodes.CHECKCAST, eventType);
        m.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ownerType, method.getName(), String.format("(L%s;)V", eventType), false);
        m.visitInsn(Opcodes.RETURN);
        m.visitMaxs(2, 3);
        m.visitEnd();

        w.visitEnd();

        byte[] classBytes = w.toByteArray();

        /*
        File df = new File("oragehack/debug/event");
        df.mkdirs();
        File d = new File(df, String.format("%s.class", handlerName));
        System.out.println(d.getAbsoluteFile().toString());
        try {
            Files.write(d.toPath(), classBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        Class clazz = classLoader.defineClass(handlerName, classBytes);

        try {
            return (EventInvoker) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EventInvoker getInvoker(Method method) {
        return getOrPutAndReturn(method);
    }

    private EventInvoker getOrPutAndReturn(Method method){
        EventInvoker r = listenerCache.putIfAbsent(method, createInvoker(method));
        return r == null ? listenerCache.get(method) : r;
    }

    @Override
    public void accept(Object event) {
        invoker.invoke(owner, event);
    }

    @Override
    public Consumer<Object> andThen(Consumer<? super Object> after) {
        return super.andThen(after);
    }


}
