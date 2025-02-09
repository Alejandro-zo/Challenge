package com.alejandro.challenge.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.conteiner.CardBase
import com.alejandro.challenge.components.spacer.Spacer12
import com.alejandro.challenge.components.spacer.Spacer26
import com.alejandro.challenge.components.spacer.SpacerWidth
import com.alejandro.challenge.components.text.Text16
import com.alejandro.challenge.components.text.Text20SemiBold
import com.alejandro.domain.entity.Account
import com.alejandro.domain.util.formatNumber

@Composable
fun ItemAccountCard(
    modifier: Modifier = Modifier,
    account: Account,
    onClickAccount: () -> Unit,
) {
    CardBase(
        modifier = modifier.clickable { onClickAccount() }
    ) {

        ItemAccount(modifier = Modifier.padding(16.dp), account = account)

    }
}

@Composable
fun ItemAccount(
    modifier: Modifier = Modifier,
    account: Account,
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.icon_image_vector_logo)
        )

        SpacerWidth(24.dp)

        Column(modifier = Modifier.weight(1f)) {

            Text16(account.description)

            Spacer12()

            Text20SemiBold("${account.currency} ${formatNumber(account.amount)}")
        }
    }
}


@Composable
fun ItemAccountError(
    modifier: Modifier = Modifier,
    message: String,
) {
    CardBase {
        Row(
            modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = stringResource(R.string.icon_image_vector_logo)
            )

            SpacerWidth(24.dp)

            Text16(text = message, modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
private fun ItemAccountErrorPreview() {
    val account = Account(
        accountNumber = "500.00",
        currency = "$/ ",
        amount = 500.00,
        description = "Cuenta Corrinnte",
    )
    PreviewComponent {

        Column {
            ItemAccountCard(account = account, onClickAccount = {})

            Spacer26()

            ItemAccountError(message = stringResource(R.string.an_error_has_occurred_please_try_again))
        }
    }
}
