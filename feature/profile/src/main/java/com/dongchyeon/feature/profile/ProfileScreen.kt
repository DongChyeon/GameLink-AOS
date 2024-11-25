package com.dongchyeon.feature.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.gamelink.designsystem.component.GButton
import com.gamelink.designsystem.component.GTextField
import com.gamelink.designsystem.theme.GameLinkTheme
import com.gamelink.model.response.RankDetails
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ProfileRoute(
    showSnackBar: (String) -> Unit,
    profileViewModel: ProfileViewModel
) {
    LaunchedEffect(Unit) {
        profileViewModel.effect.collectLatest { event ->
            when (event) {
                is ProfileContract.Effect.ShowSnackBar -> {
                    showSnackBar(event.message)
                }
            }
        }
    }

    ProfileScreen(
        profileViewModel = profileViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ProfileScreen(
    profileViewModel: ProfileViewModel,
) {
    val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background)
    ) {
        if (uiState.isNotRegistered) {
            ProfileWithNotRegistered(
                gameName = uiState.gameNameInput,
                tagLine = uiState.tagLineInput,
                updateGameName = { profileViewModel.updateGameNameInput(it) },
                updateTagLine = { profileViewModel.updateTagLineInput(it) },
                onRegisterRiotAccount = { gameName, tagLine ->
                    profileViewModel.processEvent(
                        ProfileContract.Event.RegisterRiotAccount(
                            gameName, tagLine
                        )
                    )
                }
            )
        } else {
            ProfileWithRegistered(
                uiState = uiState
            ) {
                profileViewModel.processEvent(ProfileContract.Event.FetchProfile)
            }
        }
    }
}

@Composable
internal fun ProfileWithNotRegistered(
    gameName: String,
    tagLine: String,
    updateGameName: (String) -> Unit,
    updateTagLine: (String) -> Unit,
    onRegisterRiotAccount: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GameLinkTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "현재 등록된 계정이 없어요 :(",
                style = GameLinkTheme.typography.title1,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "계정을 등록하시면 인게임 정보를\n한 눈에 확인할 수 있어요!",
                style = GameLinkTheme.typography.body1,
                color = GameLinkTheme.colors.gray1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(46.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                GTextField(
                    value = gameName,
                    onValueChange = updateGameName,
                    placeholderText = "닉네임을 입력해주세요"
                )

                GTextField(
                    value = tagLine,
                    onValueChange = updateTagLine,
                    placeholderText = "게임 태그를 입력해주세요"
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            GButton(
                text = "등록"
            ) {
                onRegisterRiotAccount(gameName, tagLine)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ProfileWithRegistered(
    uiState: ProfileContract.State,
    onRefresh: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp * 0.8f

    ProfileHeader(
        backgroundImageUrl = uiState.userProfile?.backgroundImageUrl,
        summonerIconUrl = uiState.userProfile?.summonerIconUrl,
        summonerName = uiState.userProfile?.summonerName,
        summonerTag = uiState.userProfile?.summonerTag,
        summonerLevel = uiState.userProfile?.summonerLevel
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                GButton(
                    modifier = Modifier.weight(1f),
                    text = "전적 갱신"
                ) {
                    onRefresh()
                }

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    "최근 업데이트: ${uiState.userProfile?.revisionDate?.let { formatDate(it) }}",
                    modifier = Modifier.weight(1f),
                    style = GameLinkTheme.typography.body2Bold,
                    color = GameLinkTheme.colors.gray1
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        val pagerState = rememberPagerState(pageCount = { 3 })
        val pageSize = PageSize.Fixed(pageSize = itemWidth)

        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            pageSize = pageSize,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 20.dp
        ) { page ->
            when (page) {
                0 -> {
                    uiState.userProfile?.total?.let {
                        RankContent(rankType = "전체", rankDetail = it)
                    }
                }
                1 -> {
                    uiState.userProfile?.soloRank?.let {
                        RankContent(rankType = "개인/2인 랭크", rankDetail = it)
                    }
                }
                2 -> {
                    uiState.userProfile?.teamRank?.let {
                        RankContent(rankType = "팀 랭크", rankDetail = it)

                    }
                }
            }
        }
    }
}

@Composable
internal fun ProfileHeader(
    backgroundImageUrl: String?,
    summonerIconUrl: String?,
    summonerName: String?,
    summonerTag: String?,
    summonerLevel: Int?
) {
    Box(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = GameLinkTheme.colors.primary1,
                    shape = CircleShape
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(backgroundImageUrl)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "IMG_BACKGROUND"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(90.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(summonerIconUrl)
                                .build(),
                            contentScale = ContentScale.Crop,
                            contentDescription = "IMG_BACKGROUND"
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    summonerLevel?.let { level ->
                        Box(
                            modifier = Modifier
                                .background(
                                    color = GameLinkTheme.colors.background2,
                                    shape = RoundedCornerShape(40.dp)
                                )
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 4.dp
                                ),
                                text = "$level",
                                style = GameLinkTheme.typography.body2Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (summonerName != null) {
                            Text(
                                text = summonerName,
                                style = GameLinkTheme.typography.title1,
                                color = Color.White
                            )
                        }

                        summonerTag?.let {
                            Text(
                                text = it,
                                style = GameLinkTheme.typography.body1Bold,
                                color = GameLinkTheme.colors.gray2
                            )
                        }
                    }

                    Text(
                        text = "래더 랭킹",
                        style = GameLinkTheme.typography.body2Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
internal fun RankContent(
    rankType: String,
    rankDetail: RankDetails
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = GameLinkTheme.colors.gray1,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .background(
                    color = GameLinkTheme.colors.primary3,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
            text = rankType,
            style = GameLinkTheme.typography.navi,
            color = GameLinkTheme.colors.primary1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${rankDetail.rank} ${rankDetail.tier}",
                style = GameLinkTheme.typography.body1Bold,
                color = Color.White
            )

            Text(
                text = "${rankDetail.leaguePoints} LP",
                style = GameLinkTheme.typography.body1Bold,
                color = GameLinkTheme.colors.primary1
            )
        }

        Spacer(modifier= Modifier.height(4.dp))

        Text(
            text = "${rankDetail.wins}승 ${rankDetail.losses}패 (${decimalToPercentage(rankDetail.winRate)})",
            style = GameLinkTheme.typography.body2,
            color = GameLinkTheme.colors.gray1
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            rankDetail.best3champions.forEach { champion ->
                ChampionContent(
                    championImageUrl = champion.championImageUrl,
                    kills = champion.kills,
                    deaths = champion.deaths,
                    assists = champion.assists
                )
            }
        }
    }
}

@Composable
internal fun ChampionContent(
    championImageUrl: String,
    kills: Float,
    deaths: Float,
    assists: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(
                    color = GameLinkTheme.colors.background2,
                    shape = CircleShape
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(championImageUrl)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "IMG_CHAMPION",
        )
        Spacer(modifier = Modifier.height(8.dp))

        val formattedKills = "$kills".substring(0, 3)
        val formattedDeaths = "$deaths".substring(0, 3)
        val formattedAssists = "$assists".substring(0, 3)

        Text(
            text = "$formattedKills / $formattedDeaths / $formattedAssists",
            style = GameLinkTheme.typography.navi,
            color = GameLinkTheme.colors.gray1
        )
    }
}

private fun decimalToPercentage(decimalValue: Double): String {
    val percentage = decimalValue * 100
    return String.format("%.2f%%", percentage)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatDate(input: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val outputFormatter = DateTimeFormatter.ofPattern("yy.MM.dd")

    val dateTime = LocalDateTime.parse(input, inputFormatter)

    return try {
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        ""
    }
}