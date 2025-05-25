package com.example.pruebatecnicaliverpool.ui.flows.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebatecnicaliverpool.domain.model.ProductsDomain
import com.example.pruebatecnicaliverpool.utils.CircleColorVariants
import com.example.pruebatecnicaliverpool.utils.CoilImage
import com.example.pruebatecnicaliverpool.utils.extensions.formatMoney

@Composable
fun ItemView(productsDomain: ProductsDomain) {
    val validColors = productsDomain.variantsColor.filter { it.isNotBlank() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CoilImage(productsDomain.lgImage)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {

                Text(
                    text = productsDomain.productDisplayName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )

                Text(
                    text = productsDomain.listPrice.formatMoney(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.LineThrough,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    productsDomain.promoPrice.formatMoney(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(modifier = Modifier.fillMaxWidth()) {
                    validColors.forEach { colorHex ->
                        CircleColorVariants(colorHex)
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
        }
    }
}