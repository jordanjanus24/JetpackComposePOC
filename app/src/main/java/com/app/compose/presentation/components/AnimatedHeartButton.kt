package com.app.compose.presentation.components

//@Composable
//fun AnimatedHeartButton(
//    modifier: Modifier,
//    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
//    onToggle: (HeartAnimationDefinition.HeartButtonState) -> Unit
//) {
//    val toState = if(buttonState.value == IDLE) ACTIVE else IDLE
//    val state = transition(
//        definition = heartTransitionDefinition,
//        initState = buttonState.value,
//        toState = toState
//    )
//    HeartButton(
//        modifier = modifier,
//        buttonState = buttonState,
//        state = state,
//        onToggle = onToggle)
//}
//
//@Composable
//private fun HeartButton(
//    modifier: Modifier,
//    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
//    state: TransitionState,
//    onToggle: (HeartAnimationDefinition.HeartButtonState) -> Unit
//) {
//    loadPicture(drawable = if(buttonState.value === ACTIVE) R.drawable.heart_red else R.drawable.heart_grey).value?.let { image ->
//        Image(
//            bitmap = image.asImageBitmap(),
//            modifier = modifier
//                .clickable(onClick = { onToggle(if(buttonState.value === ACTIVE) IDLE else ACTIVE) })
//                .width(state[heartSize])
//                .width(state[heartSize]),
//            contentDescription = "Heart Button"
//        )
//    }
//}
//
//object HeartAnimationDefinition {
//    enum class HeartButtonState {
//        IDLE, ACTIVE
//    }
//    val heartColor = ColorPropKey(label = "heartColor")
//    val heartSize = DpPropKey(label = "heartDp")
//    private val idleIconSize = 50.dp
//    private val expandedIconSize = 55.dp
//    val heartTransitionDefinition = transitionDefinition<HeartButtonState> {
//        state(IDLE) {
//            this[heartColor] = Color.LightGray
//            this[heartSize] = idleIconSize
//        }
//        state(ACTIVE) {
//            this[heartColor] = Color.Red
//            this[heartSize] = idleIconSize
//        }
//        transition(
//            IDLE to ACTIVE
//        ) {
//            heartColor using tween(durationMillis = 100)
//            heartSize using
//                keyframes {
//                    durationMillis = 100
//                    expandedIconSize at 100
//                    idleIconSize at 110
//            }
//        }
//        transition(
//            IDLE to ACTIVE
//        ) {
//            heartColor using tween(durationMillis = 100)
//            heartSize using
//                keyframes {
//                    durationMillis = 100
//                    expandedIconSize at 100
//                    idleIconSize at 110
//                }
//        }
//    }
//}