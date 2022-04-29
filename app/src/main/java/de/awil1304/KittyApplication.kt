package de.awil1304

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * gives dagger hilt information about application and access to application context (also added in manifest)
 */

@HiltAndroidApp
class KittyApplication : Application()