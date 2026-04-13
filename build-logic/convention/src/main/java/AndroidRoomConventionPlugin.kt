import androidx.room.gradle.RoomExtension
import com.univ.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "androidx.room")
            apply(plugin = "com.google.devtools.ksp")
            dependencies {
                "implementation"(libs.findLibrary("androidx.room").get())
                "implementation"(libs.findLibrary("androidx.room.ktx").get())
                "ksp"(libs.findLibrary("androidx.room.compiler").get())
            }
            extensions.configure<RoomExtension>{
                schemaDirectory("$projectDir/schemas")
            }
        }
    }
}