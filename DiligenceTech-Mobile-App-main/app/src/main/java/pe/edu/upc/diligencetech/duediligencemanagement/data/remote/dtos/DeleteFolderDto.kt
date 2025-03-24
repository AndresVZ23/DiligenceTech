package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class DeleteFolderDto(
    @SerializedName("body")
    val body: String
)