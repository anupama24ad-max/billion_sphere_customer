package com.billionsphere.ui.login

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.billionsphere.R
import com.billionsphere.ui.components.CompactDropdown
import com.billionsphere.ui.components.DefaultBottomSheet
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.LabeledField
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.forgotpassword.ForgotPasswordActivity
import com.billionsphere.ui.register.RegisterViewModel
import com.billionsphere.ui.theme.*
import com.billionsphere.utils.AppStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(vm: RegisterViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val loginUiState by vm.loginUiState.collectAsStateWithLifecycle()
    val isLoginEnabled by vm.isLoginEnabled.collectAsStateWithLifecycle()
    val registerUiState by vm.registerUiState.collectAsStateWithLifecycle()
    val countryList by vm.countryList.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.billion_sphere_login_ic),
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
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimen_20)
                    .clip(RoundedCornerShape(dimen_60)),
            )
            {
                Image(
                    painter = painterResource(R.drawable.glassy_transparent_login_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = dimen_36, vertical = dimen_46)
                )
                {
                    Textview(
                        text = "Welcome to",
                        color = Color.White,
                        size = font_36,
                        semiBold = true
                    )
                    Image(
                        painter = painterResource(R.drawable.billion_sphere_text_ic),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    if (!loginUiState.showMobileField) {
                        LabeledField(
                            labelRes = R.string.mobile_number_or_email,
                            value = loginUiState.emailOrPhoneNumber,
                            placeholderRes = R.string.enter_mobile_number_or_email,
                            onValueChange = {
                                val digits = it.filter { it.isDigit() }

                                vm.updateLoginUiState {
                                    copy(
                                        emailOrPhoneNumber = it,
                                        showMobileField = digits.length >= 3,
                                        type = if (digits.length >= 3)
                                            AppStrings.Type.phoneNumber
                                        else
                                            AppStrings.Type.email
                                    )
                                }
                            },
                            labelBold = false,
                            imeAction = ImeAction.Next,
                            isRequired = true,
                            labelMedium = true,
                            labelColor = Color.White,
                            height = dimen_46,
                            textColor = Color.White,
                            onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
                        )
                    }
                    if (loginUiState.showMobileField) {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            CompactDropdown(
                                modifier = Modifier
                                    .width(dimen_42)
                                    .height(dimen_46),
                                color = Color.White,
                                placeholder = loginUiState.countryCode,
                                onClick = {
                                    vm.updateRegisterUiState { copy(isRegister = true) }
                                }
                            )
                            if (registerUiState.isRegister) {
                                DefaultBottomSheet(
                                    sheetState = sheetState,
                                    title = stringResource(R.string.select_country),
                                    countryList = countryList!!,
                                    onDismiss = { vm.updateRegisterUiState { copy(isRegister = false) } },
                                    onCountrySelected = {
                                        vm.updateLoginUiState {
                                            copy(
                                                countryId = it.id ?: 0,
                                                countryCode = it.dialing_code ?: "",

                                                )
                                        }
                                    }
                                )
                            }


                            Spacer(modifier = Modifier.width(dimen_4))

                            LabeledField(
                                modifier = Modifier.weight(1f),
                                labelRes = R.string.empty_space,
                                value = loginUiState.emailOrPhoneNumber,
                                placeholderRes = R.string.enter_mobile_no,
                                onValueChange = {
                                    val digits = it.filter { it.isDigit() }
                                    vm.updateLoginUiState {
                                        copy(
                                            emailOrPhoneNumber = it,
                                            showMobileField = digits.length >= 3,
                                            type = if (digits.length >= 3)
                                                AppStrings.Type.phoneNumber
                                            else
                                                AppStrings.Type.email
                                        )
                                    }
                                },
                                textColor = Color.White,
                                isPhoneNumber = true
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.password,
                        value = loginUiState.password,
                        placeholderRes = R.string.enter_your_password,
                        onValueChange = {
                            vm.updateLoginUiState { copy(password = it) }
                        },
                        labelBold = false,
                        imeAction = ImeAction.Next,
                        isRequired = true,
                        labelMedium = true,
                        isPassword = true,
                        labelColor = Color.White,
                        height = dimen_46,
                        textColor = Color.White,
                        onImeAction = { focusManager.clearFocus() })

                    Spacer(modifier = Modifier.height(dimen_10))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Textview(
                            text = stringResource(R.string.forgot_password),
                            color = Color.White,
                            size = font_14,
                            semiBold = true,
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable {
                                    val intent = Intent(context, ForgotPasswordActivity::class.java)
                                    context.startActivity(intent)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(dimen_10))
                    GradientButton(
                        text = stringResource(R.string.login),
                        size = font_16,
                        textColor = Color.White,
                        medium = true,
                        color1 = Violet,
                        color2 = Maroon,
                        borderColor = Color.Transparent,
                        shape = dimen_16,
                        enabled = isLoginEnabled
                    ) {
                        vm.onClickLoginBtn(activity)
                    }

                }
            }
        }

    }
}