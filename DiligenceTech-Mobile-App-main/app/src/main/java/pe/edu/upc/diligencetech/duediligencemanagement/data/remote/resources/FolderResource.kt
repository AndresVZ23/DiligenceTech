package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources

import com.google.gson.annotations.SerializedName

data class FolderResource(
    @SerializedName("areaId")
    val areaId: Long,
    @SerializedName("name")
    val name: String
)