package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.resources

import com.google.gson.annotations.SerializedName

data class DueDiligenceProjectResource(
    @SerializedName("agentsRoles")
    val agentsRoles: List<Boolean>,
    @SerializedName("agentsUsernames")
    val agentsUsernames: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("active")
    val active: Boolean,
)