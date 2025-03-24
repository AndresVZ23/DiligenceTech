package pe.edu.upc.diligencetech.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.ui.theme.Montserrat

@Composable
fun SettingsScreen(
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
    onLogoutSuccess: () -> Unit,
    onPrivacyPolicyClick: () -> Unit // Nuevo parámetro para la navegación
) = WorkbenchScreen(
    onHomeClick = onHomeClick,
    onProjectsClick = onProjectsClick,
    onMessagesClick = onMessagesClick,
    onProfileClick = onProfileClick,
    onSettingsClick = onSettingsClick,
    myOption = "Ajustes"
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF1A1A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Membresía DiligenceTech +",
                style = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 22.sp
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, bottom = 30.dp, top = 30.dp)
            )

            // Información de Suscripción
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF282828))
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Información de Suscripción",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Detalles de suscripción
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                ) {
                    Text(
                        text = "Próxima fecha de pago: ",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "25/11/2024",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Text(
                        text = "Meses en Diligence Tech: ",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "7 meses",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(1.dp))
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D)
                        )
                    ) {
                        Text(
                            text = "Planes disponibles",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Montserrat
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF282828))
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Seguridad",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Opciones de seguridad
            Spacer(modifier = Modifier.padding(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF282828)
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        text = "Autenticación de dos factores",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "La autenticación de dos factores (2FA) es una función de seguridad diseñada para brindar una capa adicional de protección a su cuenta. No solo requiere una contraseña y un nombre de usuario, sino también algo que solo el usuario tenga consigo, como un token físico o un dispositivo móvil.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Verificación secundaria",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "La verificación secundaria funciona como una capa adicional de seguridad para confirmar su identidad. Esta medida garantiza que, incluso si su contraseña se ve comprometida, se requerirá una autenticación adicional para acceder a su cuenta.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Códigos de respaldo",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Los códigos de respaldo se proporcionan como parte del proceso de autenticación de dos factores. Estos códigos se pueden utilizar para acceder a su cuenta en caso de que pierda el acceso a su método de autenticación principal. Es esencial almacenar estos códigos en un lugar seguro.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Notificaciones de compra, chat, correo electrónico y escritorio",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ofrecemos varios canales para notificaciones relacionadas con la actividad de su cuenta, incluidas notificaciones de compra, chat, correo electrónico y escritorio. Estas notificaciones están diseñadas para mantenerlo informado sobre cualquier actividad inusual o no autorizada asociada con su cuenta.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { onPrivacyPolicyClick() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(1.dp))
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD6773D)
                    )
                ) {
                    Text(
                        text = "Políticas de privacidad",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    viewModel.cerrarSesion(onLogoutSuccess)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(5.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9B4A18)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.log_out_icon),
                    contentDescription = "Logout Icon",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cerrar sesión",
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
