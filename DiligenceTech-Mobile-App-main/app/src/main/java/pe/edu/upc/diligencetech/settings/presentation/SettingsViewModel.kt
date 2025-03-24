package pe.edu.upc.diligencetech.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.upc.diligencetech.common.SessionManager
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sessionManager: SessionManager // Aquí se maneja la sesión
) : ViewModel() {

    private val _twoFactorAuthEnabled = mutableStateOf(false)
    val twoFactorAuthEnabled: State<Boolean> get() = _twoFactorAuthEnabled

    // Función para cerrar la sesión
    fun cerrarSesion(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            sessionManager.logout() // Lógica de cierre de sesión
            onLogoutSuccess() // Redirigir a la pantalla de login después de cerrar sesión
        }
    }

}
