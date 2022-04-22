package com.cherry.guessthething.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cherry.guessthething.domain.ProjectColors

@Composable
fun ProjectOutlinedButton(text:String,onClick:()->Unit) {
    Button( elevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp
    ), shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, ProjectColors.DarkBlue),
        colors = ButtonDefaults.buttonColors(backgroundColor = ProjectColors.Lavander),
        onClick = {
           onClick()
        }) {
        Text(text = text, color = Color.Black, textAlign = TextAlign.Center)
    }
}