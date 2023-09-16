package com.example.githubrepositories.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.githubrepositories.R
import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.presentation.RepoViewModel
import com.example.githubrepositories.presentation.util.Constants.Companion.LightBlue
import com.example.githubrepositories.presentation.util.Constants.Companion.Orange
import com.example.githubrepositories.presentation.util.Constants.Companion.Pink
import com.example.githubrepositories.presentation.util.Constants.Companion.Violet
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@Composable
fun RepoListScreen(navController: NavController) {
    val viewModel: RepoViewModel = viewModel()

    val repoList by viewModel.repoList.observeAsState()
    var onRefreshed by remember { mutableStateOf(true) }

    var isToastNotShown by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = onRefreshed) {
        viewModel.getRepoList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                elevation = 0.dp
            ) {
                Box {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        },
    ) { innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = false),
            onRefresh = {
                isToastNotShown = true
                onRefreshed = !onRefreshed
            },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    contentColor = Color.LightGray,
                    backgroundColor = Color.Black
                )
            }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
                    .padding(innerPadding)
            ) {

                when (repoList) {
                    is Resource.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.DarkGray)
                                .padding(horizontal = 16.dp),
                        ) {
                            item {
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            repoList?.data?.let { repositories ->
                                items(repositories.size) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 16.dp)
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null
                                            ) {
                                                val id = repositories[it].id.toString()
                                                navController.navigate("repoDetailsScreen/$id")
                                            }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = repositories[it].name,
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.LightBlue,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.widthIn(max = 200.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Box(
                                                modifier = Modifier
                                                    .border(
                                                        width = 1.dp,
                                                        color = Color.Gray,
                                                        shape = CircleShape
                                                    )
                                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = repositories[it].visibility,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.LightGray
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        repositories[it].description?.let { desc ->
                                            Text(
                                                text = desc,
                                                fontSize = 16.sp,
                                                color = Color.LightGray,
                                                maxLines = 3,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            repositories[it].language?.let { lang ->
                                                val languageColor =
                                                    when (repositories[it].language) {
                                                        "Kotlin" -> Color.Violet
                                                        "Java" -> Color.Orange
                                                        else -> Color.Pink
                                                    }
                                                Canvas(
                                                    modifier = Modifier.size(10.dp)
                                                ) {
                                                    drawCircle(
                                                        color = languageColor,
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
                                                Spacer(modifier = Modifier.width(16.dp))
                                            }
                                            var formattedTime by remember { mutableStateOf<String>("") }

                                            LaunchedEffect(repositories[it].updatedAt) {
                                                val sdf = SimpleDateFormat(
                                                    "yyyy-MM-dd'T'HH:mm:ss'Z'",
                                                    Locale.US
                                                )
                                                sdf.timeZone = TimeZone.getTimeZone("UTC")
                                                val updatedAtDate =
                                                    sdf.parse(repositories[it].updatedAt)

                                                val currentTime = Calendar.getInstance().time
                                                val timeDifference =
                                                    currentTime.time - updatedAtDate!!.time

                                                formattedTime = when {
                                                    timeDifference < 3600000L -> "${timeDifference / 60000L} minutes ago"
                                                    timeDifference < 86400000L -> "${timeDifference / 3600000L} hours ago"
                                                    timeDifference < 2592000000L -> "${timeDifference / 86400000L} days ago"
                                                    timeDifference < 31536000000L -> "${timeDifference / 2592000000L} months ago"
                                                    else -> "${timeDifference / 31536000000L} years ago"
                                                }
                                            }

                                            Text(
                                                text = "Updated $formattedTime",
                                                fontSize = 12.sp,
                                                color = Color.LightGray
                                            )
                                        }
                                    }
                                    Divider()
                                }
                            }
                        }
                    }

                    is Resource.Error -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .background(Color.DarkGray)
                        ) {
                            val context = LocalContext.current

                            if (isToastNotShown) {
                                repoList?.message?.let { errorMessage ->
                                    LaunchedEffect(key1 = true) {
                                        Toast.makeText(
                                            context,
                                            errorMessage,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                isToastNotShown = false
                            }
                        }
                    }

                    else -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .background(Color.DarkGray)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(48.dp),
                                color = Color.Black
                            )
                        }
                    }
                }

            }
        }
    }

}