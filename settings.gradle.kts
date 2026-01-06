pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Test-Task-Android-Denys-Arkharov"
include(":app")
include(":commons:compose")
include(":screens:log-in")
include(":commons:resources")
include(":screens:list")
include(":domain")
include(":screens:details")
include(":data")
