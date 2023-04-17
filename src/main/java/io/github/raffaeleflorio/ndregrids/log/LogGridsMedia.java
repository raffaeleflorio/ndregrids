package io.github.raffaeleflorio.ndregrids.log;

import io.github.raffaeleflorio.ndregrids.Grid;
import io.github.raffaeleflorio.ndregrids.Grids;

public final class LogGridsMedia implements Grids.Media<String> {

    private final String log;
    private final Grid.Media<String> gridMedia;

    public LogGridsMedia() {
        this("", new LogGridMedia());
    }

    LogGridsMedia(final String log, final Grid.Media<String> gridMedia) {
        this.log = log;
        this.gridMedia = gridMedia;
    }

    @Override
    public Grids.Media<String> with(final Grid grid) {
        return new LogGridsMedia(
                this.log.concat(this.separator())
                        .concat("[")
                        .concat(this.output(grid))
                        .concat("]"),
                this.gridMedia
        );
    }

    private String separator() {
        return this.log.isEmpty() ? "" : " ";
    }

    private String output(final Grid grid) {
        return grid.show(this.gridMedia).output();
    }

    @Override
    public String output() {
        return this.log;
    }
}
