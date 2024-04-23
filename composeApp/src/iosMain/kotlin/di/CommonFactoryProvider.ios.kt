package di

actual object CommonFactoryProvider {

    private lateinit var commonFactorySingleton: CommonFactory

    actual val commonFactory: CommonFactory
        get() =
            if (::commonFactorySingleton.isInitialized) {
                commonFactorySingleton
            } else {
                CommonFactory()
            }.also { commonFactorySingleton = it }
}