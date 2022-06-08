package com.cherry.guessthething.view.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cherry.guessthething.CartoonViewModel
import com.cherry.guessthething.domain.ProjectColors
import com.cherry.guessthething.domain.quizCountDown
import com.cherry.guessthething.view.Screen
import com.cherry.guessthething.view.components.ProjectOutlinedButton

@Composable
fun QuizScreen(navController: NavHostController, viewModel: CartoonViewModel) {
    val state = viewModel.state.value
    val colorState =
        animateColorAsState(
            targetValue = if (state.isAnswerRight) ProjectColors.Mint else ProjectColors.Pinky,
            animationSpec = tween(500)
        )

    //launchedEffect for Timer


    LaunchedEffect(key1 = true){
        quizCountDown(CartoonViewModel.normalModeTime){
            navController.navigate(Screen.QuizResultScreen.route.replaceAfter("/","-1"))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorState.value),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            elevation = 0.dp,
            border = BorderStroke(2.dp,ProjectColors.DarkBlue),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.rightAnswer.posterImageUrl)
                        .crossfade(250)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = "", loading = {
                        CircularProgressIndicator(modifier = Modifier
                            .padding(48.dp)
                            .align(Alignment.Center))
                    }, modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, ProjectColors.DarkBlue, RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .aspectRatio(0.725f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ){
                        items(state.variants){ variant->

                            ProjectOutlinedButton(text =variant) {
                                viewModel.onClickEvent(variant)
                            }
                        }
                    }
                }
            }
        }
    }
}