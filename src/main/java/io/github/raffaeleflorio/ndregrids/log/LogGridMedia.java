package io.github.raffaeleflorio.ndregrids.log;

import io.github.raffaeleflorio.ndregrids.Grid;

public final class LogGridMedia implements Grid.Media<String> {

    private final String log;

    public LogGridMedia() {
        this("");
    }

    LogGridMedia(final String log) {
        this.log = log;
    }

    @Override
    public Grid.Media<String> with(final Integer row, final Integer column, final String value) {
        return new LogGridMedia(
                this.log.concat(this.separator())
                        .concat(this.coordinate(row, column))
                        .concat("=")
                        .concat(value)
        );
    }

    private String separator() {
        return this.log.isEmpty() ? "" : " ";
    }

    private String coordinate(final Integer x, final Integer y) {
        return x.toString().concat(",").concat(y.toString());
    }

    @Override
    public String output() {
        return this.log;
    }
}
