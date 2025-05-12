package com.insomniacapps.theoldlist.settings.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.insomniacapps.theoldlist.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.insomniacapps.theoldlist.settings.viewmodel.WallpaperSettingsViewModel
import com.insomniacapps.theoldlist.ui.theme.TheOldListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperSettingsUi(
    navController: NavController,
    wallpaperSettingsUiModel: WallpaperSettingsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    TheOldListTheme(wallpaperResState = wallpaperSettingsUiModel.currentlySetWallpaperResId) {
        val wallpaperUiData by wallpaperSettingsUiModel.wallpaperUiData.collectAsState()
        val currentWallpaperResId by wallpaperSettingsUiModel.currentlySetWallpaperResId.collectAsState()
        Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }, navigationIcon = {
                Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Settings", modifier = Modifier
                .padding(end = 16.dp)
                .clickable { navController.popBackStack() })
            }) }) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp)
                ) {
                    items(wallpaperUiData.wallpapers) { wallpaper ->
                        val width = (260 * 9 / 16).dp
                        Column(Modifier.width(width)) {
                            Image(
                                painter = painterResource(id = wallpaper.wallpaperResId),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(260.dp)
                                    .aspectRatio(9 / 16f)
                                    .clip(RoundedCornerShape(4.dp))
                                    .clickable {
                                        wallpaperSettingsUiModel.updateWallPaper(wallpaper.wallpaperResId)
                                    },
                                contentDescription = "Wallpaper"
                            )
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Checkbox(
                                    checked = wallpaper.wallpaperResId == currentWallpaperResId,
                                    onCheckedChange = {
                                        wallpaperSettingsUiModel.updateWallPaper(wallpaper.wallpaperResId)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = MaterialTheme.colorScheme.primary,
                                        checkmarkColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                )
                            }
                        }
                    }
                }
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}