package pe.edu.upc.diligencetech.communications.data.remote.resources

import com.google.gson.annotations.SerializedName

data class MessageResource(
    @SerializedName("message")
    val message: String,
    @SerializedName("projectId")
    val projectId: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("destinationUsername")
    val destinationUsername: String,
    @SerializedName("subject")
    val subject: String
)