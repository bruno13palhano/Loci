package com.bruno13palhano.loci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bruno13palhano.loci.ui.menu.BottomMenu
import com.bruno13palhano.loci.ui.navigation.MainNavGraph
import com.bruno13palhano.loci.ui.theme.LociTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LociTheme {
                val navController = rememberNavController()
                var showBottomBar by rememberSaveable { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(
                                visible = showBottomBar,
                                enter =
                                slideInVertically(
                                    animationSpec = spring(stiffness = Spring.StiffnessHigh),
                                    initialOffsetY = { it / 8 }
                                ),
                                exit =
                                slideOutVertically(
                                    animationSpec = spring(stiffness = Spring.StiffnessHigh),
                                    targetOffsetY = { it / 8 }
                                )
                            ) {
                                BottomMenu(navController = navController)
                            }
                        }
                    ) { innerPadding ->
                        MainNavGraph(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController
                        ) {
                            showBottomBar = it
                        }
                    }
                }
            }
        }
    }
}