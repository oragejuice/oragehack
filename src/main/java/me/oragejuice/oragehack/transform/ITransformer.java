package me.oragejuice.oragehack.transform;

import org.objectweb.asm.tree.ClassNode;


public interface ITransformer {

    /**
     * Transforms the specified {@link ClassNode}
     *
     * @param cn The target {@link ClassNode}
     */
    void transform(ClassNode cn);

    /**
     * Returns whether or not a class is targeted by this transformer.
     * The given class may or may not be given in an obfuscated form.
     *
     * @return True if this transformer targets the given class
     */
    default boolean isTarget(String className) {
        return true;
    }
}
