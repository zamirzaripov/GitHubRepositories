package com.example.githubrepositories.presentation.navigation

import com.example.githubrepositories.R

sealed class NavItem(val route: String, val titleId: Int, val iconId: Int) {
    object Repositories : NavItem("repositories",R.string.repos, R.drawable.ic_repositories)
    object Screen2Item : NavItem("screen2", R.string.screen2, R.drawable.github)
    object Screen3Item : NavItem("screen3", R.string.screen3, R.drawable.github)
    object Screen4Item : NavItem("screen4", R.string.screen4, R.drawable.github)
    object Screen5Item : NavItem("screen5", R.string.screen5, R.drawable.github)
}
