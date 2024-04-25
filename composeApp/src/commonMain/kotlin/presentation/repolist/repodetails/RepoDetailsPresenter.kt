package presentation.repolist.repodetails

import data.repository.RepoRepository
import di.CommonFactoryProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepoDetailsPresenter(
    private val repoId: Long,
    private val repoRepository: RepoRepository
) {

    private val presenterScope = CoroutineScope(Dispatchers.Main.immediate)

    private val _uiState = MutableStateFlow(RepoDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadRepos()
    }

    private fun loadRepos() {
        presenterScope.launch {
            try {
                _uiState.update { it.copy(emptyProgress = true) }

                val repoDetails = repoRepository.getRepoDetails(repoId)
                _uiState.update { it.copy(repoDetails = repoDetails) }
            } catch (e: Exception) {
                _uiState.update { it.copy(emptyError = e.message) }
            } finally {
                _uiState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    companion object {
        fun create(repoId: Long) = RepoDetailsPresenter(
            repoId = repoId,
            repoRepository = CommonFactoryProvider.commonFactory.repoRepository
        )
    }
}