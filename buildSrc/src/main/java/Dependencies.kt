private object Versions {
    const val gradle = "7.0.0"
    const val kotlin = "1.4.30"
    // Hilt
    const val hilt = "2.37"
    const val hilt_lifecycle = "1.0.0-alpha03"
    // AndroidX
    const val androidx_core = "1.3.2"
    const val androidx_lifecycle = "2.3.0"
    const val androidx_appcompat = "1.3.0-beta01"
    const val androidx_fragment = "1.3.0"
    const val androidx_constraintlayout = "2.0.4"
    // Material
    const val material = "1.3.0"
    // Compose
    const val androidx_ui = "1.0.0-alpha07"
    const val compose = "1.0.0-beta01"
    const val compose_constraint = "1.0.0-alpha03"
    const val compose_activity = "1.3.0-alpha03"
    // Navigation
    const val navigation = "2.3.3"
    // Retrofit
    const val retrofit = "2.9.0"
    // Glide
    const val glide = "4.12.0"
    // Room
    const val room = "2.4.0-alpha03"
    object Testing {
        const val junit = "4.13"
        const val junit_ext = "1.1.2"
        const val espresso = "3.3.0"
    }
}
object GlobalDeps {
    const val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hilt_android_gradle_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object ComposeOptions {
    const val kotlin_compiler = Versions.kotlin
    const val kotlin_compiler_extension = Versions.compose
}

object Deps {
    const val kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    // AndroidX
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidx_lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_fragment = "androidx.fragment:fragment-ktx:${Versions.androidx_fragment}"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"

    // Material
    const val material = "com.google.android.material:material:${Versions.material}"
    // Compose
    const val compose_ui_tooling = "androidx.ui:ui-tooling:${Versions.androidx_ui}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val compose_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val compose_rxjava2 = "androidx.compose.runtime:runtime-rxjava2:${Versions.compose}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val compose_material_icons = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val compose_material_icons_extended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val compose_animation = "androidx.compose.animation:animation:${Versions.compose}"
    const val compose_constraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.compose_constraint}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    // Navigation
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    // Hilt
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hilt_lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle}"
    const val hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_lifecycle}"
    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    // Room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

}
object TestDeps {
    const val junit = "junit:junit:${Versions.Testing.junit}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.Testing.junit_ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.Testing.espresso}"
}