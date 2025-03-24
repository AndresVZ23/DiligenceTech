package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources

import com.google.gson.annotations.SerializedName

data class EditAreaResource(
    @SerializedName("name")
    val name: String
)