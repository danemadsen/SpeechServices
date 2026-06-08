package app.grapheneos.speechservices

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar

class TtsSettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tts_settings_activity)

        val toolbar = findViewById<Toolbar>(R.id.action_bar)
        toolbar.title = getString(R.string.app_name)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        toolbar.navigationContentDescription = getString(R.string.settings_navigate_up)
        toolbar.navigationIcon?.setTint(resolveColor(android.R.attr.textColorPrimary))
        toolbar.setNavigationOnClickListener { finishAfterTransition() }

        findViewById<TextView>(R.id.settings_version_value).text =
            versionName().ifEmpty { getString(R.string.settings_version_unknown) }
    }

    override fun onNavigateUp(): Boolean {
        finishAfterTransition()
        return true
    }
}

private fun Context.versionName(): String {
    val packageInfo = packageManager.getPackageInfo(
        packageName,
        PackageManager.PackageInfoFlags.of(0),
    )
    return packageInfo.versionName.orEmpty()
}

private fun Context.resolveColor(attr: Int): Int {
    val attrs = theme.obtainStyledAttributes(intArrayOf(attr))
    return try {
        attrs.getColor(0, 0)
    } finally {
        attrs.recycle()
    }
}
