package com.arditakrasniqi.aiandit.data.model.responses

data class UserSettingsModel(
    val backgroundType: String,
    val blurAmount: Int,
    val defaultCropType: String,
    val features: List<String>,
    val logoPosition: String,
    val qualityCheck: Boolean,
    val showTutorials: Boolean,
    val speechCommands: Boolean,
    val lockCameraCapture: Boolean,
    val defaultPreset: String,
    val updatedAt: String
)
