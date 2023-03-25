package com.example.mad_exercise1_composable.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailScreen(movieId: String){
    Text(text = movieId)
}