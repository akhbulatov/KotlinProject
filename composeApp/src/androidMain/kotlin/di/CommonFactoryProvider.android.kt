package di

import org.example.kotlinproject.KotlinProjectApp

actual object CommonFactoryProvider {

    actual val commonFactory: CommonFactory
        get() = KotlinProjectApp.commonFactory
}