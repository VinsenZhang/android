package com.sdk.adsdk.responseModel;

public class Ads {

    private String adslot;

    /**
     * 返回的物料类型
     * 1:html返回 2:Native返回(目前都会返回2)
     */
    private int material_type;

    private String html_snippet;


    private NativeMaterial native_material;

    public String getAdslot() {
        return adslot;
    }

    public void setAdslot(String adslot) {
        this.adslot = adslot;
    }

    public int getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(int material_type) {
        this.material_type = material_type;
    }

    public String getHtml_snippet() {
        return html_snippet;
    }

    public void setHtml_snippet(String html_snippet) {
        this.html_snippet = html_snippet;
    }

    public NativeMaterial getNative_material() {
        return native_material;
    }

    public void setNative_material(NativeMaterial native_material) {
        this.native_material = native_material;
    }
}
