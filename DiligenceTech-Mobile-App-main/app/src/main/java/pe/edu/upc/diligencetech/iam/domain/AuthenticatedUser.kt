package pe.edu.upc.diligencetech.iam.domain

data class AuthenticatedUser(
    val id: Int,
    val email: String,
    val username: String,
    val token: String,
)