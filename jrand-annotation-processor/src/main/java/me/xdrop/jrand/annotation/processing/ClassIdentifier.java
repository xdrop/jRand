package me.xdrop.jrand.annotation.processing;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import javax.lang.model.element.PackageElement;
import java.util.Objects;

public class ClassIdentifier {
    private String className;
    private PackageElement packageName;

    public ClassIdentifier(String className, PackageElement packageName) {
        this.className = className;
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public PackageElement getPackageName() {
        return packageName;
    }

    public void setPackageName(PackageElement packageName) {
        this.packageName = packageName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageName.toString()) + Objects.hash(getClassName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClassIdentifier) {
            return this.packageName.toString().equals(((ClassIdentifier) obj).getPackageName().toString()) && this.className.equals(((ClassIdentifier) obj).className);
        }
        return false;
    }
}
