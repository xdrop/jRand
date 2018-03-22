package me.xdrop.jrand.annotation.processing

import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement

data class ClassIdentifier(var typeElement: TypeElement, var packageName: PackageElement) {
    val className = typeElement.simpleName.toString()
}
