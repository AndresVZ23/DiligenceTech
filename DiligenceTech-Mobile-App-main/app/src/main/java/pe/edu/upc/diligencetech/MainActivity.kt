package pe.edu.upc.diligencetech

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import dagger.hilt.android.AndroidEntryPoint
import pe.edu.upc.diligencetech.common.Home
import pe.edu.upc.diligencetech.iam.presentation.sing_in.SignInScreen
import pe.edu.upc.diligencetech.iam.presentation.sing_up.SignUpScreen
import pe.edu.upc.diligencetech.ui.theme.DiligenceTechAppTheme
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiligenceTechAppTheme {
                Home()
            }
        }
    }
}

