package com.example.githubrepositories.presentation.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepositories.R
import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.presentation.RepoViewModel
import com.example.githubrepositories.presentation.util.Constants.Companion.Orange
import com.example.githubrepositories.presentation.util.Constants.Companion.Pink
import com.example.githubrepositories.presentation.util.Constants.Companion.Violet


@Composable
fun RepoDetailsScreen(id: String, navController: NavController) {

    val viewModel: RepoViewModel = viewModel()

    val repoDetails by viewModel.repoDetails.observeAsState()

    LaunchedEffect(key1 = true) {
        if (id.isNotEmpty()) {
            viewModel.getRepoDetails(id.toInt())
        }
    }

    val context = LocalContext.current as ComponentActivity

    // Define a state to track whether the URL has been opened
    val isUrlOpened = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                elevation = 0.dp
            ) {
                Box {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .clickable {
                                    navController.popBackStack()
                                },
                            tint = Color.White
                        )
                        repoDetails?.data?.let {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.owner.avatarUrl)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(40.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 64.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repoDetails?.data?.let {
                            Text(
                                text = it.fullName,
                                fontSize = 16.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(paddingValues)
        ) {
            when (repoDetails) {
                is Resource.Success -> {
                    repoDetails?.data?.let { details ->
                        Column(modifier = Modifier.padding(16.dp)) {
                            details.description?.let { desc ->
                                Text(
                                    text = desc,
                                    fontSize = 16.sp,
                                    color = Color.LightGray
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Divider()
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_forks),
                                    contentDescription = null,
                                    tint = Color.LightGray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${details.forks} forks",
                                    fontSize = 14.sp,
                                    color = Color.LightGray
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_watching),
                                    contentDescription = null,
                                    tint = Color.LightGray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${details.watchers} watching",
                                    fontSize = 14.sp,
                                    color = Color.LightGray
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_public),
                                    contentDescription = null,
                                    tint = Color.LightGray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${details.visibility} repository",
                                    fontSize = 14.sp,
                                    color = Color.LightGray
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(16.dp))
                            details.language?.let { lang ->
                                val languageColor = when (lang) {
                                    "Kotlin" -> Color.Violet
                                    "Java" -> Color.Orange
                                    else -> Color.Pink
                                }
                                Text(
                                    text = stringResource(id = R.string.lang),
                                    fontSize = 18.sp,
                                    color = Color.LightGray,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Divider(
                                    color = languageColor,
                                    thickness = 10.dp,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Canvas(
                                        modifier = Modifier.size(10.dp)
                                    ) {
                                        drawCircle(
                                            color = languageColor,
                                            center = center,
                                            radius = size.minDimension / 2
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = lang,
                                        fontSize = 14.sp,
                                        color = Color.LightGray
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Divider()
                            }
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                onClick = {
                                    val intent =
                                        Intent(Intent.ACTION_VIEW, Uri.parse(details.htmlUrl))
                                    context.startActivity(intent)
                                    isUrlOpened.value = true
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Black,
                                    contentColor = Color.White
                                ),
                            ) {
                                Text("More info")
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    repoDetails?.message?.let { errorMessage ->
                        LaunchedEffect(key1 = true) {
                            Toast.makeText(
                                context,
                                errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                else -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
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