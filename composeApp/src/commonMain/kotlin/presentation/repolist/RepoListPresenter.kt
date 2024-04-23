package presentation.repolist

import data.repository.RepoRepository
import di.CommonFactoryProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepoListPresenter(
    private val repoRepository: RepoRepository
) {

    private val presenterScope = CoroutineScope(Dispatchers.Main.immediate)

    private val _uiState = MutableStateFlow(RepoListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadRepos()
    }

    private fun loadRepos() {
        presenterScope.launch {
            try {
                _uiState.update { it.copy(emptyProgress = true) }

                val repos = repoRepository.getRepos()
                _uiState.update { it.copy(repos = repos) }
            } catch (e: Exception) {
                _uiState.update { it.copy(emptyError = e.message) }
            } finally {
                _uiState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    companion object {
        fun create() = RepoListPresenter(
            repoRepository = CommonFactoryProvider.commonFactory.repoRepository
        )
    }
}