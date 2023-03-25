package com.example.mad_exercise1_composable.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun SimpleAppBar(title: String, navController: NavHostController){
    androidx.compose.material.TopAppBar(
        title = {
            Text(text = title,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        backgroundColor = Color.Black
    )
}