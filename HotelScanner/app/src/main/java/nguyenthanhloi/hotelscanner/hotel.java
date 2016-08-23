package nguyenthanhloi.hotelscanner;

import java.io.Serializable;

/**
 * Created by ThanhLoi on 4/25/2016.
 */
public class hotel implements Serializable {
    String _name;
    String _price;
    String _remainRoom;
    String _address;
    String _phonenum;
    String _photo;

    public hotel(String name, String price, String remain, String address, String phonenum, String photo)
    {
        _name = name;
        _price = price;
        _remainRoom = remain;
        _address = address;
        _phonenum = phonenum;
        _photo = photo;
    }

    public String get_address() {
        return _address;
    }

    public String get_phonenum() {
        return _phonenum;
    }

    public String get_name() {
        return _name;
    }

    public String get_photo() {
        return _photo;
    }

    public String get_price() {
        return _price;
    }

    public String get_remainRoom() {
        return _remainRoom;
    }

    @Override
    public String toString()
    {
        return this._name;
    }
}
