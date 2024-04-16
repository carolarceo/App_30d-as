package com.example.frasesmotivacionales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frasesmotivacionales.ui.theme.FrasesMotivacionalesTheme
import com.example.frasesmotivacionales.model.Frase
import androidx.compose.material3.Card
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.frasesmotivacionales.data.Datasource
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrasesMotivacionalesTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Title(text = "30 Días para Brillar")
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AffirmationsApp()
                    }
                }
            }
        }
    }
}




@Composable
fun AffirmationsApp() {
    val affirmationListWithDay = Datasource().loadFrasesmotivacionales().mapIndexed { index, frase ->
        (index + 1).toString() to frase
    }
    AffirmationList(
        affirmationList = affirmationListWithDay,
    )
}


@Composable
fun Title(text: String) {
        Text(
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.headlineSmall

        )
}


@Composable
fun AffirmationCard(day: String, affirmation: Frase, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DÍA $day",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .height(350.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                fontSize = 20.sp,
                modifier = Modifier.padding(15.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}


@Composable
fun AffirmationList(affirmationList: List<Pair<String, Frase>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { (day, affirmation) ->
            AffirmationCard(
                day = day,
                affirmation = affirmation,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun PreviewAffirmationsApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = "30 Días para Brillar")
        AffirmationsApp()
    }
}
