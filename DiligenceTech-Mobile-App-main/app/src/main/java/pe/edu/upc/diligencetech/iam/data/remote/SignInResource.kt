package pe.edu.upc.diligencetech.iam.data.remote

import com.google.gson.annotations.SerializedName

data class SignInResource(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)