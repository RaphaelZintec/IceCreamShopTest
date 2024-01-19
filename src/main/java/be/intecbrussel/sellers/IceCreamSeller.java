package be.intecbrussel.sellers;

import be.intecbrussel.eatables.*;

public interface IceCreamSeller extends Profitable {
    Cone orderCone(Cone.Flavor[] balls);
    IceRocket orderIceRocket();
    Magnum orderMagnum(Magnum.MagnumType magnumType);
}
