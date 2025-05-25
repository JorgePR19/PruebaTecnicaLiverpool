package com.example.pruebatecnicaliverpool.ui.flows.composables


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pruebatecnicaliverpool.R
import com.example.pruebatecnicaliverpool.domain.model.SortModel
import com.example.pruebatecnicaliverpool.utils.Constants

@Composable
fun DialogViewScreen(actionListener: (Constants.TypeSort) -> Unit) {
    val listSortType = listOf<SortModel>(
        SortModel(Constants.TypeSort.DEFAULT.s, Constants.TypeSort.DEFAULT),
        SortModel(Constants.TypeSort.LOWEST_PRICE.s, Constants.TypeSort.LOWEST_PRICE),
        SortModel(Constants.TypeSort.HIGHER_PRICE.s, Constants.TypeSort.HIGHER_PRICE),
    )


    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(
                text = stringResource(R.string.sort),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listSortType) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(it.title.uppercase(), fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = {
                                actionListener(it.action)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = { actionListener(Constants.TypeSort.CANCEL) }) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}