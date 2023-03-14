object Dependencies {

    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }

    val recyclerView by lazy { "androidx.recyclerview:recyclerview:${Versions.recyclerView}" }
    val cardView by lazy { "androidx.cardview:cardview:${Versions.cardView}" }


    val hiltCore by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltDaggerAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val hiltCommon by lazy { "androidx.hilt:hilt-common:${Versions.hiltSnapShot}" }
    val hiltCompiler by lazy { "androidx.hilt:hilt-compiler:${Versions.hiltSnapShot}" }


    val okHttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okHttp}" }
    val okHttpLogging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}" }

    val pixplicity by lazy{ "com.pixplicity.sharp:library:1.1.0"}

    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitAdapter by lazy { "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}" }

    // Gson
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gsonConvertor}" }

    //ROOM
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomKts by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }


    // Lottie & Coil
    val lottie by lazy { "com.airbnb.android:lottie:${Versions.lottie}" }
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideKapt by lazy { "com.github.bumptech.glide:compiler:${Versions.glideKapt}" }
    val glideVector by lazy { "com.github.corouteam:GlideToVectorYou:${Versions.glideVector}" }

    // Android svg
    val androidSvg by lazy { "com.caverock:androidsvg-aar:${Versions.androidSvg}" }


    // Coroutine
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }


    val lifecycleViewModel by lazy {
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }
    val lifecycleLiveDataKtx by lazy {
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    }
    val lifecycleCommon by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}" }
    val lifecycleExtension by lazy { "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}" }


    val splashAPI by lazy { "androidx.core:core-splashscreen:${Versions.splashAPI}" }

    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.materialDesign}" }


    // Navigation component
    val navComponentFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navComponent}" }
    val navComponentUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navComponent}" }

    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"


}

object TestLibs {
    val coreTesting by lazy { "androidx.arch.core:core-testing:${Versions.coreTesting}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val junitExtension by lazy { "androidx.test.ext:junit:${Versions.junit_extension}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso_core}" }
    val mockitoKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}" }
}

object Modules {
    const val datasource = ":datasource"
    const val central = ":central"
}