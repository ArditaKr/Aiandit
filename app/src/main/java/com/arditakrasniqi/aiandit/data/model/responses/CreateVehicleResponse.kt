package com.arditakrasniqi.aiandit.data.model.responses

data class CreateVehicleResponse(
    val id: String?,
    val vin: String,
    val make: String?,
    val model: String?,
    val displacement: String?,
    val horsePower: String?,
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
    val processings: Processings?,
    val imports: Imports?,
    val vehicleBodyType: VehicleBodyType?
){
    data class Processings(
        val id: String?,
        val type: String?,
        val createdAt: String?,
        val updatedAt: String?,
        val deletedAt: String?,
        val vehicleId: String?
    )

    data class Imports(
        val id: String?,
        val status: String?,
        val createdAt: String?,
        val updatedAt: String?,
        val deletedAt: String?,
        val importerId: String?,
        val vehicleId: String?
    )

    data class VehicleBodyType(
        val id: String?,
        val name: String?,
        val value: String?,
        val createdAt: String?,
        val updatedAt: String?,
        val deletedAt: String?,
        val files: Files?
    )

    data class Files(
        val id: String?,
        val filename: String?,
        val path: String?,
        val size: String?,
        val type: String?,
        val isPrimary: Boolean?,
        val legacy: String?,
        val contentType: String?,
        val visible: Boolean?,
        val meta: String?,
        val qc: String?,
        val createdAt: String?,
        val updatedAt: String?,
        val deletedAt: String?,
        val dmsId: String?,
        val imageId: String?,
        val fileVehicleBodyTypes: FileVehicleBodyTypes?
    )

    data class FileVehicleBodyTypes(
        val fileId: String?,
        val vehicleBodyTypeId: String?,
        val createdAt: String?,
        val updatedAt: String?
    )
}


