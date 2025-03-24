package pe.edu.upc.diligencetech.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.ui.theme.Montserrat

@Composable
fun DashboardScreen(
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) = WorkbenchScreen(
    onHomeClick = onHomeClick,
    onProjectsClick = onProjectsClick,
    onMessagesClick = onMessagesClick,
    onProfileClick = onProfileClick,
    onSettingsClick = onSettingsClick
) {
    val projectCount by viewModel.projectCount.collectAsState()
    val totalMembers by viewModel.totalMembers.collectAsState()
    val activeProjectCount by viewModel.activeProjectCount.collectAsState()
    val completedProjectCount by viewModel.completedProjectCount.collectAsState()



    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF1A1A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 20.dp, start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Descripción de proyectos",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            ProjectCard("Total de proyectos", projectCount)
            ProjectCard("Proyectos activos", activeProjectCount)
            ProjectCard("Proyectos completados", completedProjectCount)
            ProjectCard("Total de miembros", totalMembers)
        }
    }
}

@Composable
fun ProjectCard(title: String, count: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f)
                .clickable { },
            colors = CardDefaults.cardColors(containerColor = Color(0xFF282828)),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFE0E0E0),
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = count.toString(),
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Proyectos en el mes",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFE0E0E0),
                            fontSize = 14.sp
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
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
                }
            }
        }
    }
}
