package data.network.model

import domain.model.Repo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoNetModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
)

fun RepoNetModel.toDomainModel() = Repo(
    id = id,
    name = name,
    description = description
)