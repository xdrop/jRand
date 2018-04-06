package me.xdrop.jrand.annotation.processing

import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement

data class ClassIdentifier(var typeElement: TypeElement, var packageName: PackageElement) {
    val className = typeElement.simpleName.toString()

    override fun equals(other: Any?): Boolean {
        if (other is ClassIdentifier) {
            return this.className == other.className
                    && this.packageName.toString() == other.packageName.toString()
        }
        return false
    }

    override fun hashCode(): Int {
        var result = packageName.toString().hashCode()
        result = 31 * result + className.hashCode()
        return result
    }

    override fun toString(): String {
        return this.packageName.toString().plus('.').plus(this.className)
    }
}
