package pe.edu.upc.diligencetech.iam.presentation.sing_in

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.Constants
import pe.edu.upc.diligencetech.ui.theme.Montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel(), onSignUpTask: () -> Unit, onSignInTask: (id: Long, username: String,token: String) -> Unit) {
    val username = viewModel.username.value
    val password = viewModel.password.value
    val passwordVisible = viewModel.passwordVisible.value
    val errorMessage = viewModel.errorMessage.value


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Constants.SCREEN_BACKGROUND_COLOR)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.brand),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp, 150.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Constants.CARD_BACKGROUND_COLOR
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Iniciar sesión",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Color.White,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 40.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 50.dp)
                    ) {
                        Text(
                            text = "¿No tienes una cuenta aún?",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Card(
                            onClick = onSignUpTask,
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            ),
                        ) {
                            Text(
                                text = "Regístrate",
                                style = TextStyle(
                                    color = Constants.ACCENT_COLOR,
                                    textDecoration = TextDecoration.Underline,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Normal
                                )
                            )

                        }
                    }

                    OutlinedTextField(
                        value = username,
                        onValueChange = { viewModel.onUsernameChange(it) },
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Normal
                        ),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.LightGray,
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        placeholder = {
                            Text(
                                text = "Correo electrónico",
                                color = Color.LightGray,
                                fontSize = 15.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    )
                    Box(modifier = Modifier.padding(top = 8.dp, bottom = 36.dp)) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { viewModel.onPasswordChange(it) },
                            textStyle = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal
                            ),
                            shape = RoundedCornerShape(16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.LightGray,
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            placeholder = {
                                Text(
                                    text = "Contraseña",
                                    color = Color.LightGray,
                                    fontSize = 15.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val image = if (passwordVisible) {
                                    painterResource(id = R.drawable.show_password_icon)
                                } else {
                                    painterResource(id = R.drawable.hide_password_icon)
                                }

                                // Ícono clickeable para mostrar/ocultar la contraseña
                                Image(
                                    painter = image,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    modifier = Modifier
                                        .size(30.dp) // Cambia el tamaño del ícono aquí (20.dp es un ejemplo)
                                        .clickable {
                                            viewModel.onPasswordVisibilityChange(!passwordVisible)
                                        }
                                )
                            }

                        )
                    }
                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.signIn(onSignInTask)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Constants.ACCENT_COLOR
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            color = Color.White,
                            style = TextStyle(
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, bottom = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Divider(
                                color = Color(0xFFD9D9D9),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                            )

                            Text(
                                text = "o inicia sesión con",
                                color = Color(0xFFD9D9D9),
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.CenterVertically),
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )

                            Divider(
                                color = Color(0xFFD9D9D9),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(1.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 30.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {  },
                            modifier = Modifier
                                .size(85.dp)
                                .background(Color.White, shape = RoundedCornerShape(42.5.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.google_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                contentScale = ContentScale.Fit
                            )
                        }

                        Spacer(modifier = Modifier.width(18.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier
                                .size(85.dp)
                                .background(Color.White, shape = RoundedCornerShape(42.5.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.linkedin_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }
        }
    }
}