package com.bruno13palhano.loci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bruno13palhano.loci.ui.theme.LociTheme
import com.bruno13palhano.model.Contract

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val contract = Contract(
            id = 1L,
            serviceId = 1L,
            serviceTitle = "Service title",
            serviceDescription = "Service description",
            customerName = "Customer name",
            freelancerName = "Freelancer name",
            price = 100f,
            status = "Status"
        )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LociTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LociTheme {
        Greeting("Android")
    }
}