package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources

import com.google.gson.annotations.SerializedName

data class AreaResource(
    @SerializedName("projectId")
    val projectId: Long,
    @SerializedName("name")
    val name: String,
)