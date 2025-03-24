package pe.edu.upc.diligencetech.notifications.data.remote.resources

import com.google.gson.annotations.SerializedName

data class NotificationResource(
    @SerializedName("agentId")
    val agentId: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("type")
    val type: String
)