package entity;

import lombok.Getter;
import lombok.Setter;
import objects.Interactable;

@Getter
@Setter
public class Inventory {

    private Interactable leftHand;
    private Interactable rightHand;

    public Inventory() {
    }
}
