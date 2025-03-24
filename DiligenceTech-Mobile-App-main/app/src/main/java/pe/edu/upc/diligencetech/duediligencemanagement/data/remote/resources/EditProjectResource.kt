package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources

import com.google.gson.annotations.SerializedName

data class EditProjectResource(
    @SerializedName("active")
    val active: Boolean
)
