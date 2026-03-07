package com.billionsphere.ui.register

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.billionsphere.R
import com.billionsphere.ui.components.CompactDropdown
import com.billionsphere.ui.components.CompactDropdownWithLabel
import com.billionsphere.ui.components.DefaultBottomSheet
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.LabelTextview
import com.billionsphere.ui.components.LabeledField
import com.billionsphere.ui.components.OtpView
import com.billionsphere.ui.components.TermsAndConditionsCheckbox
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.theme.*
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(vm: RegisterViewModel) {
    val focusManager = LocalFocusManager.current
    val registerUiState by vm.registerUiState.collectAsStateWithLifecycle()
    val isRegisterEnabled by vm.isRegisterEnabled.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val sheetState = rememberModalBottomSheetState()
    val countryList by vm.countryList.collectAsStateWithLifecycle()


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
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .imePadding()
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimen_20, vertical = dimen_68)
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
                    Image(
                        painter = painterResource(R.drawable.signup_page_text_ic),
                        contentDescription = null,
                        modifier = Modifier
                            .width(dimen_258)
                            .height(dimen_74)
                    )
                    Spacer(modifier = Modifier.height(dimen_28))
                    Row {
                        Column(modifier = Modifier.weight(1f)) {
                            LabeledField(
                                labelRes = R.string.first_name,
                                value = registerUiState.firstName,
                                placeholderRes = R.string.enter_first_name,
                                onValueChange = {
                                    vm.updateRegisterUiState {
                                        copy(firstName = it)
                                    }
                                },
                                labelBold = false,
                                imeAction = ImeAction.Next,
                                isRequired = true,
                                labelMedium = true,
                                onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                            )
                        }
                        Spacer(modifier = Modifier.width(dimen_8))
                        Column(modifier = Modifier.weight(1f)) {
                            LabeledField(
                                labelRes = R.string.last_name,
                                value = registerUiState.lastName,
                                placeholderRes = R.string.enter_last_name,
                                onValueChange = {
                                    vm.updateRegisterUiState {
                                        copy(lastName = it)
                                    }
                                },
                                labelBold = false,
                                imeAction = ImeAction.Next,
                                isRequired = true,
                                labelMedium = true,
                                onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(dimen_10))
                    val label = stringResource(id = R.string.mobile_no)

                    val annotated = buildAnnotatedString {
                        append(label)
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append(" *")
                        }
                    }
                    LabelTextview(
                        text = annotated,
                        color = LightGrey,
                        size = font_12,
                        medium = true
                    )
                    Spacer(modifier = Modifier.height(dimen_4))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CompactDropdown(
                            modifier = Modifier
                                .width(dimen_42)
                                .height(dimen_46),
                            placeholder = registerUiState.countryCode.ifEmpty {
                                stringResource(
                                    R.string.empty_space
                                )
                            },
                            onClick = {
                                vm.updateRegisterUiState { copy(isRegister = true) }
                            })
                        if (registerUiState.isRegister) {
                            DefaultBottomSheet(
                                sheetState = sheetState,
                                title = stringResource(R.string.select_country),
                                countryList = countryList!!,
                                onDismiss = { vm.updateRegisterUiState { copy(isRegister = false) } },
                                onCountrySelected = {
                                    vm.updateRegisterUiState {
                                        copy(
                                            countryId = it.id ?: 0,
                                            countryCode = it.dialing_code ?: ""
                                        )
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.width(dimen_4))
                        LabeledField(
                            modifier = Modifier.weight(1f),
                            labelRes = R.string.empty_space,
                            value = registerUiState.mobileNumber,
                            placeholderRes = R.string.enter_mobile_no,
                            onValueChange = {
                                vm.updateRegisterUiState {
                                    copy(mobileNumber = it)
                                }
                            },
                            labelBold = false,
                            imeAction = ImeAction.Next,
                            isRequired = false,
                            labelMedium = true,
                            isPhoneNumber = true,
                            onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                        )
                    }
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.email_id,
                        value = registerUiState.emailId,
                        placeholderRes = R.string.enter_email_id,
                        onValueChange = {
                            vm.updateRegisterUiState {
                                copy(emailId = it)
                            }
                        },
                        labelBold = false,
                        imeAction = ImeAction.Next,
                        isRequired = true,
                        labelMedium = true,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.address,
                        value = registerUiState.address,
                        placeholderRes = R.string.enter_address,
                        onValueChange = {
                            vm.updateRegisterUiState {
                                copy(address = it)
                            }
                        },
                        labelBold = false,
                        imeAction = ImeAction.Next,
                        isRequired = true,
                        labelMedium = true,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    Row {
                        Column(modifier = Modifier.weight(1f)) {
                            LabeledField(
                                labelRes = R.string.city,
                                value = registerUiState.city,
                                placeholderRes = R.string.enter_city_name,
                                onValueChange = {
                                    vm.updateRegisterUiState {
                                        copy(city = it)
                                    }
                                },
                                labelBold = false,
                                imeAction = ImeAction.Next,
                                isRequired = true,
                                labelMedium = true,
                                onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                            )
                        }
                        Spacer(modifier = Modifier.width(dimen_8))
                        Column(modifier = Modifier.weight(1f)) {
                            LabeledField(
                                labelRes = R.string.pin_code,
                                value = registerUiState.pinCode,
                                placeholderRes = R.string.enter_pin_code,
                                onValueChange = {
                                    vm.updateRegisterUiState {
                                        copy(pinCode = it)
                                    }
                                },
                                labelBold = false,
                                imeAction = ImeAction.Next,
                                isRequired = true,
                                labelMedium = true,
                                onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(dimen_10))
                /*    CompactDropdownWithLabel(
                        placeholder = stringResource(R.string.select_state),
                        isImage = true,
                        labelMedium = true,
                        onClick = {

                        },
                        labelRes = R.string.state,
                        isRequired = true
                    )*/
                    LabeledField(
                        labelRes = R.string.state,
                        value = registerUiState.state,
                        placeholderRes = R.string.enter_state,
                        onValueChange = {
                            vm.updateRegisterUiState {
                                copy(state = it)
                            }
                        },
                        labelMedium = true,
                        isPassword = true,
                        labelBold = false,
                        isRequired = true,
                        imeAction = ImeAction.Next,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.password,
                        value = registerUiState.password,
                        placeholderRes = R.string.enter_your_password,
                        onValueChange = {
                            vm.updateRegisterUiState {
                                copy(password = it)
                            }
                        },
                        labelMedium = true,
                        isPassword = true,
                        labelBold = false,
                        isRequired = true,
                        imeAction = ImeAction.Next,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.confirm_password,
                        value = registerUiState.confirmPassword,
                        placeholderRes = R.string.enter_your_confirm_password,
                        onValueChange = {
                            vm.updateRegisterUiState {
                                copy(confirmPassword = it)
                            }
                        },
                        labelMedium = true,
                        isPassword = true,
                        labelBold = false,
                        isRequired = true,
                        imeAction = ImeAction.Next,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    LabeledField(
                        labelRes = R.string.sponsor_name,
                        value = registerUiState.sponsorName,
                        placeholderRes = R.string.enter_sponsor_name,
                        onValueChange = {
                            vm.updateRegisterUiState {
                                copy(sponsorName = it)
                            }
                        },
                        labelMedium = true,
                        labelBold = false,
                        isRequired = true,
                        imeAction = ImeAction.Next,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) } // ← moves to next field
                    )
                    Spacer(modifier = Modifier.height(dimen_10))

                    val labelReference = stringResource(id = R.string.reference_code)

                    val annotatedReference = buildAnnotatedString {
                        append(labelReference)
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append(" *")
                        }
                    }
                    LabelTextview(
                        text = annotatedReference,
                        color = LightGrey,
                        size = font_12,
                        medium = true
                    )
                    Spacer(modifier = Modifier.height(dimen_10))
                    OtpView(
                        onFilled = {
                        },
                        onChanged = {
                            vm.updateRegisterUiState{copy(referenceCode = it)}
                        }

                    )
                    Spacer(modifier = Modifier.height(dimen_12))

                    GradientButton(
                        text = stringResource(R.string.next),
                        size = font_16,
                        textColor = Color.White,
                        medium = true,
                        color1 = Violet,
                        color2 = Maroon,
                        borderColor = Color.Transparent,
                        shape = dimen_16,
                        enabled = isRegisterEnabled
                    ) {
                        vm.onClickRegisterBtn(activity)
                    }
                    Spacer(modifier = Modifier.height(dimen_12))
                    TermsAndConditionsCheckbox(
                        onTermsClick = {},
                        onPrivacyClick = {

                        }
                    )


                }

            }

        }


    }
}