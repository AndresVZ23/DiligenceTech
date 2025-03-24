package pe.edu.upc.diligencetech.iam.presentation.sing_up

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pe.edu.upc.diligencetech.R
import pe.edu.upc.diligencetech.common.Constants
import pe.edu.upc.diligencetech.ui.theme.Montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), onSignInTask: () -> Unit, onSignUpTask: () -> Unit) {
    val firstname = viewModel.firstname.value
    val lastname = viewModel.lastname.value
    val email = viewModel.email.value
    val password = viewModel.password.value
    val acceptTerms = viewModel.acceptTerms.value
    val passwordVisible = viewModel.passwordVisible.value

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
                        text = "Registrarse",
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
                        modifier = Modifier.padding(bottom = 20.dp)
                    ) {
                        Text(
                            text = "¿Ya tienes una cuenta?",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Card(
                            onClick = onSignInTask,
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            ),
                        ) {
                            Text(
                                text = "Inicia sesión",
                                style = TextStyle(
                                    color = Constants.ACCENT_COLOR,
                                    textDecoration = TextDecoration.Underline,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = firstname,
                            onValueChange = { viewModel.onFirstnameChange(it) },
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
                                .weight(1f)
                                .height(52.dp),
                            placeholder = {
                                Text(
                                    text = "Nombre*",
                                    color = Color.LightGray,
                                    fontSize = 15.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        )

                        OutlinedTextField(
                            value = lastname,
                            onValueChange = { viewModel.onLastnameChange(it) },
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
                                .weight(1f)
                                .height(52.dp),
                            placeholder = {
                                Text(
                                    text = "Apellido*",
                                    color = Color.LightGray,
                                    fontSize = 15.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        )
                    }

                    Box(modifier = Modifier.padding(top = 8.dp)){
                        OutlinedTextField(
                            value = email,
                            onValueChange = { viewModel.onEmailChange(it) },
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
                                    text = "Correo electrónico*",
                                    color = Color.LightGray,
                                    fontSize = 15.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        )
                    }
                    Box(modifier = Modifier.padding(top = 8.dp)) {
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
                                    text = "Contraseña*",
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    ) {
                        Checkbox(
                            checked = acceptTerms,
                            onCheckedChange = { viewModel.onAcceptTermsChange(it) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Constants.ACCENT_COLOR,
                                checkmarkColor = Color.White
                            )
                        )
                        Text(
                            text = "Acepto los ",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Términos y condiciones",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Normal,
                                color = Constants.ACCENT_COLOR,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.signUp(onSignUpTask)
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
                            text = "Registrarse",
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
                                text = "o registrate con",
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
                            .padding(top = 20.dp, bottom = 10.dp),
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