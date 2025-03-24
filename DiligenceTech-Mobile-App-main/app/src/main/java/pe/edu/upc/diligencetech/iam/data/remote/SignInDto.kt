package pe.edu.upc.diligencetech.iam.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.diligencetech.iam.domain.AuthenticatedUser

data class SignInDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("token")
    val token: String,
)

fun SignInDto.toAuthenticatedUser() = AuthenticatedUser(
    id = id.toInt(),
    email = email,
    username = username,
    token = token
)