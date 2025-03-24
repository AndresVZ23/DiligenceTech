package pe.edu.upc.diligencetech.notifications.data.remote

import pe.edu.upc.diligencetech.notifications.data.remote.dtos.NotificationDto
import pe.edu.upc.diligencetech.notifications.data.remote.resources.NotificationResource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface NotificationsService {
    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("notifications")
    suspend fun getAllNotifications(): Call<List<NotificationDto>>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @POST("notifications")
    suspend fun createNotification(@Body notificationResource: NotificationResource): Call<NotificationDto?>

    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("notifications/{notificationId}")
    suspend fun getNotificationById(@Path("notificationId") notificationId: Long): Call<NotificationDto?>
}