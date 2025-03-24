package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources

import com.google.gson.annotations.SerializedName

data class DocumentResource(
    @SerializedName("folderId")
    val folderId: Long,
    @SerializedName("filename")
    val filename: String,
    @SerializedName("fileUrl")
    val fileUrl: String,
)