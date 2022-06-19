package lazycoder21.droid.crypto.domain.model

data class SortingModel(
    val title: String,
    val sortOptions: Int,
    val ascIcon: String? = null,
    val descIcon: String? = null,
) {
    var isSelected = false
}
