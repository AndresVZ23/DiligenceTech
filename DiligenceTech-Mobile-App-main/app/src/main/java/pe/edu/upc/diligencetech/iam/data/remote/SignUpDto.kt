package pe.edu.upc.diligencetech.iam.data.remote

import com.google.gson.annotations.SerializedName

data class SignUpDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("roles")
    val roles: List<String>
)