package pl.netigen.amazon.payments

data class AmazonUserData(
    val userId: String = "",
    val marketplace: String = "",
) {
    val isEmpty = userId.isEmpty() || marketplace.isEmpty()
}
