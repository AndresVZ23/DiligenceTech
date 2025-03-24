package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos

import com.google.gson.annotations.SerializedName
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Folder

data class FolderDto(
    @SerializedName("areaId")
    val areaId: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)

fun FolderDto.toFolder() = Folder(id, name)