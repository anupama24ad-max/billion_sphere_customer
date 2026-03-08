package com.billionsphere.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.billionsphere.R
import com.billionsphere.ui.register.model.GetDropDownsResponseItem
import com.billionsphere.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color1: Color = SkyBlue,
    color2: Color = Blue,
    textColor: Color = Color.White,
    borderColor: Color = LightSkyBlue,
    height: Dp = dimen_48,
    medium: Boolean = false,
    semiBold: Boolean = false,
    bold: Boolean = false,
    size: TextUnit = font_12,
    shape: Dp = dimen_10,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .alpha(if (enabled) 1f else 0.5f),
        shape = RoundedCornerShape(shape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (enabled) 1f else 0.5f)
                .background(
                    Brush.linearGradient(listOf(color1, color2)),
                    RoundedCornerShape(shape)
                )
                .border(
                    width = dimen_1,
                    color = borderColor,
                    shape = RoundedCornerShape(shape)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = size,
                    fontWeight = if (medium) FontWeight.Medium else if (semiBold) FontWeight.SemiBold else if (bold) FontWeight.Bold else FontWeight.Normal,
                    color = textColor
                )
            )
        }
    }
}

@Composable
fun Textview(
    text: String,
    size: TextUnit = font_12,      // e.g., 14.sp, 20.sp
    bold: Boolean = false,
    semiBold: Boolean = false,
    medium: Boolean = false,
    color: Color = Grey,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        style = TextStyle(
            fontSize = size,
            fontWeight = when {
                bold -> FontWeight.Bold
                semiBold -> FontWeight.SemiBold
                medium -> FontWeight.Medium
                else -> FontWeight.Normal
            }
        ),
    )
}

/*@Composable
fun GlassContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dimen_60))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        RedWith8Opacity,
                        RedWith8Opacity,
                        GlassyViolet
                    )
                )
            )
            .blur(dimen_80)
    ) {
        content()
    }
}*/

@Composable
fun LabeledField(
    @StringRes labelRes: Int,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholderRes: Int,
    modifier: Modifier = Modifier,
    // Label styling
    labelSize: TextUnit = font_14,
    labelBold: Boolean = false,
    labelMedium: Boolean = false,
    labelSemiBold: Boolean = false,
    labelColor: Color = LightGrey,
    // Spacing
    labelToFieldSpace: Dp = dimen_4,
    bottomSpace: Dp = dimen_20,
    // AppEditText passthroughs
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    isPhoneNumber: Boolean = false,
    enabled: Boolean = true,
    height: Dp = dimen_46,
    moreText: Boolean = false,
    isRequired: Boolean = false,
    isLock: Boolean = false,
    textColor: Color = LightGrey
) {
    val labelText = buildAnnotatedString {
        append(stringResource(labelRes))
        if (isRequired) {
            append(" ")
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("*")
            }
        }
    }
    LabelTextview(
        text = labelText,
        size = labelSize,
        bold = labelBold,
        medium = labelMedium,
        semiBold = labelSemiBold,
        color = labelColor
    )
    Spacer(Modifier.height(labelToFieldSpace))

    CompactEditText(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        isPassword = isPassword,
        imeAction = imeAction,
        onImeAction = onImeAction,
        placeholder = stringResource(placeholderRes),
        isPhoneNumber = isPhoneNumber,
        enabled = enabled,
        height = height,
        singleLine = singleLine,
        moreText = moreText,
        isLock = isLock,
        textColor = textColor
    )

}

@Composable
fun LabelTextview(
    text: AnnotatedString,
    size: TextUnit = font_12,      // e.g., 14.sp, 20.sp
    bold: Boolean = false,
    semiBold: Boolean = false,
    medium: Boolean = false,
    color: Color = Grey,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        style = TextStyle(
            fontSize = size,
            fontWeight = when {
                bold -> FontWeight.Bold
                semiBold -> FontWeight.SemiBold
                medium -> FontWeight.Medium
                else -> FontWeight.Normal
            }
        ),
    )
}

@Composable
fun CompactEditText(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    isPhoneNumber: Boolean = false,
    enabled: Boolean = true,
    height: Dp = dimen_32,
    moreText: Boolean = false,
    singleLine: Boolean = true,
    textColor: Color = LightGrey,
    isLock: Boolean = false,
    onImeAction: () -> Unit = {},

    ) {
    var obscure by rememberSaveable(isPassword) { mutableStateOf(isPassword) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(dimen_1, Color.White.copy(alpha = 0.35f), RoundedCornerShape(dimen_10)),
        shape = RoundedCornerShape(dimen_10),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White.copy(alpha = 0.08f)
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = dimen_12,
                        end = if (isPassword) dimen_0 else dimen_12,
                        top = dimen_8,
                        bottom = dimen_8
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                /* if (isLock) {
                     Image(
                         painter = painterResource(R.drawable.grey_lock_ic),
                         contentDescription = null,
                         modifier = Modifier.padding(end = dimen_10)
                     )
                 }*/
                // text field area
                BasicTextField(
                    value = value,
                    onValueChange = {
                        /*   if (isPhoneNumber) {
                               // Allow only digits & max 10 length
                               val digitsOnly = it.filter { it.isDigit() }
                               if (digitsOnly.length <= 10) {
                                   onValueChange(digitsOnly)
                               }
                           } else {

                           }*/
                        onValueChange(it)
                    },
                    singleLine = singleLine,
                    enabled = enabled,
                    textStyle = TextStyle(color = textColor, font_10),
                    visualTransformation = if (isPassword && obscure) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = if (isPassword) KeyboardType.Password else if (isPhoneNumber) KeyboardType.Number else KeyboardType.Text,
                        imeAction = imeAction
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onImeAction() },
                        onNext = { onImeAction() }),
                    cursorBrush = SolidColor(Color.White),
                    modifier = Modifier
                        .weight(1f)
                ) { inner ->
                    // placeholder + text
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = if (moreText) Alignment.TopStart else Alignment.CenterStart // Center the placeholder text
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                placeholder,
                                color = textColor,
                                style = TextStyle(fontSize = font_10)
                            )
                        }
                        inner()
                    }
                }

                if (isPassword) {
                    IconButton(
                        onClick = { obscure = !obscure },
                        modifier = Modifier.padding(end = dimen_0)
                    ) {
                        Image(
                            painter = painterResource(if (obscure) R.drawable.password_hide_ic else R.drawable.password_ic),
                            contentDescription = null,
                            modifier = Modifier.size(dimen_20)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CompactDropdown(
    placeholder: String = "Select option",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = dimen_32,
    onClick: () -> Unit,
    color: Color = LightGrey,
    isImage: Boolean = false
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(dimen_1, Color.White.copy(alpha = 0.35f), RoundedCornerShape(dimen_10))
            .clickable(enabled = enabled) {
                onClick()
            },
        shape = RoundedCornerShape(dimen_10),
        color = Color.Transparent
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White.copy(alpha = 0.08f)
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimen_12),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = placeholder,
                    color = color,
                    fontSize = font_10,
                    modifier = Modifier.weight(1f)
                )
                if (isImage) {
                    Image(
                        painter = painterResource(R.drawable.down_arrow_ic),
                        contentDescription = null,
                        modifier = Modifier.size(dimen_15)
                    )
                }


            }
        }
    }
}

@Composable
fun CompactDropdownWithLabel(
    placeholder: String = "Select option",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = dimen_46,
    onClick: () -> Unit,
    color: Color = LightGrey,
    isImage: Boolean = false,
    labelSize: TextUnit = font_14,
    labelBold: Boolean = false,
    labelMedium: Boolean = false,
    labelSemiBold: Boolean = false,
    labelColor: Color = LightGrey,
    @StringRes labelRes: Int,
    isRequired: Boolean = false,
    labelToFieldSpace: Dp = dimen_4

) {
    val labelText = buildAnnotatedString {
        append(stringResource(labelRes))
        if (isRequired) {
            append(" ")
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("*")
            }
        }
    }
    LabelTextview(
        text = labelText,
        size = labelSize,
        bold = labelBold,
        medium = labelMedium,
        semiBold = labelSemiBold,
        color = labelColor
    )
    Spacer(Modifier.height(labelToFieldSpace))


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(dimen_1, Color.White.copy(alpha = 0.35f), RoundedCornerShape(dimen_10))
            .clickable(enabled = enabled) {
                onClick()
            },
        shape = RoundedCornerShape(dimen_10),
        color = Color.Transparent
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White.copy(alpha = 0.08f)
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimen_12),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = placeholder,
                    color = color,
                    fontSize = font_10,
                    modifier = Modifier.weight(1f)
                )
                if (isImage) {
                    Image(
                        painter = painterResource(R.drawable.down_arrow_ic),
                        contentDescription = null,
                        modifier = Modifier.size(dimen_12)
                    )
                }


            }
        }
    }
}

@Composable
fun OtpView(
    value: String,
    modifier: Modifier = Modifier,
    cells: Int = 6,
    isNumber: Boolean = false,
    keyboardOpen: Boolean = false,
    onFilled: (String) -> Unit = {},
    onChanged: (String) -> Unit = {} // <— add this
) {

    OtpField(
        value = value,
        onValueChange = {
            if (isNumber) {
                val digitsOnly = it.filter(Char::isDigit).take(cells)
                if (digitsOnly != value) {
                    onChanged(digitsOnly)                 // notify parent every change
                    if (digitsOnly.length == cells) onFilled(digitsOnly)
                }
            } else {
                val newValue = it.take(cells)
                if (newValue != value) {
                    onChanged(newValue)
                    if (newValue.length == cells) onFilled(newValue)
                }
            }

        },
        cells = cells,
        modifier = modifier,
        keyboardOpen = keyboardOpen,
        isNumber = isNumber
    )
}

@Composable
fun OtpField(
    value: String,
    onValueChange: (String) -> Unit,
    cells: Int,
    modifier: Modifier = Modifier,
    cellWidth: Dp = dimen_36,
    cellHeight: Dp = dimen_36,
    cornerRadius: Dp = dimen_10,
    gap: Dp = dimen_10,
    placeholderChar: Char = '0',
    keyboardOpen: Boolean = false,
    isNumber: Boolean = false

) {
    val focusRequester = remember { FocusRequester() }
    val bg = Color.White.copy(alpha = 0.08f)
    val placeholderColor = LightGrey
    val shape = RoundedCornerShape(cornerRadius)
    if (keyboardOpen) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text
        ),
        cursorBrush = SolidColor(Color.Transparent), // hide cursor
        modifier = modifier
            .focusRequester(focusRequester)
            .onKeyEvent { event ->
                // Make backspace behave nicely when nothing is selected
                if (event.type == KeyEventType.KeyUp && event.key == Key.Backspace && value.isNotEmpty()) {
                    onValueChange(value.dropLast(1))
                    true
                } else false
            },
        decorationBox = { innerTextField ->
            // The invisible editor covers the boxes so it can receive input.
            Box {
                Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
                    repeat(cells) { index ->
                        val isActive = index == value.length && value.length < cells
                        val ch = value.getOrNull(index)?.toString() ?: placeholderChar.toString()

                        Box(
                            modifier = Modifier
                                .size(cellWidth, cellHeight)
                                .clip(shape)
                                .background(bg)
                                .border(
                                    width = dimen_1,
                                    Color.White.copy(alpha = 0.35f),
                                    shape = shape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ch,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = font_10,
                                    fontWeight = FontWeight.Medium,
                                ),
                                color = if (value.getOrNull(index) != null)
                                    LightGrey
                                else
                                    placeholderColor
                            )
                        }
                    }
                }
                // Invisible, full-size editor to capture input & paste
                Box(
                    Modifier
                        .matchParentSize()
                        .alpha(0f)
                ) { innerTextField() }
            }
        }
    )
}

@Composable
fun TermsAndConditionsCheckbox(
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {


        val annotatedText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = font_14
                )
            ) {
                append(stringResource(R.string.by_using_billion_sphere_you_agree_to_the) + " ")
            }

            pushStringAnnotation(tag = "TERMS", annotation = "terms")
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = font_14,
                    textDecoration = TextDecoration.Underline

                )
            ) {
                append(stringResource(R.string.terms))
            }
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = font_14
                )
            ) {
                append(" and ")
            }

            pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = font_14,
                    textDecoration = TextDecoration.Underline

                )
            ) {
                append(stringResource(R.string.privacy_policy))
            }
        }

        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            onClick = { offset ->
                annotatedText.getStringAnnotations(tag = "TERMS", start = offset, end = offset)
                    .firstOrNull()?.let { onTermsClick() }

                annotatedText.getStringAnnotations(tag = "PRIVACY", start = offset, end = offset)
                    .firstOrNull()?.let { onPrivacyClick() }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultBottomSheet(
    sheetState: SheetState,
    title: String,
    countryList: List<GetDropDownsResponseItem>,
    onCountrySelected: (GetDropDownsResponseItem) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White
    ) {

        Column(Modifier.padding(dimen_16)) {
            Textview(
                text = title,
                size = font_18,
                bold = true,
                color = DarkBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimen_16))
            if (countryList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimen_20),
                    contentAlignment = Alignment.Center
                ) {
                    Textview(
                        text = stringResource(R.string.no_data_found),
                        size = font_14,
                        bold = true,
                        color = Color.Black
                    )
                }
            } else {
                LazyColumn {
                    items(countryList) { country ->
                        Textview(
                            text = country.dialing_code.toString() + " " + country.country_name.toString(),
                            size = font_14,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(horizontal = dimen_8, vertical = dimen_12)
                                .clickable {
                                    onCountrySelected(country)
                                    onDismiss()
                                }
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun ResendOtp(
    onResendOtp: () -> Unit,
    totalSeconds: Int = 30,
) {
    var secondsLeft by rememberSaveable { mutableIntStateOf(totalSeconds) }
    var timerKey by rememberSaveable { mutableIntStateOf(0) } // bump to restart timer
    val isEnabled = secondsLeft == 0


    // run/restart countdown whenever timerKey changes
    LaunchedEffect(timerKey) {
        secondsLeft = totalSeconds
        while (secondsLeft > 0) {
            delay(1_000)
            secondsLeft--
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {


        val annotatedText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = font_12
                )
            ) {
                append(stringResource(R.string.i_don_t_receive_code) + " ")
            }

            pushStringAnnotation(tag = "RESENDOTP", annotation = "resendotp")
            withStyle(
                style = SpanStyle(
                    color = if (isEnabled) Color.White else LightGrey,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = font_12
                )
            ) {
                append(stringResource(R.string.resend_otp))
            }

        }

        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.bodyMedium.copy(color = Grey),
            onClick = { offset ->
                if (!isEnabled) return@ClickableText

                annotatedText.getStringAnnotations(tag = "RESENDOTP", start = offset, end = offset)
                    .firstOrNull()?.let {
                        onResendOtp()
                        timerKey++
                    }

            }
        )
        Spacer(modifier = Modifier.width(dimen_8))
        Textview(
            text = "(" + formatAsMMSS(secondsLeft) + ")",
            color = if (isEnabled) LightGrey else Color.White,
            size = font_12,
        )
    }
}

private fun formatAsMMSS(seconds: Int): String {
    val m = seconds / 60
    val s = seconds % 60
    return String.format("%02d:%02d", m, s)
}