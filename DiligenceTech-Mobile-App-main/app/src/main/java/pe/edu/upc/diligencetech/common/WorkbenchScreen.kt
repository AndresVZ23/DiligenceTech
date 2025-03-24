package pe.edu.upc.diligencetech.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.communications.presentation.ProjectsListForCommunicationsScreen
import pe.edu.upc.diligencetech.ui.theme.Montserrat
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkbenchScreen(
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    myOption: String = "Inicio",
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(110.dp),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(

                                painter = painterResource(id = R.drawable.brand),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 35.dp)
                                    .size(170.dp, 90.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        IconButton(onClick = {
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Constants.CARD_BACKGROUND_COLOR,
                )
            )
        },
        bottomBar = {
            BottomBar(
                selectedOption = myOption,
                onHomeClick = {
                    onHomeClick()
                },
                onProjectsClick = {
                    onProjectsClick()
                },
                onMessagesClick = {
                    onMessagesClick()
                },
                onProfileClick = {
                    onProfileClick()
                },
                onSettingsClick = {
                    onSettingsClick()
                },
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            content()
        }
    }
}



@Composable
fun BottomBar(
    selectedOption: String,
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(Constants.CARD_BACKGROUND_COLOR)
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        BottomBarItem(
            imageVector = Icons.Filled.Home,
            text = "Inicio",
            isSelected = selectedOption == "Inicio",
            onClick = onHomeClick
        )
        BottomBarItem(
            imageVector = Icons.Filled.DateRange,
            text = "Proyectos",
            isSelected = selectedOption == "Proyectos",
            onClick = onProjectsClick
        )
        BottomBarItem(
            imageVector = Icons.Filled.Email,
            text = "Mensajes",
            isSelected = selectedOption == "Mensajes",
            onClick = onMessagesClick
        )
        BottomBarItem(
            imageVector = Icons.Filled.Person,
            text = "Perfil",
            isSelected = selectedOption == "Perfil",
            onClick = onProfileClick
        )
        BottomBarItem(
            imageVector = Icons.Filled.Settings,
            text = "Ajustes",
            isSelected = selectedOption == "Ajustes",
            onClick = onSettingsClick
        )
    }
}

@Composable
fun BottomBarItem(
    imageVector: ImageVector,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit = {}
) {
    val selectedColor = Color(0xFFD6773D)
    val unselectedColor = Color.White

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = text,
                tint = if (isSelected) selectedColor else unselectedColor
            )
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    color = if (isSelected) selectedColor else unselectedColor
                )
            )
        }
    }
}