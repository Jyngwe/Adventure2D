package entity;

import lombok.Getter;
import lombok.Setter;
import objects.Interactable;
import objects.SuperObject;

@Getter
@Setter
public class Inventory {

    private SuperObject leftHand;
    private SuperObject rightHand;

    public Inventory() {
    }
}
