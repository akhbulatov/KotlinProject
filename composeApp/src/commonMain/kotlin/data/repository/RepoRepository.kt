package data.repository

import data.network.GitHubApi
import data.network.model.toDomainModel
import domain.model.Repo

class RepoRepository(
    private val gitHubApi: GitHubApi,
) {

    suspend fun getRepos(): List<Repo> {
        return gitHubApi.getRepos()
            .let { repos ->
                repos.map { repo ->
                    repo.toDomainModel()
                }
            }
    }
}