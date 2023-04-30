package com.arditakrasniqi.aiandit.data.model.responses

data class CustomerModel(
    val credits: Int,
    val id: String,
    val name: String,
    val status: String,
    val phone: String?,
    val address: String?,
    val allowChilds: Boolean,
    val supportEmails: List<String>,
    val allowNegativeCredits: Boolean,
    val shadowType: String,
    val removeReflectionsEnabled: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
    val parentId: String?,
    val logoId: String?,
    val logo: String?
)
