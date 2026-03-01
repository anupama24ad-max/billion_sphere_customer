package com.billionsphere.ui.resetpassword

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.billionsphere.R
import com.billionsphere.ui.components.CompactDropdown
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.LabeledField
import com.billionsphere.ui.components.OtpView
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.congrats.CongratActivity
import com.billionsphere.ui.congrats.CongratScreen
import com.billionsphere.ui.theme.*


@Composable
fun ResetPasswordScreen(vm: ResetPasswordViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.billion_sphere_auth_bg_ic),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            contentAlignment = Alignment.Center
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimen_20)
                    .clip(RoundedCornerShape(dimen_60))
            )
            {
                Image(
                    painter = painterResource(R.drawable.glassy_transparent_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimen_36, vertical = dimen_46)
                )
                {
                    Textview(
                        text = stringResource(R.string.create_new),
                        color = LightWhite,
                        size = font_40,
                        semiBold = true
                    )
                    Textview(
                        text = stringResource(R.string.password),
                        color = LightWhite,
                        size = font_55,
                        semiBold = true
                    )
                    Spacer(modifier = Modifier.height(dimen_22))
                    LabeledField(
                        labelRes = R.string.password,
                        value = "",
                        placeholderRes = R.string.enter_your_password,
                        onValueChange = {

                        },
                        labelBold = false,
                        imeAction = ImeAction.Next,
                        isRequired = true,
                        labelMedium = true,
                        height = dimen_55,
                        isPassword = true,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.confirm_password,
                        value = "",
                        placeholderRes = R.string.enter_your_confirm_password,
                        onValueChange = {

                        },
                        labelBold = false,
                        imeAction = ImeAction.Next,
                        isRequired = true,
                        labelMedium = true,
                        height = dimen_55,
                        isPassword = true,
                        onImeAction = { focusManager.clearFocus() } // ← moves to next field
                    )

                    Spacer(modifier = Modifier.height(dimen_42))
                    GradientButton(
                        text = stringResource(R.string.next),
                        size = font_16,
                        textColor = Color.White,
                        medium = true,
                        color1 = Violet,
                        color2 = Maroon,
                        borderColor = Color.Transparent,
                        shape = dimen_16
                    ) {
                        val intent = Intent(context, CongratActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}