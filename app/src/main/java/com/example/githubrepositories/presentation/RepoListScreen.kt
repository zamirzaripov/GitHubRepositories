package com.example.githubrepositories.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlin.random.Random

@Composable
fun RepoListScreen() {
    val viewModel: RepoViewModel = viewModel()

    val repoList by viewModel.repoList.observeAsState()
    var onRefreshed by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = onRefreshed) {
        viewModel.getRepoList()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = false),
        onRefresh = { onRefreshed = !onRefreshed },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                contentColor = Color.Blue
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(horizontal = 16.dp)
        )
        {
            repoList?.let { repositories ->
                items(repositories.size) {
                    Column {
                        Row {
                            Text(
                                text = repositories[it].name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.LightGray, shape = CircleShape)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = repositories[it].visibility,
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        repositories[it].description?.let { desc ->
                            Text(
                                text = desc,
                                fontSize = 16.sp,
                                color = Color.LightGray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Row {
                            repositories[it].language?.let { lang ->
                                val randomColor = Color(
                                    Random.nextFloat(),
                                    Random.nextFloat(),
                                    Random.nextFloat(),
                                    1f
                                )
                                Canvas(
                                    modifier = Modifier.size(30.dp)
                                ) {
                                    drawCircle(
                                        color = randomColor,
                                        center = center,
                                        radius = size.minDimension / 2
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = lang,
                                    fontSize = 12.sp,
                                    color = Color.LightGray
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = repositories[it].updatedAt,
                                fontSize = 12.sp,
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }
        }
    }
}