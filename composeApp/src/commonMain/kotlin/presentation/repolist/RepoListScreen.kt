package presentation.repolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import domain.model.Repo
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.repo_list_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

object RepoListScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val presenter: RepoListPresenter = remember { RepoListPresenter.create() }
        val state: RepoListUiState by presenter.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.repo_list_title))
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when {
                    state.emptyProgress -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        )
                    }

                    !state.emptyError.isNullOrBlank() -> {
                        Text(
                            text = state.emptyError.orEmpty(),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        )
                    }

                    state.repos.isNotEmpty() -> {
                        RepoList(list = state.repos)
                    }
                }
            }
        }
    }

    @Composable
    private fun RepoList(list: List<Repo>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(all = 15.dp)
        ) {
            items(
                count = list.size
            ) { index ->
                RepoListItem(item = list[index])

                if (index < list.size - 1) {
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RepoListItem(item: Repo) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = item.name,
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                text = item.description.orEmpty(),
                modifier = Modifier
                    .padding(top = 8.dp),
                color = Color.DarkGray,
                fontSize = 14.sp
            )
        }
    }
}