plugins {
    id("java-library")
    alias(libs.plugins.doraPlug.jvm.library)
}
dependencies {
    implementation(libs.coroutines.core)
}