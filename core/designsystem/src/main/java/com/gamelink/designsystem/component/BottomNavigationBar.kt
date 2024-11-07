package com.gamelink.designsystem.component

import android.graphics.PointF
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.gamelink.core.designsystem.R
import com.gamelink.designsystem.theme.GameLinkTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        shadowElevation = 10.dp,
        color = backgroundColor,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = GameLinkTheme.colors.background,
                    shape = BottomNavCurve()
                )
                .padding(top = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .selectableGroup()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 25.dp, end = 25.dp,
                            top = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    content()
                }
            }

            MatchButton(
                modifier = Modifier.align(Alignment.TopCenter),
                onClick = {}
            )
        }
    }
}

@Composable
fun MatchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(55.dp)
            .clip(CircleShape),
        color = GameLinkTheme.colors.primary2,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_match),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun BottomNavigationBarItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    @DrawableRes icon: Int,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit
) {
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Surface(
        modifier = modifier,
        color = Color.Transparent,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.size(width = 50.dp, height = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = contentColor
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = label,
                    color = contentColor,
                    style = GameLinkTheme.typography.navi.copy(
                        color = contentColor
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    showSystemUi = true
)
@Composable
fun BottomNavigationBarPreview() {
    GameLinkTheme {
        BottomNavigationBar(
            backgroundColor = Color.White
        ) {
            BottomNavigationBarItem(
                label = "카테고리",
                selected = true,
                icon = R.drawable.ic_category,
                selectedContentColor = GameLinkTheme.colors.primary3,
                unselectedContentColor = GameLinkTheme.colors.gray1,
                onClick = {}
            )
            BottomNavigationBarItem(
                label = "채팅",
                selected = false,
                icon = R.drawable.ic_chat,
                selectedContentColor = GameLinkTheme.colors.primary3,
                unselectedContentColor = GameLinkTheme.colors.gray1,
                onClick = {}
            )
            Spacer(modifier = Modifier.size(50.dp))
            BottomNavigationBarItem(
                label = "프로필",
                selected = false,
                icon = R.drawable.ic_profile,
                selectedContentColor = GameLinkTheme.colors.primary3,
                unselectedContentColor = GameLinkTheme.colors.gray1,
                onClick = {}
            )
            BottomNavigationBarItem(
                label = "설정",
                selected = false,
                icon = R.drawable.ic_setting,
                selectedContentColor = GameLinkTheme.colors.primary3,
                unselectedContentColor = GameLinkTheme.colors.gray1,
                onClick = {}
            )
        }
    }
}

private const val CURVE_CIRCLE_RADIUS = 4000
private val TOP_PADDING_DP = 16.dp

private val mFirstCurveStartPoint = PointF()
private val mFirstCurveControlPoint1 = PointF()
private val mFirstCurveControlPoint2 = PointF()
private val mFirstCurveEndPoint = PointF()

private val mSecondCurveControlPoint1 = PointF()
private val mSecondCurveControlPoint2 = PointF()
private var mSecondCurveStartPoint = PointF()
private var mSecondCurveEndPoint = PointF()

class BottomNavCurve : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val topPaddingPx = with(density) { TOP_PADDING_DP.toPx() }

        return Outline.Generic(path = Path().apply {
            // 곡선의 깊이를 상단 패딩으로 조정
            val curveDepth = topPaddingPx + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4F)

            // 첫 번째 곡선의 시작점과 끝점 설정
            mFirstCurveStartPoint.x = (size.width / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3)
            mFirstCurveStartPoint.y = curveDepth

            mFirstCurveEndPoint.x = size.width / 2
            mFirstCurveEndPoint.y = topPaddingPx

            // 두 번째 곡선의 시작점과 끝점 설정
            mSecondCurveStartPoint = mFirstCurveEndPoint
            mSecondCurveEndPoint.set(
                (size.width / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3),
                curveDepth
            )

            // 첫 번째 곡선의 제어점 설정
            mFirstCurveControlPoint1.set(
                mFirstCurveStartPoint.x + curveDepth,
                mFirstCurveStartPoint.y
            )
            mFirstCurveControlPoint2.set(
                mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS,
                mFirstCurveEndPoint.y
            )

            // 두 번째 곡선의 제어점 설정
            mSecondCurveControlPoint1.set(
                mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS,
                mSecondCurveStartPoint.y
            )
            mSecondCurveControlPoint2.set(
                mSecondCurveEndPoint.x - curveDepth,
                mSecondCurveEndPoint.y
            )

            // Path 구성: 곡선을 그리며 패딩 및 깊이를 적용
            moveTo(0f, curveDepth)
            lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y)
            cubicTo(
                mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
            )
            cubicTo(
                mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
            )
            lineTo(size.width, curveDepth)
            lineTo(size.width, size.height)
            lineTo(0F, size.height)
            close()
        })
    }
}
