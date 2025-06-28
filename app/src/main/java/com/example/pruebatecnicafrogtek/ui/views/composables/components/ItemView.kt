package com.example.pruebatecnicafrogtek.ui.views.composables.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.pruebatecnicafrogtek.domain.models.BeersDomain
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp100
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp150
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp180
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp4
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp50
import com.example.pruebatecnicafrogtek.ui.theme.Dimens.Dp8
import com.example.pruebatecnicafrogtek.ui.theme.Sizes
import com.example.pruebatecnicafrogtek.ui.theme.blueColor
import com.example.pruebatecnicafrogtek.ui.theme.purpleColor
import com.example.pruebatecnicafrogtek.utils.Constants.formatDate

@Composable
fun ItemView(beersDomain: BeersDomain) {
    Card(
        modifier = Modifier
            .height(Dp180)
            .width(Dp150)
            .padding(vertical = Dp8, horizontal = Dp4)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize().padding(Dp4)) {
            val (image, name, tagline, date, ibu, description) = createRefs()

            Text(
                beersDomain.name,
                fontWeight = FontWeight.Bold,
                fontSize = Sizes.Sp12,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(name) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                })

            Text(
                beersDomain.tagline,
                fontWeight = FontWeight.Light,
                fontSize = Sizes.Sp10,
                textAlign = TextAlign.Center,
                color = blueColor,
                modifier = Modifier.constrainAs(tagline) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(name.bottom, margin = Dp4)
                })

            CoilImage(
                beersDomain.productImage,
                modifier = Modifier.size(width = Dp50, height = Dp100).constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(tagline.bottom)
                    bottom.linkTo(parent.bottom)
                })

            Text(
                beersDomain.description,
                fontWeight = FontWeight.Normal,
                fontSize = Sizes.Sp10,
                textAlign = TextAlign.Justify,
                modifier = Modifier.constrainAs(description) {
                    start.linkTo(image.end, margin = Dp4)
                    top.linkTo(image.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }, maxLines = 4, overflow = TextOverflow.Ellipsis)

            Text(
                "Ibu: ${beersDomain.ibu}",
                fontWeight = FontWeight.Medium,
                fontSize = Sizes.Sp10,
                textAlign = TextAlign.Center,
                color = purpleColor,
                modifier = Modifier.constrainAs(ibu) {
                    start.linkTo(description.start)
                    bottom.linkTo(image.bottom)
                })
            Text(
                beersDomain.first_brewed.formatDate(),
                fontWeight = FontWeight.Medium,
                fontSize = Sizes.Sp10,
                textAlign = TextAlign.Center,
                color = purpleColor,
                modifier = Modifier.constrainAs(date) {
                    end.linkTo(description.end)
                    bottom.linkTo(image.bottom)
                })
        }
    }
}