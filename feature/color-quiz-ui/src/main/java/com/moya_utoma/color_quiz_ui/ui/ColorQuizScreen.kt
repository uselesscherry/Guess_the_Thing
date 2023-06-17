package com.moya_utoma.color_quiz_ui.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moya_utoma.color_quiz_ui.util.parseHexToColor
import com.moya_utoma.presentation.QuizColors
import com.moya_utoma.quizer.timer.countDown

@Composable
fun ColorQuizScreen(
    navigateToResultScreen: (Int) -> Unit,
    viewModel: ColorQuizViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val colorState =
        animateColorAsState(
            targetValue = if (state.value.isPreviousAnswerCorrect) QuizColors.Mint else QuizColors.Pinky,
            animationSpec = tween(700)
        )

    //launchedEffect for Timer

    LaunchedEffect(key1 = true) {

        countDown(30) {
            viewModel.saveResults()
            navigateToResultScreen(viewModel.score)
        }
    }

    if (state.value.question.isNotEmpty) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorState.value),
            contentAlignment = Alignment.Center
        ) {
            QuizCard(
                questionColor = state.value.question.rightAnswer.description.parseHexToColor(),
                answerVariants = state.value.question.variants,
                onClick = viewModel::checkAnswer
            )
        }
    }

}

@Composable
fun QuizCard(questionColor: Color, answerVariants: List<String>, onClick: (String) -> Unit) = Card(
    modifier = Modifier
        .fillMaxWidth(0.6f),
    elevation = 0.dp,
    border = BorderStroke(2.dp, QuizColors.DarkBlue),
    shape = RoundedCornerShape(10),
    backgroundColor = Color.White.copy(alpha = 0.5f)
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(BorderStroke(2.dp, QuizColors.DarkBlue), RoundedCornerShape(10))
                .clip(RoundedCornerShape(10))
                .background(questionColor)
        )
        Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                answerVariants.forEach { variant ->
                    OutlinedButton(onClick = { onClick(variant) }) {
                        Text(text = variant)
                    }
                }
            }
        }
    }
}