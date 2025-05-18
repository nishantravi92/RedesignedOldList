package com.insomniacapps.theoldlist.ui

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
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
            topBar = {
                TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },
                    actions = { IconActions(navController) })
            }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                AddTaskUi(onAddTaskButtonClick = { tasksViewModel.addTask(it)})
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