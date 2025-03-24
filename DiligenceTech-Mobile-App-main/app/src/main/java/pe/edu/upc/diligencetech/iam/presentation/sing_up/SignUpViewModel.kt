package pe.edu.upc.diligencetech.iam.presentation.sing_up

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.Resource
import pe.edu.upc.diligencetech.iam.data.remote.SignUpResource
import pe.edu.upc.diligencetech.iam.data.repositories.AuthenticationRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {
    private val _firstname = mutableStateOf("")
    val firstname: State<String> get() = _firstname
    fun onFirstnameChange(firstname: String) {
        _firstname.value = firstname
    }

    private val _lastname = mutableStateOf("")
    val lastname: State<String> get() = _lastname
    fun onLastnameChange(lastname: String) {
        _lastname.value = lastname
    }

    private val _email = mutableStateOf("")
    val email: State<String> get() = _email
    fun onEmailChange(email: String) {
        _email.value = email
    }

    val _password = mutableStateOf("")
    val password: State<String> get() = _password
    fun onPasswordChange(password: String) {
        _password.value = password
    }

    val _acceptTerms = mutableStateOf(false)
    val acceptTerms: State<Boolean> get() = _acceptTerms
    fun onAcceptTermsChange(acceptTerms: Boolean) {
        _acceptTerms.value = acceptTerms
    }

    val _passwordVisible = mutableStateOf(false)
    val passwordVisible: State<Boolean> get() = _passwordVisible
    fun onPasswordVisibilityChange(boolean: Boolean) {
        _passwordVisible.value = boolean
    }

    fun signUp(onSuccessfulSignUp: () -> Unit) {
        val signUpResource = SignUpResource(
            firstName = firstname.value,
            lastName = lastname.value,
            email = email.value,
            username = email.value.substringBefore('@'),
            password = password.value,
            roles = listOf("ROLE_USER")
        )
        if (signUpResource.firstName.isEmpty() || signUpResource.lastName.isEmpty() || signUpResource.email.isEmpty() || signUpResource.password.isEmpty()) {
            return
        }
        viewModelScope.launch {
            val result = repository.signUp(signUpResource)
            if (result) {
                onSuccessfulSignUp()
            }
        }
    }
}