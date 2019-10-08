package pl.netigen.payments;

public interface ItemConsumedListener {
    void onItemConsumed(String response, String itemToken);
}
