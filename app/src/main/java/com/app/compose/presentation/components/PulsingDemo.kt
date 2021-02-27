package com.app.compose.presentation.components


//@Composable
//fun PulsingDemo(){
//    val color = MaterialTheme.colors.primary
//
//    val pulseAnim = transition(
//        definition = pulseDefinition,
//        initState = INITIAL,
//        toState = FINAL
//    )
//
//    val pulseMagnitude = pulseAnim[pulsePropKey]
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(55.dp),
//        horizontalArrangement = Arrangement.Center
//    ){
//        Image(
//            modifier = Modifier
//                .align(Alignment.CenterVertically)
//                .height(pulseMagnitude.dp)
//                .width(pulseMagnitude.dp)
//            ,
//            imageVector = Icons.Default.Favorite,
//            contentDescription = "Favorite"
//        )
//    }
//
//
//    Canvas(
//        modifier = Modifier.fillMaxWidth().height(55.dp),
//    ) {
//        drawCircle(
//            radius = pulseMagnitude,
//            brush = SolidColor(color),
//        )
//    }
//}
//
//
//object PulseAnimationDefinitions{
//
//    enum class PulseState{
//        INITIAL, FINAL
//    }
//
//    val pulsePropKey = FloatPropKey("pulseKey")
//
//    val pulseDefinition = transitionDefinition<PulseState> {
//        state(INITIAL) { this[pulsePropKey] = 40f }
//        state(FINAL) { this[pulsePropKey] = 50f }
//
//        transition(
//            INITIAL to FINAL
//        ) {
//            pulsePropKey using infiniteRepeatable(
//                animation = tween(
//                    durationMillis = 500,
//                    easing = FastOutSlowInEasing
//                ),
//                repeatMode = RepeatMode.Restart
//            )
//        }
//    }
//}