package pe.edu.upc.diligencetech.duediligencemanagement.data.remote.dtos

import com.google.gson.annotations.SerializedName
import pe.edu.upc.diligencetech.profiles.domain.User

data class UserDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("roles")
    val roles: List<String>

)

fun UserDto.toUser() = User(
    id = id,
    email = email,
    roles = roles
)