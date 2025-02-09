package com.alejandro.challenge.components.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CopyAll
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandro.challenge.R
import com.alejandro.challenge.components.PreviewComponent
import com.alejandro.challenge.components.conteiner.CardBase
import com.alejandro.challenge.components.conteiner.RowAlignedLeftRight
import com.alejandro.challenge.components.icon.IconImageVectorButton
import com.alejandro.challenge.components.spacer.Spacer16
import com.alejandro.challenge.components.text.Text14
import com.alejandro.challenge.components.text.Text16
import com.alejandro.domain.entity.Account
import com.alejandro.domain.util.Constants.YOU_HAVE_NO_MOVEMENTS
import com.alejandro.domain.util.copyToClipboard
import com.alejandro.domain.util.sharedString

@Composable
fun AccountDetail(
    modifier: Modifier = Modifier,
    account: Account,
) {
    val context = LocalContext.current
    CardBase(modifier = modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            ItemAccount(modifier = Modifier, account = account)

            Spacer16()

            RowAlignedLeftRight(
                contentLeft = {
                    Text14(stringResource(R.string.account_number))

                    Text16(account.accountNumber)

                },
                contentRight = {

                    Row {
                        IconImageVectorButton(
                            imageVector = Icons.Rounded.CopyAll, description = "",
                            onClick = { copyToClipboard(context, account.accountNumber) }
                        )

                        IconImageVectorButton(
                            imageVector = Icons.Rounded.Share, description = "",
                            onClick = {
                                sharedString(
                                    context = context,
                                    value = "$YOU_HAVE_NO_MOVEMENTS ${account.accountNumber}"
                                )
                            }
                        )
                    }

                }
            )
        }
    }
}

@Preview
@Composable
private fun AccountDetailPreview() {
    val account = Account(
        accountNumber = "987789987789",
        currency = "$/ ",
        amount = 500.00,
        description = "Cuenta Corrinnte",
    )
    PreviewComponent {
        AccountDetail(account = account)
    }
}
