package com.varqulabs.dolarblue.core.presentation.generics.top_bars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerAppBar(
    title: String,
    @DrawableRes actionIcon: Int? = null,
    modifier: Modifier = Modifier,
    onClickActionIcon: () -> Unit = {},
    onClickDrawer: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(top = 44.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onClickDrawer) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.menu_p),
                    contentDescription = "Menu Icon Drawer",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = onClickActionIcon) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = actionIcon),
                        contentDescription = "Action Icon Drawer",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
    )
}