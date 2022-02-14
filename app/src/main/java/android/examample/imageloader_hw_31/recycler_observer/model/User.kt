package android.examample.imageloader_hw_31.recycler_observer.model

data class User(
    val id: Long,
    val photo: String,
    val name: String,
    val company: String,
    val telephone: String
)

data class UserDetails(
    val user: User,
    val details: String
)