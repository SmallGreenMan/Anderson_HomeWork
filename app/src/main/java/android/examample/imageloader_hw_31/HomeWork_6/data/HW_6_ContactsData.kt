package android.examample.imageloader_hw_31.HomeWork_6.data

lateinit var hw6_contactsData: MutableMap<Int, HW_6_ContactsData>

fun intData(maxData: Int) {
    hw6_contactsData = mutableMapOf()
    for (i in 0..maxData) {
        hw6_contactsData[i] = HW_6_ContactsData(
            "Name_$i",
            "Lastname_$i",
            "+111111${(111 + i)}",
            i,
            "https://picsum.photos/300/300/?temp={${(123 + i)}}"
        )
    }
}
data class HW_6_ContactsData(
    var name: String,
    var lastName: String,
    var telephoneNumber: String,
    var id: Int,
    var img: String,
)

