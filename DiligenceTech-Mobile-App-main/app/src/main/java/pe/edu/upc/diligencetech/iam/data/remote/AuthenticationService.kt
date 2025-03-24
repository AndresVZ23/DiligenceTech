package pe.edu.upc.diligencetech.iam.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationService {
    @Headers(
        value = [
            "Content-Type: application/json",
            "accept: application/json"
        ]
    )
    @POST("authentication/sign-up")
    fun signUp(@Body signUpResource: SignUpResource): Call<SignUpDto?>


    @Headers(
        value = [
            "Content-Type: application/json",
            "accept: application/json"
        ]
    )
    @POST("authentication/sign-in")
    fun signIn(@Body signInResource: SignInResource): Call<SignInDto?>
}