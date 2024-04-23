package presentation.repolist

import domain.model.Repo

data class RepoListUiState(
    val emptyProgress: Boolean = false,
    val emptyError: String? = null,
    val repos: List<Repo> = emptyList()
)