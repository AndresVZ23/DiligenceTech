package pe.edu.upc.diligencetech.profiles.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.dashboard.presentation.DashboardViewModel
import pe.edu.upc.diligencetech.ui.theme.Montserrat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ProfileScreen(
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    viewModel: DashboardViewModel = hiltViewModel()
) = WorkbenchScreen(
    onHomeClick = onHomeClick,
    onProjectsClick = onProjectsClick,
    onMessagesClick = onMessagesClick,
    onProfileClick = onProfileClick,
    onSettingsClick = onSettingsClick,
    myOption = "Perfil"
) {

    val user = profileViewModel.user.collectAsState().value
    val agentName = profileViewModel.agentName.collectAsState().value
    val currentDate = SimpleDateFormat("dd 'de' MMMM, yyyy",Locale("es", "ES")).format(Date())

    val projectCount by viewModel.projectCount.collectAsState()
    val formattedCreatedAt = agentName?.createdAt?.substringBefore(" ")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF1A1A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            if (user == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            } else {
                val firstLetter = user.email.substringBefore("@").first().uppercaseChar()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(start = 16.dp, end = 16.dp)
                        .shadow(
                            elevation = 6.dp,
                            shape = RoundedCornerShape(8.dp),
                            clip = true
                        )
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_background),
                        contentDescription = "Background",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp, start = 5.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Bienvenido a\nDiligence Tech",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = Montserrat
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = currentDate,
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = Montserrat
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_image),
                                contentDescription = "Logo",
                                modifier = Modifier
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(275.dp)
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(8.dp),
                                clip = true
                            )
                            .background(Color(0xFF282828), RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(start = 13.dp, top = 20.dp, bottom = 10.dp, end = 13.dp),
                            ) {
                                Text(
                                    text = "Total de proyectos",
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Montserrat
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = projectCount.toString(),
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = Montserrat
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(75.dp)
                                    .background(Color(0xFF9B4A18), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.projects_dashboard_icon),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(13.dp))
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF7DB768), CircleShape)
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "↑ 40.00%",
                                    style = TextStyle(
                                        fontFamily = Montserrat,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFE0E0E0),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(23.dp))
                            Row(
                                modifier = Modifier
                                    .padding(start = 12.dp, bottom = 20.dp)
                                    .align(Alignment.Start)
                            ) {
                                Text(
                                    text = "Proyectos del mes",
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Montserrat,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(275.dp)
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(8.dp),
                                clip = true
                            )
                            .background(Color(0xFF282828), RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 13.dp, top = 20.dp, bottom = 10.dp, end = 13.dp)
                        ) {
                            Text(
                                text = "Información \ndel Perfil",
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontFamily = Montserrat
                            )
                            Spacer(modifier = Modifier.height(18.dp))

                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF9B4A18), CircleShape)
                                    .size(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = firstLetter.toString(),
                                    style = TextStyle(
                                        fontFamily = Montserrat,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 40.sp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                            Text(
                                text = agentName?.name.toString(),
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Montserrat
                            )
                            Text(
                                text = user.email,
                                color = Color.White,
                                fontSize = 11.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = Montserrat
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(start = 16.dp, end = 16.dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(8.dp),
                            clip = true
                        )
                        .background(Color(0xFF282828), RoundedCornerShape(8.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(13.dp)
                    ) {
                        Text(
                            text = "Detalles del Perfil:",
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Montserrat
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row {
                            Text(
                                text = "User ID:",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Montserrat,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = user.email.substringBefore("@"),
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontFamily = Montserrat,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row {
                            Text(
                                text = "Nombre completo:",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Montserrat,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = agentName?.name.toString(),
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontFamily = Montserrat,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row {
                            Text(
                                text = "Fecha de creación de la cuenta:",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Montserrat,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = formattedCreatedAt ?: "Cargando...",
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontFamily = Montserrat,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(21.dp))
            }
        }
    }
}
