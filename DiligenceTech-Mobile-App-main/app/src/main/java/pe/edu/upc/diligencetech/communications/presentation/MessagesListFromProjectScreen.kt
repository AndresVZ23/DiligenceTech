package pe.edu.upc.diligencetech.communications.presentation

import android.os.Message
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.Constants
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.communications.data.remote.dtos.MessageDto
import pe.edu.upc.diligencetech.communications.data.remote.resources.MessageResource
import pe.edu.upc.diligencetech.communications.domain.Messages
import pe.edu.upc.diligencetech.duediligencemanagement.presentation.ProjectInputDialog
import pe.edu.upc.diligencetech.duediligencemanagement.presentation.ProjectsListViewModel
import pe.edu.upc.diligencetech.ui.theme.Montserrat


@Composable
fun MessagesListFromProjectScreen(
    projectId: Long,
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    navController: NavController,
    viewModel: MessagesViewModel = hiltViewModel()
) {

    // Estado para manejar el icono de flecha
    var isExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val selectedTab = remember { mutableStateOf("Recibidos") }

    LaunchedEffect(projectId) {
        viewModel.fetchMessagesForProject(projectId)
    }

    val messages by viewModel.messages.collectAsState()
    val userMessages by viewModel.userMessages.collectAsState()

    WorkbenchScreen(
        onHomeClick = onHomeClick,
        onProjectsClick = onProjectsClick,
        onMessagesClick = onMessagesClick,
        onProfileClick = onProfileClick,
        onSettingsClick = onSettingsClick,
        myOption = "Mensajes"
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1A1A1A)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 24.dp, start = 16.dp),
                verticalAlignment = Alignment.CenterVertically, // Esto asegura que el contenido esté alineado verticalmente
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF282828), shape = CircleShape)
                        .clickable { navController.popBackStack() },
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
                    text = "Mensajería de Proyecto",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFD6773D),
                        fontSize = 22.sp
                    ),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF282828))
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Todos los mensajes recibidos",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Row con el ícono que cambia entre arriba y abajo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp)
                    .clickable { isExpanded = !isExpanded }, // Cambia el estado cuando se hace clic
                horizontalArrangement = Arrangement.Start, // Asegura que los elementos estén alineados a la izquierda
                verticalAlignment = Alignment.CenterVertically // Mantiene el contenido alineado verticalmente en el centro
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, // Alterna entre las flechas
                    contentDescription = if (isExpanded) "Flecha hacia arriba" else "Flecha hacia abajo",
                    tint = Color.White, // Color del ícono
                    modifier = Modifier.size(24.dp) // Tamaño del ícono
                )

                Spacer(modifier = Modifier.width(8.dp)) // Separación entre el ícono y el texto

                Text(
                    text = "Ordenar",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
            }

            Divider(
                color = Color(0xFF626262),
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 9.dp)
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                messages.forEachIndexed { index, message ->
                    MessageCard(
                        firstLetter = try {
                            userMessages[index].substringBefore("@").first().uppercaseChar()
                        } catch (e: Exception) {
                            message.userId.toString().first().uppercaseChar()
                        },
                        contactName = try {
                            "De: " + userMessages[index]
                        } catch (e: Exception) {
                            "De: " + message.userId.toString()
                        },
                        messageTitle = message.subject,
                        onClick = {
                            navController.navigate("messageDetailsScreen/${message.id}")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.padding(12.dp))

            if (showDialog) {
                MessageInput (
                    onDismiss = {
                        showDialog = false
                    },
                    onAddMessage = { messageDto ->
                        viewModel.createMessage(messageDto)
                        showDialog = false
                    },
                    projectId = projectId,
                    userId = Constants.id ?: 0L
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = {
                        showDialog = true
                    },
                    containerColor = Color.White,
                    modifier = Modifier
                        .padding(bottom = 40.dp, end = 16.dp)
                        .size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar",
                        tint = Color(0xFFD6773D),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MessageCard(
    firstLetter: Char,
    contactName: String,
    messageTitle: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(75.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF282828)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF9B4A18), CircleShape)
                    .size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = firstLetter.toString(),
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = contactName,
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    text = messageTitle,
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(
    onDismiss: () -> Unit,
    onAddMessage: (MessageResource) -> Unit,
    projectId: Long,
    userId: Long,
) {
    val contactName = remember { mutableStateOf("") }
    val messageTitle = remember { mutableStateOf("") }
    val messageText = remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color(0xFF282828), shape = RoundedCornerShape(8.dp))
                .padding(25.dp)
        ) {
            Column {
                Text(
                    text = "Envíar mensaje",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 17.sp
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFF626262))
                )
                Spacer(modifier = Modifier.height(25.dp))


                OutlinedTextField(
                    value = contactName.value,
                    onValueChange = {
                        contactName.value = it
                    },
                    label = { Text("Para", fontFamily = Montserrat, color = Color.White) },
                    placeholder = { Text("Correo del destinatario*", fontFamily = Montserrat, color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = Color(0xFFD6773D),
                        unfocusedLabelColor = Color(0xFFD6773D),
                        cursorColor = Color(0xFFD6773D),
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontFamily = Montserrat
                    )
                )
                Spacer(modifier = Modifier
                    .height(16.dp)
                )

                OutlinedTextField(
                    value = messageTitle.value,
                    onValueChange = {
                        messageTitle.value = it
                    },
                    label = { Text("Título", fontFamily = Montserrat, color = Color.White) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = Color(0xFFD6773D),
                        unfocusedLabelColor = Color(0xFFD6773D),
                        cursorColor = Color(0xFFD6773D),
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontFamily = Montserrat
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = messageText.value,
                    onValueChange = {
                        messageText.value = it
                    },
                    label = { Text("Mensaje", fontFamily = Montserrat, color = Color.White) },
                    placeholder = { Text("Escribe todo el mensaje aquí*", fontFamily = Montserrat, color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = Color(0xFFD6773D),
                        unfocusedLabelColor = Color(0xFFD6773D),
                        cursorColor = Color(0xFFD6773D),
                    ),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontFamily = Montserrat
                    )
                )
                Spacer(modifier = Modifier.height(46.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFD6773D)
                        ),
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            "Cancelar",
                            style = TextStyle(
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            val messageDto = MessageResource(
                                userId = userId,
                                destinationUsername = contactName.value,
                                projectId = projectId,
                                subject = messageTitle.value,
                                message = messageText.value
                            )
                            onAddMessage(messageDto)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            "Envíar",
                            style = TextStyle(
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    }
}