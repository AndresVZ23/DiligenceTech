package pe.edu.upc.diligencetech.notifications.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class NotificationDto(
    @SerializedName("agentId")
    val agentId: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("type")
    val type: String
)