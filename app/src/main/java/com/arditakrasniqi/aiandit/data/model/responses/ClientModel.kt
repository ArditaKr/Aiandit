package com.arditakrasniqi.aiandit.data.model.responses

data class ClientModel(
    val credits: Int,
    val id: String,
    val name: String,
    val status: String,
    val description: String?,
    val icon: String?,
    val email: String,
    val country: String?,
    val postCode: String?,
    val city: String?,
    val address: String?,
    val notes: String?,
    val paymentType: String,
    val priority: String?,
    val outputImageResolution: String?,
    val interiorBackgroundEnabled: Boolean,
    val shadowType: String,
    val numberId: String?,
    val removeReflectionsEnabled: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
    val customerId: String,
    val logoId: String?,
    val logo: String?
)
