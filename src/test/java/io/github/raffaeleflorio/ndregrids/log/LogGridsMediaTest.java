package io.github.raffaeleflorio.ndregrids.log;

import io.github.raffaeleflorio.ndregrids.Grid;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class LogGridsMediaTest {

    @Test
    void appends_in_order_each_grid_media() {
        var output = new LogGridsMedia()
                .with(new Grid() {
                    @Override
                    public <O> Media<O> show(final Media<O> media) {
                        return media.with(0, 0, "a");
                    }
                })
                .with(new Grid() {
                    @Override
                    public <O> Media<O> show(final Media<O> media) {
                        return media.with(0, 0, "b");
                    }
                })
                .output();

        assertThat(output)
                .isEqualTo("[0,0=a] [0,0=b]");
    }
}