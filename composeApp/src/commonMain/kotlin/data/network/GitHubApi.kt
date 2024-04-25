package data.network

import data.network.model.RepoDetailsNetModel
import data.network.model.RepoNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class GitHubApi(
    private val httpClient: HttpClient
) {

    suspend fun getRepos(): List<RepoNetModel> {
        val httpResponse: HttpResponse = httpClient.get(urlString = "repositories")
        return httpResponse.body()
    }

    suspend fun getRepoDetails(repoId: Long): RepoDetailsNetModel {
        val httpResponse: HttpResponse = httpClient.get(urlString = "repositories/$repoId")
        return httpResponse.body()
    }
}