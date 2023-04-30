package com.arditakrasniqi.aiandit.data.model.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    data class Vehicle(
        val imageCount: ImageCount,
        @PrimaryKey
        val id: String,
        val catcherId: String?,
        val createdAt: String,
        val updatedAt: String,
        val deletedAt: String?,
        val userId: String,
        val vehicleId: String,
        val requestId: String?,
        val user: UserModel,
        val vehicleInfo: VehicleInfo,
        val requestInfo: String?,
        val coverImage: String?
    ) {
        data class ImageCount(
            val total: Int
        )

        data class VehicleInfo(
            val completedProcessing: Int,
            val failedProcessing: Int,
            val isProcessing: Boolean,
            val isProcessed: Boolean,
            val processing: Int,
            val status: String,
            val importStatus: String,
            val isUploaded: Boolean,
            val id: String,
            val vin: String,
            val make: String?,
            val model: String?,
            val displacement: Double?,
            val horsePower: Double?,
            val description: String?,
            val bodyType: String?,
            val fuelType: String?,
            val count: Int,
            val visible: Boolean,
            val features: List<String>,
            val equipment: String?,
            val cropType: String,
            val createdAt: String,
            val updatedAt: String,
            val deletedAt: String?,
            val clientLicensePlateId: String?,
            val presetId: String,
            val vehicleBodyTypeId: String?,
            val profileId: String?,
            val vehicleTypeId: String?,
            val isImported: Boolean,
            val areImagesImported: Boolean
        )
    }
