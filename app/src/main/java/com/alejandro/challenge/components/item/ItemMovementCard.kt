package com.alejandro.challenge.components.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.conteiner.CardBase
import com.alejandro.challenge.components.conteiner.RowAlignedLeftRight
import com.alejandro.challenge.components.spacer.Spacer26
import com.alejandro.challenge.components.text.Text14
import com.alejandro.challenge.components.text.Text16
import com.alejandro.challenge.components.text.Text16Medium
import com.alejandro.domain.entity.Account
import com.alejandro.domain.entity.Movement
import com.alejandro.domain.util.Constants.POSITIVE_SIGN
import com.alejandro.domain.util.formatNumber

@Composable
fun ItemMovementCard(
    modifier: Modifier = Modifier,
    movement: Movement,
    currency: String,
) {
    CardBase(modifier = modifier) {

        ItemMovement(modifier = Modifier.padding(16.dp), movement = movement, currency = currency)

    }
}

@Composable
fun ItemMovement(
    modifier: Modifier = Modifier,
    movement: Movement,
    currency: String,
) {
    val color = if (movement.sing == POSITIVE_SIGN) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.error
    RowAlignedLeftRight(
        modifier = modifier.fillMaxWidth(),
        contentLeft = {
            Column {
                Text16(movement.concept)
                Text14(movement.date)
            }
        },
        contentRight = {
            Text16Medium(
                text = "$currency ${movement.sing}${formatNumber(movement.amount)}",
                color = color
            )
        }
    )
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
