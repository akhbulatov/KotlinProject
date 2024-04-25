package domain.model

data class RepoDetails(
    val id: Long,
    val name: String,
    val owner: Owner,
    val description: String?
) {

    data class Owner(
        val login: String,
        val avatarUrl: String
    )
}