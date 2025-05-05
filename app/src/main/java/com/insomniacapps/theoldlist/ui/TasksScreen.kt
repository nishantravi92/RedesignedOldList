package com.insomniacapps.theoldlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.insomniacapps.theoldlist.data.TaskUiData
import com.insomniacapps.theoldlist.data.TaskUiModelAction
import com.insomniacapps.theoldlist.ui.theme.TheOldListTheme
import com.insomniacapps.theoldlist.viewmodel.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(navController: NavController, tasksViewModel: TasksViewModel = hiltViewModel()) {
    TheOldListTheme(tasksViewModel.wallpaperResState) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = { TopAppBar(title = { Text("The Old list") },
                actions = { IconActions(navController) }) }) { innerPadding ->
            Column {
                AddTaskUi(Modifier.padding(innerPadding), onAddTaskButtonClick = { })
                TaskUi(
                    taskUiData = TaskUiData(
                        title = "My task",
                        dueDate = "10/23/2025",
                        isStarred = true,
                        taskUiModelAction = object : TaskUiModelAction {
                            override fun onChecked() {
                                TODO("Not yet implemented")
                            }

                            override fun onStarClicked(isStarred: Boolean) {
                                TODO("Not yet implemented")
                            }

                            override fun onLongClicked() {
                                TODO("Not yet implemented")
                            }
                        }),
                )
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