package com.insomniacapps.theoldlist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.insomniacapps.theoldlist.R
import com.insomniacapps.theoldlist.data.TaskUiData
import com.insomniacapps.theoldlist.viewmodel.TasksViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskUi(taskUiData: TaskUiData, modifier: Modifier = Modifier) {
    val isChecked by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 1.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .roundedShape()
            .background(
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
            )
            .combinedClickable(onLongClick = { taskUiData.taskUiModelAction.onLongClicked(taskId = taskUiData.id) }) { }
    ) {
        Spacer(modifier = Modifier.width(width = 8.dp))
        Checkbox(
            checked = isChecked,
            colors = CheckboxDefaults.colors(uncheckedColor = MaterialTheme.colorScheme.onPrimary),
            onCheckedChange = {
                taskUiData.taskUiModelAction.onChecked()
            })
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .wrapContentHeight()
                .fillMaxWidth(0.8f)
        ) {
            Text(
                text = taskUiData.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            taskUiData.dueDate?.let {
                Text(
                    text = "Due: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            val painterResource =
                if (!taskUiData.isStarred) painterResource(id = R.drawable.ic_baseline_star_outline_24)
                else painterResource(
                    id = R.drawable.ic_baseline_star_filled_24
                )
            Icon(
                painterResource,
                "Star task",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .width(24.dp)
                    .clickable {
                        taskUiData.taskUiModelAction.onStarClicked(!taskUiData.isStarred)
                    }
            )
        }
    }
}

@Preview
@Composable
fun TaskPreview() {
    TaskUi(taskUiData = TasksViewModel.TEST_DATA)
}