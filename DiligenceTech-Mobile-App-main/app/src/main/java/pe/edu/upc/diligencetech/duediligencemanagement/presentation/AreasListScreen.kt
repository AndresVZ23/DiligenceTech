package pe.edu.upc.diligencetech.duediligencemanagement.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.WorkbenchScreen
import pe.edu.upc.diligencetech.duediligencemanagement.domain.Area
import pe.edu.upc.diligencetech.ui.theme.Montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreasListScreen(
    viewModel: AreasListViewModel = hiltViewModel(),
    projectId: Long = 0,
    onHomeClick: () -> Unit,
    onProjectsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onEnteringAreaClick: (areaId: Long) -> Unit,
    onBackClick: () -> Unit
) {
    WorkbenchScreen(
        onHomeClick = onHomeClick,
        onProjectsClick = onProjectsClick,
        onMessagesClick = onMessagesClick,
        onProfileClick = onProfileClick,
        onSettingsClick = onSettingsClick,
        myOption = "Proyectos"
    ) {
        // variables
        val areas = viewModel.areas
        var searchQuery by remember { mutableStateOf("") } // Variable para el buscador
        var showDialog by remember { mutableStateOf(false) }
        var showEditDialog by remember { mutableStateOf(false) }
        var selectedArea by remember { mutableStateOf<Pair<Long, String>?>(null) }
        val totalStorage = 36
        val usedStorage = 10
        val areasCreated by remember { mutableStateOf(viewModel.getAreas(projectId)) }
        // content
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1A1A1A)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 30.dp, start = 16.dp),
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
                        text = viewModel.selectedProjectName.value,
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
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Buscar",
                                    tint = Color(0xFF868686)
                                )
                                Spacer(modifier = Modifier.width(1.dp))
                                Text("Buscar por área", fontFamily = Montserrat, color = Color(0xFF626262), fontSize = 14.sp)
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color.Transparent,
                            unfocusedLabelColor = Color.Transparent,
                            cursorColor = Color(0xFF626262),

                        ),
                        shape = RoundedCornerShape(8.dp),
                        textStyle = TextStyle(fontFamily = Montserrat, color = Color(0xFF626262), fontSize = 14.sp), // Estilo del texto ingresado
                        modifier = Modifier
                            .fillMaxWidth(0.94f)
                            .height(55.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFF626262), RoundedCornerShape(8.dp))
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                ) {
                    // Título
                    Text(
                        text = "Almacenamiento",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.padding(bottom = 20.dp, top = 10.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .background(Color.White, shape = RoundedCornerShape(2.dp))
                    ) {
                        val usedPercentage = if (totalStorage > 0) usedStorage.toFloat() / totalStorage else 0f
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(usedPercentage)
                                .background(Color(0xFFD6773D), shape = RoundedCornerShape(0.dp))
                        )
                    }
                    Text(
                        text = "$usedStorage GB de $totalStorage GB",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            color = Color.White,
                            fontSize = 14.sp,
                        ),
                        modifier = Modifier.padding(top = 15.dp, bottom = 2.dp)
                    )
                }
                Divider(
                    color = Color(0xFF626262),
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.999f)
                        .padding(horizontal = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp, start = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.all_areas_icon),
                        contentDescription = "Ícono de áreas",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 8.dp, bottom = 2.dp) // Espaciado opcional
                    )
                    Text(
                        text = "Todas las áreas",
                        style = TextStyle(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Divider(
                    color = Color(0xFF626262), // Color de la línea
                    thickness = 1.dp, // Grosor de la línea
                    modifier = Modifier
                        .fillMaxWidth(0.999f) // Ocupa el 90% del ancho
                        .padding(horizontal = 16.dp) // Espaciado horizontal para alinearlo con otros elementos
                )

                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (area in areas) {
                        if (searchQuery.isEmpty() || area.name.contains(searchQuery, ignoreCase = true)) {
                            AreaCard(
                                areaId = area.id,
                                projectName = area.name,
                                onClick = { onEnteringAreaClick(area.id) },
                                onEditClick = { areaId, areaName ->
                                    selectedArea = areaId to areaName
                                    showEditDialog = true
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre las tarjetas
                        }
                    }
                }

                if (showEditDialog && selectedArea != null) {
                    AreaEditDialog(
                        areaName = selectedArea!!.second,
                        onDismiss = { showEditDialog = false },
                        ondEditArea = { newName ->
                            showEditDialog = false
                            viewModel.editArea(selectedArea!!.first, newName)
                        }
                    )
                }

                if (showDialog) {
                    AreaInputDialog(
                        onDismiss = { showDialog = false },
                        onAddProject = {
                            viewModel.addArea(projectId)
                            showDialog = false
                        }
                    )
                }
            }

            FloatingActionButton(
                onClick = { showDialog = true },
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
fun AreaCard(
    areaId: Long,
    projectName: String,
    onClick: () -> Unit,
    onEditClick: (Long, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF282828)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // Centra el contenido verticalmente en la fila
            horizontalArrangement = Arrangement.SpaceBetween // Coloca elementos espaciados
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start// Para que los elementos internos estén alineados
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.areas_icon), // Reemplaza con tu ícono
                    contentDescription = "Ícono de áreas",
                    tint = Color(0xFFD6773D),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 8.dp, end = 8.dp) // Agrega un espaciado entre el ícono y el checkbox/texto
                )

                Text(
                    text = projectName,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.edit_icon), // Reemplaza con tu ícono de lápiz
                contentDescription = "Editar",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onEditClick(areaId, projectName)
                    }
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaInputDialog(
    viewModel: AreasListViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onAddProject: () -> Unit
) {
    val newArea = viewModel.newArea

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color(0xFF282828), shape = RoundedCornerShape(8.dp))
                .padding(25.dp)
        ) {
            Column {
                Text(
                    text = "Crear nueva área",
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
                    value = newArea.value,
                    onValueChange = {
                        viewModel.onNewAreaChange(it)
                    },
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
                        onClick = onAddProject,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            "Crear",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaEditDialog(
    areaName: String, // Nombre actual del folder
    onDismiss: () -> Unit,
    ondEditArea: (newName: String) -> Unit
) {
    var updatedAreaName by remember { mutableStateOf(areaName) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color(0xFF282828), shape = RoundedCornerShape(8.dp))
                .padding(25.dp)
        ) {
            Column {
                Text(
                    text = "Editar Area",
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
                    value = updatedAreaName,
                    onValueChange = { updatedAreaName = it },
                    label = { Text("Nombre", fontFamily = Montserrat, color = Color.White) },
                    placeholder = { Text("Ingrese nuevo nombre", fontFamily = Montserrat, color = Color.Gray) },
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
                        onClick = { onDismiss() },
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
                        onClick = { ondEditArea(updatedAreaName) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD6773D),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f),
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
}