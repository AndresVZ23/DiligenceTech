package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos

import com.google.gson.annotations.SerializedName
import pe.edu.upc.diligencetech.duediligencemanagement.domain.DueDiligenceProject

data class DueDiligenceProjectDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("projectName")
    val projectName: String,
    @SerializedName("active")
    val active: Boolean
)

fun DueDiligenceProjectDto.toProject() = DueDiligenceProject(id, projectName, active)