package pe.edu.upc.diligencetech.iam.data.remote

import com.google.gson.annotations.SerializedName

data class SignUpResource(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("username")
    val username: String
)