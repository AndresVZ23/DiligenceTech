package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos

import com.google.gson.annotations.SerializedName
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Area

data class AreaDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("fileName")
    val fileName: String,
)

fun AreaDto.toArea() = Area(id, fileName)