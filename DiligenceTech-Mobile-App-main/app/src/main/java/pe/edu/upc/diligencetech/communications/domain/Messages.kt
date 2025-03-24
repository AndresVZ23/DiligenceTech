package pe.edu.upc.diligencetech.communications.domain

data class Messages (
    val createdAt: String,
    val id: Long,
    val message: String,
    val projectId: Long,
    val userId: Long,
    val destinationUserId: Long,
    val subject: String
)