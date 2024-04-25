package presentation.repolist.repodetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import domain.model.RepoDetails

data class RepoDetailsScreen(
    private val repoId: Long
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val presenter: RepoDetailsPresenter = remember {
            RepoDetailsPresenter.create(repoId = repoId)
        }
        val state: RepoDetailsUiState by presenter.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(text = state.repoDetails?.name.orEmpty())
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { navigator.pop() }
                    )
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (state.emptyProgress) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                    )
                }

                if (!state.emptyError.isNullOrBlank()) {
                    Text(
                        text = state.emptyError.orEmpty(),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                    )
                }

                state.repoDetails?.let { repoDetails ->
                    RepoDetails(repoDetails = repoDetails)
                }
            }
        }
    }

    @Composable
    private fun RepoDetails(repoDetails: RepoDetails) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp)
        ) {
            Text(
                text = repoDetails.name,
                style = MaterialTheme.typography.h3
            )
            Spacer(
                modifier = Modifier.height(15.dp)
            )
            Text(
                text = repoDetails.description.orEmpty(),
                style = MaterialTheme.typography.body1
            )
            Spacer(
                modifier = Modifier.height(25.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = repoDetails.owner.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(size = 100.dp)
                        .clip(shape = CircleShape)
                )
                Spacer(
                    modifier = Modifier.width(15.dp)
                )
                Text(
                    text = repoDetails.owner.login,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}