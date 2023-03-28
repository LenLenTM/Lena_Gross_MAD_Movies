package com.example.mad_exercise1_composable

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_exercise1_composable.navigation.Navigation
import com.example.mad_exercise1_composable.ui.theme.MAD_Exercise1_ComposableTheme
import com.example.movieappmad23.viewModels.MoviesViewModel


class MainActivity : ComponentActivity() {

    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MoviesViewModel by viewModels()
        viewModel.movieList

        setContent {
            MAD_Exercise1_ComposableTheme {
                
                navController = rememberNavController()
                Navigation(navController = navController, moviesViewModel = viewModel);

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy")
    }
    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart")
    }
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause")
    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume")
    }
    override fun onStart(){
        super.onStart()
        Log.i("MainActivity", "onStart")
    }

}