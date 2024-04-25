package presentation.repolist.repodetails

import domain.model.RepoDetails

data class RepoDetailsUiState(
    val emptyProgress: Boolean = false,
    val emptyError: String? = null,
    val repoDetails: RepoDetails? = null
)