package com.insomniacapps.theoldlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp


@Composable
fun AddTaskUi(modifier: Modifier = Modifier, onAddTaskButtonClick: (String) -> Unit) {
    Row(
        modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {

        var addTaskText by rememberSaveable { mutableStateOf("") }
        val focusManager = LocalFocusManager.current
        TextField(
            value = addTaskText,
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Add task") },
            textStyle = MaterialTheme.typography.labelMedium,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            keyboardActions = KeyboardActions(onDone = {
                addTaskText = sendAddTaskButtonCLickEventAndReset(onAddTaskButtonClick, addTaskText)
            },
                onPrevious = {
                    addTaskText = ""
                }),
            leadingIcon = {
                Icon(
                    Icons.Filled.Add,
                    "Add task icon",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            addTaskText = sendAddTaskButtonCLickEventAndReset(
                                onAddTaskButtonClick,
                                addTaskText
                            )
                        }
                )
            },
            onValueChange = { addTaskText = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onKeyEvent {
                    if (it.key == Key.Back) {
                        focusManager.clearFocus(false)
                    }
                    false
                },
            colors = TextFieldDefaults.colors().copy(
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

private fun sendAddTaskButtonCLickEventAndReset(
    onAddTaskButtonClick: (String) -> Unit,
    taskName: String
): String {
    if (taskName.isNotEmpty()) {
        onAddTaskButtonClick(taskName)
    }
    return ""
}