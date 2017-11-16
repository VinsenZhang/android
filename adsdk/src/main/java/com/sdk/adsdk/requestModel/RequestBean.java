package com.sdk.adsdk.requestModel;


public class RequestBean {

    private Media media;

    private Device device;

    private Network network;

    private Client client;

    private Location location;

    private Adslot adslot;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Adslot getAdslot() {
        return adslot;
    }

    public void setAdslot(Adslot adslot) {
        this.adslot = adslot;
    }
}
