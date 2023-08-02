package pl.netigen.amazon.payments

data class UserIapData(
    val amazonUserId: String = "",
    val amazonMarketplace: String = "",
) {
    val isEmpty = amazonUserId.isEmpty() || amazonMarketplace.isEmpty()
}
