package model;

import java.io.IOException;
import java.sql.SQLException;


public final class ModelFacade implements IModel {

    private ILevel  level;

    public ModelFacade(final int idlevel) throws IOException, SQLException {
        this.setLevel(new Level(idlevel));
    }

    @Override
    public final ILevel getLevel() {
        return this.level;
    }

    private void setLevel(final ILevel level) {
        this.level = level;
    }
}
