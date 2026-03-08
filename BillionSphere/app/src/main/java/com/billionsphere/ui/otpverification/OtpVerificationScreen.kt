package com.billionsphere.ui.otpverification

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.billionsphere.R
import com.billionsphere.ui.components.CompactDropdown
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.LabeledField
import com.billionsphere.ui.components.OtpView
import com.billionsphere.ui.components.ResendOtp
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.resetpassword.ResetPasswordActivity
import com.billionsphere.ui.theme.*
import com.billionsphere.utils.AppStrings

@Composable
fun OtpVerificationScreen(vm: OtpVerificationViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val verifyUiState by vm.verifyUiState.collectAsStateWithLifecycle()
    val isOtpEnabled by vm.isOtpEnabled.collectAsStateWithLifecycle()

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
                        text = stringResource(R.string.otp),
                        color = LightWhite,
                        size = font_40,
                        semiBold = true
                    )
                    Textview(
                        text = stringResource(R.string.verification),
                        color = LightWhite,
                        size = font_40,
                        semiBold = true
                    )
                    Spacer(modifier = Modifier.height(dimen_22))
                    Row {
                        Textview(
                            text = when (verifyUiState.from) {
                                AppStrings.Type.phoneNumber ->
                                    "Enter Otp to get your mobile number ${verifyUiState.countryCode} ${verifyUiState.phoneNumber}"
                                AppStrings.Type.email ->
                                    "Enter Otp to verify your email ${verifyUiState.email}"
                                else -> ""
                            },
                            color = LightGrey,
                            size = font_14,
                            medium = true
                        )
                    }
                    Spacer(modifier = Modifier.height(dimen_10))
                    OtpView(
                        value = verifyUiState.otp,
                        onFilled = {
                        },
                        onChanged = {
                            vm.updateVerifyUiState { copy(otp = it) }

                        }

                    )
                    Spacer(modifier = Modifier.height(dimen_18))
                    ResendOtp(
                        {
                            vm.resendOtpApi()
                            vm.updateVerifyUiState { copy(otp = "") }
                        },
                    )
                    Spacer(modifier = Modifier.height(dimen_38))
                    GradientButton(
                        text = stringResource(R.string.next),
                        size = font_16,
                        textColor = Color.White,
                        medium = true,
                        color1 = Violet,
                        color2 = Maroon,
                        borderColor = Color.Transparent,
                        shape = dimen_16,
                        enabled = isOtpEnabled
                    ) {
                        vm.verifyOtpApi()
//                        val intent = Intent(context, ResetPasswordActivity::class.java)
//                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}