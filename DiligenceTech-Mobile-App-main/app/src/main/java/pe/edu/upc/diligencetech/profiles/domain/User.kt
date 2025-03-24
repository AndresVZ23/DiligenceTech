package pe.edu.upc.diligencetech.profiles.domain

data class User(
    val id: String,
    val email: String,
    val roles: List<String>,
)