package pe.edu.upc.diligencetech.communications.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.ui.theme.Montserrat
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.communications.presentation.ProjectsListViewModel

@Composable
fun ProjectsListForCommunicationsScreen(
    viewModel: ProjectsListViewModel = hiltViewModel(),
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onEnteringProjectClick: (projectId: Long) -> Unit
) {

    WorkbenchScreen(
        onHomeClick = onHomeClick,
        onProjectsClick = onProjectsClick,
        onMessagesClick = onMessagesClick,
        onProfileClick = onProfileClick,
        onSettingsClick = onSettingsClick,
        myOption = "Mensajes"
    ) {
        val projects = viewModel.projects

        val projectsObtained by remember {
            mutableStateOf(viewModel.getProjects())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1A1A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mensajería",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF282828))
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Todos los proyectos",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (i in projects.indices) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ProjectCard(
                            projectName = projects[i].projectName
                        ) {
                            onEnteringProjectClick(projects[i].id)
                        }
                        if (i + 1 < projects.size) {
                            ProjectCard(
                                projectName = projects[i + 1].projectName,
                            ) {
                                onEnteringProjectClick(projects[i + 1].id)
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun ProjectCard(
    projectName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable {  },
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF282828)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.project_icon),
                contentDescription = "Folder",
                tint = Color(0xFFD6773D),
                modifier = Modifier.size(44.dp)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = projectName,
                color = Color.White,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold
            )
        }
    }
}