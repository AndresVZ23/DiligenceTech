package pe.edu.upc.diligencetech.common

import androidx.compose.ui.graphics.Color
import pe.edu.upc.diligencetech.profiles.domain.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    //const val BASE_URL = "https://diligencetech-backend.azurewebsites.net/api/v1/"
    const val BASE_URL = "https://diligencetech-final.azurewebsites.net/api/v1/"
    //http://localhost:8080/api/v1/authentication/sign-in
    val SCREEN_BACKGROUND_COLOR = Color(0xFF1A1A1A)
    val CARD_BACKGROUND_COLOR = Color(0xFF282828)
    val ACCENT_COLOR = Color(0xFFD6773D)
    var token: String? = null
    var id: Long? = null
    var username: String? = null
}