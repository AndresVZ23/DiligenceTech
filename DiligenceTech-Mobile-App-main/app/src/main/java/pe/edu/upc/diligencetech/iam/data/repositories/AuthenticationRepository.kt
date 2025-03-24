package pe.edu.upc.diligencetech.iam.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.iam.data.remote.AuthenticationService
import pe.edu.upc.diligencetech.iam.data.remote.SignInResource
import pe.edu.upc.diligencetech.iam.data.remote.SignUpResource
import pe.edu.upc.diligencetech.iam.data.remote.toAuthenticatedUser
import pe.edu.upc.diligencetech.iam.domain.AuthenticatedUser

class AuthenticationRepository(
    private val service: AuthenticationService
) {
    suspend fun signIn(signInResource: SignInResource): Resource<AuthenticatedUser> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.signIn(signInResource).execute()
                if (response.isSuccessful) {
                    val user = response.body()?.toAuthenticatedUser()
                    Log.d("user", "Response: ${response}")

                    if (user != null) {
                        return@withContext Resource.Success(user)
                    }
                    return@withContext Resource.Error("User not found")
                }
                Log.d(
                    "SignInResource",
                    "Username: ${signInResource.username}, Password: ${signInResource.password}"
                )
                return@withContext Resource.Error(response.message())
            } catch (e: Exception) {
                return@withContext Resource.Error(e.message ?: "An error occurred")
            }
        }

    suspend fun signUp(signUpDto: SignUpResource): Boolean
    = withContext(Dispatchers.IO) {
        try {
            val response = service.signUp(signUpDto).execute()
            return@withContext response.isSuccessful
        } catch (e: Exception) {
            return@withContext false
        }
    }
}