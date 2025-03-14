package com.insomniacapps.theoldlist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.insomniacapps.theoldlist.data.TaskUiData
import com.insomniacapps.theoldlist.data.TaskUiModelAction
import com.insomniacapps.theoldlist.ui.theme.TaskUi
import com.insomniacapps.theoldlist.ui.theme.TheOldListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    TheOldListTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { TopAppBar(title = { Text("The Old list") }) }) { innerPadding ->
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
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}