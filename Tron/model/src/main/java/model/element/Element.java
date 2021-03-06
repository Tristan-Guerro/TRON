package model.element;

import java.awt.Image;

import model.IElement;
import model.ISprite;
import model.Permeability;

public class Element implements IElement{

    private ISprite sprite;
    private Permeability permeability;

    public Element(final ISprite sprite, final Permeability permeability) {
        this.setSprite(sprite);
        this.setPermeability(permeability);
    }

    @Override
    public final ISprite getSprite() {
        return this.sprite;
    }

    protected final void setSprite(final ISprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public final Permeability getPermeability() {
        return this.permeability;
    }

    public void setPermeability(final Permeability permeability) {
        this.permeability = permeability;
    }

    @Override
    public final Image getImage() {
        return this.getSprite().getImage();
    }
}
