package com.example.composepopupissuesample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.composepopupissuesample.ui.theme.ComposePopupIssueSampleTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val context = context ?: return null
        return ComposeView(context).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            /*
            addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) = Unit
                override fun onViewDetachedFromWindow(v: View) {
                    disposeComposition()
//                    removeAllViews()
                }
            })
             */

            setContent {
                ComposePopupIssueSampleTheme {
                    val backgroundColor = remember { MutableTransitionState(Color.Red) }
                    Column(modifier = Modifier.safeDrawingPadding()) {
                        AnimatedContent(
                            targetState = backgroundColor.targetState,
                            label = "AnimatedBackground",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        ) { color ->
                            Box(
                                Modifier
                                    .size(200.dp)
                                    .drawBehind { drawRect(color) },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(text = color.toString())
                            }
                        }

                        HorizontalPager(
                            state = rememberPagerState { 3 },
                            modifier = Modifier.padding(horizontal = 20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .drawBehind { drawRect(getRandomColor()) }
                            )
                        }

                        LazyColumn {
                            items(100) { index ->
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = index.toString(),
                                        Modifier.align(Alignment.CenterHorizontally)
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                }
                                HorizontalDivider()
                            }
                        }
                    }

                    LaunchedEffect(key1 = Unit) {
                        while (true) {
                            delay(3000)
                            backgroundColor.targetState = getRandomColor()
                        }
                    }
                }
            }
        }
    }

    private fun getRandomColor(): Color {
        val red = Random.nextInt(0, 255)
        val green = Random.nextInt(0, 255)
        val blue = Random.nextInt(0, 255)

        val androidColor = android.graphics.Color.rgb(red, green, blue)
        return Color(androidColor)
    }
}
