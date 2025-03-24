package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos

import com.google.gson.annotations.SerializedName
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Document

data class DocumentDto(
    @SerializedName("fileUrl")
    val fileUrl: String,
    @SerializedName("filename")
    val filename: String,
    @SerializedName("folderId")
    val folderId: Long,
    @SerializedName("id")
    val id: Long
)

fun DocumentDto.toDocument() = Document(id, filename, fileUrl)