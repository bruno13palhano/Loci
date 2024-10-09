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

rootProject.name = "Loci"
include(":app")
include(":core:data")
include(":core:model")
include(":core:network")
include(":feature:login")
include(":feature:workspace")
include(":feature:account")
include(":feature:profile")
include(":feature:messages")
include(":core:ui")
