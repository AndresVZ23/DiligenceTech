package pe.edu.upc.diligencetech.duediligencemanagement.presentation

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.Constants
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.duediligencemanagement.domain.DueDiligenceProject
import pe.edu.upc.diligencetech.ui.theme.Montserrat

@Composable
fun ProjectsListScreen(
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
        myOption = "Proyectos"
    ) {
        val projects = viewModel.projects
        val newProject = viewModel.newProject
        val rawBuyAgents = viewModel.rawBuyAgents
        val rawSellAgents = viewModel.rawSellAgents

        // only at the beginning
        val projectsObtained by remember {
            mutableStateOf(viewModel.getProjects())
        }
        val test = remember { mutableStateListOf<String>() }

        var showDialog by remember { mutableStateOf(false) }
        var showChangeDialog by remember { mutableStateOf(false) }
        var selectedProjectId by remember { mutableStateOf<Long?>(null) }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1A1A1A)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Bienvenido ")
                        withStyle(style = SpanStyle(color = Color(0xFFD6773D))) {
                            Constants.username.let {
                                if (it is String) {
                                    append(it.substringBefore('@'))
                                }
                            }
                        }
                    },
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
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (i in projects.indices step 2) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ProjectCard(
                                project = projects[i],
                                projectNumber = i + 1,
                                onClick = { onEnteringProjectClick(projects[i].id) },
                                onChangeActiveStatus = { projectId, active ->
                                    // Mostrar el dialog para confirmar el cambio de estado
                                    selectedProjectId = projectId
                                    showChangeDialog = true
                                }
                            )
                            if (i + 1 < projects.size) {
                                ProjectCard(
                                    project = projects[i + 1],
                                    projectNumber = i + 2,
                                    onClick = { onEnteringProjectClick(projects[i + 1].id) },
                                    onChangeActiveStatus = { projectId, active ->
                                        selectedProjectId = projectId
                                        showChangeDialog = true
                                    }
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
                if (showChangeDialog && selectedProjectId != null) {
                    ConfirmChangeDialog(
                        projectId = selectedProjectId!!,
                        onConfirm = {
                            viewModel.editProjectActive(selectedProjectId!!, active = true)
                            showChangeDialog = false
                        },
                        onDismiss = {
                            showChangeDialog = false
                        }
                    )
                }

                if (showDialog) {
                    ProjectInputDialog(
                        onDismiss = {
                            showDialog = false
                            viewModel.onNewProjectChange("")
                            viewModel.onRawBuyAgentsChange("")
                            viewModel.onRawSellAgentsChange("")
                        },
                        onAddProject = {
                            viewModel.addProject()
                            showDialog = false
                        }
                    )
                }
            }

            FloatingActionButton(
                onClick = {
                    showDialog = true },
                containerColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
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

@Composable
fun ConfirmChangeDialog(
    projectId: Long,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color(0xFF282828), shape = RoundedCornerShape(8.dp))
                .padding(30.dp)
        ) {
            Column {
                Text(
                    text = "Cambiar estado",
                    style = TextStyle(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "¿Deseas marcar este proyecto como completado? Esta acción no se puede deshacer.",
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(25.dp))

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
                        Text("Cancelar", fontFamily = Montserrat)
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            onConfirm()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Confirmar", fontFamily = Montserrat)
                    }
                }
            }
        }
    }
}


@Composable
fun ProjectCard(
    project: DueDiligenceProject,
    projectNumber: Int,
    onClick: () -> Unit,
    onChangeActiveStatus: (Long, Boolean) -> Unit
) {
    val cardColor = if (project.active) Color(0xC4AF673A) else Color(0xFF282828) // Naranja claro si está completado
    val isCompleted = project.active // Verificar si está completado

    Card(
        modifier = Modifier
            .width(180.dp)
            .height(180.dp)
            .clickable(enabled = !isCompleted) { // Deshabilitar clic si está completado
                onClick()
            },
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Texto alineado a la izquierda
                Text(
                    text = "$projectNumber.",
                    color = if (isCompleted) Color(0xFF282828) else Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                // Ícono alineado a la derecha
                Icon(
                    painter = painterResource(id = R.drawable.active_menu_icon),
                    contentDescription = "Active Menu",
                    tint = if (isCompleted) Color(0xFF282828) else Color.White, // Cambiar color del ícono
                    modifier = Modifier
                        .size(12.dp)
                        .clickable(enabled = !isCompleted) {
                            onChangeActiveStatus(project.id, true)
                        }
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.project_icon),
                contentDescription = "Folder",
                tint = if (isCompleted) Color(0xFF282828) else  Color(0xFFD6773D),
                modifier = Modifier.size(64.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = project.projectName,
                    color = if (isCompleted) Color(0xFF282828) else Color.White,
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (project.active) "Completado" else "No Completado",
                    color = if (isCompleted) Color.White else Color(0xFFD6773D),
                    fontFamily = Montserrat,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectInputDialog(
    viewModel: ProjectsListViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onAddProject: () -> Unit
) {
    val newProject = viewModel.newProject
    val rawBuyAgent = remember { mutableStateOf("") }
    val buyAgentsList = remember { mutableStateListOf<String>() }
    val invalidBuyAgents = remember { mutableStateListOf<String>() } // Lista de correos inválidos
    val rawSellAgent = remember { mutableStateOf("") }
    val sellAgentsList = remember { mutableStateListOf<String>() }
    val invalidSellAgents = remember { mutableStateListOf<String>() } // Lista de correos inválidos
    val showInvalidEmailsDialog = remember { mutableStateOf(false) } // Estado para el diálogo de correos inválidos

    // Helper para validar correo electrónico
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color(0xFF282828), shape = RoundedCornerShape(8.dp))
                .padding(25.dp)
        ) {
            Column {
                // Título
                Text(
                    text = "Crear nuevo proyecto",
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

                // Nombre del proyecto
                OutlinedTextField(
                    value = newProject.value,
                    onValueChange = { viewModel.onNewProjectChange(it) },
                    label = { Text("Nombre", fontFamily = Montserrat, color = Color.White) },
                    placeholder = { Text("Ingrese nombre", fontFamily = Montserrat, color = Color.Gray) },
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

                // Campo y chips para Agentes de Compra
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = rawBuyAgent.value,
                        onValueChange = { rawBuyAgent.value = it },
                        label = { Text("Agentes de Compra", fontFamily = Montserrat, color = Color.White) },
                        placeholder = { Text("Ingrese correo", fontFamily = Montserrat, color = Color.Gray) },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                val email = rawBuyAgent.value.trim()
                                if (email.isNotEmpty()) {
                                    if (isValidEmail(email)) {
                                        buyAgentsList.add(email)
                                        invalidBuyAgents.remove(email)
                                    } else {
                                        invalidBuyAgents.add(email)
                                    }
                                    rawBuyAgent.value = "" // Limpiar campo
                                }
                            }
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color(0xFFD6773D),
                            unfocusedLabelColor = Color(0xFFD6773D),
                            cursorColor = Color(0xFFD6773D),
                        ),
                        shape = RoundedCornerShape(8.dp),
                        textStyle = TextStyle(
                            color = Color.White,
                            fontFamily = Montserrat
                        ),
                        modifier = Modifier.weight(1f) // Ensures the text field takes up available space
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Space between text field and button
                    Button(
                        onClick = {
                            val email = rawBuyAgent.value.trim()
                            if (email.isNotEmpty()) {
                                if (isValidEmail(email)) {
                                    buyAgentsList.add(email)
                                    invalidBuyAgents.remove(email)
                                } else {
                                    invalidBuyAgents.add(email)
                                }
                                rawBuyAgent.value = "" // Clear the input field
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(60.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Add",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    items(buyAgentsList) { email ->
                        Chip(
                            text = email,
                            isInvalid = invalidBuyAgents.contains(email),
                            onRemove = {
                                buyAgentsList.remove(email)
                                invalidBuyAgents.remove(email)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Campo y chips para Agentes de Venta
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = rawSellAgent.value,
                        onValueChange = { rawSellAgent.value = it },
                        label = { Text("Agentes de Venta", fontFamily = Montserrat, color = Color.White) },
                        placeholder = { Text("Ingrese correo", fontFamily = Montserrat, color = Color.Gray) },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                val email = rawSellAgent.value.trim()
                                if (email.isNotEmpty()) {
                                    if (isValidEmail(email)) {
                                        sellAgentsList.add(email)
                                        invalidSellAgents.remove(email)
                                    } else {
                                        invalidSellAgents.add(email)
                                    }
                                    rawSellAgent.value = "" // Clear the input field
                                }
                            }
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color(0xFFD6773D),
                            unfocusedLabelColor = Color(0xFFD6773D),
                            cursorColor = Color(0xFFD6773D),
                        ),
                        shape = RoundedCornerShape(8.dp),
                        textStyle = TextStyle(
                            color = Color.White,
                            fontFamily = Montserrat
                        ),
                        modifier = Modifier.weight(1f) // Ensures the text field takes up available space
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Space between text field and button
                    Button(
                        onClick = {
                            val email = rawSellAgent.value.trim()
                            if (email.isNotEmpty()) {
                                if (isValidEmail(email)) {
                                    sellAgentsList.add(email)
                                    invalidSellAgents.remove(email)
                                } else {
                                    invalidSellAgents.add(email)
                                }
                                rawSellAgent.value = "" // Clear the input field
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(60.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Add",
                            tint = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    items(sellAgentsList) { email ->
                        Chip(
                            text = email,
                            isInvalid = invalidSellAgents.contains(email),
                            onRemove = {
                                sellAgentsList.remove(email)
                                invalidSellAgents.remove(email)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(46.dp))

                // Botones de acción
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFFD6773D)
                        ),
                        modifier = Modifier.weight(1f),
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
                            if (invalidBuyAgents.isNotEmpty() || invalidSellAgents.isNotEmpty()) {
                                showInvalidEmailsDialog.value = true
                            } else {
                                val buyAgentsString = buyAgentsList.joinToString(",")
                                val sellAgentsString = sellAgentsList.joinToString(",")
                                viewModel.onRawBuyAgentsChange(buyAgentsString)
                                viewModel.onRawSellAgentsChange(sellAgentsString)
                                onAddProject()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            "Guardar",
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

    // Diálogo para mostrar correos inválidos
    if (showInvalidEmailsDialog.value) {
        AlertDialog(
            onDismissRequest = { showInvalidEmailsDialog.value = false },
            title = { Text("Correos inválidos") },
            text = {
                Column {
                    Text("No se puede crear el proyecto porque hay correos inválidos:")
                    Spacer(modifier = Modifier.height(8.dp))
                    invalidBuyAgents.forEach { email ->
                        Text("- $email", color = Color.Red)
                    }
                    invalidSellAgents.forEach { email ->
                        Text("- $email", color = Color.Red)
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showInvalidEmailsDialog.value = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun Chip(text: String, isInvalid: Boolean, onRemove: () -> Unit) {
    val backgroundColor = if (isInvalid) Color.Red else Color.Gray
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onRemove() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = text, color = Color.White, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
