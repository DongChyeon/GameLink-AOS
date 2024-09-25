package model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceInfo(
    @SerialName("uniqueId")
    val uniqueId: String,
    @SerialName("model")
    val model: String,
    @SerialName("deviceId")
    val deviceId: String,
    @SerialName("deviceName")
    val deviceName: String
)
