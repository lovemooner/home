package love.moon.wx.service;

public interface IWxService {

    boolean checkSignature(String signature, String timestamp, String nonce);
}
