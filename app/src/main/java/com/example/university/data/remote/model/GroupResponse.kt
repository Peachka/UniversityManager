package com.example.university.data.remote.model

data class Group(
    val id: String,
    val name: String,
    val faculty: String
)

data class Paging(
    val pageCount: Int,
    val totalItemCount: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean,
    val isFirstPage: Boolean,
    val isLastPage: Boolean,
    val firstItemOnPage: Int,
    val lastItemOnPage: Int
)

data class GroupResponse(
    val paging: Paging,
    val data: List<Group>
)

// Extension function to get name by ID
fun List<Group>.getNameById(id: String): String? {
    return this.find { it.id == id }?.name
}

// Extension function to get ID by name
fun List<Group>.getIdByName(name: String): String? {
    return this.find { it.name == name }?.id
}
