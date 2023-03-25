package com.example.mad_exercise1_composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_exercise1_composable.navigation.Navigation
import com.example.mad_exercise1_composable.ui.theme.MAD_Exercise1_ComposableTheme


class MainActivity : ComponentActivity() {

    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MAD_Exercise1_ComposableTheme {
                
                navController = rememberNavController()
                Navigation(navController = navController);

            }
        }
    }
}