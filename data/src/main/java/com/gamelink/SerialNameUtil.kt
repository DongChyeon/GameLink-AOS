package com.gamelink

import kotlinx.serialization.SerialName

internal inline fun <reified T : Enum<T>> T.toSerializedName(): String? {
    val enumClass = T::class
    val enumConstant = enumClass.java.getField(this.name).getAnnotation(SerialName::class.java)
    return enumConstant?.value
}