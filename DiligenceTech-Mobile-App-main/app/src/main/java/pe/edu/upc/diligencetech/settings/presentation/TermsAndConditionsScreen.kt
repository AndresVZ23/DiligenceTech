package pe.edu.upc.diligencetech.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.ui.theme.Montserrat


@Composable
fun TermsAndConditionsScreen(
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onBackClick: () -> Unit // Nuevo parámetro para navegación hacia atrás

) = WorkbenchScreen(
    onHomeClick = onHomeClick,
    onProjectsClick = onProjectsClick,
    onMessagesClick = onMessagesClick,
    onProfileClick = onProfileClick,
    onSettingsClick = onSettingsClick,
    myOption = "Ajustes"
) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF1A1A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 10.dp, start = 16.dp),
                verticalAlignment = Alignment.CenterVertically, // Esto asegura que el contenido esté alineado verticalmente
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF282828), shape = CircleShape)
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Términos y Condiciones de Uso",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Espacio alrededor de la tarjeta
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF282828) // Color de fondo de la tarjeta
                )
            ){
                Column(
                    modifier = Modifier.padding(18.dp) // Espaciado interno
                ){
                    Text(
                        text = "1. Aceptación de los Términos",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "Al descargar o usar nuestra aplicación de diligencia debida, Diligence Tech, usted acepta cumplir con estos Términos y Condiciones. Si no está de acuerdo con los Términos, no debe utilizar la Aplicación.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto


                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "2. Uso de la Aplicación",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "Diligence Tech tiene como objetivo proporcionar herramientas para la gestión y análisis de procesos de diligencia debida en diversas industrias. Usted acepta usar la Aplicación solo para los fines lícitos y profesionales para los que fue diseñada. Cualquier uso indebido, malintencionado o en violación de estos Términos puede resultar en la terminación de su acceso a la Aplicación.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto


                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "3. Cuentas y Seguridad",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "Para acceder a algunas funciones de la Aplicación, puede requerirse que cree una cuenta personal. Usted es responsable de mantener la confidencialidad de su cuenta y contraseña, así como de todas las actividades que ocurran bajo su cuenta. Nos reservamos el derecho de suspender o cancelar cuentas si se detecta un uso fraudulento o en violación de los Términos.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto


                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "4. Privacidad y Protección de Datos",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "La recopilación y el uso de sus datos personales están sujetos a nuestra Política de Privacidad, la cual forma parte de estos Términos. Al utilizar la Aplicación, usted acepta que procesemos sus datos de acuerdo con dicha política.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto


                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "5. Propiedad Intelectual",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "Todos los derechos de propiedad intelectual asociados con la Aplicación, incluyendo su diseño, código fuente, contenido y cualquier actualización o mejora, pertenecen exclusivamente a MobilLoom. Usted se compromete a no copiar, modificar, distribuir ni crear trabajos derivados de la Aplicación sin el consentimiento previo por escrito.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto

                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "6. Limitación de Responsabilidad",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "La Aplicación se ofrece tal cual, sin garantías explícitas o implícitas de ningún tipo. No garantizamos que la Aplicación funcione sin interrupciones, errores o vulnerabilidades de seguridad. MobilLoom no será responsable por cualquier daño directo, indirecto, incidental o consecuente que pueda resultar del uso o la incapacidad de usar la Aplicación.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto
                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "7. Modificaciones a los Términos",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "MobilLoom se reserva el derecho de modificar o actualizar estos Términos en cualquier momento. Los cambios se publicarán en la Aplicación, y su uso continuado de la misma después de tales modificaciones constituirá su aceptación de los Términos actualizados.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto
                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "8. Ley Aplicable y Jurisdicción",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp,
                        )
                    )
                    Text(
                        text = "Estos Términos se regirán e interpretarán de acuerdo con las leyes del país donde MobilLoom tiene su sede. Cualquier disputa relacionada con los Términos o el uso de la Aplicación será resuelta exclusivamente en los tribunales competentes de dicha jurisdicción.",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Justify // Justifica el texto
                        )
                    )
                    Spacer(modifier = Modifier.height(18.dp))

                }

            }
            Spacer(modifier = Modifier.height(18.dp))

        }
    }
}