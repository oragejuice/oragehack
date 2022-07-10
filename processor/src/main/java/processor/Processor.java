package processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;


@SupportedAnnotationTypes("processor.TestAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("Called!");
        for (Element annotation : roundEnvironment.getElementsAnnotatedWith(TestAnnotation.class)) {
            System.out.println("THING FOUND!!!!");
        }
        return true;
    }
}
