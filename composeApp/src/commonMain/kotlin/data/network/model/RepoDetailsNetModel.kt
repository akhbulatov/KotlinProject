package data.network.model

import domain.model.RepoDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoDetailsNetModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("owner") val owner: OwnerNetModel,
    @SerialName("description") val description: String,
) {

    @Serializable
    data class OwnerNetModel(
        @SerialName("login") val login: String,
        @SerialName("avatar_url") val avatarUrl: String
    )
}

fun RepoDetailsNetModel.toDomainModel() = RepoDetails(
    id = id,
    name = name,
    owner = RepoDetails.Owner(
        login = owner.login,
        avatarUrl = owner.avatarUrl
    ),
    description = description
)