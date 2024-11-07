package com.gamelink.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    @SerialName("userId")
    val userId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("backgroundImageUrl")
    val backgroundImageUrl: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("puuid")
    val puuid: String?,
    @SerialName("summonerId")
    val summonerId: String?,
    @SerialName("summonerName")
    val summonerName: String?,
    @SerialName("summonerTag")
    val summonerTag: String?,
    @SerialName("summonerIconUrl")
    val summonerIconUrl: String?,
    @SerialName("revisionDate")
    val revisionDate: String?,
    @SerialName("summonerLevel")
    val summonerLevel: Int?,
    @SerialName("total")
    val total: RankDetails?,
    @SerialName("soloRank")
    val soloRank: RankDetails?,
    @SerialName("teamRank")
    val teamRank: RankDetails?
)

@Serializable
data class RankDetails(
    @SerialName("rankImageUrl")
    val rankImageUrl: String,
    @SerialName("tier")
    val tier: String,
    @SerialName("rank")
    val rank: String,
    @SerialName("leaguePoints")
    val leaguePoints: Int,
    @SerialName("wins")
    val wins: Int,
    @SerialName("losses")
    val losses: Int,
    @SerialName("winRate")
    val winRate: Int,
    @SerialName("kda")
    val kda: Double,
    @SerialName("avgKills")
    val avgKills: Double,
    @SerialName("avgDeaths")
    val avgDeaths: Double,
    @SerialName("avgAssists")
    val avgAssists: Double,
    @SerialName("avgCs")
    val avgCs: Double,
    @SerialName("best3champions")
    val best3champions: List<ChampionDetails>,
    @SerialName("veteran")
    val veteran: Boolean,
    @SerialName("inactive")
    val inactive: Boolean,
    @SerialName("freshBlood")
    val freshBlood: Boolean,
    @SerialName("hotStreak")
    val hotStreak: Boolean
)

@Serializable
data class ChampionDetails(
    @SerialName("championImageUrl")
    val championImageUrl: String,
    @SerialName("kills")
    val kills: Int,
    @SerialName("deaths")
    val deaths: Int,
    @SerialName("assists")
    val assists: Int,
    @SerialName("winRate")
    val winRate: Int,
    @SerialName("wins")
    val wins: Int,
    @SerialName("losses")
    val losses: Int
)
