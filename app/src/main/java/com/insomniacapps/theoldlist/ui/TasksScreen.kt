package com.insomniacapps.theoldlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.insomniacapps.theoldlist.R
import com.insomniacapps.theoldlist.ui.theme.TheOldListTheme
import com.insomniacapps.theoldlist.viewmodel.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(navController: NavController, tasksViewModel: TasksViewModel = hiltViewModel()) {
    TheOldListTheme(tasksViewModel.wallpaperResState) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },
                actions = { IconActions(navController) }) }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                AddTaskUi(onAddTaskButtonClick = { })
                val tasks = tasksViewModel.tasks.collectAsLazyPagingItems()
                LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    items(tasks.itemCount, itemContent = { index ->
                        TaskUi(taskUiData = tasks[index]!!)
                    })
                }
            }
        }
    }
}

@Composable
private fun IconActions(navController: NavController) {
    Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings", modifier = Modifier
        .padding(end = 16.dp)
        .clickable { navController.navigate(route = "settings") })
}